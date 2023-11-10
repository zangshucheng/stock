package com.data.stock.openfeign.tushare.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * ClassName : BasePageDTO
 * Description : 此处描述该类简要功能
 *
 * @author : zangshucheng
 * Date : 2023/11/7 12:10
 * History :
 * <author>         <time>          <version>        <desc>
 */
@Data
public class TuSahreBasePageDTO implements Serializable {

    /**
     * 单次返回数据长度
     */
    int limit;

    /**
     * 请求数据的开始位移量
     */
    int offset;
}
