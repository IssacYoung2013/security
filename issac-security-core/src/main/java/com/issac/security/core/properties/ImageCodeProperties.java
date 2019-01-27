package com.issac.security.core.properties;

import lombok.Data;

/**
 * author:  ywy
 * date:    2019-01-27
 * desc:
 */
@Data
public class ImageCodeProperties {

    private int width = 67;

    private int height = 23;

    private int length = 4;

    private int expireIn = 60;

    private String url;

}
