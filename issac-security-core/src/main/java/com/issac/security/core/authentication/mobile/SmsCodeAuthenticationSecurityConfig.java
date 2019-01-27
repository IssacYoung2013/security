package com.issac.security.core.authentication.mobile;

import com.issac.security.core.properties.SecurityProperties;
import com.issac.security.core.verify.code.SmsCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 *
 * author:  ywy
 * date:    2019-01-27
 * desc:
 */
@Component
public class SmsCodeAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain,HttpSecurity> {


    @Autowired
    private AuthenticationFailureHandler issacAuthenticationFailureHandler;

    @Autowired
    private AuthenticationSuccessHandler issascAuthenticationSuccessHandler;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void configure(HttpSecurity http) throws Exception {

        SmsCodeAuthenticationFilter smsCodeAuthenticationFilter = new SmsCodeAuthenticationFilter();

        smsCodeAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        smsCodeAuthenticationFilter.setAuthenticationSuccessHandler(issascAuthenticationSuccessHandler);
        smsCodeAuthenticationFilter.setAuthenticationFailureHandler(issacAuthenticationFailureHandler);

        SmsCodeAuthenticationProvider smsCodeAuthenticationProvider = new SmsCodeAuthenticationProvider();
        smsCodeAuthenticationProvider.setUserDetailsService(userDetailsService);

        http.authenticationProvider(smsCodeAuthenticationProvider)
                .addFilterAfter(smsCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
