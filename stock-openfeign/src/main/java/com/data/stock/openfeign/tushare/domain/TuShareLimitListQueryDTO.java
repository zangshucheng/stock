package com.data.stock.openfeign.tushare.domain;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
public class TuShareLimitListQueryDTO extends TuSahreBasePageDTO implements Serializable {

    public TuShareLimitListQueryDTO(){
    }

    public TuShareLimitListQueryDTO(int limit, int offset, String trade_date){
        this.limit = limit;
        this.offset = offset;
        this.trade_date = trade_date;
    }

    public TuShareLimitListQueryDTO(int limit, int offset, String start_date, String end_date){
        this.limit = limit;
        this.offset = offset;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    /**
     * 交易日期
     */
    private String trade_date;

    /**
     * 股票代码
     */
    private String ts_code;

    /**
     * 涨跌停类型（U涨停D跌停Z炸板）
     */
    private String limit_type;

    /**
     * 交易所（SH上交所SZ深交所BJ北交所）
     */
    private String exchange;

    /**
     * 开始日期
     */
    private String start_date;

    /**
     * 结束日期
     */
    private String end_date;
}
