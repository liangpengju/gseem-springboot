# 添加Swagger2依赖
```
<!--springfox-swagger2 -->
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger2</artifactId>
    <version>2.9.2</version>
</dependency>
<!--springfox-swagger-ui -->
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger-ui</artifactId>
    <version>2.9.2</version>
</dependency>
```

# 添加配置类Swagger2Config
在 Swagger2Config类上添加两个注解：
`@Configuration`:启动时加载此类；
`@EnableSwagger2`:表示此项目启用 Swagger API 文档；

```java
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
                .apis(RequestHandlerSelectors.basePackage("com.gseem.springboot.web"))
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
```
# Swagger2 常用注解
Swagger 通过注解表明该接口会生成文档，包括接口名、请求方法、参数、返回信息等，常用注解内容如下：

作用范围 | API	| 使用位置
--|--|--
协议集描述	 | @Api	 | 用于 Controller 类上
协议描述	 | @ApiOperation	 | 用在 Controller 的方法上
非对象参数集		 | @ApiImplicitParams		 | 用在 Controller 的方法上
非对象参数描述		 | @ApiImplicitParam		 | 用在 @ApiImplicitParams 的方法里边
响应集		 | @ApiResponses		 | 用在 Controller 的方法上
响应信息参数		 | @ApiResponse		 | 用在 @ApiResponses 里边
描述返回对象的意义		 | @ApiModel		 | 用在返回对象类上
对象属性		 | @ApiModelProperty		 | 用在出入参数对象的字段上





