package com.cetuer.parking.app.util;

import com.cetuer.parking.app.domain.BeaconRssi;
import com.cetuer.parking.app.domain.KNNBean;

import java.util.List;
import java.util.Map;

/**
 * 计算工具类
 *
 * @author zhangqb
 * @date 2022/3/24 22:41
 */
public class CalculateUtil {
    /**
     * 计算欧氏距离
     * LaTex公式：L_i=\sqrt{\sum_{1}^{n}\left(R_i-R_j\right)^2}
     *
     * @param onlineRssis 在线的强度
     * @param libRssis    指纹库的强度
     * @return 欧氏距离
     */
    public static Double calcDistance(List<BeaconRssi> onlineRssis, Map<String, Double> libRssis) {
        double sum = 0;
        for (BeaconRssi b : onlineRssis) {
            sum += Math.pow(Math.abs(libRssis.get(b.getMac()) - b.getRssi()), 2);
        }
        return Math.sqrt(sum);
    }
}
