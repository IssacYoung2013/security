package com.issac.security.core.verify.code;

import com.issac.security.core.properties.SecurityProperties;
import lombok.Data;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * author:  ywy
 * date:    2019-01-27
 * desc:
 */
@Component("smsCodeGenerator")
@Data
public class SmsCodeGenerator implements VerifyCodeGenerator {

    @Autowired
    SecurityProperties securityProperties;

    @Override
    public VerifyCode generate(ServletWebRequest request) {
        String code = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
        return new VerifyCode(code,securityProperties.getCode().getSms().getExpireIn());
    }

}
