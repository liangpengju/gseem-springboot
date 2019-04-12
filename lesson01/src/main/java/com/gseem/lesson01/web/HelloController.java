package com.gseem.lesson01.web;

import com.gseem.lesson01.util.BaseResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
public class HelloController {

    /**
     * 标注了@ApiIgnore的接口不会生成到文档
     * @return
     */
    @ApiIgnore
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String index() {
        return "Hello World";
    }

    @ApiOperation(value = "测试BaseResult")
    @RequestMapping(value = "/base", method = RequestMethod.GET)
    public BaseResult base() {
        return new BaseResult(1,"success","ok");
    }

}
