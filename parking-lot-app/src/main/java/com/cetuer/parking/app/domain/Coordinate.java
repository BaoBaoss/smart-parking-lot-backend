package com.cetuer.parking.app.domain;

import com.cetuer.parking.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
* TODO()
* 
* @author zhangqb
* @date 2022/3/20 15:25
*/
@ApiModel(value="`coordinate`")
@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
public class Coordinate extends BaseEntity {

    private static final long serialVersionUID = 8605281052893103399L;

    @ApiModelProperty(value="主键")
    private Integer id;

    @ApiModelProperty(value="x坐标")
    private Integer x;

    @ApiModelProperty(value="y坐标")
    private Integer y;
}