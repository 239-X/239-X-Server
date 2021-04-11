package com.minimal.controller.test;

import com.minimal.common.api.dto.TestDto;
import com.minimal.common.sdk.utils.JsonUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 测试控制类
 * @author linzhiqiang
 * @date 2020/3/19
 */

@Controller
//@RequestMapping(value = "", produces = {"text/html;charset=UTF-8;", "application/json;charset=UTF-8;"})
public class TestController {

    @RequestMapping(value = "testDemo", method = RequestMethod.POST)
    @ResponseBody
    public TestDto testDemo(@RequestBody TestDto testDto) {
        return testDto;
    }
}