package com.data.stock.openfeign.configration;

import feign.Logger;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.Collections;

/**
 * ClassName : FeignConfigration
 * Description : 此处描述该类简要功能
 *
 * @author : zangshucheng
 * Date : 2022/11/9 12:09
 * History :
 * <author> <time> <version> <desc>
 */
@Configuration
public class FeignConfigration {

//    @Bean
//    public FeignLogger feignLogger(){
//        return new FeignLogger();
//    }
//
//    @Bean
//    public Logger.Level feignLoggerLevel(){
//        return Logger.Level.FULL;
//    }


    /**
     * 处理 no suitable HttpMessageConverter found for response [text/plain;charset=UTF-8]
     */
    @Bean
    public MappingJackson2HttpMessageConverter customMappingJackson2HttpMessageConverter() {
        return new CustomMappingJackson2HttpMessageConverter();
    }

    class CustomMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {
        public CustomMappingJackson2HttpMessageConverter() {
            setSupportedMediaTypes(Collections.singletonList(MediaType.TEXT_PLAIN));
        }
    }


}
