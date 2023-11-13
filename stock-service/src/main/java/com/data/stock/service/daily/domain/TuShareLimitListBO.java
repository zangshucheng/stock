package com.data.stock.service.daily.domain;

import lombok.Data;

@Data
public class TuShareLimitListBO {
    /**
     * 交易日期
     */
    private String tradeDate;

    /**
     * TS股票代码
     */
    private String stockCode;

    /**
     * 所属行业
     */
    private String industry;

    /**
     * 股票名称
     */
    private String stockName;

    /**
     * 收盘价
     */
    private String close;

    /**
     * 涨跌幅
     */
    private String pctChange;

    /**
     * 成交额
     */
    private String amount;

    /**
     * 板上成交金额(涨停无此数据)
     */
    private String limitAmount;

    /**
     * 	流通市值
     */
    private String circulationMarketValue;

    /**
     * 总市值
     */
    private String totalMarketValue;

    /**
     * 换手率
     */
    private String turnoverRatio;

    /**
     * 封单金额
     */
    private String closeAmount;

    /**
     * 	首次封板时间（跌停无此数据）
     */
    private String firstTime;

    /**
     * 最后封板时间
     */
    private String lastTime;

    /**
     * 炸板次数(跌停为开板次数)
     */
    private String openTimes;

    /**
     * 涨停统计（N/T T天有N次涨停）
     */
    private String upStatistics;

    /**
     * 连板数
     */
    private String limitTimes;

    /**
     * D跌停U涨停Z炸板
     */
    private String limitType;

    /**
     * 振幅
     */
    private String volatility;
}
