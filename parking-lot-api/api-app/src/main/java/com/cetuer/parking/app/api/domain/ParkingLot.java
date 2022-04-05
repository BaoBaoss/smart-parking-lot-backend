package com.cetuer.parking.app.api.domain;

import com.cetuer.parking.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 停车场实体类
 * @TableName parking_lot
 */
@ApiModel(value="停车场实体")
@Data
@EqualsAndHashCode(callSuper=true)
public class ParkingLot extends BaseEntity {

    private static final long serialVersionUID = 8784744870947838395L;

    /**
     * 主键
     */
    @ApiModelProperty(value="主键")
    private Integer id;

    /**
     * 停车场名称
     */
    @ApiModelProperty(value="停车场名称")
    private String name;

    /**
     * 经度
     */
    @ApiModelProperty(value="经度")
    private Double longitude;

    /**
     * 纬度
     */
    @ApiModelProperty(value="纬度")
    private Double latitude;

    /**
     * 车位总数
     */
    @ApiModelProperty(value="车位总数")
    private Integer carportCount;

    /**
     * 空余车位
     */
    @ApiModelProperty(value="空余车位")
    private Integer emptyParking;

    /**
     * 信标数量
     */
    @ApiModelProperty(value="信标数量")
    private Integer beaconCount;

    /**
     * 收费标准  元/小时
     */
    @ApiModelProperty(value="收费标准  元/小时")
    private Double priceStandard;
}