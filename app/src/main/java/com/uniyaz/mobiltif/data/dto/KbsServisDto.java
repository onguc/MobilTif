package com.uniyaz.mobiltif.data.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by İrfan Öngüç on 22.07.2019
 */

public class KbsServisDto {

    @SerializedName("id")
    private Integer id;
    @SerializedName("adi")
    private String adi;
    @SerializedName("kisaAdi")
    private Object kisaAdi;
    @SerializedName("kod")
    private String kod;
    @SerializedName("turu")
    private String turu;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdi() {
        return adi;
    }

    public void setAdi(String adi) {
        this.adi = adi;
    }

    public Object getKisaAdi() {
        return kisaAdi;
    }

    public void setKisaAdi(Object kisaAdi) {
        this.kisaAdi = kisaAdi;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getTuru() {
        return turu;
    }

    public void setTuru(String turu) {
        this.turu = turu;
    }

}