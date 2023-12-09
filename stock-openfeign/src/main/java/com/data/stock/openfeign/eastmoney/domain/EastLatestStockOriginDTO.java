package com.data.stock.openfeign.eastmoney.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class EastLatestStockOriginDTO implements Serializable {
    /**
     * total
     */
    private Integer total;
    /**
     * diff
     */
    private List<EastLatestStockDTO> diff;
}

