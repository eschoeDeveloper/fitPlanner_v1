package com.fitplanner.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * package     : com.fitplanner.core.config
 * file        : RootConfig
 * author      : choeuiseung
 * date        : 2022/10/22
 * description
 * =======================================================
 * DATE                   AUTHOR                    NOTE
 * =======================================================
 * 2022/10/22                choeuiseung                 최초 생성
 * =======================================================
 */
@Configuration
@Import(value= { DatabaseConfig.class, JasyptConfig.class } )
public class RootConfig {
}
