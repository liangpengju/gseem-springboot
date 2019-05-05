package com.gseem.lesson04.config.druid;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author liangpengju-ds
 */
@Component
@ConfigurationProperties(prefix="spring.datasource.druid")
@Data
public class DruidConfig {

    protected String url;
    protected String username;
    protected String password;
    protected String driverClassName;
    protected int initialSize;
    protected int minIdle;
    protected int maxActive;
    protected int maxWait;
    protected int timeBetweenEvictionRunsMillis;
    protected long minEvictableIdleTimeMillis;
    protected long maxEvictableIdleTimeMillis;
    protected String validationQuery;
    protected boolean testWhileIdle;
    protected boolean testOnBorrow;
    protected boolean testOnReturn;
    protected boolean poolPreparedStatements;
    protected int maxPoolPreparedStatementPerConnectionSize;
    protected String filters;
    protected String connectionProperties;

}
