package com.data.stock.openfeign.tushare.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CommonResponseDataDTO implements Serializable {

    /**
     * 返回包含的字段
     */
    private List<String> fields;

    /**
     * 返回字段对应的值
     */
    private List<List<String>> items;

    /**
     * 后面是否还存在数据
     */
    private boolean has_more;
}
