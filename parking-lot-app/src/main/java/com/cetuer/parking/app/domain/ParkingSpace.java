package com.cetuer.parking.app.domain;

import com.cetuer.parking.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 *  停车位实体
 * @TableName parking_space
 */
@ApiModel(value="停车位实体")
@Data
@EqualsAndHashCode(callSuper=true)
public class ParkingSpace extends BaseEntity {
    private static final long serialVersionUID = 6334208350727799772L;
    /**
     * 主键
     */
    @ApiModelProperty("主键")
    private Integer id;

    /**
     * 停车场id
     */
    @ApiModelProperty("停车场id")
    private Integer parkingLotId;

    /**
     * 车辆id
     */
    @ApiModelProperty("车辆id")
    private Integer carId;

    /**
     * x坐标
     */
    @ApiModelProperty("x坐标")
    private Integer x;

    /**
     * y坐标
     */
    @ApiModelProperty("y坐标")
    private Integer y;

    /**
     * 车位是否可用：0->不可用；1->可用
     */
    @ApiModelProperty("车位是否可用")
    private Integer available;

}