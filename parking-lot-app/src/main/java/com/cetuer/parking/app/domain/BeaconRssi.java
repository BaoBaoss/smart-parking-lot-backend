package com.cetuer.parking.app.domain;

import lombok.Data;
import lombok.ToString;

/**
 * 信标rssi
 *
 * @author zhangqb
 * @date 2022/3/26 13:48
 */
@Data
@ToString
public class BeaconRssi {
    /**
     * 物理地址
     */
    private String mac;
    /**
     * 信号强度
     */
    private Double rssi;
}
