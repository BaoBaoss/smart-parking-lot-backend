package com.cetuer.parking.app.service.impl;

import com.cetuer.parking.app.api.domain.Car;
import com.cetuer.parking.app.api.domain.ParkingSpace;
import com.cetuer.parking.app.mapper.CarMapper;
import com.cetuer.parking.app.mapper.ParkingSpaceMapper;
import com.cetuer.parking.app.service.ParkingSpaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Administrator
 * @description 针对表【parking_space】的数据库操作Service实现
 * @createDate 2022-03-31 14:56:34
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ParkingSpaceServiceImpl implements ParkingSpaceService {

    private final ParkingSpaceMapper parkingSpaceMapper;
    private final CarMapper carMapper;

    @Override
    public List<ParkingSpace> listByParkingId(Integer parkingLotId) {
        return parkingSpaceMapper.selectByParkingId(parkingLotId);
    }

    @Override
    public Void changeStatus(Integer parkingId, Integer x, Integer y, Integer status) {
        return parkingSpaceMapper.updateStatus(parkingId, x, y, status);
    }

    @Override
    public boolean hasSpace(Integer parkingId) {
        return parkingSpaceMapper.selectByParkingId(parkingId).size() > 0;
    }

    @Override
    public void delAllByParkingId(Integer parkingId) {
        parkingSpaceMapper.delAllByParkingId(parkingId);
    }

    @Override
    public List<ParkingSpace> selectListByPage(Integer parkingId) {
        return parkingSpaceMapper.selectByParkingId(parkingId);
    }

    @Override
    public void insert(ParkingSpace parkingSpace) {
        parkingSpaceMapper.insert(parkingSpace);
    }

    @Override
    public boolean hasSpace(Integer parkingId, Integer x, Integer y) {
        return parkingSpaceMapper.selectByLocation(parkingId, x, y) != null;
    }

    @Override
    public ParkingSpace selectById(Integer spaceId) {
        return parkingSpaceMapper.selectById(spaceId);
    }

    @Override
    public void update(ParkingSpace parkingSpace) {
        parkingSpaceMapper.update(parkingSpace);
    }

    @Override
    public void updateNoLocation(ParkingSpace parkingSpace) {
        parkingSpaceMapper.updateNoLocation(parkingSpace);
    }

    @Override
    public void del(Integer[] ids) {
        parkingSpaceMapper.delByIds(ids);
    }

    @Override
    @Transactional
    public void parking(Integer userId, Integer spaceId) {
        Car car = carMapper.selectCarByMemberId(userId);
        parkingSpaceMapper.updateCarIdById(spaceId, car.getCarId(), 0);
        carMapper.updateSpaceId(car.getId(), spaceId);
    }

    @Override
    public ParkingSpace carSpace(Integer userId) {
        //先获取车
        Car car = carMapper.selectCarByMemberId(userId);
        if(car == null) {
            return null;
        }
        return selectById(car.getSpaceId());
    }

    @Override
    @Transactional
    public void findCar(Integer userId, Integer spaceId) {
        //先获取车
        Car car = carMapper.selectCarByMemberId(userId);
        if(car == null) {
            return;
        }
        carMapper.updateSpaceId(car.getId(), -1);
        parkingSpaceMapper.updateCarIdById(spaceId, "", 1);
    }
}
