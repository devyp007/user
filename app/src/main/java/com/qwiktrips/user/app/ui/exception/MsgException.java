package com.qwiktrips.user.app.ui.exception;

public class MsgException extends Exception {
    private int code;

    public MsgException(String message, int code) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
