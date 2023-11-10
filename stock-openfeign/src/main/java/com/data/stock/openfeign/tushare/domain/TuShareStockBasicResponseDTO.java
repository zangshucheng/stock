package com.data.stock.openfeign.tushare.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * 参考文档 https://tushare.pro/document/2?doc_id=25
 */
@Data
public class TuShareStockBasicResponseDTO implements Serializable {
    /**
     * 本次请求的唯一表示
     */
    private String request_id;

    /**
     * 响应code
     */
    private int code;

    /**
     * 返回的数据
     */
    private TuSahreCommonResponseDataDTO data;
}
