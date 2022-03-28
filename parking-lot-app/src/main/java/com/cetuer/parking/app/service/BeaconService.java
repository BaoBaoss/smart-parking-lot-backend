package com.cetuer.parking.app.service;

import com.cetuer.parking.app.domain.BeaconDevice;
import com.cetuer.parking.app.domain.BeaconPoint;

import java.util.List;

/**
* 信标操作服务层
* 
* @author Cetuer
* @date 2022/3/6 18:03
*/
public interface BeaconService {

    /**
     * 根据停车场id查找其所有信标
     * @param parkingLotId 停车场id
     * @return 信标列表
     */
    List<BeaconDevice> selectDeviceByParkingLotId(Integer parkingLotId);

    /**
     * 根据停车场id查找其终点坐标
     * @param parkingLotId 停车场id
     * @return 终点坐标
     */
    BeaconPoint selectEndPointByParkingLotId(Integer parkingLotId);
}
