package com.xwh.config;

import com.xwh.filter.JwtTokenAuthenticationFilter;
import com.xwh.handler.AuthenticationFailHandle;
import com.xwh.handler.AuthenticationSuccessHandler;
import com.xwh.handler.CustomHttpBasicServerAuthenticationEntryPoint;
import com.xwh.handler.TimingLogoutSuccessHandle;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author 陈方银
 * @date 2023/4/5
 * @since 1.0
 */

@EnableWebFluxSecurity
public class SecurityConfig {

    private static final String[] excludeAuthPages = {"/auth/login"};

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //TODO 实现该处的ReactiveAuthenticationManager
    @Bean
    public ReactiveAuthenticationManager reactiveAuthenticationManager(ReactiveUserDetailsService userDetailsService,
                                                                       PasswordEncoder passwordEncoder) {
        UserDetailsRepositoryReactiveAuthenticationManager authenticationManager = new UserDetailsRepositoryReactiveAuthenticationManager(userDetailsService);
        authenticationManager.setPasswordEncoder(passwordEncoder);
        return authenticationManager;
    }


    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http,
                                                         JwtProperties jwtProperties,
                                                         AuthenticationSuccessHandler authenticationSuccessHandler,
                                                         AuthenticationFailHandle authenticationFailHandle,
                                                         TimingLogoutSuccessHandle timingLogoutSuccessHandle,
                                                         CustomHttpBasicServerAuthenticationEntryPoint customHttpBasicServerAuthenticationEntryPoint,
                                                         ReactiveAuthenticationManager reactiveAuthenticationManager) {
        http.csrf(ServerHttpSecurity.CsrfSpec::disable)
                .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
                .authenticationManager(reactiveAuthenticationManager)
                .exceptionHandling()
                .authenticationEntryPoint(customHttpBasicServerAuthenticationEntryPoint) //TODO 自定义 authenticationEntryPoint
                .accessDeniedHandler(new ServerAccessDeniedHandler() {
                    @Override
                    public Mono<Void> handle(ServerWebExchange exchange, AccessDeniedException denied) {
                        exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                        return exchange.getResponse().writeWith(Mono.just(new DefaultDataBufferFactory().wrap("FORBIDDEN".getBytes())));
                    }
                }).and()
                .securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
                .authorizeExchange()
                .pathMatchers(excludeAuthPages).permitAll() //TODO 白名单
                .pathMatchers(HttpMethod.OPTIONS).permitAll()
                .anyExchange()
                .authenticated()
                .and()
                .formLogin().loginPage("/auth/login")
                .authenticationSuccessHandler(authenticationSuccessHandler) //TODO 自定义authenticationSuccessHandler
                .authenticationFailureHandler(authenticationFailHandle) //TODO 自定义authenticationFailureHandler
                .and()
                .logout().logoutUrl("/auth/logout")
                .logoutSuccessHandler(timingLogoutSuccessHandle) //TODO 自定义logoutSuccessHandler
                .and()
                .addFilterAt(new JwtTokenAuthenticationFilter(), SecurityWebFiltersOrder.HTTP_BASIC) // TODO 增加tokenFilter
        ;
        return http.build();
    }

}
