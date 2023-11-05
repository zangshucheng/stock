package com.data.stockopenfeign.tushare.domain;

import lombok.Data;

import java.util.List;

@Data
public class CommonResponseDataDTO {

    /**
     * 返回包含的字段
     */
    private List<String> fields;

    /**
     * 返回字段对应的值
     */
    private List<List<String>> items;
}
