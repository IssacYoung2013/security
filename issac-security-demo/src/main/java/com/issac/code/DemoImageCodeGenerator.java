package com.issac.code;

import com.issac.security.core.verify.code.ImageCode;
import com.issac.security.core.verify.code.VerifyCodeGenerator;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 *
 * author:  ywy
 * date:    2019-01-27
 * desc:
 */
//@Component("imageCodeGenerator")
public class DemoImageCodeGenerator implements VerifyCodeGenerator{

    @Override
    public ImageCode generate(ServletWebRequest request) {
        System.out.println("更高级的图形验证码生成代码");
        return null;
    }
}
