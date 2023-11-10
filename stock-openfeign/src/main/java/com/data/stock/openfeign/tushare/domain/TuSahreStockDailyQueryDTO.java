package com.data.stock.openfeign.tushare.domain;

import lombok.Data;

@Data
public class TuSahreStockDailyQueryDTO extends TuSahreBasePageDTO {

    public TuSahreStockDailyQueryDTO(){}

    public TuSahreStockDailyQueryDTO(int limit, int offset, String trade_date){
        this.limit = limit;
        this.offset = offset;
        this.trade_date = trade_date;
    }

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
     * 交易日期
     * 必填：N
     */
    private String trade_date;

    /**
     * 股票代码（支持多个股票同时提取，逗号分隔）
     * 必填：N
     */
    private String ts_code;
}
