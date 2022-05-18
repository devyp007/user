package com.qwiktrips.user.app.ui.exception;

public class RequiredFieldExceptions extends Exception {

    public RequiredFieldExceptions(String s) {
        super(s);
    }

    public RequiredFieldExceptions() {
        super("All fields are required!");
    }

}
