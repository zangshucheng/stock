package com.data.stock.openfeign.tushare.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * ClassName : TradeCalendarDTO
 * Description : 此处描述该类简要功能
 *
 * @author : zangshucheng
 * Date : 2023/11/7 12:07
 * History :
 * <author>         <time>          <version>        <desc>
 */
@Data
public class TuSahreTradeCalendarDTO implements Serializable {

    /**
     * 交易所 SSE上交所,SZSE深交所,CFFEX 中金所,SHFE 上期所,CZCE 郑商所,DCE 大商所,INE 上能源
     */
    private String exchange;

    /**
     * 日历日期
     */
    private String cal_date;

    /**
     *是否交易 0休市 1交易
     */
    private String pretrade_date;

    /**
     * 是否交易 '0'休市 '1'交易
     */
    private String is_open;
}
