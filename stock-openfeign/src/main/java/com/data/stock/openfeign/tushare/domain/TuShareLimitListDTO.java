package com.data.stock.openfeign.tushare.domain;

import lombok.Data;

@Data
public class TuShareLimitListDTO {
    /**
     * 交易日期
     */
    private String trade_date;

    /**
     * TS股票代码
     */
    private String ts_code;

    /**
     * 所属行业
     */
    private String industry;

    /**
     * 股票名称
     */
    private String name;

    /**
     * 收盘价
     */
    private String close;

    /**
     * 涨跌幅
     */
    private String pct_chg;

    /**
     * 成交额
     */
    private String amount;

    /**
     * 板上成交金额(涨停无此数据)
     */
    private String limit_amount;

    /**
     * 	流通市值
     */
    private String float_mv;

    /**
     * 总市值
     */
    private String total_mv;

    /**
     * 换手率
     */
    private String turnover_ratio;

    /**
     * 封单金额
     */
    private String fd_amount;

    /**
     * 	首次封板时间（跌停无此数据）
     */
    private String first_time;

    /**
     * 最后封板时间
     */
    private String last_time;

    /**
     * 炸板次数(跌停为开板次数)
     */
    private String open_times;

    /**
     * 涨停统计（N/T T天有N次涨停）
     */
    private String up_stat;

    /**
     * 连板数
     */
    private String limit_times;

    /**
     * D跌停U涨停Z炸板
     */
    private String limit;

    /**
     * 振幅
     */
    private String swing;
}
