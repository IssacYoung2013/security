package com.issac.security.core.properties;

import lombok.Data;

/**
 *
 * author:  ywy
 * date:    2019-01-28
 * desc:
 */
@Data
public class QQProperties {

    /**
     * Application id.
     */
    private String appId;

    /**
     * Application secret.
     */
    private String appSecret;

    private String providerId = "qq";
}
