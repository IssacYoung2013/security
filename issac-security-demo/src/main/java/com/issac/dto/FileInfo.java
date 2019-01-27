package com.issac.dto;

import lombok.Data;

/**
 *
 * author:  ywy
 * date:    2019-01-24
 * desc:
 */
@Data
public class FileInfo {

    public FileInfo(String path) {
        this.path = path;
    }

    private String path;

}
