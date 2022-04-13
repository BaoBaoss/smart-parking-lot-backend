package com.cetuer.parking.app.api.domain;

import com.cetuer.parking.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
* 公告实体
* 
* @author zhangqb
* @date 2022/4/13 10:28
*/
@ApiModel(value="`notice`")
@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
public class Notice extends BaseEntity {
    private static final long serialVersionUID = -4962177677675976963L;
    @ApiModelProperty(value="主键")
    private Integer id;

    @ApiModelProperty(value="停车场编号")
    private Integer parkingLotId;

    @ApiModelProperty(value="标题")
    private String title;

    @ApiModelProperty(value="内容")
    private String content;

    @ApiModelProperty(value="是否展示：0->不展示;1->展示")
    private Integer show;

    @ApiModelProperty(value="公告级别：0->普通；1->紧急；2->特急")
    private Integer level;
}