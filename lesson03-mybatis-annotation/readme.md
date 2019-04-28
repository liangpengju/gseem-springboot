# SpringBoot集成MyBatis Annotation注解版

# yml配置
注解版在 application.yml中 只需要指明实体类的包路径即可
```yaml
#数据源
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/gseem?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&useSSL=true
    username: root
    password: 123456
    driver-class: com.mysql.cj.jdbc.Driver

#mybatis  配置文件、mapper-xml文件
mybatis:
  type-aliases-package: com.gseem.lesson03.entity
```

## 注解参数传递方式

1. 直接使用`#{id,jdbcType=BIGINT}`来接收同名的参数
```java
@Select({
            "select",
            "id, name, password, age, sex, birthday, create_time, update_time, status",
            "from user",
            "where id = #{id,jdbcType=BIGINT}"
})
User selectByPrimaryKey(Long id);
```
2. 使用@Param指定参数名称
```java
 @Select("SELECT * FROM user WHERE name = #{name}")
 List<User> findListByName(@Param("name") String name);
```

3. 使用Bean对象来接收参数

```java
 @Insert({
        "insert into user (id, name, ",
        "password, age, sex, ",
        "birthday, create_time, ",
        "update_time, status)",
        "values (#{id,jdbcType=BIGINT}, #{name,jdbcType=VARCHAR}, ",
        "#{password,jdbcType=VARCHAR}, #{age,jdbcType=INTEGER}, #{sex,jdbcType=TINYINT}, ",
        "#{birthday,jdbcType=DATE}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{updateTime,jdbcType=TIMESTAMP}, #{status,jdbcType=INTEGER})"
    })
    int insert(User record);
```

4. 使用Map来传递多个参数
```java
@Select("SELECT * FROM user WHERE name = #{name} AND sex = #{sex}")
List<User> findListByNameAndSex(Map<String, Object> map);
```

## MyBatis Annotation注解说明

**@Select 注解**
主要在查询的时候使用，查询类的注解

**@Results和 @Result注解**
@Select查询返回结果

```java
  @Select({
            "select",
            "id, name, password, age, sex, birthday, create_time, update_time, status",
            "from user",
            "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
            @Result(column="age", property="age", jdbcType=JdbcType.INTEGER),
            @Result(column="sex", property="sex", jdbcType=JdbcType.TINYINT),
            @Result(column="birthday", property="birthday", jdbcType=JdbcType.DATE),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="status", property="status", jdbcType=JdbcType.INTEGER)
    })
    User selectByPrimaryKey(Long id);
```

**@Insert 注解**
@Insert注解在插入数据入库时使用，直接传入实体类会自动解析属性到对应的值

**@Update 注解**
@Update，所有的更新操作 SQL 都可以使用 @Update。

**@Delete 注解**
@Delete 处理数据删除。

允许构建动态 SQL。这些备选的 SQL 注解允许你指定类名和返回在运行时执行的 SQL 语句的方法。
当执行映射语句的时候，MyBatis 会实例化类并执行方法，类和方法就是填入了注解的值。
你可以把已经传递给映射方法了的对象作为参数

**@InsertProvider注解**
```java
@InsertProvider(type=UserSqlProvider.class, method="insertSelective")
int insertSelective(User record);
```
**@UpdateProvider注解**
```java
 @UpdateProvider(type=UserSqlProvider.class, method="updateByPrimaryKeySelective")
int updateByPrimaryKeySelective(User record);
```
**@DeleteProvider注解**

**@SelectProvider注解**
```java
@SelectProvider(type = UserSqlProvider.class, method = "findList")
    List<User> findList(User user);
```

更多注解说明请查看
http://www.mybatis.org/mybatis-3/zh/java-api.html