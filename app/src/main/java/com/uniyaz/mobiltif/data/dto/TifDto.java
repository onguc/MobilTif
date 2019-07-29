package com.uniyaz.mobiltif.data.dto;

import com.google.gson.annotations.SerializedName;

public class TifDto<T> {

    @SerializedName("vysTasinirDemirbasIdList")
    private Long[] idEnvanterList;
    private String islemTuru;
    @SerializedName(value = "islem", alternate = {"vysTasinirTransferRequestDto,vysTasinirZimmetRequestDto"})
    private T vysTasinirTransferRequestDto;

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

    public T getVysTasinirTransferRequestDto() {
        return vysTasinirTransferRequestDto;
    }

    public void setVysTasinirTransferRequestDto(T vysTasinirTransferRequestDto) {
        this.vysTasinirTransferRequestDto = vysTasinirTransferRequestDto;
    }
}