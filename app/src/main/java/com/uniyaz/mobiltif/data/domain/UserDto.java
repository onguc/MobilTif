package com.uniyaz.mobiltif.data.domain;

/**
 * Created by İrfan Öngüç on 19.05.2019
 */

public class UserDto {
    private String username;
    private String password;
    private Boolean isChecked;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public final String getServicePrefix() {
        return "http://cas.uni-yaz.com:8071/cas/v1/tickets";
    }

    public Boolean getChecked() {
        return isChecked;
    }

    public void setChecked(Boolean checked) {
        isChecked = checked;
    }
}
