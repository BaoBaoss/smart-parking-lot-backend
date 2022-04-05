package com.cetuer.parking.app.service;

import com.cetuer.parking.app.api.domain.vo.BeaconDevice;
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

    /**
     * 分页查询信标列表
     * @param beacon 查询参数
     * @return 信标列表
     */
    List<BeaconDevice> selectListByPage(BeaconDevice beacon);

    /**
     * 插入信标
     * @param beaconDevice 信标
     */
    void insert(BeaconDevice beaconDevice);

    /**
     * 根据信标物理地址判断是否已有信标
     * @param parkingId 停车场编号
     * @param mac 物理地址
     * @return true -> 有；false -> 没有
     */
    boolean hasBeacon(Integer parkingId, String mac);

    /**
     * 根据停车场id判断是否有信标
     * @param parkingId 停车场编号
     * @return true -> 有；false -> 没有
     */
    boolean hasBeacon(Integer parkingId);

    /**
     * 根据信标物理地址判断是否已有信标(不算自己)
     * @param id id
     * @param parkingId 停车场编号
     * @param mac 物理地址
     * @return true -> 有；false -> 没有
     */
    boolean hasBeaconExcludesSelf(Integer id, Integer parkingId, String mac);

    /**
     * 根据id获取信标信息
     * @param beaconId 信标id
     * @return 信标信息
     */
    BeaconDevice selectById(Integer beaconId);

    /**
     * 修改信标信息
     * @param beaconDevice 信标
     */
    void update(BeaconDevice beaconDevice);

    /**
     * 批量删除信标
     * @param ids 信标编号列表
     */
    void del(Integer[] ids);

    /**
     * 根据停车场id删除所有信标
     * @param parkingId 停车场id
     */
    void delAllByParkingId(Integer parkingId);
}
