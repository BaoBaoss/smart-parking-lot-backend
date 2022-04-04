package com.cetuer.parking.app.service.impl;

import com.cetuer.parking.app.api.domain.ParkingLot;
import com.cetuer.parking.app.domain.ParkingSpace;
import com.cetuer.parking.app.mapper.ParkingLotMapper;
import com.cetuer.parking.app.mapper.ParkingSpaceMapper;
import com.cetuer.parking.app.service.ParkingLotService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Administrator
 * @description 针对表【parking_lot】的数据库操作Service实现
 * @createDate 2022-03-26 23:24:57
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ParkingLotServiceImpl implements ParkingLotService {

    private final ParkingLotMapper parkingLotMapper;
    private final ParkingSpaceMapper parkingSpaceMapper;

    @Override
    public List<ParkingLot> list() {
        return parkingLotMapper.selectAll();
    }

    @Override
    public Integer parkingIdByLatLng(Double longitude, Double latitude) {
        return parkingLotMapper.selectParkingIdByLatLng(longitude, latitude);
    }

    @Override
    public List<ParkingLot> selectListByPage(ParkingLot parkingLot) {
        return parkingLotMapper.listByPage(parkingLot).stream().peek(parking -> {
            List<ParkingSpace> parkingSpaces = parkingSpaceMapper.selectByParkingId(parking.getId());
            //车位总数
            parking.setCarportCount(parkingSpaces.size());
            //空余车位
            parking.setEmptyParking((int) parkingSpaces.stream().filter(space -> space.getAvailable() == 1).count());
        }).collect(Collectors.toList());
    }

    @Override
    public void insert(ParkingLot parkingLot) {
        parkingLotMapper.insert(parkingLot);
    }

    @Override
    public ParkingLot selectById(Integer parkingId) {
        return parkingLotMapper.selectById(parkingId);
    }

    @Override
    public void update(ParkingLot parkingLot) {
        parkingLotMapper.update(parkingLot);
    }

    @Override
    public void delById(Integer parkingId) {
        parkingLotMapper.deleteById(parkingId);
    }
}
