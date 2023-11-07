package com.data.stock.common.constant;

import java.util.Arrays;
import java.util.List;

public final class TuShareURLConstants {

    /**
     * 基础数据-股票列表
     */
    public static final String STOCK_BASIC_URL = "stock_basic";

    /**
     * 股票列表返回字段
     */
    public static final List<String> STOCK_BASIC_FIELDS = Arrays.asList("ts_code", "symbol", "name", "area", "industry", "market", "list_date",
            "fullname", "enname", "cnspell", "exchange", "curr_type", "list_status", "delist_date", "is_hs", "act_name", "act_ent_type");;
}
