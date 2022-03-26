package com.cetuer.parking.app.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * KNN定位实体
 *
 * @author zhangqb
 * @date 2022/3/24 21:51
 */
@Data
@ToString
public class KNNBean implements Comparable<KNNBean>{
    /**
     * 坐标
     */
    private Coordinate coordinate;
    /**
     * 欧氏距离
     */
    private Double distance = Double.MAX_VALUE;

    public KNNBean(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    @Override
    public int compareTo(KNNBean o) {
        return (int) Math.round(this.getDistance() - o.getDistance());
    }
}
