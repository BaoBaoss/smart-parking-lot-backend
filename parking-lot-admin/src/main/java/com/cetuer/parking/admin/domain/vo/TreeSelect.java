package com.cetuer.parking.admin.domain.vo;

import com.cetuer.parking.admin.domain.Menu;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TreeSelect树结构实体类
 *
 * @author Cetuer
 * @date 2022/2/13 17:07
 */
@Data
@ApiModel("下拉树结构实体")
public class TreeSelect implements Serializable {
    private static final long serialVersionUID = -1202849691467364577L;

    @ApiModelProperty("节点id")
    private Integer id;

    @ApiModelProperty("节点名称")
    private String label;

    @ApiModelProperty("子节点")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<TreeSelect> children;

    @ApiModelProperty("是否不可用")
    private Boolean disabled;

    public TreeSelect(Menu menu) {
        this.id = menu.getId();
        this.label = menu.getName();
        this.children = menu.getChildren().stream().map(TreeSelect::new).collect(Collectors.toList());
        this.disabled = menu.getStatus() == 0;
    }
}
