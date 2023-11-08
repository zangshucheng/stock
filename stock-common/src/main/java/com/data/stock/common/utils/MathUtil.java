package com.data.stock.common.utils;

import com.data.stock.common.constant.MagicNumberConstants;

import java.math.BigDecimal;

/**
 * 数学计算工具
 */
public final class MathUtil {

    /**
     * 判断股票是否涨停
     * @param stockCode
     * @param stockPrice
     * @return
     */
    public static boolean upLimt(String stockCode, String stockPrice, String previousPrice){
        BigDecimal stockGrowth = null;
        if(stockCode.startsWith("00") || stockCode.startsWith("60")){
            stockGrowth = MagicNumberConstants.UP_LIMIT_TEN;
        }else {
            stockGrowth = MagicNumberConstants.UP_LIMIT_TWENTY;
        }

        BigDecimal upLimit = new BigDecimal(stockPrice).multiply(BigDecimal.ONE.add(stockGrowth)).setScale(2, BigDecimal.ROUND_HALF_UP);

        return new BigDecimal(stockPrice).compareTo(upLimit) == 0;
    }

    public static boolean downLimt(String stockCode, String stockPrice, String previousPrice){
        BigDecimal stockGrowth = null;
        if(stockCode.startsWith("00") || stockCode.startsWith("60")){
            stockGrowth = MagicNumberConstants.DOWN_LIMIT_TEN;
        }else {
            stockGrowth = MagicNumberConstants.DOWN_LIMIT_TWENTY;
        }

        BigDecimal downLimit = new BigDecimal(stockPrice).multiply(BigDecimal.ONE.subtract(stockGrowth)).setScale(2, BigDecimal.ROUND_HALF_UP);

        return new BigDecimal(stockPrice).compareTo(downLimit) == 0;
    }
}
