package com.uniyaz.mobiltif.data.domain;

/**
 * Created by İrfan Öngüç on 19.05.2019
 */

public class LoginInfo extends Info {
    private String authorizationTicket;

    public String getAuthorizationTicket() {
        return authorizationTicket;
    }

    public void setAuthorizationTicket(String authorizationTicket) {
        this.authorizationTicket = authorizationTicket;
    }
}
