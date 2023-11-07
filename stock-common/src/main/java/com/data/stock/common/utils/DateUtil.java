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

    public static String getDateFormat(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        return dateFormat.format(calendar.getTime());
    }
}
