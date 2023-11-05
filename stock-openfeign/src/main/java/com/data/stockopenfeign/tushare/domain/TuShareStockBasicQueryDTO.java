package com.data.stockopenfeign.tushare.domain;

import lombok.Data;

/**
 * 参考文档 https://tushare.pro/document/2?doc_id=25
 */
@Data
public class TuShareStockBasicQueryDTO {

    public TuShareStockBasicQueryDTO(){

    }

    public TuShareStockBasicQueryDTO(int limit, int offset){
        this.limit = limit;
        this.offset = offset;
    }
    /**
     * TS股票代码
     * 必填：N
     */
    private String ts_code;

    /**
     * 名称
     * 必填：N
     */
    private String name;

    /**
     * 市场类别 （主板/创业板/科创板/CDR/北交所）
     * 必填：N
     */
    private String market;

    /**
     * 上市状态 L上市 D退市 P暂停上市，默认是L
     * 必填：N
     */
    private String list_status;

    /**
     * 交易所 SSE上交所 SZSE深交所 BSE北交所
     * 必填：N
     */
    private String exchange;

    /**
     * 是否沪深港通标的，N否 H沪股通 S深股通
     * 必填：N
     */
    private String s_hs;

    /**
     * 单次返回数据长度
     */
    private  int limit;

    /**
     * 请求数据的开始位移量
     */
    private int offset;
}
