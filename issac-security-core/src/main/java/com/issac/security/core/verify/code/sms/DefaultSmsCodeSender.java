package com.issac.security.core.verify.code.sms;

/**
 *
 * author:  ywy
 * date:    2019-01-27
 * desc:
 */
public class DefaultSmsCodeSender implements SmsCodeSender {
    @Override
    public void send(String mobile, String code) {
        System.out.println("向手机" + mobile + "发送短信验证码" + code);
    }
}
