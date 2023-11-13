package com.data.stock.common.constant;

import java.math.BigDecimal;

public final class MagicNumberConstants {

    /**
     * 每次取数据的条数
     */
    public static final int STOCK_BASIC_LIMIT = 5000;

    /**
     * 每次取数据的条数
     */
    public static final int STOCK_DAILY_LIMIT = 500;

    /**
     * 其实查询位置
     */
    public static final int STOCK_BASIC_OFFSET_START = 0;

    /**
     * 上涨百分之10
     */
    public static final BigDecimal UP_LIMIT_TEN = new BigDecimal("0.1");

    /**
     * 上涨百分之20
     */
    public static final BigDecimal UP_LIMIT_TWENTY = new BigDecimal("0.2");

    /**
     * 下降百分之10
     */
    public static final BigDecimal DOWN_LIMIT_TEN = new BigDecimal("0.1");

    /**
     * 下降百分之20
     */
    public static final BigDecimal DOWN_LIMIT_TWENTY = new BigDecimal("0.2");
}
