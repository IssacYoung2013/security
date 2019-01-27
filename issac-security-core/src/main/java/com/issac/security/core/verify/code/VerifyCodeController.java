package com.issac.security.core.verify.code;

import com.issac.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
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
    private VerifyCodeGenerator imageCodeGenerator;


    public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @GetMapping("/code/image")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // 根据随机数生成图片
        ImageCode imageCode = imageCodeGenerator.generate(new ServletWebRequest(request));

        // 将随机数存到 Session
        sessionStrategy.setAttribute(new ServletWebRequest(request),SESSION_KEY,imageCode);

        // 生成图片存在 response outputstream
        ImageIO.write(imageCode.getImage(),"JPEG",response.getOutputStream());
    }

}