package com.minimal.controller.test;
import com.minimal.common.sdk.Page;
import com.minimal.entity.model.Config;
import com.minimal.service.impl.ConfigServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author linzhiqiang
 * @date 2019/3/4
 */

@RestController
public class MybatisTestController {
    private Logger logger = LoggerFactory.getLogger(MybatisTestController.class);

    @Autowired(required = false)
    private ConfigServiceImpl configService;

//    @RequestMapping("selectAll")
//    public Object select(){
//        Page<Config> configPage = configService.selectAll();
//        return configPage;
//    }

    @RequestMapping("select")
    public Object select(String id){
        Config config = configService.select(id);
        return config;
    }
}
