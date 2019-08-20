package com.uniyaz.mobiltif.data.domain;

/**
 * Created by İrfan Öngüç on 18.08.2019
 */

public class Info {
    private Boolean success;
    private String resultCode;
    private String resultMessage;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }
}
