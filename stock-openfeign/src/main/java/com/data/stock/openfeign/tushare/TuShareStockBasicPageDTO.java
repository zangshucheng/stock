package com.data.stock.openfeign.tushare;

import com.data.stock.openfeign.tushare.domain.TuShareStockBasicDTO;
import lombok.Data;

import java.util.List;

@Data
public class TuShareStockBasicPageDTO {

    private List<TuShareStockBasicDTO> tuShareStockBasics;

    /**
     * 后面是否还存在数据
     */
    private boolean has_more;
}
