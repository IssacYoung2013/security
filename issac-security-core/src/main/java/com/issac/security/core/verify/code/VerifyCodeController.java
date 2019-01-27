package com.issac.security.core.verify.code;

import com.issac.security.core.properties.SecurityProperties;
import com.issac.security.core.verify.code.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;
import java.util.Random;

/**
 *
 * author:  ywy
 * date:    2019-01-26
 * desc:
 */
@RestController
public class VerifyCodeController {

    @Autowired
    private Map<String,VerifyCodeProcessor> verifyCodeProcessors;

    @GetMapping("/code/{type}")
    public void createCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type) throws Exception {

        verifyCodeProcessors.get(type+ "CodeProcessor").create(new ServletWebRequest(request,response));
    }
}
