
package com.qwiktrips.user.app.model.signup;

import com.google.gson.annotations.Expose;


public class SignupData {

    @Expose
    private Object error;
    @Expose
    private Boolean success;

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

}
