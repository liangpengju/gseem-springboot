package com.gseem.lesson03.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2注解配置类
 * http://localhost:8080/swagger-ui.html
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    /**
     * 创建Bean，返回Docket（Swagger API 摘要信息）
     * @return
     */
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.gseem.lesson03.web"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 配置页面展示的基本信息包括，标题、描述、版本、服务条款、联系方式等
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot中使用Swagger2构建RESTful APIs")
                .description("更多Spring Boot相关文章请关注：http://www.gseem.com/")
                .termsOfServiceUrl("http://www.gseem.com/")
                .contact(new Contact("feiyue","www.gseem.com","feiyue.xk@qq.com"))
                .version("1.0")
                .build();
    }


}


















