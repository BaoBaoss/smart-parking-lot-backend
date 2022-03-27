package com.cetuer.parking.app.service.impl;

import com.cetuer.parking.app.domain.ParkingLot;
import com.cetuer.parking.app.mapper.ParkingLotMapper;
import com.cetuer.parking.app.service.ParkingLotService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Administrator
 * @description 针对表【parking_lot】的数据库操作Service实现
 * @createDate 2022-03-26 23:24:57
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ParkingLotServiceImpl implements ParkingLotService {

    private final ParkingLotMapper parkingLotMapper;

    @Override
    public List<ParkingLot> list() {
        return parkingLotMapper.selectAll();
    }
}
