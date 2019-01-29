package com.issac.security.core.social.qq.connect;

import com.issac.security.core.social.qq.api.QQ;
import com.issac.security.core.social.qq.api.QQImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Template;

/**
 * author:  ywy
 * date:    2019-01-27
 * desc:
 */
public class QQServicecProvider extends AbstractOAuth2ServiceProvider<QQ> {

    private String appId;

    public static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";

    public static final String URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";

    public QQServicecProvider(String appId, String appSecret) {
        super(new OAuth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESS_TOKEN));
    }

    @Override
    public QQ getApi(String accessToken) {
        return new QQImpl(accessToken, appId);
    }
}
