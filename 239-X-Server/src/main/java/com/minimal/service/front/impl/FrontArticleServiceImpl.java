package com.minimal.service.front.impl;

import com.minimal.mapper.front.FrontArticleMapper;
import com.minimal.service.front.FrontArticleService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 前端文章服务
 * @author linzhiqiang
 * @date 2021-05-15
 */
@Service
@Log4j2
public class FrontArticleServiceImpl implements FrontArticleService {
    @Autowired
    private FrontArticleMapper frontArticleMapper;

}