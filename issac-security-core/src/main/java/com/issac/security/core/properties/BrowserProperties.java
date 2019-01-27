package com.issac.security.core.properties;

import lombok.Data;

/**
 *
 * author:  ywy
 * date:    2019-01-26
 * desc:
 */
@Data
public class BrowserProperties {

    private String loginPage = "/issac-signIn.html";

    private LoginResponseType loginResponseType =  LoginResponseType.JSON;

    private int rememberMeSeconds = 3600;
}
