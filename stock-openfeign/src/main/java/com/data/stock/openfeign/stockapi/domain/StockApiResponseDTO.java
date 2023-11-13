package com.data.stock.openfeign.stockapi.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public final class StockApiResponseDTO <T extends Serializable> implements Serializable{

    private static final long serialVersionUID = 1L;

    private String code;
    private String msg;
    private T data;
}
