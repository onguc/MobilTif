package com.uniyaz.mobiltif.data.domain;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;


/**
 * Created by ONGUC on 21.07.2018.
 */

public class ResponseInfo<T> {
    @SerializedName("status_code")
    private int statusCode;
    @SerializedName("status_info")
    private String statusInfo;
    @SerializedName("success")
    private Boolean success;

    @SerializedName(value = "response", alternate = {"sbsMuhatapDtoList","vysTifIslemResponseDto","pbsPersonelBilgileriDtoList","vysTasinirAmbarDtoList","vysTasinirDemirbasDto", "vysTasinirDemirbasDtoList", "demirbasImageContent", "vysVarlikLokasyonDto", "vysSayimOdaDtoList"})
    private T response;

    @SerializedName("pages")
    public HashMap pages;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusInfo() {
        return statusInfo;
    }

    public void setStatusInfo(String statusInfo) {
        this.statusInfo = statusInfo;
    }

    public Boolean getSuccess() {
        if (success == null) return false;
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    public HashMap getPages() {
        return pages;
    }

    public void setPages(HashMap pages) {
        this.pages = pages;
    }
}