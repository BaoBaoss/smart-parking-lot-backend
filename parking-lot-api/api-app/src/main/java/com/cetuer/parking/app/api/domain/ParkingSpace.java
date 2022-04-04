package com.cetuer.parking.app.api.domain;

import com.cetuer.parking.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
     * 车牌号
     */
    @ApiModelProperty("车牌号")
    private String carId;

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