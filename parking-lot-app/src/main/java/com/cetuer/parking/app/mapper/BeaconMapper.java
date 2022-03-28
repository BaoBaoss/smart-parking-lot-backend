package com.cetuer.parking.app.mapper;

import com.cetuer.parking.app.domain.BeaconDevice;

import java.util.List;
import java.util.Set;

/**
* 信标操作数据层
* 
* @author Cetuer
* @date 2022/3/6 18:03
*/
public interface BeaconMapper {
    /**
     * 根据停车场id查找其所有信标
     * @param parkingLotId 停车场id
     * @return 信标列表
     */
    List<BeaconDevice> selectDeviceByParkingLotId(Integer parkingLotId);

    /**
     * 根据mac查找id
     * @param mac 物理地址
     * @return id
     */
    Integer selectIdByMac(String mac);
}