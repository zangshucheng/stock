package com.data.stock.data.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * @TableName stock_daily
 */
@Data
@TableName(value ="stock_daily")
public class StockDaily implements Serializable {

    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 股票代码
     */
    private String stockCode;

    /**
     * 交易日期
     */
    private String tradeDate;

    /**
     * 开盘价
     */
    private BigDecimal open;

    /**
     * 最高价
     */
    private BigDecimal high;

    /**
     * 最低价
     */
    private BigDecimal low;

    /**
     * 收盘价
     */
    private BigDecimal close;

    /**
     * 
     */
    private BigDecimal preClose;

    /**
     * 
     */
    private BigDecimal changeRange;

    /**
     * 
     */
    private BigDecimal pctChg;

    /**
     * 
     */
    private BigDecimal tradeVolume;

    /**
     * 
     */
    private BigDecimal amount;

}