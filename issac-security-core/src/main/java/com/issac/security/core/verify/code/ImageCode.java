package com.issac.security.core.verify.code;

import lombok.Data;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 *
 * author:  ywy
 * date:    2019-01-26
 * desc:
 */
@Data
public class ImageCode  extends VerifyCode {

    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
        super(code,expireTime);
        this.image = image;
    }

    public ImageCode(BufferedImage image, String code,int expireIn) {
        super(code,expireIn);
        this.image = image;
    }

    private BufferedImage image;
}
