package com.cetuer.parking.app.service;

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
}
