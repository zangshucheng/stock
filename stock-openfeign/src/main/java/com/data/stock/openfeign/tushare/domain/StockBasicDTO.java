package com.data.stock.openfeign.tushare.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * 参考文档 https://tushare.pro/document/2?doc_id=25
 */
@Data
public class StockBasicDTO implements Serializable {
    /**
     * TS股票代码
     * 默认显示：Y
     */
    private String ts_code;

    /**
     * 股票代码
     * 默认显示：Y
     */
    private String symbol;

    /**
     * 名称
     * 默认显示：Y
     */
    private String name;

    /**
     * 地域
     * 默认显示：Y
     */
    private String area;

    /**
     * 所属行业
     * 默认显示：Y
     */
    private String industry;

    /**
     * 股票全称
     * 默认显示：N
     */
    private String fullname;

    /**
     * 英文全称
     * 默认显示：N
     */
    private String enname;

    /**
     * 拼音缩写
     * 默认显示：N
     */
    private String cnspell;

    /**
     * 市场类别 （主板/创业板/科创板/CDR/北交所）
     * 默认显示：Y
     */
    private String market;

    /**
     * 交易所 SSE上交所 SZSE深交所 BSE北交所
     * 必填：N
     */
    private String exchange;

    /**
     * 交易货币
     * 必填：N
     */
    private String curr_type;

    /**
     * 上市状态 L上市 D退市 P暂停上市，默认是L
     * 默认显示：N
     */
    private String list_status;

    /**
     * 上市日期
     * 默认显示：Y
     */
    private String list_date;

    /**
     * 退市日期
     * 默认显示：N
     */
    private String delist_date;

    /**
     * 是否沪深港通标的，N否 H沪股通 S深股通
     * 默认显示：N
     */
    private String is_hs;

    /**
     * 实控人名称
     * 默认显示：N
     */
    private String act_name;

    /**
     * 实控人企业性质
     * 默认显示：N
     */
    private String act_ent_type;
}
