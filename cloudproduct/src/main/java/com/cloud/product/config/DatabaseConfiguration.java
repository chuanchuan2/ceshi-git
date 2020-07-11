package com.cloud.product.config;


import com.alibaba.druid.pool.DruidDataSource;
import io.seata.rm.datasource.DataSourceProxy;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

//Seata分布式事务的数据源代理
@Configuration
public class DatabaseConfiguration {

   /*private final ApplicationContext applicationContext;

    public DatabaseConfiguration(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }*/
   @Bean
    @ConfigurationProperties(prefix = "spring.datasource.ds0")
    public DruidDataSource ds0(){
        DruidDataSource druidDataSource=new DruidDataSource();
        return druidDataSource;
    }

    @Primary
    @Bean
    public DataSource DataSource(DruidDataSource ds0){
        DataSourceProxy pds0=new DataSourceProxy(ds0);
        return pds0;
    }
}
