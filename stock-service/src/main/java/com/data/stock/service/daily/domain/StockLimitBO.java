package com.data.stock.service.daily.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class StockLimitBO {

    /**
     * 表中文名称
     */
    private String stockCode;

    /**
     * 表英文名称
     */
    private String stockName;

    /**
     * 交易日期
     */
    private String tradeDate;

    /**
     * 炒作题材
     */
    private String hypeSubject;

    /**
     * 当前价格
     */
    private BigDecimal close;

    /**
     * 涨幅
     */
    private BigDecimal pctChange;

    /**
     * 换手率
     */
    private BigDecimal turnoverRate;

    /**
     * 流通市值
     */
    private BigDecimal circulateMarketValue;

    /**
     * 振幅
     */
    private BigDecimal volatility;

    /**
     * 成交额，单位：亿
     */
    private BigDecimal amount;

    /**
     * 板上成交额，单位：亿
     */
    private BigDecimal limitAmount;

    /**
     * 首次封板时间
     */
    private String firstTime;

    /**
     * 最后封板时间
     */
    private String lastTime;

    /**
     * 炸板次数
     */
    private Integer openTimes;

    /**
     * 涨停统计
     */
    private String upStatistics;

    /**
     * 连板数
     */
    private Integer limitTimes;

    /**
     * 类型 up down
     */
    private String limitType;

    /**
     * 所属行业
     */
    private String industry;

}
