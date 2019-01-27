package com.issac.security.browser;

import com.issac.security.core.authentication.AbstractChannelSecurityConfig;
import com.issac.security.browser.authentication.IssacAuthenticationFailureHandler;
import com.issac.security.browser.authentication.IssacAuthenticationSuccessHandler;
import com.issac.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.issac.security.core.constants.SecurityConstants;
import com.issac.security.core.properties.SecurityProperties;
import com.issac.security.core.verify.code.SmsCodeFilter;
import com.issac.security.core.verify.code.VerifyCodeFilter;
import com.issac.security.core.verify.code.VerifyCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * author:  ywy
 * date:    2019-01-25
 * desc:
 */
@Configuration
public class BrowserSecurityConfig extends AbstractChannelSecurityConfig {

//    @Bean
//    public static NoOpPasswordEncoder passwordEncoder() {
//        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
//    }

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private IssacAuthenticationSuccessHandler issacAuthenticationSuccessHandler;

    @Autowired
    private IssacAuthenticationFailureHandler issacAuthenticationFailureHandler;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailService;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private VerifyCodeSecurityConfig verifyCodeSecurityConfig;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
//        tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        applyPasswordAuthenticationConfig(http);
        
        VerifyCodeFilter verifyCodeFilter = new VerifyCodeFilter();
        verifyCodeFilter.setAuthenticationFailureHandler(issacAuthenticationFailureHandler);
        verifyCodeFilter.setSecurityProperties(securityProperties);
        verifyCodeFilter.afterPropertiesSet();

        SmsCodeFilter smsCodeFilter = new SmsCodeFilter();
        smsCodeFilter.setAuthenticationFailureHandler(issacAuthenticationFailureHandler);
        smsCodeFilter.setSecurityProperties(securityProperties);
        smsCodeFilter.afterPropertiesSet();

        http.apply(verifyCodeSecurityConfig)
                .and()
            .apply(smsCodeAuthenticationSecurityConfig)
                .and()
            .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                .userDetailsService(userDetailService)
                .and()
            .authorizeRequests()
                .antMatchers(
                        SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                        SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                        securityProperties.getBrowser().getLoginPage(),
                        SecurityConstants.DEFAULT_VERIFY_CODE_URL_PREFIX + "/*"
                )
                .permitAll()
            .anyRequest()
            .authenticated()
            .and()
        .csrf().disable();
    }
}
