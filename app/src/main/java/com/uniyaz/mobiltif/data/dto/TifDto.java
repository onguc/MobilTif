package com.uniyaz.mobiltif.data.dto;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.uniyaz.mobiltif.data.enums.EnumIslemTuru;
import com.uniyaz.mobiltif.utils.StaticUtils;

public class TifDto<T> {

    public TifDto() {
        this.kullaniciAdi = StaticUtils.kullaniciAdi;
    }

    private String kullaniciAdi;

    @SerializedName("vysTasinirDemirbasIdList")
    private Long[] idEnvanterList;
    @SerializedName("islemTuru")
    private EnumIslemTuru islemTuru;
    @SerializedName(value = "islem", alternate = {"vysTasinirRequestDto,vysTasinirZimmetRequestDto,vysTasinirHibeRequestDto,vysTasinirHurdayaAyirmaRequestDto"})
    private T vysTasinirRequestDto;

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

    public T getVysTasinirRequestDto() {
        return vysTasinirRequestDto;
    }

    public void setVysTasinirRequestDto(T vysTasinirRequestDto) {
        this.vysTasinirRequestDto = vysTasinirRequestDto;
    }

    public String getJson() {
        StringBuilder jsonBuilder = new StringBuilder();
        Gson gson = new Gson();
        jsonBuilder.append("{");
        if (kullaniciAdi != null) {
            jsonBuilder.append("\"kullaniciAdi\":\"" + kullaniciAdi + "\"");
        }
        if (idEnvanterList != null && idEnvanterList.length > 0)
            jsonBuilder.append(getComma(jsonBuilder)).append("\"vysTasinirDemirbasIdList\":" + gson.toJson(idEnvanterList));
        if (islemTuru != null)
            jsonBuilder.append(getComma(jsonBuilder)).append("\"islemTuru\":\"" + islemTuru.name() + "\"");
        if (vysTasinirRequestDto != null) {
            jsonBuilder.append(getComma(jsonBuilder)).append("\"" + getKeyName() + "\":" + gson.toJson(vysTasinirRequestDto));
        }
        jsonBuilder.append("}");
        return jsonBuilder.toString();
    }

    private String getComma(StringBuilder jsonBuilder) {
        if (jsonBuilder.length() > 0)
            return ",";
        else
            return "";
    }

    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public void setKullaniciAdi(String kullaniciAdi) {
        this.kullaniciAdi = kullaniciAdi;
    }

    private String getKeyName() {
        if (vysTasinirRequestDto instanceof TifIslemHibeDto) {
            return "vysTasinirHibeRequestDto";
        } else if (vysTasinirRequestDto instanceof TifIslemHurdayaAyirmaDto) {
            return "vysTasinirHurdayaAyirmaRequestDto";
        } else if (vysTasinirRequestDto instanceof TifIslemTransferDto) {
            return "vysTasinirTransferRequestDto";
        } else if (vysTasinirRequestDto instanceof TifIslemZimmetDto) {
            return "vysTasinirZimmetRequestDto";
        } else if (vysTasinirRequestDto instanceof TifIslemZimmetDevriDto) {
            return "vysTasinirZimmetRequestDto";
        } else if (vysTasinirRequestDto instanceof TifIslemZimmetIadeDto) {
            return "vysTasinirZimmetRequestDto";
        } else return "result";
    }
}