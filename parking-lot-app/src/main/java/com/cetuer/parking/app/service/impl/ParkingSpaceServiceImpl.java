package com.cetuer.parking.app.service.impl;

import com.cetuer.parking.app.domain.ParkingSpace;
import com.cetuer.parking.app.mapper.ParkingSpaceMapper;
import com.cetuer.parking.app.service.ParkingSpaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Administrator
* @description 针对表【parking_space】的数据库操作Service实现
* @createDate 2022-03-31 14:56:34
*/
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ParkingSpaceServiceImpl implements ParkingSpaceService{

    private final ParkingSpaceMapper parkingSpaceMapper;

    @Override
    public List<ParkingSpace> listByParkingId(Integer parkingLotId) {
        return parkingSpaceMapper.selectByParkingId(parkingLotId);
    }

    @Override
    public Void changeStatus(Integer parkingId, Integer x, Integer y, Integer status) {
        return parkingSpaceMapper.updateStatus(parkingId, x, y, status);
    }
}
