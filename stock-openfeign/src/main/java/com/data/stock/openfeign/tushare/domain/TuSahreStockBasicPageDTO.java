package com.data.stock.openfeign.tushare.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class TuSahreStockBasicPageDTO<T> implements Serializable {

    private List<T> tuShareStockBasics;

    /**
     * 后面是否还存在数据
     */
    private boolean has_more;
}
