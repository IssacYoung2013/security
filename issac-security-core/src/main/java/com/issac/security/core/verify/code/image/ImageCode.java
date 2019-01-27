package com.issac.security.core.verify.code.image;

import com.issac.security.core.verify.code.VerifyCode;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 *
 * author:  ywy
 * date:    2019-01-26
 * desc:
 */
@Getter
@Setter
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
