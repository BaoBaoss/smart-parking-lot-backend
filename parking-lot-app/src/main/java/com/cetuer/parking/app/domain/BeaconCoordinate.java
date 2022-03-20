package com.cetuer.parking.app.domain;

import com.cetuer.parking.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
* TODO()
* 
* @author zhangqb
* @date 2022/3/19 16:01
*/
@ApiModel(value="`beacon_coordinate`")
@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
public class BeaconCoordinate extends BaseEntity {

    private static final long serialVersionUID = -43585537749643027L;

    @ApiModelProperty(value="主键")
    private Integer id;

    @ApiModelProperty(value="坐标id")
    private Integer coordinateId;

    @ApiModelProperty(value="信标id")
    private Integer beaconId;

    @ApiModelProperty(value="rssi值")
    private Double rssi;
}