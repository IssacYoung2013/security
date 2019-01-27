package com.issac.security.core.verify.code.image;

import com.issac.security.core.verify.code.impl.AbstractVerifyCodeProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;

/**
 *
 * author:  ywy
 * date:    2019-01-27
 * desc: 图片验证码处理器
 */
@Component("imageCodeProcessor")
public class ImageCodeProcessor extends AbstractVerifyCodeProcessor<ImageCode>{

    /**
     * 发送图形验证码，将其写到响应中
     * @param request
     * @param verifyCode
     * @throws Exception
     */
    @Override
    protected void send(ServletWebRequest request, ImageCode verifyCode) throws Exception {
        ImageIO.write(verifyCode.getImage(),"JPEG",request.getResponse().getOutputStream());

    }
}
