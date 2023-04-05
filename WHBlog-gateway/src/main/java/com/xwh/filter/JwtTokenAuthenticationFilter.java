package com.xwh.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author 陈方银
 * @date 2023/4/5
 * @since 1.0
 */
public class JwtTokenAuthenticationFilter  implements WebFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange, WebFilterChain webFilterChain) {
        String token = resolveToken(serverWebExchange.getRequest());
        if (StringUtils.hasLength(token)){
            //TODO 验证token有效性
        }
        return webFilterChain.filter(serverWebExchange);
    }

    public String resolveToken(ServerHttpRequest request){
        String token = request.getHeaders().getFirst("token");
        if (StringUtils.hasText(token)){
            return token;
        }
        return null;
    }
}
