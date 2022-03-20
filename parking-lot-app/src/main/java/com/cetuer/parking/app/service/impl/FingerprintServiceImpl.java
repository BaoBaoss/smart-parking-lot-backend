package com.cetuer.parking.app.service.impl;

import com.cetuer.parking.app.domain.BeaconCoordinate;
import com.cetuer.parking.app.domain.Coordinate;
import com.cetuer.parking.app.mapper.BeaconCoordinateMapper;
import com.cetuer.parking.app.mapper.BeaconMapper;
import com.cetuer.parking.app.mapper.CoordinateMapper;
import com.cetuer.parking.app.service.FingerprintService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

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

    @Override
    public void collectFingerprint(Map<String, Map<String, Double>> fingerprintData) {
        for(String coordinateStr : fingerprintData.keySet()) {
            //信标列表
            Map<String, Double> beaconValue = fingerprintData.get(coordinateStr);
            //切割出坐标，0是x，1是y
            String[] coordinate = coordinateStr.split(",");
            //坐标实体类
            Coordinate c = new Coordinate(null, Integer.parseInt(coordinate[0]), Integer.parseInt(coordinate[1]));
            Integer coordinateId = coordinateMapper.selectCoordinateId(c);
            //不存在坐标则插入
            if(coordinateId == null) {
                coordinateMapper.insertCoordinate(c);
                coordinateId = c.getId();
            }
            for(String mac: beaconValue.keySet()) {
                beaconCoordinateMapper.insertBeaconCoordinate(new BeaconCoordinate(null, coordinateId, beaconMapper.selectIdByMac(mac), beaconValue.get(mac)));
            }
        }
    }
}
