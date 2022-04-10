package com.cetuer.parking.app.service.impl;

import com.cetuer.parking.app.domain.*;
import com.cetuer.parking.app.mapper.BeaconCoordinateMapper;
import com.cetuer.parking.app.mapper.BeaconMapper;
import com.cetuer.parking.app.mapper.CoordinateMapper;
import com.cetuer.parking.app.service.FingerprintService;
import com.cetuer.parking.app.util.CalculateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 指纹服务实现
 *
 * @author zhangqb
 * @date 2022/3/20 15:06
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FingerprintServiceImpl implements FingerprintService {
    private final CoordinateMapper coordinateMapper;
    private final BeaconCoordinateMapper beaconCoordinateMapper;
    private final BeaconMapper beaconMapper;
    /**
     * KNN中K的取值
     */
    private int KNN_K = 3;

    @Override
    public void collectFingerprint(Map<String, Map<String, Double>> fingerprintData) {
        for (String coordinateStr : fingerprintData.keySet()) {
            //信标列表
            Map<String, Double> beaconValue = fingerprintData.get(coordinateStr);
            //切割出坐标，0是x，1是y
            String[] coordinate = coordinateStr.split(",");
            //坐标实体类
            Coordinate c = new Coordinate(null, Integer.parseInt(coordinate[0]), Integer.parseInt(coordinate[1]));
            Integer coordinateId = coordinateMapper.selectCoordinateId(c);
            //不存在坐标则插入
            if (coordinateId == null) {
                coordinateMapper.insertCoordinate(c);
                coordinateId = c.getId();
            }
            //获取所有信标
            for (String mac : beaconValue.keySet()) {
                //信标id
                Integer beaconId = beaconMapper.selectIdByMac(mac);
                //已有数据则更新
                if(beaconCoordinateMapper.selectByCoordIdAndBeaconId(coordinateId, beaconId) != null) {
                    beaconCoordinateMapper.updateBeaconCoordinate(new BeaconCoordinate(null, coordinateId, beaconId, beaconValue.get(mac)));
                    continue;
                }
                beaconCoordinateMapper.insertBeaconCoordinate(new BeaconCoordinate(null, coordinateId, beaconId, beaconValue.get(mac)));
            }
        }
    }

    @Override
    public Coordinate location(List<BeaconRssi> onlineBeaconRssis) {
        //指纹库
        Map<KNNBean, List<BeaconRssi>> fingerprintLib = new HashMap<>(128);
        //所有坐标
        List<Coordinate> coordinateList = coordinateMapper.selectAll();
        for(Coordinate coordinate: coordinateList) {
            List<BeaconRssi> beaconRssiList = beaconCoordinateMapper.selectMacRssiByCoordinateId(coordinate.getId());
            fingerprintLib.put(new KNNBean(coordinate), beaconRssiList);
        }
        //计算欧氏距离
        for(KNNBean knnBean: fingerprintLib.keySet()) {
            Map<String, Double> beaconRssis = fingerprintLib.get(knnBean).stream().collect(Collectors.toMap(BeaconRssi::getMac, BeaconRssi::getRssi));
            knnBean.setDistance(CalculateUtil.calcDistance(onlineBeaconRssis, beaconRssis));
        }
        List<KNNBean> knnBeanList = new ArrayList<>(fingerprintLib.keySet());
        System.out.println(onlineBeaconRssis);
        //排序
        Collections.sort(knnBeanList);
        int xSum = 0, ySum = 0;
        //取k个计算平均坐标
        for (int i = 0; i < KNN_K; i++) {
            Coordinate coordinate = knnBeanList.get(i).getCoordinate();
            xSum += coordinate.getX();
            ySum += coordinate.getY();
        }
        return new Coordinate(null, (int) Math.round((xSum * 1.0) / KNN_K), (int) Math.round((ySum * 1.0) / KNN_K));
    }
}
