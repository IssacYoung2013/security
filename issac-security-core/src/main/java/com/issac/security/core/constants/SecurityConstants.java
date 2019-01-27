package com.issac.security.core.constants;

/**
 *
 * author:  ywy
 * date:    2019-01-27
 * desc:
 */
public interface SecurityConstants {

    /**
     * 默认的用户名密码登录请求url
     */
    String DEFAULT_LOGIN_PROCESSING_URL_FORM = "/authentication/form";

    /**
     * 默认的手机验证码登录请求处理url
     */
    String DEFAULT_LOGIN_PROCESSING_URL_MOBILE = "/authentication/mobile";

    /**
     * 当请求需要身份认证时，默认跳转url
     */
    String DEFAULT_UNAUTHENTICATION_URL = "/authentication/require";

    /**
     * 默认的处理验证码的url前缀
     */
    String DEFAULT_VERIFY_CODE_URL_PREFIX = "/code";

    /**
     * 默认登录页面
     *
     */
    String DEFAILT_LOGIN_PAGE_URL = "/issac-signIn.html";
}
