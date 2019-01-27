package com.issac.security.core.verify.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 *
 * author:  ywy
 * date:    2019-01-27
 * desc:
 */
public interface VerifyCodeGenerator {

    VerifyCode generate(ServletWebRequest request);
}
