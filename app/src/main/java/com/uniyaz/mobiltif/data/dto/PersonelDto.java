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
    private Integer sbsKisiId;
    @SerializedName("yas")
    private Integer yas;
    @SerializedName("birim")
    private String birim;
    @SerializedName("birimId")
    private Integer birimId;
    @SerializedName("dogumGunu")
    private String dogumGunu;
    @SerializedName("kbsOrgut")
    private String kbsOrgut;
    @SerializedName("kbsOrgutId")
    private Integer kbsOrgutId;
    @SerializedName("dahiliTel")
    private Object dahiliTel;
    @SerializedName("eposta")
    private String eposta;
    @SerializedName("kurumSicilNo")
    private Integer kurumSicilNo;
    @SerializedName("kbsServisGorev")
    private Object kbsServisGorev;
    @SerializedName("lokasyonBilgisi")
    private Object lokasyonBilgisi;
    @SerializedName("mezuniyet")
    private Object mezuniyet;
    @SerializedName("personelTuru")
    private String personelTuru;
    @SerializedName("iseGirisTarihi")
    private Object iseGirisTarihi;
    @SerializedName("cinsiyet")
    private Object cinsiyet;
    @SerializedName("tercihEdilenTelefon")
    private String tercihEdilenTelefon;
    @SerializedName("tcKimlikNo")
    private String tcKimlikNo;
    @SerializedName("girisTarihi")
    private String girisTarihi;
    @SerializedName("cikisTarihi")
    private Object cikisTarihi;
    @SerializedName("cuzdanSeri")
    private String cuzdanSeri;
    @SerializedName("cuzdanNo")
    private String cuzdanNo;
    @SerializedName("kimlikKartNo")
    private Object kimlikKartNo;
    @SerializedName("gorevUnvani")
    private String gorevUnvani;

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

    public Integer getSbsKisiId() {
        return sbsKisiId;
    }

    public void setSbsKisiId(Integer sbsKisiId) {
        this.sbsKisiId = sbsKisiId;
    }

    public Integer getYas() {
        return yas;
    }

    public void setYas(Integer yas) {
        this.yas = yas;
    }

    public String getBirim() {
        return birim;
    }

    public void setBirim(String birim) {
        this.birim = birim;
    }

    public Integer getBirimId() {
        return birimId;
    }

    public void setBirimId(Integer birimId) {
        this.birimId = birimId;
    }

    public String getDogumGunu() {
        return dogumGunu;
    }

    public void setDogumGunu(String dogumGunu) {
        this.dogumGunu = dogumGunu;
    }

    public String getKbsOrgut() {
        return kbsOrgut;
    }

    public void setKbsOrgut(String kbsOrgut) {
        this.kbsOrgut = kbsOrgut;
    }

    public Integer getKbsOrgutId() {
        return kbsOrgutId;
    }

    public void setKbsOrgutId(Integer kbsOrgutId) {
        this.kbsOrgutId = kbsOrgutId;
    }

    public Object getDahiliTel() {
        return dahiliTel;
    }

    public void setDahiliTel(Object dahiliTel) {
        this.dahiliTel = dahiliTel;
    }

    public String getEposta() {
        return eposta;
    }

    public void setEposta(String eposta) {
        this.eposta = eposta;
    }

    public Integer getKurumSicilNo() {
        return kurumSicilNo;
    }

    public void setKurumSicilNo(Integer kurumSicilNo) {
        this.kurumSicilNo = kurumSicilNo;
    }

    public Object getKbsServisGorev() {
        return kbsServisGorev;
    }

    public void setKbsServisGorev(Object kbsServisGorev) {
        this.kbsServisGorev = kbsServisGorev;
    }

    public Object getLokasyonBilgisi() {
        return lokasyonBilgisi;
    }

    public void setLokasyonBilgisi(Object lokasyonBilgisi) {
        this.lokasyonBilgisi = lokasyonBilgisi;
    }

    public Object getMezuniyet() {
        return mezuniyet;
    }

    public void setMezuniyet(Object mezuniyet) {
        this.mezuniyet = mezuniyet;
    }

    public String getPersonelTuru() {
        return personelTuru;
    }

    public void setPersonelTuru(String personelTuru) {
        this.personelTuru = personelTuru;
    }

    public Object getIseGirisTarihi() {
        return iseGirisTarihi;
    }

    public void setIseGirisTarihi(Object iseGirisTarihi) {
        this.iseGirisTarihi = iseGirisTarihi;
    }

    public Object getCinsiyet() {
        return cinsiyet;
    }

    public void setCinsiyet(Object cinsiyet) {
        this.cinsiyet = cinsiyet;
    }

    public String getTercihEdilenTelefon() {
        return tercihEdilenTelefon;
    }

    public void setTercihEdilenTelefon(String tercihEdilenTelefon) {
        this.tercihEdilenTelefon = tercihEdilenTelefon;
    }

    public String getTcKimlikNo() {
        return tcKimlikNo;
    }

    public void setTcKimlikNo(String tcKimlikNo) {
        this.tcKimlikNo = tcKimlikNo;
    }

    public String getGirisTarihi() {
        return girisTarihi;
    }

    public void setGirisTarihi(String girisTarihi) {
        this.girisTarihi = girisTarihi;
    }

    public Object getCikisTarihi() {
        return cikisTarihi;
    }

    public void setCikisTarihi(Object cikisTarihi) {
        this.cikisTarihi = cikisTarihi;
    }

    public String getCuzdanSeri() {
        return cuzdanSeri;
    }

    public void setCuzdanSeri(String cuzdanSeri) {
        this.cuzdanSeri = cuzdanSeri;
    }

    public String getCuzdanNo() {
        return cuzdanNo;
    }

    public void setCuzdanNo(String cuzdanNo) {
        this.cuzdanNo = cuzdanNo;
    }

    public Object getKimlikKartNo() {
        return kimlikKartNo;
    }

    public void setKimlikKartNo(Object kimlikKartNo) {
        this.kimlikKartNo = kimlikKartNo;
    }

    public String getGorevUnvani() {
        return gorevUnvani;
    }

    public void setGorevUnvani(String gorevUnvani) {
        this.gorevUnvani = gorevUnvani;
    }

    @NonNull
    @Override
    public String toString() {
        return getIsim();
    }
}