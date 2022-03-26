package com.cetuer.parking.app.mapper;

import com.cetuer.parking.app.domain.Coordinate;

import java.util.List;

/**
* 坐标数据层
* 
* @author zhangqb
* @date 2022/3/20 15:25
*/
public interface CoordinateMapper {

    /**
     * 根据坐标获取其ID
     * @param coordinate 坐标
     * @return 坐标数量
     */
    Integer selectCoordinateId(Coordinate coordinate);

    /**
     * 插入坐标
     * @param coordinate 坐标
     */
    void insertCoordinate(Coordinate coordinate);

    /**
     * 获取所有坐标
     * @return 坐标列表
     */
    List<Coordinate> selectAll();
}