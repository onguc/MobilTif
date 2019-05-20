package com.uniyaz.mobiltif.data.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ONGUC on 17.04.2018.
 */

public class User extends BaseModel {

    public final static String TABLE_NAME = "User";
    public final static String keyId = "id";
    public final static String keyName = "name";
    public final static String keySurname = "surname";
    public final static String keyUserName = "username";
    public final static String keyEmail = "e_mail";
    public final static String keyPassword = "password";
    public final static String keyToken = "token";

    private Long id;
    private String name;
    private String surname;
    private String username;

    @SerializedName("e_mail")
    private String email;
    private String password;
    private String token;

    @Override
    public String getIdString() {
        return String.valueOf(id);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isNull() {
        return getIdString() == null || getToken() == null;
    }
}
