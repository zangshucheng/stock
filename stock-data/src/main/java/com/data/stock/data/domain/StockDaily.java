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
     * 股票代码
     */
    @TableField(value = "stock_Name")
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
     * 价格变动百分比
     */
    @TableField(value = "pct_chg")
    private BigDecimal pctChg;

    /**
     * 成交总额
     */
    @TableField(value = "amount")
    private BigDecimal amount;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}