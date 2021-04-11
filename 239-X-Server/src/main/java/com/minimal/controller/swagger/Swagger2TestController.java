package com.minimal.controller.swagger;

import com.minimal.common.api.dto.SampleApi;
import com.minimal.common.api.dto.SampleDto;
import com.minimal.common.sdk.Page;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author linzhiqiang
 * @date 2019/3/7
 */
@RestController
@RequestMapping("gateway/samples")
@Api(tags = "示例")
public class Swagger2TestController implements SampleApi{
    private static final Logger logger = LoggerFactory.getLogger(Swagger2TestController.class);

    @Override
    public SampleDto echo(SampleDto dto) {
        return null;
    }

    @Override
    public SampleDto getById(@PathVariable("id") Long id) {
        return null;
    }

    @Override
    public Long create(@RequestBody SampleDto dto) {
        return null;
    }

    @Override
    public boolean update(@PathVariable("id") Long id, @RequestBody SampleDto dto) {
        return false;
    }

    @Override
    public boolean delete(@PathVariable("id") Long id) {
        return false;
    }

    @Override
    public Page<SampleDto> page(@RequestParam("index") int index, @RequestParam(value = "size", defaultValue = "20") int size) {
        return null;
    }
}
