/*
 *Copyright @2021 Grapefruit. All rights reserved.
 */

package com.grapefruit.shiro.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * 数据库配置
 *
 * @author zhihuangzhang
 * @version 1.0
 * @date 2021-06-24 11:24 下午
 */
@Configuration
public class DatabaseConfig {
    @Bean
    public DataSource getDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/grapefruit?useUnicode=true&characterEncoding=UTF-8&useSSL" +
                "=false");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        return dataSource;
    }
}
