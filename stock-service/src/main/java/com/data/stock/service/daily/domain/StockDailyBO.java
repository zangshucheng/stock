package com.data.stock.service.daily.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.data.stock.openfeign.tushare.domain.TuShareLimitListDTO;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class StockDailyBO {

    /**
     * 股票代码
     */
    private String stockCode;

    /**
     * 股票代码
     */
    private String stockName;

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
     * 上个交易日收盘价格
     */
    private BigDecimal preClose;

    /**
     * 涨跌额
     */
    private BigDecimal changeRange;

    /**
     * 振幅
     */
    private BigDecimal amplitudeRatio;

    /**
     * 涨跌幅
     */
    private BigDecimal pctChange;

    /**
     * 成交总手数
     */
    private BigDecimal tradeVolume;

    /**
     * 成交总额
     */
    private BigDecimal amount;

    /**
     * 换手率
     */
    private BigDecimal turnoverRate;

    /**
     * 流通市值，单位：亿
     */
    private BigDecimal circulateMarketValue;

    /**
     * 量比
     */
    private BigDecimal volumnRation;
}
