package com.uniyaz.mobiltif.data.domain;

/**
 * Created by İrfan Öngüç on 19.05.2019
 */

public class LoginInfo {
    private Boolean success;
    private String authorizationTicket;
    private String resultCode;
    private String resultMessage;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getAuthorizationTicket() {
        return authorizationTicket;
    }

    public void setAuthorizationTicket(String authorizationTicket) {
        this.authorizationTicket = authorizationTicket;
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
