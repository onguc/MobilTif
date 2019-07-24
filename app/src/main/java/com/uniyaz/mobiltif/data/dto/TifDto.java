package com.uniyaz.mobiltif.data.dto;

import com.google.gson.annotations.SerializedName;

public class TifDto<T> {

    @SerializedName("vysTasinirDemirbasIdList")
    private Long[] idEnvanterList;
    private String islemTuru;
    private T islem;

    public Long[] getIdEnvanterList() {
        return idEnvanterList;
    }

    public void setIdEnvanterList(Long[] idEnvanterList) {
        this.idEnvanterList = idEnvanterList;
    }

    public String getIslemTuru() {
        return islemTuru;
    }

    public void setIslemTuru(String islemTuru) {
        this.islemTuru = islemTuru;
    }

    public T getIslem() {
        return islem;
    }

    public void setIslem(T islem) {
        this.islem = islem;
    }
}