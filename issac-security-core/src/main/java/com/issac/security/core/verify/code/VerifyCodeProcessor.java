package com.issac.security.core.verify.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 *
 * author:  ywy
 * date:    2019-01-27
 * desc: 校验码处理器，封装不同校验码的处理逻辑
 */
public interface VerifyCodeProcessor {

    /**
     * 验证码放入session时的前缀
     */
    String SESSION_KEY_PREFIX = "SESSION_KEY_IMAGE_CODE";

    /**
     * 创建校验码
     * @param request
     * @throws Exception
     */
    void create(ServletWebRequest request) throws Exception;
}
