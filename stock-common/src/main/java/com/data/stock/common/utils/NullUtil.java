package com.data.stock.common.utils;

import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * ClassName : NullUtil
 * Description : 此处描述该类简要功能
 *
 * @author : zangshucheng
 * Date : 2022/11/16 14:53
 * History :
 * <author> <time> <version> <desc>
 */
public final class NullUtil {

    public static boolean isNotNull(Object object){
        return !Objects.isNull(object);
    }

    public static boolean isNull(Object object){
        return Objects.isNull(object);
    }

    public static boolean isNotBlank(CharSequence cs){
        return !StringUtils.isEmpty(cs);
    }

    /**
     * 原有元素非null
     * @param args
     * @return
     */
    public static boolean isAllNotNull(Object... args) {
        for (Object arg : args) {
            if(Objects.isNull(arg)){
                return false;
            }
        }
        return true;
    }
}
