package com.data.stock.common.utils;

import com.data.stock.common.constant.MagicNumberConstants;
import org.springframework.util.StringUtils;

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
    public static boolean upLimt(String stockCode, BigDecimal stockPrice, BigDecimal previousPrice){
        BigDecimal stockGrowth = null;
        if(stockCode.startsWith("00") || stockCode.startsWith("60")){
            stockGrowth = MagicNumberConstants.UP_LIMIT_TEN;
        }else {
            stockGrowth = MagicNumberConstants.UP_LIMIT_TWENTY;
        }

        BigDecimal upLimit = previousPrice.multiply(BigDecimal.ONE.add(stockGrowth)).setScale(2, BigDecimal.ROUND_HALF_UP);

        return stockPrice.compareTo(upLimit) == 0;
    }

    public static boolean downLimt(String stockCode, BigDecimal stockPrice, BigDecimal previousPrice){
        BigDecimal stockGrowth = null;
        if(stockCode.startsWith("00") || stockCode.startsWith("60")){
            stockGrowth = MagicNumberConstants.DOWN_LIMIT_TEN;
        }else {
            stockGrowth = MagicNumberConstants.DOWN_LIMIT_TWENTY;
        }

        BigDecimal downLimit = previousPrice.multiply(BigDecimal.ONE.subtract(stockGrowth)).setScale(2, BigDecimal.ROUND_HALF_UP);

        return stockPrice.compareTo(downLimit) == 0;
    }

    public static BigDecimal stringToBigdecimal(String v){
        if(StringUtils.isEmpty(v) || "-".equals(v)){
            return null;
        }
        return new BigDecimal(v);
    }

    public static Integer stringToInteger(String v){
        if(StringUtils.isEmpty(v) || "-".equals(v)){
            return null;
        }
        return Integer.parseInt(v);
    }

    public static BigDecimal stringToBigdecimalDivide(String v, int divide){
        if(StringUtils.isEmpty(v) || "-".equals(v)){
            return null;
        }
        return new BigDecimal(v).divide(new BigDecimal(divide), 2, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal stringToBigdecimalDivide100(String v){
        if(StringUtils.isEmpty(v) || "-".equals(v)){
            return null;
        }
        return new BigDecimal(v).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
    }
}
