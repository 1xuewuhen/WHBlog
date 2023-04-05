package com.xwh.handler;

import com.alibaba.fastjson2.JSON;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.authentication.HttpBasicServerAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 陈方银
 * @date 2023/4/5
 * @since 1.0
 */

@Component
public class CustomHttpBasicServerAuthenticationEntryPoint extends HttpBasicServerAuthenticationEntryPoint {

    @Override
    public Mono<Void> commence(ServerWebExchange exchange, AuthenticationException ex) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add("Content-Type","application/json;charset=UTF-8");
        Map<String,Object> result = new HashMap<>(4);
        result.put("status",HttpStatus.UNAUTHORIZED);
        result.put("message","鉴权失败");
        byte[] bytes = JSON.toJSONString(result).getBytes();
        DataBuffer wrap = response.bufferFactory().wrap(bytes);
        return response.writeWith(Mono.just(wrap));
    }
}
