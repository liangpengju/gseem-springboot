# SpringBoot集成JPA使用Druid多数据源

因为 Druid 官方还没有针对 Spring Boot 2.0 进行优化，在某些场景下使用就会出现问题，
比如在 JPA 多数据源的情况下直接使用 Druid 提供的 druid-spring-boot-starter 就会报错，
我们就使用 Druid 的原生包进行封装。

## 添加依赖
```xml
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid</artifactId>
    <version>1.1.10</version>
</dependency>
<dependency>
    <groupId>log4j</groupId>
    <artifactId>log4j</artifactId>
    <version>1.2.17</version>
</dependency>
```

## 多数据源配置
primary是主数据源，以`spring.datasource.druid.one`开头
secondary是第二个数据源，以`spring.datasource.druid.two`开头
```properties

spring.application.name=lesson04-jpa

# logback配置
logging.config=classpath:logback-spring.xml
logging.path=d:/gseem

#primary
spring.datasource.druid.one.url=jdbc:mysql://localhost:3306/gseem?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&useSSL=true
spring.datasource.druid.one.username=root
spring.datasource.druid.one.password=123456
spring.datasource.druid.one.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.druid.one.initialSize=3
spring.datasource.druid.one.minIdle=3
spring.datasource.druid.one.maxActive=10

#secondary
spring.datasource.druid.two.url=jdbc:mysql://localhost:3306/demo?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&useSSL=true
spring.datasource.druid.two.username=root
spring.datasource.druid.two.password=123456
spring.datasource.druid.two.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.druid.two.initialSize=6
spring.datasource.druid.two.minIdle=20
spring.datasource.druid.two.maxActive=30

#配置获取连接等待超时的时间
spring.datasource.druid.maxWait=60000
#配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
spring.datasource.druid.timeBetweenEvictionRunsMillis=60000
#配置一个连接在池中最小生存的时间，单位是毫秒
spring.datasource.druid.minEvictableIdleTimeMillis=600000
spring.datasource.druid.maxEvictableIdleTimeMillis=900000
spring.datasource.druid.validationQuery=SELECT 1 FROM DUAL
#y检测连接是否有效
spring.datasource.druid.testWhileIdle=true
#是否在从池中取出连接前进行检验连接池的可用性
spring.datasource.druid.testOnBorrow=false
#是否在归还到池中前进行检验连接池的可用性
spring.datasource.druid.testOnReturn=false
# 是否打开PSCache，
spring.datasource.druid.poolPreparedStatements=true
#并且指定每个连接上PSCache的大小
spring.datasource.druid.maxPoolPreparedStatementPerConnectionSize=20
#配置监控统计拦截的filters
spring.datasource.druid.filters=stat,wall,log4j
#通过connectProperties属性来打开mergeSql功能；慢SQL记录
spring.datasource.druid.connectionProperties=druid.stat.mergeSql=true;druid.stat.slowSqlMillis=600

spring.jpa.properties.hibernate.hbm2ddl.auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5InnoDBDialect
#sql输出
spring.jpa.show-sql=true
#format一下sql进行输出
spring.jpa.properties.hibernate.format_sql=true

```

## 定义Druid配置类
定义 DruidConfig 来加载所有的公共配置项，再分别定义 DruidOneConfig 来加载数据源 1 的配置项，并继承 DruidConfig；
再定义一个 DruidTwoConfig 来加载数据源 2 的配置项并继承 DruidConfig。

## 创建类 DruidDBConfig 在启动的时候注入配置的多数据源信息
创建类 DruidDBConfig 在启动的时候注入配置的多数据源信息。
```java
package com.gseem.lesson04.config.druid;

import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.sql.SQLException;
import javax.sql.DataSource;

/**
 * @author liangpengju-ds
 */
@Configuration
public class DruidDBConfig {
    private Logger logger = LoggerFactory.getLogger(DruidDBConfig.class);

    @Autowired
    private DruidConfig druidOneConfig;
    @Autowired
    private DruidConfig druidTwoConfig;
    @Autowired
    private DruidConfig druidConfig;
    
    @Bean(name = "primaryDataSource")
    @Primary
    public DataSource dataSource() {
        return initDruidDataSource(druidOneConfig);
    }

    @Bean(name = "secondaryDataSource")
    public DataSource secondaryDataSource() {
        return initDruidDataSource(druidTwoConfig);
    }

    private DruidDataSource initDruidDataSource(DruidConfig config) {
        DruidDataSource datasource = new DruidDataSource();
        logger.info("url:"+config.getUrl());
        datasource.setUrl(config.getUrl());
        datasource.setUsername(config.getUsername());
        datasource.setPassword(config.getPassword());
        datasource.setDriverClassName(config.getDriverClassName());
        datasource.setInitialSize(config.getInitialSize());
        datasource.setMinIdle(config.getMinIdle());
        datasource.setMaxActive(config.getMaxActive());

        // common config
        datasource.setMaxWait(druidConfig.getMaxWait());
        datasource.setTimeBetweenEvictionRunsMillis(druidConfig.getTimeBetweenEvictionRunsMillis());
        datasource.setMinEvictableIdleTimeMillis(druidConfig.getMinEvictableIdleTimeMillis());
        datasource.setMaxEvictableIdleTimeMillis(druidConfig.getMaxEvictableIdleTimeMillis());
        datasource.setValidationQuery(druidConfig.getValidationQuery());
        datasource.setTestWhileIdle(druidConfig.isTestWhileIdle());
        datasource.setTestOnBorrow(druidConfig.isTestOnBorrow());
        datasource.setTestOnReturn(druidConfig.isTestOnReturn());
        datasource.setPoolPreparedStatements(druidConfig.isPoolPreparedStatements());
        datasource.setMaxPoolPreparedStatementPerConnectionSize(druidConfig.getMaxPoolPreparedStatementPerConnectionSize());
        try {
            datasource.setFilters(druidConfig.getFilters());
        } catch (SQLException e) {
            logger.error("druid configuration initialization filter : {0}", e);
        }
        datasource.setConnectionProperties(druidConfig.getConnectionProperties());

        return datasource;
    }
}
```

## 开启监控页面
创建配置类DruidConfiguration，手动开启监控、配置统计相关内容。
```java
package com.gseem.lesson04.config.druid;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liangpengju-ds
 */
@Configuration
public class DruidConfiguration {
    @Bean
    public ServletRegistrationBean<StatViewServlet> druidStatViewServlet() {
        ServletRegistrationBean<StatViewServlet> servletRegistrationBean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        servletRegistrationBean.addInitParameter("loginUsername", "admin");
        servletRegistrationBean.addInitParameter("loginPassword", "admin");
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<WebStatFilter> druidStatFilter() {
        FilterRegistrationBean<WebStatFilter> filterRegistrationBean = new FilterRegistrationBean<>(new WebStatFilter());
        filterRegistrationBean.setName("DruidWebStatFilter");
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }
}
```

配置完成后，重启启动项目访问地址 http://localhost:8080/druid/sql.html 
就可以看到有两个数据源的 SQL 操作语句，证明多数据源 SQL 监控配置成功。




