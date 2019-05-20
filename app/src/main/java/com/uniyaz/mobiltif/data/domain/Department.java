package com.uniyaz.mobiltif.data.domain;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

/**
 * Created by İrfan Öngüç on 19.05.2019
 */

public class Department extends BaseModel {
    public static final String TABLE_NAME = "Mudurluk";
    public final static String keyId = "id";
    public final static String keyName = "adi";
    public final static String keyCode = "kod";

    private Integer id;
    @SerializedName("adi")
    private String name;
    @SerializedName("kod")
    private String code;


    @Override
    public String getIdString() {
        return String.valueOf(id);
    }

    public Integer getIdInt() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Department)) return false;
        Department that = (Department) o;
        return Objects.equals(getCode(), that.getCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCode());
    }
}
