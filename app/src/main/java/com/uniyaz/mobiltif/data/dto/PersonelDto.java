package com.uniyaz.mobiltif.data.dto;


import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

/**
 * Created by İrfan Öngüç on 22.07.2019
 */

public class PersonelDto {

    @SerializedName("id")
    private Long id;
    @SerializedName("isim")
    private String isim;
    @SerializedName("sbsKisiId")
    private Long sbsKisiId;
    //    @SerializedName("yas")
//    private Integer yas;
    @SerializedName("birim")
    private String birim;
    @SerializedName("birimId")
    private Long birimId;
    //    @SerializedName("dogumGunu")
//    private String dogumGunu;
    @SerializedName("kbsOrgut")
    private String kbsOrgut;
    @SerializedName("kbsOrgutId")
    private Long kbsOrgutId;
//    @SerializedName("dahiliTel")
//    private Object dahiliTel;
//    @SerializedName("eposta")
////    private String eposta;
//    @SerializedName("kurumSicilNo")
//    private Long kurumSicilNo;
//    @SerializedName("kbsServisGorev")
//    private Object kbsServisGorev;
//    @SerializedName("lokasyonBilgisi")
//    private Object lokasyonBilgisi;
//    @SerializedName("mezuniyet")
//    private Object mezuniyet;
//    @SerializedName("personelTuru")
//    private String personelTuru;
//    @SerializedName("iseGirisTarihi")
//    private Object iseGirisTarihi;
//    @SerializedName("cinsiyet")
//    private Object cinsiyet;
//    @SerializedName("tercihEdilenTelefon")
//    private String tercihEdilenTelefon;
//    @SerializedName("tcKimlikNo")
//    private String tcKimlikNo;
//    @SerializedName("girisTarihi")
//    private String girisTarihi;
//    @SerializedName("cikisTarihi")
//    private Object cikisTarihi;
//    @SerializedName("cuzdanSeri")
//    private String cuzdanSeri;
//    @SerializedName("cuzdanNo")
//    private String cuzdanNo;
//    @SerializedName("kimlikKartNo")
//    private Object kimlikKartNo;
//    @SerializedName("gorevUnvani")
//    private String gorevUnvani;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public Long getSbsKisiId() {
        return sbsKisiId;
    }

    public void setSbsKisiId(Long sbsKisiId) {
        this.sbsKisiId = sbsKisiId;
    }

    public String getBirim() {
        return birim;
    }

    public void setBirim(String birim) {
        this.birim = birim;
    }

    public Long getBirimId() {
        return birimId;
    }

    public void setBirimId(Long birimId) {
        this.birimId = birimId;
    }

    public String getKbsOrgut() {
        return kbsOrgut;
    }

    public void setKbsOrgut(String kbsOrgut) {
        this.kbsOrgut = kbsOrgut;
    }

    public Long getKbsOrgutId() {
        return kbsOrgutId;
    }

    public void setKbsOrgutId(Long kbsOrgutId) {
        this.kbsOrgutId = kbsOrgutId;
    }

    @NonNull
    @Override
    public String toString() {
        return getIsim();
    }
}