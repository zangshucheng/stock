package com.data.stock.common.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * ClassName : DateUtil
 * Description : 此处描述该类简要功能
 *
 * @author : zangshucheng
 * Date : 2023/11/7 17:36
 * History :
 * <author>         <time>          <version>        <desc>
 */
public final class DateUtil {

    public static final String YYYYMMDD = "yyyyMMdd";

    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    public static String getDateFormat(String format){
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        return dateFormat.format(calendar.getTime());
    }

    public static String getDateFormatYYYYMMDD(){
        SimpleDateFormat dateFormat = new SimpleDateFormat(YYYYMMDD);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        return dateFormat.format(calendar.getTime());
    }
}
