package com.cetuer.parking.app.service;

import com.cetuer.parking.app.domain.BeaconDevice;
import com.cetuer.parking.app.domain.BeaconPoint;

import java.util.List;
import java.util.Map;

/**
* 信标操作服务层
* 
* @author Cetuer
* @date 2022/3/6 18:03
*/
public interface BeaconService {

    /**
     * 查找所有信标
     * @return 信标列表
     */
    List<BeaconDevice> selectAll();

    /**
     * 查找信标终点坐标
     * @return 终点坐标
     */
    BeaconPoint selectEndPoint();
}
