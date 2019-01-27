package com.issac.web.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.*;
import java.io.IOException;
import java.util.Date;

/**
 * author:  ywy
 * date:    2019-01-24
 * desc:
 */
@Slf4j
//@Component
public class TimeFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("time filter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("time filter start");
        long start = System.currentTimeMillis();
        filterChain.doFilter(servletRequest, servletResponse);
        log.info("time filter : {}", System.currentTimeMillis() - start);
        log.info("time filter end");
    }

    @Override
    public void destroy() {
        log.info("time filter destory");
    }
}
