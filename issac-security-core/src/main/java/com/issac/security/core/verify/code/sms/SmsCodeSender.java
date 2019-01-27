package com.issac.security.core.verify.code.sms;

/**
 *
 * author:  ywy
 * date:    2019-01-27
 * desc:
 */
public interface SmsCodeSender {

    void send(String mobile,String code);
}
