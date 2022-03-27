package com.cetuer.parking.app.service;

import com.cetuer.parking.app.domain.ParkingLot;

import java.util.List;

/**
* @author Administrator
* @description 针对表【parking_lot】的数据库操作Service
* @createDate 2022-03-26 23:24:57
*/
public interface ParkingLotService {

    /**
     * 获得所有停车场
     * @return 停车场列表
     */
    List<ParkingLot> list();
}
