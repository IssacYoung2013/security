package com.issac.exceptions;

/**
 *
 * author:  ywy
 * date:    2019-01-24
 * desc:
 */
public class UserNotExistException extends RuntimeException {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserNotExistException(String id) {
        super("user not exist");
        this.id = id;
    }
}
