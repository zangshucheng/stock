package com.data.stock.common.constant;

import java.util.Arrays;
import java.util.List;

public final class TuShareURLConstants {

    /**
     * 基础数据-股票列表
     */
    public static final String STOCK_BASIC_URL = "stock_basic";

    /**
     * 获取交易日历
     */
    public static final String  TRADE_CAL = "trade_cal";

    /**
     * 获取交易日历
     */
    public static final String  DAILY = "daily";

    /**
     * 非交易日
     */
    public static final String TRADE_CLOSE = "1";

    /**
     * 交易日
     */
    public static final String TRADE_OPEN = "1";

    /**
     * 股票列表返回字段
     */
    public static final List<String> STOCK_BASIC_FIELDS = Arrays.asList("ts_code", "symbol", "name", "area", "industry", "market", "list_date",
            "fullname", "enname", "cnspell", "exchange", "curr_type", "list_status", "delist_date", "is_hs", "act_name", "act_ent_type");

    /**
     * 交易日历返回字段
     */
    public static final List<String> TRADE_CAL_FIELDS = Arrays.asList("exchange", "cal_date", "is_open", "pretrade_date");

    /**
     * 每日行情
     */
    public static final List<String> DAILY_FIELDS= Arrays.asList("ts_code","trade_date","open","high","low","close","pre_close","change","pct_chg","vol","amount");
}
