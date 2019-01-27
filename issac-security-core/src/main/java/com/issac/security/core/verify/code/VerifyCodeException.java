package com.issac.security.core.verify.code;


import org.springframework.security.core.AuthenticationException;

/**
 *
 * author:  ywy
 * date:    2019-01-26
 * desc:
 */
public class VerifyCodeException extends AuthenticationException {
    public VerifyCodeException(String msg) {
        super(msg);
    }
}
