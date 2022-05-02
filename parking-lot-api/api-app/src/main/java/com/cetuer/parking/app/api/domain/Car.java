package com.cetuer.parking.app.api.domain;

import com.cetuer.parking.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
* 车辆信息
* 
* @author zhangqb
* @date 2022/4/23 12:13
*/
@ApiModel(value="车辆实体")
@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
public class Car extends BaseEntity {
    private static final long serialVersionUID = -1719343821596023615L;
    @ApiModelProperty(value="主键")
    private Integer id;

    @ApiModelProperty(value="车牌号")
    private String carId;

    @ApiModelProperty(value="用户id")
    private Integer memberId;

    @ApiModelProperty(value="车位编号，-1则未停车")
    private Integer spaceId;

    @ApiModelProperty(value="品牌")
    private String brand;

    @ApiModelProperty(value="购入价格")
    private Integer price;

    @ApiModelProperty(value="颜色")
    private String color;
}