package com.cetuer.parking.app.mapper;

import com.cetuer.parking.app.api.domain.ParkingSpace;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author Administrator
* @description 针对表【parking_space】的数据库操作Mapper
* @createDate 2022-03-31 14:56:34
* @Entity com.cetuer.parking.app.api.domain.ParkingSpace
*/
public interface ParkingSpaceMapper {

    /**
     * 根据停车场编号获得其车位
     * @param parkingLotId 停车场编号
     * @return 车位列表
     */
    List<ParkingSpace> selectByParkingId(Integer parkingLotId);

    /**
     * 根据停车场编号和车位坐标更改车位状态
     *
     * @param parkingId 停车场编号
     * @param x         车位x坐标
     * @param y         车位y坐标
     * @param status    要更改的状态
     * @return 无
     */
    Void updateStatus(@Param("parkingId") Integer parkingId, @Param("x") Integer x, @Param("y") Integer y, @Param("status") Integer status);

    /**
     * 根据停车场id删除所有车位
     * @param parkingId 停车场id
     */
    void delAllByParkingId(Integer parkingId);

    /**
     * 插入车位
     * @param parkingSpace 车位
     */
    void insert(ParkingSpace parkingSpace);

    /**
     * 根据位置查找车位
     * @param parkingId 停车场编号
     * @param x x坐标
     * @param y y坐标
     * @return 车位
     */
    ParkingSpace selectByLocation(@Param("parkingId") Integer parkingId, @Param("x") Integer x, @Param("y") Integer y);

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
     * 批量删除车位
     * @param ids 车位编号列表
     */
    void delByIds(Integer[] ids);
}
