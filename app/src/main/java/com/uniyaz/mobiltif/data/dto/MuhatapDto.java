package com.uniyaz.mobiltif.data.dto;

import com.google.gson.annotations.SerializedName;

public class MuhatapDto {

    @SerializedName("sbsMuhatapId")
    private Long sbsMuhatapId;
    @SerializedName("isim")
    private String isim;

    @SerializedName("vergiDairesi")
    private String vergiDairesi;

    @SerializedName("tcKimlikNo")
    private String tcKimlikNo;
    @SerializedName("telefon")
    private String telefon;
    @SerializedName("eMail")
    private String eMail;


    public Long getSbsMuhatapId() {
        return sbsMuhatapId;
    }

    public void setSbsMuhatapId(Long sbsMuhatapId) {
        this.sbsMuhatapId = sbsMuhatapId;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public String getVergiDairesi() {
        return vergiDairesi;
    }

    public void setVergiDairesi(String vergiDairesi) {
        this.vergiDairesi = vergiDairesi;
    }

    public String getTcKimlikNo() {
        return tcKimlikNo;
    }

    public void setTcKimlikNo(String tcKimlikNo) {
        this.tcKimlikNo = tcKimlikNo;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }
}