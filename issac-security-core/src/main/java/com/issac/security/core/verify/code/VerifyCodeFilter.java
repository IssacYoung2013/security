package com.issac.security.core.verify.code;

import com.issac.security.core.constants.SecurityConstants;
import com.issac.security.core.properties.SecurityProperties;
import com.issac.security.core.verify.code.image.ImageCode;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * author:  ywy
 * date:    2019-01-26
 * desc:
 */
@Slf4j
@Data
@Component("verifyCodeFilter")
public class VerifyCodeFilter extends OncePerRequestFilter implements InitializingBean {

    /**
     * Session 操作类
     */
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    private AuthenticationFailureHandler authenticationFailureHandler;

    private Set<String> urls = new HashSet<>();

    /**
     * 存放所有需要校验验证码的url
     */
    private Map<String,VerifyCodeType> urlMap = new HashMap<>();

    /**
     * 系统配置信息
     */
    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 验证请求url与配置url是否匹配的工具类
     */
    private AntPathMatcher pathMatcher = new AntPathMatcher();

    /**
     * 初始化要拦截的url配置信息
     *
     * @throws ServletException
     */
    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        urlMap.put(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM,VerifyCodeType.IMAGE);
        addUrlMap(securityProperties.getCode().getImage().getUrl(),VerifyCodeType.IMAGE);

        urlMap.put(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,VerifyCodeType.SMS);
        addUrlMap(securityProperties.getCode().getSms().getUrl(),VerifyCodeType.SMS);
    }

    /**
     * 将系统中配置的需要校验码的URL根据校验类型放入map
     * @param urlString
     * @param type
     */
    protected void addUrlMap(String urlString,VerifyCodeType type) {
        if(StringUtils.isNotBlank(urlString)) {
            String[] configUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(securityProperties.getCode().getImage().getUrl(), ",");
            for (String configUrl :
                    configUrls) {
                urlMap.put(configUrl,type);
            }
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

//        if (StringUtils.equals("/authentication/form", request.getRequestURI())
//                && StringUtils.equalsIgnoreCase(request.getMethod(), "post")) {
        boolean action = false;
        for (String url :
                urls) {
            if (pathMatcher.match(url, request.getRequestURI())) {
                action = true;
            }
        }
        if (action) {
            try {
                validate(new ServletWebRequest(request));
                log.info("验证码校验通过");
            } catch (VerifyCodeException e) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    private void validate(ServletWebRequest request) throws ServletRequestBindingException {

        ImageCode codeInSession = (ImageCode) sessionStrategy.getAttribute(request, VerifyCodeProcessor.SESSION_KEY_PREFIX + "IMAGE");

        String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), "imageCode");

        if (StringUtils.isBlank(codeInRequest)) {
            throw new VerifyCodeException("验证码的值不能为空");
        }

        if (codeInSession == null) {
            throw new VerifyCodeException("验证码不存在");
        }

        if (codeInSession.isExpired()) {
            sessionStrategy.removeAttribute(request, VerifyCodeProcessor.SESSION_KEY_PREFIX + "IMAGE");
            throw new VerifyCodeException("验证码已过期");
        }

        if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
            throw new VerifyCodeException("验证码不匹配");
        }

        sessionStrategy.removeAttribute(request, VerifyCodeProcessor.SESSION_KEY_PREFIX + "IMAGE");
    }
}
