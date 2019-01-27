package com.issac.security.browser;

import com.issac.security.browser.authentication.IssacAuthenticationFailureHandler;
import com.issac.security.browser.authentication.IssacAuthenticationSuccessHandler;
import com.issac.security.core.properties.SecurityProperties;
import com.issac.security.core.verify.code.VerifyCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * author:  ywy
 * date:    2019-01-25
 * desc:
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

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

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        VerifyCodeFilter verifyCodeFilter = new VerifyCodeFilter();
        verifyCodeFilter.setAuthenticationFailureHandler(issacAuthenticationFailureHandler);
        verifyCodeFilter.setSecurityProperties(securityProperties);
        verifyCodeFilter.afterPropertiesSet();
//        所有的请求都需要身份认证
        http.addFilterBefore(verifyCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
//                .loginPage("/issac-signIn.html")
                .loginPage("/authentication/require")
                .loginProcessingUrl("/authentication/form")
                .successHandler(issacAuthenticationSuccessHandler)
                .failureHandler(issacAuthenticationFailureHandler)
//        http.httpBasic()
                .and()
                .authorizeRequests()
                // 访问这个页面不需要身份认证
//                .antMatchers("/issac-signIn.html").permitAll()
                .antMatchers("/authentication/require",
                        securityProperties.getBrowser().getLoginPage(),
                        "/code/image").permitAll()
                .anyRequest()
                .authenticated()
                .and().csrf().disable();
    }
}
