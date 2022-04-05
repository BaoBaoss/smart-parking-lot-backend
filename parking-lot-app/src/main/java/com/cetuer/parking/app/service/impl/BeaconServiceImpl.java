package com.cetuer.parking.app.service.impl;

import com.cetuer.parking.app.api.domain.vo.BeaconDevice;
import com.cetuer.parking.app.domain.BeaconPoint;
import com.cetuer.parking.app.mapper.BeaconMapper;
import com.cetuer.parking.app.service.BeaconService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 信标操作服务实现
 *
 * @author Cetuer
 * @date 2022/3/6 18:03
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BeaconServiceImpl implements BeaconService {

    private final BeaconMapper beaconMapper;

    @Override
    public List<BeaconDevice> selectDeviceByParkingLotId(Integer parkingLotId) {
        return beaconMapper.selectDeviceByParkingLotId(parkingLotId);
    }

    @Override
    public BeaconPoint selectEndPointByParkingLotId(Integer parkingLotId) {
        BeaconPoint beaconPoint = new BeaconPoint();
        int maxX = 0;
        int maxY = 0;
        List<BeaconDevice> deviceList = selectDeviceByParkingLotId(parkingLotId);
        for(BeaconDevice device:deviceList) {
            if(device.getX() > maxX) {
                maxX = device.getX();
            }
            if(device.getY() > maxY) {
                maxY = device.getY();
            }
        }
        beaconPoint.setX(maxX);
        beaconPoint.setY(maxY);
        return beaconPoint;
    }

    @Override
    public List<BeaconDevice> selectListByPage(BeaconDevice beacon) {
        return beaconMapper.selectListByPage(beacon);
    }

    @Override
    public void insert(BeaconDevice beaconDevice) {
        beaconMapper.insert(beaconDevice);
    }

    @Override
    public boolean hasBeacon(Integer parkingId, String mac) {
        return beaconMapper.selectByMac(parkingId, mac) != null;
    }

    @Override
    public boolean hasBeacon(Integer parkingId) {
        return beaconMapper.selectCountByParkingId(parkingId) != 0;
    }

    @Override
    public boolean hasBeaconExcludesSelf(Integer id, Integer parkingId, String mac) {
        BeaconDevice beaconDevice = beaconMapper.selectByMac(parkingId, mac);
        return beaconDevice != null && !Objects.equals(beaconDevice.getId(), id);
    }

    @Override
    public BeaconDevice selectById(Integer beaconId) {
        return beaconMapper.selectById(beaconId);
    }

    @Override
    public void update(BeaconDevice beaconDevice) {
        beaconMapper.update(beaconDevice);
    }

    @Override
    public void del(Integer[] ids) {
        beaconMapper.delByIds(ids);
    }

    @Override
    public void delAllByParkingId(Integer parkingId) {
        beaconMapper.delAllByParkingId(parkingId);
    }
}
