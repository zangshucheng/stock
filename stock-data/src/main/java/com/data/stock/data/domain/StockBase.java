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
     * 股票代码
     */
    @TableId(value = "stock_code")
    private String stockCode;

    /**
     * 自定义编码
     */
    @TableField(value = "ts_code")
    private String tsCode;

    /**
     * 股票名称
     */
    @TableField(value = "stock_name")
    private String stockName;

    /**
     * 所属区域
     */
    @TableField(value = "area")
    private String area;

    /**
     * 所属行业
     */
    @TableField(value = "industry")
    private String industry;

    /**
     * 股票全称
     */
    @TableField(value = "full_name")
    private String fullName;

    /**
     * 英文名称
     */
    @TableField(value = "en_name")
    private String enName;

    /**
     * 中文缩写
     */
    @TableField(value = "cn_spell")
    private String cnSpell;

    /**
     * 市场类型（主板/创业板/科创板/CDR）
     */
    @TableField(value = "market")
    private String market;

    /**
     * 上市状态 L上市 D退市 P暂停上市
     */
    @TableField(value = "status")
    private String status;

    /**
     * 是否沪深港通标的，N否 H沪股通 S深股通
     */
    @TableField(value = "is_hs")
    private String isHs;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}