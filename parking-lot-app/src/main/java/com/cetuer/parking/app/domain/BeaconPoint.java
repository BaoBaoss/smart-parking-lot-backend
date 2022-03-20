package com.cetuer.parking.app.domain;

import com.cetuer.parking.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 信标坐标
 *
 * @author zhangqb
 * @date 2022/3/12 14:47
 */
@Data
public class BeaconPoint {
    private static final long serialVersionUID = -4373214856555181777L;

    @ApiModelProperty(value="x坐标")
    private Integer x;

    @ApiModelProperty(value="y坐标")
    private Integer y;

}
