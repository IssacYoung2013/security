package com.issac.security.core.social.qq.api;

import lombok.Data;

/**
 *
 * author:  ywy
 * date:    2019-01-27
 * desc:
 */
@Data
public class QQUserInfo {

    /**
     * 返回码
     */
    private int ret;

    private String msg;

    private String nickname;

    private String figureUrl;

    private String figureUrl_1;

    private String figureUrl_2;

    /**
     * 40*40像素头像  所有用户都有
     */
    private String figureUrl_qq_1;

    private String figureUrl_qq_2;

    private String gender;

    private String is_yellow_vip;

    private String vip;

    private String yellow_vip_level;

    private String level;

    private String is_yellow_year_vip;

    private String openId;

}
