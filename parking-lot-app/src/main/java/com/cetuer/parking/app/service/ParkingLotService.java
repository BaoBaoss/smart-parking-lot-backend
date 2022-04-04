package com.cetuer.parking.app.service;

import com.cetuer.parking.app.api.domain.ParkingLot;

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

    /**
     * 根据经纬度查询停车场id
     * @param longitude 经度
     * @param latitude 纬度
     * @return 停车场id
     */
    Integer parkingIdByLatLng(Double longitude, Double latitude);

    /**
     * 分页查询所有停车场
     * @param parkingLot 查询条件
     * @return 分页后停车场
     */
    List<ParkingLot> selectListByPage(ParkingLot parkingLot);

    /**
     * 新增停车场
     * @param parkingLot 停车场
     */
    void insert(ParkingLot parkingLot);

    /**
     * 根据id获取停车场信息
     * @param parkingId 停车场id
     * @return 停车场信息
     */
    ParkingLot selectById(Integer parkingId);

    /**
     * 修改停车场信息
     * @param parkingLot 停车场信息
     */
    void update(ParkingLot parkingLot);

    /**
     * 根据停车场id删除停车场
     * @param parkingId 停车场id
     */
    void delById(Integer parkingId);
}
