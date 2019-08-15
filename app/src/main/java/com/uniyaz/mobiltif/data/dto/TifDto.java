package com.uniyaz.mobiltif.data.dto;

import com.google.gson.annotations.SerializedName;
import com.uniyaz.mobiltif.data.enums.EnumIslemTuru;

public class TifDto<T> {

    @SerializedName("vysTasinirDemirbasIdList")
    private Long[] idEnvanterList;
    private EnumIslemTuru islemTuru;
    @SerializedName(value = "islem", alternate = {"vysTasinirTransferRequestDto,vysTasinirZimmetRequestDto"})
    private T vysTasinirTransferRequestDto;

    public Long[] getIdEnvanterList() {
        return idEnvanterList;
    }

    public void setIdEnvanterList(Long[] idEnvanterList) {
        this.idEnvanterList = idEnvanterList;
    }

    public EnumIslemTuru getIslemTuru() {
        return islemTuru;
    }

    public void setIslemTuru(EnumIslemTuru islemTuru) {
        this.islemTuru = islemTuru;
    }

    public T getVysTasinirTransferRequestDto() {
        return vysTasinirTransferRequestDto;
    }

    public void setVysTasinirTransferRequestDto(T vysTasinirTransferRequestDto) {
        this.vysTasinirTransferRequestDto = vysTasinirTransferRequestDto;
    }
}