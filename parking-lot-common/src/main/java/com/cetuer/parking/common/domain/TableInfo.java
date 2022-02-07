package com.cetuer.parking.common.domain;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 分页信息
 *
 * @author Cetuer
 * @date 2022/2/6 8:53
 */
@Data
@ApiModel("分页信息")
public class TableInfo<T> {
    private static final long serialVersionUID = 958124097671449153L;

    private TableInfo() {
    }

    @ApiModelProperty(value = "总记录数")
    private long total;

    @ApiModelProperty(value = "列表数据")
    private List<T> rows;

    /**
     * 根据分页数据返回分页实体
     * @param data 分页数据
     * @param <T> 数据类型
     * @return 分页实体
     */
    public static <T> TableInfo<T> getInstance(List<T> data) {
        TableInfo<T> t = new TableInfo<>();
        t.setTotal(PageInfo.of(data).getTotal());
        t.setRows(data);
        return t;
    }
}
