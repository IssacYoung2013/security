package com.issac.security.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *
 * author:  ywy
 * date:    2019-01-26
 * desc:
 */
@ConfigurationProperties(prefix = "issac.security")
@Data
public class SecurityProperties {

    private  BrowserProperties browser = new BrowserProperties();

    private VerifyCodeProperties code = new VerifyCodeProperties();

}
