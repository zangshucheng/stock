package com.data.stockopenfeign.tushare.domain;

import lombok.Data;

/**
 * 参考文档 https://tushare.pro/document/2?doc_id=25
 */
@Data
public class TuShareStockBasicResponseDTO {
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
    private CommonResponseDataDTO data;

    /**
     * 后面是否还存在数据
     */
    private boolean has_more;
}
