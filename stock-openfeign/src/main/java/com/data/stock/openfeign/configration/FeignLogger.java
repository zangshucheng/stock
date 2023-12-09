package com.data.stock.openfeign.configration;

import feign.Logger;
import feign.Request;
import feign.Response;
import feign.Util;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import static feign.Util.UTF_8;
import static feign.Util.decodeOrDefault;

/**
 * ClassName : FeignLogger Description : 此处描述该类简要功能
 *
 * @author : zangshucheng Date : 2023/3/30 14:09 History : <author> <time> <version> <desc>
 */
@Slf4j
public class FeignLogger extends Logger {
    @Override
    protected void logRequest(String configKey, Level logLevel, Request request) {

        if (request.body() != null) {

            String bodyText = request.charset() != null ? new String(request.body(), request.charset()) : null;
            log(configKey, "---> %s %s HTTP/1.1, request: %s ", request.httpMethod().name(), request.url(),
                bodyText != null ? bodyText : "Binary data");
        } else {
            log(configKey, "---> %s %s HTTP/1.1", request.httpMethod().name(), request.url());
        }
    }

    @Override
    protected Response logAndRebufferResponse(String configKey, Level logLevel, Response response, long elapsedTime)
        throws IOException {

        String reason = response.reason() != null ? " " + response.reason() : "";

        if (response.body() != null) {

            byte[] bodyData = Util.toByteArray(response.body().asInputStream());

            log(configKey, "<--- HTTP/1.1 %s%s (%sms), response: %s", response.status(), reason, elapsedTime,
                decodeOrDefault(bodyData, UTF_8, "Binary data").length() > 1000
                    ? decodeOrDefault(bodyData, UTF_8, "Binary data").substring(1, 1000)
                    : decodeOrDefault(bodyData, UTF_8, "Binary data"));

            return response.toBuilder().body(bodyData).build();
        } else {
            log(configKey, "<--- HTTP/1.1 %s%s (%sms), response: %s", response.status(), reason, elapsedTime, "");
        }

        return response;
    }

    @Override
    protected void log(String configKey, String format, Object... args) {
        log.info(String.format(methodTag(configKey) + format, args));
    }

}
