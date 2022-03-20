package com.cetuer.parking.app.mapper;

import com.cetuer.parking.app.domain.BeaconCoordinate;

/**
* 信标RSSI值数据操作
* 
* @author zhangqb
* @date 2022/3/19 16:01
*/
public interface BeaconCoordinateMapper {

    /**
     * 插入数据
     * @param beaconCoordinate 数据
     */
    void insertBeaconCoordinate(BeaconCoordinate beaconCoordinate);
}