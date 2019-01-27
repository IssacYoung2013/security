package com.issac.security.browser.support;

import lombok.Data;

/**
 *
 * author:  ywy
 * date:    2019-01-26
 * desc:
 */
@Data
public class SimpleResponse {

    public SimpleResponse(Object content) {
        this.content = content;
    }

    private Object content;
}
