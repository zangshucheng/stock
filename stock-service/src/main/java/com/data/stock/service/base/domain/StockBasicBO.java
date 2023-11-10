package com.data.stock.service.base.domain;

import com.data.stock.data.domain.StockBase;
import lombok.Data;

@Data
public class StockBasicBO {

    /**
     * 股票代码
     */
    private String stockCode;

    /**
     * 自定义编码
     */
    private String tsCode;

    /**
     * 股票名称
     */
    private String stockName;

    /**
     * 所属区域
     */
    private String area;

    /**
     * 所属行业
     */
    private String industry;

    /**
     * 股票全称
     */
    private String fullName;

    /**
     * 英文名称
     */
    private String enName;

    /**
     * 中文缩写
     */
    private String cnSpell;

    /**
     * 市场类型（主板/创业板/科创板/CDR）
     */
    private String market;

    /**
     * 上市状态 L上市 D退市 P暂停上市
     */
    private String status;

    /**
     * 是否沪深港通标的，N否 H沪股通 S深股通
     */
    private String isHs;

    public static StockBase buildStockBase(StockBasicBO basic){
        StockBase stockBase = new StockBase();
        stockBase.setTsCode(basic.getTsCode());
        stockBase.setStockName(basic.getStockName());
        stockBase.setStockCode(basic.getStockCode());
        stockBase.setArea(basic.getArea());
        stockBase.setIndustry(basic.getIndustry());
        stockBase.setFullName(basic.getFullName());
        stockBase.setEnName(basic.getEnName());
        stockBase.setCnSpell(basic.getCnSpell());
        stockBase.setMarket(basic.getMarket());
        stockBase.setStatus(basic.getStatus());
        stockBase.setIsHs(basic.getIsHs());
        return stockBase;
    }
}
