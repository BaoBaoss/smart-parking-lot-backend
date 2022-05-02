package com.cetuer.parking.app.service;

import com.cetuer.parking.app.api.domain.ParkingSpace;

import java.util.List;

/**
* @author Administrator
* @description 针对表【parking_space】的数据库操作Service
* @createDate 2022-03-31 14:56:34
*/
public interface ParkingSpaceService {

    /**
     * 根据停车场编号获得其车位
     * @param parkingLotId 停车场编号
     * @return 车位列表
     */
    List<ParkingSpace> listByParkingId(Integer parkingLotId);

    /**
     * 根据停车场编号和车位坐标更改车位状态
     *
     * @param parkingId 停车场编号
     * @param x         车位x坐标
     * @param y         车位y坐标
     * @param status    要更改的状态
     * @return 无
     */
    Void changeStatus(Integer parkingId, Integer x, Integer y, Integer status);

    /**
     * 根据停车场id判断是否有停车位
     * @param parkingId 停车场id
     * @return true -> 有；false -> 没有
     */
    boolean hasSpace(Integer parkingId);

    /**
     * 根据停车场id删除所有车位
     * @param parkingId 停车场id
     */
    void delAllByParkingId(Integer parkingId);

    /**
     * 分页查询停车场下车位
     * @param parkingId 停车场id
     * @return 车位列表
     */
    List<ParkingSpace> selectListByPage(Integer parkingId);

    /**
     * 插入车位
     * @param parkingSpace 车位
     */
    void insert(ParkingSpace parkingSpace);

    /**
     * 根据车位坐标判断是否已有停车位
     * @param parkingId 停车场编号
     * @param x x坐标
     * @param y y坐标
     * @return true -> 有；false -> 没有
     */
    boolean hasSpace(Integer parkingId, Integer x, Integer y);

    /**
     * 根据id获取车位信息
     * @param spaceId 车位id
     * @return 车位信息
     */
    ParkingSpace selectById(Integer spaceId);

    /**
     * 修改车位信息
     * @param parkingSpace 车位
     */
    void update(ParkingSpace parkingSpace);

    /**
     * 修改车位信息(不修改坐标)
     * @param parkingSpace 车位
     */
    void updateNoLocation(ParkingSpace parkingSpace);

    /**
     * 批量删除车位
     * @param ids 车位编号列表
     */
    void del(Integer[] ids);

    /**
     * 停车
     * @param userId 用户ID
     * @param spaceId 车位id
     */
    void parking(Integer userId, Integer spaceId);

    /**
     * 根据用户id获取停车车位
     * @param userId 用户id
     * @return 停车车位
     */
    ParkingSpace carSpace(Integer userId);

    /**
     * 寻车成功，更改车辆和车位状态
     * @param userId 用户id
     * @param spaceId 之前所停放车位
     */
    void findCar(Integer userId, Integer spaceId);
}
