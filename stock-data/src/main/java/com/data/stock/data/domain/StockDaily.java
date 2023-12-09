package com.data.stock.data.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * 
 * @TableName stock_daily
 */
@TableName(value ="stock_daily")
@Data
public class StockDaily implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 股票代码
     */
    @TableField(value = "stock_code")
    private String stockCode;

    /**
     * 
     */
    @TableField(value = "stock_name")
    private String stockName;

    /**
     * 交易日期
     */
    @TableField(value = "trade_date")
    private String tradeDate;

    /**
     * 开盘价
     */
    @TableField(value = "open")
    private BigDecimal open;

    /**
     * 最高价
     */
    @TableField(value = "high")
    private BigDecimal high;

    /**
     * 最低价
     */
    @TableField(value = "low")
    private BigDecimal low;

    /**
     * 收盘价
     */
    @TableField(value = "close")
    private BigDecimal close;

    /**
     * 上个交易日收盘价格
     */
    @TableField(value = "pre_close")
    private BigDecimal preClose;

    /**
     * 涨跌幅
     */
    @TableField(value = "pct_change")
    private BigDecimal pctChange;

    /**
     * 振幅
     */
    @TableField(value = "amplitude_ratio")
    private BigDecimal amplitudeRatio;

    /**
     * 换手率
     */
    @TableField(value = "turnover_ratio")
    private BigDecimal turnoverRatio;

    /**
     * 价格变动幅度
     */
    @TableField(value = "change_range")
    private BigDecimal changeRange;

    /**
     * 流通市值，单位：亿
     */
    @TableField(value = "circulate_market_value")
    private BigDecimal circulateMarketValue;

    /**
     * 量比
     */
    @TableField(value = "volumn_ration")
    private BigDecimal volumnRation;

    /**
     * 成交总手数，单位：万
     */
    @TableField(value = "trade_volume")
    private BigDecimal tradeVolume;

    /**
     * 成交总额,单位：亿
     */
    @TableField(value = "amount")
    private BigDecimal amount;

    /**
     * 市盈率
     */
    @TableField(value = "pe_ration")
    private BigDecimal peRation;

    /**
     * 市静率
     */
    @TableField(value = "pb_ration")
    private BigDecimal pbRation;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}