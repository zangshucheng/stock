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
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 表英文名称
     */
    @TableField(value = "stock_name")
    private String stockName;

    /**
     * 表中文名称
     */
    @TableField(value = "stock_code")
    private String stockCode;

    /**
     * 
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
    @TableField(value = "price")
    private BigDecimal price;

    /**
     * 类型 up down
     */
    @TableField(value = "limit_type")
    private String limitType;

    /**
     * 涨幅
     */
    @TableField(value = "range_percent")
    private BigDecimal rangePercent;

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

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}