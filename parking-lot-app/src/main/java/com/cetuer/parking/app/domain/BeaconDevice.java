package com.cetuer.parking.app.domain;

import com.cetuer.parking.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
* 信标实体类
* 
* @author Cetuer
* @date 2022/3/6 18:03
*/
@ApiModel(value="信标实体")
@Data
@EqualsAndHashCode(callSuper=true)
public class BeaconDevice extends BaseEntity {

    private static final long serialVersionUID = 395177982138647810L;

    @ApiModelProperty(value="主键")
    private Integer id;

    @ApiModelProperty(value="物理地址")
    private String mac;

    @ApiModelProperty(value="uuid")
    private String uuid;

    @ApiModelProperty(value="主要标识")
    private Integer major;

    @ApiModelProperty(value="次要标识")
    private Integer minor;

    @ApiModelProperty(value="x坐标")
    private Integer x;

    @ApiModelProperty(value="y坐标")
    private Integer y;

    @ApiModelProperty(value="停车场id")
    private Integer parkingLotId;
}