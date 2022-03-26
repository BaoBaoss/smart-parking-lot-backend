package com.cetuer.parking.app.service;

import com.cetuer.parking.app.domain.BeaconPoint;
import com.cetuer.parking.app.domain.BeaconRssi;
import com.cetuer.parking.app.domain.Coordinate;

import java.util.List;
import java.util.Map;

/**
 * 指纹服务
 *
 * @author zhangqb
 * @date 2022/3/20 15:06
 */
public interface FingerprintService {
    /**
     * 收集指纹数据
     * @param fingerprintData 指纹数据
     */
    void collectFingerprint(Map<String, Map<String, Double>> fingerprintData);

    /**
     * 定位
     * @param beaconRssis 信标数据强度
     * @return 定位到的坐标
     */
    Coordinate location(List<BeaconRssi> beaconRssis);
}
