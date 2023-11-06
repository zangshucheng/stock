package com.data.stock.data.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 股票基础信息
 * @TableName stock_base
 */
@TableName(value ="stock_base")
@Data
public class StockBase implements Serializable {

    /**
     * 自定义编码
     */
    private String tsCode;

    /**
     * 股票名称
     */
    private String stockName;

    /**
     * 股票代码
     */
    @TableId()
    private String stockCode;

    /**
     * 所属区域
     */
    private String area;

    /**
     * 所属行业
     */
    private String industry;

    /**
     * 股票全称
     */
    private String fullName;

    /**
     * 英文名称
     */
    private String enName;

    /**
     * 中文缩写
     */
    private String cnSpell;

    /**
     * 市场类型（主板/创业板/科创板/CDR）
     */
    private String market;

    /**
     * 上市状态 L上市 D退市 P暂停上市
     */
    private String status;

    /**
     * 是否沪深港通标的，N否 H沪股通 S深股通
     */
    private String isHs;
}