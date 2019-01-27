package com.issac.security.core.properties;

import lombok.Data;

/**
 * author:  ywy
 * date:    2019-01-27
 * desc:
 */
@Data
public class SmsCodeProperties {

    private int length = 6;

    private int expireIn = 60;

    private String url = "";

}
