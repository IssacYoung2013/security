package com.issac.service.impl;

import com.issac.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * author:  ywy
 * date:    2019-01-23
 * desc:
 */
@Service
@Slf4j
public class HelloServiceImpl implements HelloService {

    @Override
    public void greeting() {
        log.info("hello world");
    }
}
