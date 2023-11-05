package com.data.stockopenfeign.tushare;

import com.data.stockopenfeign.tushare.domain.TuShareStockBasicDTO;
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
