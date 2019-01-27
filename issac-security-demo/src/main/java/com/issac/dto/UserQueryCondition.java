package com.issac.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * author:  ywy
 * date:    2019-01-23
 * desc:
 */
@Data
public class UserQueryCondition {

    @ApiModelProperty(value = "用户姓名")
    private String username;

    @ApiModelProperty(value = "用户年龄起")
    private int age;

    @ApiModelProperty(value = "用户年龄止")
    private int ageTo;

    private String xxx;
}
