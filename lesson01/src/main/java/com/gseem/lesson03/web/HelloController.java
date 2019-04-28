package com.gseem.lesson03.web;

import com.gseem.lesson03.util.BaseResult;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author liangpengju-ds
 */
@Slf4j
@RestController
public class HelloController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 标注了@ApiIgnore的接口不会生成到文档
     * @return
     */
    @ApiIgnore
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String index() {
        log.trace("@Slf4j日志输出 trace");
        log.debug("@Slf4j日志输出 debug");
        log.info("@Slf4j日志输出 info");
        log.warn("@Slf4j日志输出 warn");
        log.error("@Slf4j日志输出 error");
        logger.info("====================");
        logger.trace("logger日志输出 trace");
        logger.debug("logger日志输出 debug");
        logger.info("logger日志输出 info");
        logger.warn("logger日志输出 warn");
        logger.error("logger日志输出 error");
        return "Hello World";
    }

    @ApiOperation(value = "测试BaseResult")
    @RequestMapping(value = "/base", method = RequestMethod.GET)
    public BaseResult base() {
        return new BaseResult(1,"success","ok");
    }

}
