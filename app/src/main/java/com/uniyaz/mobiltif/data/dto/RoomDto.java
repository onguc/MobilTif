package com.uniyaz.mobiltif.data.dto;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 * Created by İrfan Öngüç on 19.05.2019
 */

public class RoomDto {

    @SerializedName("etiketNo")
    private Integer qrCode; //etiket No  ayrıca id'dir
    @SerializedName("adi")
    private String name;
    @SerializedName("kbsServisId")
    private Integer idDepartment;
    @SerializedName("odaResimleri")
    private Map<String, byte[]> photoList;

    public Integer getQrCode() {
        return qrCode;
    }

    public void setQrCode(Integer qrCode) {
        this.qrCode = qrCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(Integer idDepartment) {
        this.idDepartment = idDepartment;
    }

    public Map<String, byte[]> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(Map<String, byte[]> photoList) {
        this.photoList = photoList;
    }
}
