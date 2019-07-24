package com.uniyaz.mobiltif.data.dto;


import com.google.gson.annotations.SerializedName;

/**
 * Created by İrfan Öngüç on 22.07.2019
 */

public class AmbarDto {

    @SerializedName("id")
    private Long id;
    @SerializedName("adi")
    private String adi;
    @SerializedName("aciklama")
    private Object aciklama;
    @SerializedName("kbsServisDto")
    private KbsServisDto kbsServisDto;
    @SerializedName("ambarKodu")
    private String ambarKodu;
    @SerializedName("durum")
    private String durum;
    @SerializedName("durumAsStr")
    private String durumAsStr;
    @SerializedName("merkezAmbari")
    private String merkezAmbari;
    @SerializedName("merkezAmbariAsStr")
    private String merkezAmbariAsStr;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdi() {
        return adi;
    }

    public void setAdi(String adi) {
        this.adi = adi;
    }

    public Object getAciklama() {
        return aciklama;
    }

    public void setAciklama(Object aciklama) {
        this.aciklama = aciklama;
    }

    public KbsServisDto getKbsServisDto() {
        return kbsServisDto;
    }

    public void setKbsServisDto(KbsServisDto kbsServisDto) {
        this.kbsServisDto = kbsServisDto;
    }

    public String getAmbarKodu() {
        return ambarKodu;
    }

    public void setAmbarKodu(String ambarKodu) {
        this.ambarKodu = ambarKodu;
    }

    public String getDurum() {
        return durum;
    }

    public void setDurum(String durum) {
        this.durum = durum;
    }

    public String getDurumAsStr() {
        return durumAsStr;
    }

    public void setDurumAsStr(String durumAsStr) {
        this.durumAsStr = durumAsStr;
    }

    public String getMerkezAmbari() {
        return merkezAmbari;
    }

    public void setMerkezAmbari(String merkezAmbari) {
        this.merkezAmbari = merkezAmbari;
    }

    public String getMerkezAmbariAsStr() {
        return merkezAmbariAsStr;
    }

    public void setMerkezAmbariAsStr(String merkezAmbariAsStr) {
        this.merkezAmbariAsStr = merkezAmbariAsStr;
    }

    @Override
    public String toString() {
        return adi;
    }
}

