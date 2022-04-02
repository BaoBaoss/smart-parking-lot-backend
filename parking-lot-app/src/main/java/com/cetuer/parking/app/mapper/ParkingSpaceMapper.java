package com.cetuer.parking.app.mapper;

import com.cetuer.parking.app.domain.ParkingSpace;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author Administrator
* @description 针对表【parking_space】的数据库操作Mapper
* @createDate 2022-03-31 14:56:34
* @Entity com.cetuer.parking.app.domain.ParkingSpace
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
}
