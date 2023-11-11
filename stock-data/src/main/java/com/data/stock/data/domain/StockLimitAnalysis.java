package com.data.stock.data.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * 股票涨跌分析
 * @TableName stock_limit_analysis
 */
@TableName(value ="stock_limit_analysis")
@Data
public class StockLimitAnalysis implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 表中文名称
     */
    @TableField(value = "stock_code")
    private String stockCode;

    /**
     * 表英文名称
     */
    @TableField(value = "stock_name")
    private String stockName;

    /**
     * 交易日期
     */
    @TableField(value = "trade_date")
    private String tradeDate;

    /**
     * 炒作题材
     */
    @TableField(value = "hype_subject")
    private String hypeSubject;

    /**
     * 当前价格
     */
    @TableField(value = "close")
    private BigDecimal close;

    /**
     * 涨幅
     */
    @TableField(value = "pct_change")
    private BigDecimal pctChange;

    /**
     * 换手率
     */
    @TableField(value = "turnover_rate")
    private BigDecimal turnoverRate;

    /**
     * 流通市值
     */
    @TableField(value = "circulate_market_value")
    private BigDecimal circulateMarketValue;

    /**
     * 振幅
     */
    @TableField(value = "volatility")
    private BigDecimal volatility;

    /**
     * 成交额，单位：亿
     */
    @TableField(value = "amount")
    private BigDecimal amount;

    /**
     * 板上成交额，单位：亿
     */
    @TableField(value = "limit_amount")
    private BigDecimal limitAmount;

    /**
     * 首次封板时间
     */
    @TableField(value = "first_time")
    private String firstTime;

    /**
     * 最后封板时间
     */
    @TableField(value = "last_time")
    private String lastTime;

    /**
     * 炸板次数
     */
    @TableField(value = "open_times")
    private Integer openTimes;

    /**
     * 涨停统计
     */
    @TableField(value = "up_statistics")
    private String upStatistics;

    /**
     * 连板数
     */
    @TableField(value = "limit_times")
    private Integer limitTimes;

    /**
     * 类型 up down
     */
    @TableField(value = "limit_type")
    private String limitType;

    /**
     * 所属行业
     */
    @TableField(value = "industry")
    private String industry;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}