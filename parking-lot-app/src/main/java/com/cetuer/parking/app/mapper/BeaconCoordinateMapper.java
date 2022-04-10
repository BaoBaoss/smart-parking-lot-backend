package com.cetuer.parking.app.mapper;

import com.cetuer.parking.app.domain.BeaconCoordinate;
import com.cetuer.parking.app.domain.BeaconRssi;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* 信标RSSI值数据操作
* 
* @author zhangqb
* @date 2022/3/19 16:01
*/
public interface BeaconCoordinateMapper {

    /**
     * 插入数据
     * @param beaconCoordinate 数据
     */
    void insertBeaconCoordinate(BeaconCoordinate beaconCoordinate);

    /**
     * 根据坐标id查找所在坐标的信标mac和rssi
     * @param coordinateId 坐标id
     * @return mac和rssi
     */
    List<BeaconRssi> selectMacRssiByCoordinateId(Integer coordinateId);

    /**
     * 根据坐标id和信标id查找数据
     * @param coordinateId 坐标id
     * @param beaconId 信标id
     * @return 数据
     */
    BeaconCoordinate selectByCoordIdAndBeaconId(@Param("coordinateId") Integer coordinateId, @Param("beaconId") Integer beaconId);

    /**
     * 修改数据
     * @param beaconCoordinate 数据
     */
    void updateBeaconCoordinate(BeaconCoordinate beaconCoordinate);
}