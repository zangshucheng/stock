package com.data.stock.openfeign.tushare.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * ClassName : TradeCalendarPageDTO
 * Description : 此处描述该类简要功能
 *
 * @author : zangshucheng
 * Date : 2023/11/7 12:25
 * History :
 * <author>         <time>          <version>        <desc>
 */
@Data
public class TradeCalendarPageDTO implements Serializable {

    /**
     * 日历数据
     */
    private List<TradeCalendarDTO> tradeCalendars;

    /**
     * 后面是否还存在数据
     */
    private boolean has_more;
}
