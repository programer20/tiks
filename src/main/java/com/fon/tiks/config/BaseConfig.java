package com.fon.tiks.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.fon.tiks.mapper")
public class BaseConfig {

}
