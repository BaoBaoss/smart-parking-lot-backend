package com.cetuer.parking.app.service.impl;

import com.cetuer.parking.app.api.domain.Car;
import com.cetuer.parking.app.mapper.CarMapper;
import com.cetuer.parking.app.service.CarService;
import com.cetuer.parking.common.core.enums.ResultCode;
import com.cetuer.parking.common.core.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 车辆服务实现层
 *
 * @author zhangqb
 * @date 2022/4/23 12:13
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CarServiceImpl implements CarService {
    private final CarMapper carMapper;

    @Override
    public void addOrUpdate(Integer userId, Car car) {
        Car selectCar = carMapper.selectCarByMemberId(userId);
        if (selectCar != null) {
            //有重复车牌号并且不是自己原本车牌
            if (!Objects.equals(getCarByCarId(car.getCarId()).getId(), selectCar.getId())) {
                throw new ServiceException(ResultCode.HAS_CAR_ID_ERROR);
            }
            car.setId(selectCar.getId());
            carMapper.update(car);
            return;
        }
        //有重复车牌号
        if (getCarByCarId(car.getCarId()) != null) {
            throw new ServiceException(ResultCode.HAS_CAR_ID_ERROR);
        }
        car.setMemberId(userId);
        carMapper.insert(car);
    }

    @Override
    public Car getCarByUserId(Integer userId) {
        return carMapper.selectCarByMemberId(userId);
    }

    @Override
    public Car getCarByCarId(String carId) {
        return carMapper.selectCarByCarId(carId);
    }

    @Override
    public Boolean canParking(Integer userId) {
        Car userCar = getCarByUserId(userId);
        //有车且未停车则可停车
        return userCar != null && userCar.getSpaceId() == -1;
    }

    @Override
    public Boolean canFindCar(Integer userId) {
        Car userCar = getCarByUserId(userId);
        //有车且已停车则可寻车
        return userCar != null && userCar.getSpaceId() != -1;
    }
}
