package com.issac.web.async;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;

/**
 * author:  ywy
 * date:    2019-01-24
 * desc:
 */
@RestController
@Slf4j
public class AsyncController {

    @Autowired
    private MockQueue mockQueue;

    @Autowired
    private DeferredResultHolder deferredResultHolder;

    @RequestMapping("/order")
    public DeferredResult<String> order() throws InterruptedException {
        log.info("主线程开始");
//        Callable<String> result = new Callable<String>() {
//            @Override
//            public String call() throws Exception {
//                log.info("副线程开始");
//                Thread.sleep(1000);
//                log.info("副线程返回");
//                return "success";
//            }
//        };
        DeferredResult<String> result = new DeferredResult<>();
        String orderNumer = RandomStringUtils.randomNumeric(8);
        mockQueue.setPlaceOrder(orderNumer);
        deferredResultHolder.getMap().put(orderNumer,result);

        log.info("主线程返回");
        return result;
    }
}
