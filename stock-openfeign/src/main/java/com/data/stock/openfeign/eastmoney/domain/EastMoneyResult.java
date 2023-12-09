package com.data.stock.openfeign.eastmoney.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class EastMoneyResult<T extends Serializable> implements Serializable{
    private String rc;

    private Integer rt;

    private Long svr;

    private Integer lt;

    private Integer full;

    private String dlmkts;

    private T data;
}
