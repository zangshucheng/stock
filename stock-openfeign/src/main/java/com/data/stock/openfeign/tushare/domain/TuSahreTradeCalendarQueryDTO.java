package com.data.stock.openfeign.tushare.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * ClassName : TradeCalendarQueryDTO
 * Description : 此处描述该类简要功能
 *
 * @author : zangshucheng
 * Date : 2023/11/7 12:04
 * History :
 * <author>         <time>          <version>        <desc>
 */
@Data
public class TuSahreTradeCalendarQueryDTO extends TuSahreBasePageDTO implements Serializable {

    /**
     * 交易所 SSE上交所,SZSE深交所,CFFEX 中金所,SHFE 上期所,CZCE 郑商所,DCE 大商所,INE 上能源
     * 必填：N
     */
    private String exchange;

    /**
     * 开始日期 （格式：YYYYMMDD 下同）
     *必填：N
     */
    private String start_date;

    /**
     * 结束日期
     * 必填：N
     */
    private String end_date;

    /**
     * 是否交易 '0'休市 '1'交易
     * 必填：N
     */
    private String is_open;

}
