package com.issac.security.core.verify.code.sms;

import com.issac.security.core.verify.code.VerifyCode;
import com.issac.security.core.verify.code.impl.AbstractVerifyCodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 *
 * author:  ywy
 * date:    2019-01-27
 * desc: 短信验证码处理器
 */
@Component("smsCodeProcessor")
public class SmsCodeProcessor extends AbstractVerifyCodeProcessor<VerifyCode> {

    /**
     * 短信验证码发送器
     */
    @Autowired
    private SmsCodeSender smsCodeSender;

    @Override
    protected void send(ServletWebRequest request, VerifyCode verifyCode) throws Exception {
        String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(),"mobile");
        smsCodeSender.send(mobile,verifyCode.getCode());
    }
}
