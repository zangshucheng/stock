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
     * 市值（单位亿）
     */
    @TableField(value = "market_value")
    private Long marketValue;

    /**
     * 类型
     */
    @TableField(value = "limit_type")
    private String limitType;

    /**
     * 涨幅百分比
     */
    @TableField(value = "range_percent")
    private BigDecimal rangePercent;

    /**
     * 换手率
     */
    @TableField(value = "turnover_rate")
    private BigDecimal turnoverRate;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}