package com.data.stockopenfeign.tushare.domain;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

/**
 * 参考文档 https://tushare.pro/document/2?doc_id=25
 */
@Data
public class TuShareStockBasicRequestDTO {

    /**
     * 接口名称
     */
    private String api_name;

    /**
     * 查询参数
     */
    private TuShareStockBasicQueryDTO params;

    /**
     * 查询字段；
     */
    private List<String> fields = Arrays.asList("ts_code", "symbol", "name", "area", "industry", "market", "list_date",
            "fullname", "enname", "cnspell", "exchange", "curr_type", "list_status", "delist_date", "is_hs", "act_name", "act_ent_type");

    /**
     * 认证
     */
    private String token;
}
