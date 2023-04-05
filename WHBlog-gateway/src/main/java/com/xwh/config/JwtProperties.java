package com.xwh.config;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

/**
 * @author 陈方银
 * @date 2023/4/5
 * @since 1.0
 */


@Data
@Configuration
public class JwtProperties {

    private String secretKey;
    private Integer validateInMs;

}
