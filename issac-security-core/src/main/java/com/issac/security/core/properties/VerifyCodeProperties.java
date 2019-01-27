package com.issac.security.core.properties;

import lombok.Data;

/**
 *
 * author:  ywy
 * date:    2019-01-27
 * desc:
 */
@Data
public class VerifyCodeProperties {

    private ImageCodeProperties image = new ImageCodeProperties();

    private SmsCodeProperties sms = new SmsCodeProperties();

}
