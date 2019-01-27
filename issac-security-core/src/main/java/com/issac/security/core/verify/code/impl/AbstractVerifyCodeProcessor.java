package com.issac.security.core.verify.code.impl;

import com.issac.security.core.verify.code.VerifyCodeGenerator;
import com.issac.security.core.verify.code.VerifyCodeProcessor;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Map;

/**
 *
 * author:  ywy
 * date:    2019-01-27
 * desc:
 */
public abstract class AbstractVerifyCodeProcessor<C> implements VerifyCodeProcessor {

    /**
     * 操作Session工具类
     */
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    /**
     * 收集系统中所有的 {@link VerifyCodeGenerator} 接口的实现 依赖搜索
     */
    @Autowired
    private Map<String,VerifyCodeGenerator> verifyCodeGenerators;

    /**
     * @see VerifyCodeProcessor#create(ServletWebRequest)
     * @param request
     * @throws Exception
     */
    @Override
    public void create(ServletWebRequest request) throws Exception {

        // 根据随机数生成图片
        C verifyCode = generate(request);

        // 将随机数存到 Session
        save(request,verifyCode);

        //  发送
        send(request,verifyCode);
    }

    private void save(ServletWebRequest request, C verifyCode) {
        // 将随机数存到 Session
        sessionStrategy.setAttribute(request,SESSION_KEY,verifyCode);
    }

    /**
     * 生成校验码
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
    private C generate(ServletWebRequest request){
        String type = getProcessorType(request);
        VerifyCodeGenerator verifyCodeGenerator = verifyCodeGenerators.get(type + "CodeGenerator");
        return (C) verifyCodeGenerator.generate(request);
    }

    /**
     * 根据请求url获取校验码类型
     * @param request
     * @return
     */
    private String getProcessorType(ServletWebRequest request){
        return StringUtils.substringAfter(request.getRequest().getRequestURI(),"/code/");
    }

    /**
     * 发送校验码，由子类实现
     * @param request
     * @param verifyCode
     * @throws Exception
     */
    protected abstract void send(ServletWebRequest request,C verifyCode) throws Exception;

}
