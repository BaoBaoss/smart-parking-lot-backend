package com.cetuer.parking.app.mapper;

import com.cetuer.parking.app.domain.ParkingLot;

import java.util.List;

/**
* @author Administrator
* @description 针对表【parking_lot】的数据库操作Mapper
* @createDate 2022-03-26 23:24:57
* @Entity com.cetuer.parking.app.domain.ParkingLot
*/
public interface ParkingLotMapper {

    /**
     * 查询所有停车场
     * @return 停车场列表
     */
    List<ParkingLot> selectAll();
}
