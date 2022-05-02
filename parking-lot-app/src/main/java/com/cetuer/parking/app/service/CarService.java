package com.cetuer.parking.app.service;

import com.cetuer.parking.app.api.domain.Car;

/**
 * 车辆服务层
 *
 * @author zhangqb
 * @date 2022/4/23 12:13
 */
public interface CarService {

    /**
     * 添加或修改车辆信息
     *
     * @param userId 用户id
     * @param car    车辆信息
     */
    void addOrUpdate(Integer userId, Car car);

    /**
     * 根据用户id获取车辆信息
     *
     * @param userId 用户id
     * @return 车辆信息
     */
    Car getCarByUserId(Integer userId);

    /**
     * 根据车牌号获取车辆信息
     *
     * @param carId 车牌号
     * @return 车辆信息
     */
    Car getCarByCarId(String carId);

    /**
     * 能否停车
     * @param userId 用户id
     * @return true->可以停车；false->不可停车
     */
    Boolean canParking(Integer userId);

    /**
     * 是否可以反向寻车
     * @param userId 用id
     * @return true->可以寻车；false->不可寻车
     */
    Boolean canFindCar(Integer userId);
}
