package com.data.stock.openfeign.tushare.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TuShareDailyBasicDTO {

    /**
     * TS股票代码
     */
    private String ts_code;

    /**
     * 交易日期
     */
    private String trade_date;

    /**
     * 换手率
     */
    private String close;

    /**
     * 换手率
     */
    private String turnover_rate;

    /**
     * 换手率(自由流通股)
     */
    private String turnover_rate_f;

    /**
     * 量比
     */
    private String volume_ratio;

    /**
     * 市盈率（总市值/净利润）
     */
    private String pe;

    /**
     * 市盈率（TTM）
     */
    private String 	pe_ttm;

    /**
     * 市净率（总市值/净资产）
     */
    private String pb;

    /**
     * 市销率
     */
    private String 	ps;

    /**
     * 市销率（TTM）
     */
    private String ps_ttm;

    /**
     * 股息率 （%）
     */
    private String dv_ratio;

    /**
     * 股息率（TTM） （%）
     */
    private String dv_ttm;

    /**
     * 总股本
     */
    private String total_share;

    /**
     * 流通股本
     */
    private String float_share;

    /**
     * 自由流通股本
     */
    private String free_share;

    /**
     * 总市值
     */
    private String total_mv;

    /**
     * 流通市值
     */
    private String circ_mv;

    /**
     * 涨跌停状态
     */
    private String limit_status;
}
