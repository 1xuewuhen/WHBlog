package com.xwh.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xwh.whblogcommon.utils.R;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author 陈方银
 * @date 2023/4/5
 * @since 1.0
 */

@Component
public class AuthenticationFailHandle implements ServerAuthenticationFailureHandler {
    @Override
    public Mono<Void> onAuthenticationFailure(WebFilterExchange webFilterExchange, AuthenticationException exception) {
        ServerWebExchange exchange = webFilterExchange.getExchange();
        ServerHttpResponse response = exchange.getResponse();
        HttpHeaders headers = response.getHeaders();
        headers.add("Content-Type", "application/json;charset=UTF-8");
        headers.add("Cache-Control", "no-store,no-cache,must-revalidate,max-age-8");
        R r = R.error();
        byte[] dataBytes = {};
        ObjectMapper mapper = new ObjectMapper();
        try {
            dataBytes = mapper.writeValueAsBytes(r);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        DataBuffer wrap = response.bufferFactory().wrap(dataBytes);
        return response.writeWith(Mono.just(wrap));
    }
}
