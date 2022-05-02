package com.cetuer.parking.app.mapper;

import com.cetuer.parking.app.api.domain.Car;
import org.apache.ibatis.annotations.Param;

/**
* 车辆数据层
* 
* @author zhangqb
* @date 2022/4/23 12:13
*/
public interface CarMapper {
    /**
     * 根据用户id查找车辆信息
     * @param memberId 用户id
     * @return 车辆信息
     */
    Car selectCarByMemberId(Integer memberId);

    /**
     * 更新车辆信息
     * @param car 车辆信息
     */
    void update(Car car);

    /**
     * 插入车辆
     * @param car 车辆信息
     */
    void insert(Car car);

    /**
     * 根据车牌号查找车辆
     * @param carId 车牌号
     * @return 车辆信息
     */
    Car selectCarByCarId(String carId);

    /**
     * 更新车位信息
     * @param id 车辆编号
     * @param spaceId 车位编号
     */
    void updateSpaceId(@Param("id") Integer id, @Param("spaceId") Integer spaceId);
}