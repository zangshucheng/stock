package com.data.stock.openfeign.tushare.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class TuShareDailyBasicQueryDTO {

    public TuShareDailyBasicQueryDTO(int limit, int offset, String trade_date){
        this.limit = limit;
        this.offset = offset;
        this.trade_date = trade_date;
    }

    /**
     * ts_code
     */
    private String ts_code;
    /**
     * trade_date
     */
    private String trade_date;
    /**
     * start_date
     */
    private String start_date;
    /**
     * end_date
     */
    private String end_date;
    /**
     * limit
     */
    private int limit;
    /**
     * offset
     */
    private int offset;
}
