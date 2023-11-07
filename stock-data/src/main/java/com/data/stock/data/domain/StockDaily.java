package com.data.stock.data.domain;

import java.io.Serializable;

/**
 * 
 * @TableName stock_daily
 */
public class StockDaily implements Serializable {
    /**
     * 股票代码
     */
    private String tsCode;

    /**
     * 交易日期
     */
    private String tradeDate;

    /**
     * 开盘价
     */
    private Double open;

    /**
     * 最高价
     */
    private Double high;

    /**
     * 最低价
     */
    private Double low;

    /**
     * 收盘价
     */
    private Double close;

    /**
     * 
     */
    private Double preClose;

    /**
     * 
     */
    private Double change;

    /**
     * 
     */
    private Double pctChg;

    /**
     * 
     */
    private Integer tradeVolume;

    /**
     * 
     */
    private Double amount;

    private static final long serialVersionUID = 1L;

    /**
     * 股票代码
     */
    public String getTsCode() {
        return tsCode;
    }

    /**
     * 股票代码
     */
    public void setTsCode(String tsCode) {
        this.tsCode = tsCode;
    }

    /**
     * 交易日期
     */
    public String getTradeDate() {
        return tradeDate;
    }

    /**
     * 交易日期
     */
    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate;
    }

    /**
     * 开盘价
     */
    public Double getOpen() {
        return open;
    }

    /**
     * 开盘价
     */
    public void setOpen(Double open) {
        this.open = open;
    }

    /**
     * 最高价
     */
    public Double getHigh() {
        return high;
    }

    /**
     * 最高价
     */
    public void setHigh(Double high) {
        this.high = high;
    }

    /**
     * 最低价
     */
    public Double getLow() {
        return low;
    }

    /**
     * 最低价
     */
    public void setLow(Double low) {
        this.low = low;
    }

    /**
     * 收盘价
     */
    public Double getClose() {
        return close;
    }

    /**
     * 收盘价
     */
    public void setClose(Double close) {
        this.close = close;
    }

    /**
     * 
     */
    public Double getPreClose() {
        return preClose;
    }

    /**
     * 
     */
    public void setPreClose(Double preClose) {
        this.preClose = preClose;
    }

    /**
     * 
     */
    public Double getChange() {
        return change;
    }

    /**
     * 
     */
    public void setChange(Double change) {
        this.change = change;
    }

    /**
     * 
     */
    public Double getPctChg() {
        return pctChg;
    }

    /**
     * 
     */
    public void setPctChg(Double pctChg) {
        this.pctChg = pctChg;
    }

    /**
     * 
     */
    public Integer getTradeVolume() {
        return tradeVolume;
    }

    /**
     * 
     */
    public void setTradeVolume(Integer tradeVolume) {
        this.tradeVolume = tradeVolume;
    }

    /**
     * 
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * 
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        StockDaily other = (StockDaily) that;
        return (this.getTsCode() == null ? other.getTsCode() == null : this.getTsCode().equals(other.getTsCode()))
            && (this.getTradeDate() == null ? other.getTradeDate() == null : this.getTradeDate().equals(other.getTradeDate()))
            && (this.getOpen() == null ? other.getOpen() == null : this.getOpen().equals(other.getOpen()))
            && (this.getHigh() == null ? other.getHigh() == null : this.getHigh().equals(other.getHigh()))
            && (this.getLow() == null ? other.getLow() == null : this.getLow().equals(other.getLow()))
            && (this.getClose() == null ? other.getClose() == null : this.getClose().equals(other.getClose()))
            && (this.getPreClose() == null ? other.getPreClose() == null : this.getPreClose().equals(other.getPreClose()))
            && (this.getChange() == null ? other.getChange() == null : this.getChange().equals(other.getChange()))
            && (this.getPctChg() == null ? other.getPctChg() == null : this.getPctChg().equals(other.getPctChg()))
            && (this.getTradeVolume() == null ? other.getTradeVolume() == null : this.getTradeVolume().equals(other.getTradeVolume()))
            && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTsCode() == null) ? 0 : getTsCode().hashCode());
        result = prime * result + ((getTradeDate() == null) ? 0 : getTradeDate().hashCode());
        result = prime * result + ((getOpen() == null) ? 0 : getOpen().hashCode());
        result = prime * result + ((getHigh() == null) ? 0 : getHigh().hashCode());
        result = prime * result + ((getLow() == null) ? 0 : getLow().hashCode());
        result = prime * result + ((getClose() == null) ? 0 : getClose().hashCode());
        result = prime * result + ((getPreClose() == null) ? 0 : getPreClose().hashCode());
        result = prime * result + ((getChange() == null) ? 0 : getChange().hashCode());
        result = prime * result + ((getPctChg() == null) ? 0 : getPctChg().hashCode());
        result = prime * result + ((getTradeVolume() == null) ? 0 : getTradeVolume().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", tsCode=").append(tsCode);
        sb.append(", tradeDate=").append(tradeDate);
        sb.append(", open=").append(open);
        sb.append(", high=").append(high);
        sb.append(", low=").append(low);
        sb.append(", close=").append(close);
        sb.append(", preClose=").append(preClose);
        sb.append(", change=").append(change);
        sb.append(", pctChg=").append(pctChg);
        sb.append(", tradeVolume=").append(tradeVolume);
        sb.append(", amount=").append(amount);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}