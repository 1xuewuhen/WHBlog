package com.xwh.handler;

import com.alibaba.fastjson2.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xwh.to.AuthUserDetails;
import com.xwh.whblogcommon.enums.AppHttpCodeEnum;
import com.xwh.whblogcommon.utils.R;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.WebFilterChainServerAuthenticationSuccessHandler;
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
public class AuthenticationSuccessHandler extends WebFilterChainServerAuthenticationSuccessHandler {


    @Override
    public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange, Authentication authentication) {
        ServerWebExchange exchange = webFilterExchange.getExchange();
        ServerHttpResponse response = exchange.getResponse();
        HttpHeaders headers = response.getHeaders();
        headers.add("Content-Type", "application/json;charset=UTF-8");
        headers.add("Cache-Control", "no-store,no-cache,must-revalidate,max-age-8");

        byte[] dataBytes = {};
        ObjectMapper mapper = new ObjectMapper();
        try {
            User user = (User) authentication.getPrincipal();
            AuthUserDetails userDetails = buildUser(user);
            HashMap<String, Object> map = new HashMap<>(2);
            map.put("user-info", userDetails);
            //TODO 生成token
            String jwtToken = "";
            userDetails.setToken(jwtToken);
            R r = R.ok().put(userDetails);
            dataBytes = mapper.writeValueAsBytes(r);
        } catch (JsonProcessingException e) {
            Map<String, Object> map = new HashMap<>(4);
            map.put("code", AppHttpCodeEnum.SYSTEM_ERROR.getCode());
            map.put("msg", AppHttpCodeEnum.SYSTEM_ERROR.getMsg());
            dataBytes = JSON.toJSONString(map).getBytes();
            e.printStackTrace();
        }
        DataBuffer wrap = response.bufferFactory().wrap(dataBytes);
        return response.writeWith(Mono.just(wrap));
    }

    private AuthUserDetails buildUser(User user) {
        AuthUserDetails authUserDetails = new AuthUserDetails();
        // 获取数据库信息
        return authUserDetails;
    }
}
