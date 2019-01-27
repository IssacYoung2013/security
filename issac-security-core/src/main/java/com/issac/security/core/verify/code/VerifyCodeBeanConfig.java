package com.issac.security.core.verify.code;

import com.issac.security.core.properties.SecurityProperties;
import com.issac.security.core.verify.code.image.ImageCodeGenerator;
import com.issac.security.core.verify.code.sms.DefaultSmsCodeSender;
import com.issac.security.core.verify.code.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * author:  ywy
 * date:    2019-01-27
 * desc:
 */
@Configuration
public class VerifyCodeBeanConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    @ConditionalOnMissingBean(name = "imageCodeGenerator") // 条件装配
    public VerifyCodeGenerator imageCodeGenerator() {
        ImageCodeGenerator imageCodeGenerator = new ImageCodeGenerator();
        imageCodeGenerator.setSecurityProperties(securityProperties);
        return imageCodeGenerator;
    }

    @Bean
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeSender smsCodeSender() {
        return new DefaultSmsCodeSender();
    }
}
