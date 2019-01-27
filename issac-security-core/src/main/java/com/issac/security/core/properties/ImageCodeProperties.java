package com.issac.security.core.properties;

import lombok.Data;

/**
 * author:  ywy
 * date:    2019-01-27
 * desc:
 */
@Data
public class ImageCodeProperties extends SmsCodeProperties {

    public ImageCodeProperties() {
        setLength(4);
    }

    private int width = 67;

    private int height = 23;

}
