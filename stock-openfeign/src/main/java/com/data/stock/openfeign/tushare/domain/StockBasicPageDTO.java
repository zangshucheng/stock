package com.data.stock.openfeign.tushare.domain;

import com.data.stock.openfeign.tushare.domain.StockBasicDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class StockBasicPageDTO<T> implements Serializable {

    private List<T> tuShareStockBasics;

    /**
     * 后面是否还存在数据
     */
    private boolean has_more;
}
