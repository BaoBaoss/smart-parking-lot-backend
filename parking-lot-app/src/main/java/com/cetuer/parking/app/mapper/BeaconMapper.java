package com.cetuer.parking.app.mapper;

import com.cetuer.parking.app.api.domain.vo.BeaconDevice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
     * 根据信标物理地址查找信标
     * @param parkingId 停车场编号
     * @param mac 物理地址
     * @return 信标信息
     */
    BeaconDevice selectByMac(@Param("parkingId") Integer parkingId, @Param("mac") String mac);

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
    void delByIds(Integer[] ids);

    /**
     * 根据停车场id查找信标数量
     * @param parkingId 停车场id
     * @return 信标数量
     */
    Integer selectCountByParkingId(Integer parkingId);

    /**
     * 根据停车场id删除所有信标
     * @param parkingId 停车场id
     */
    void delAllByParkingId(Integer parkingId);
}