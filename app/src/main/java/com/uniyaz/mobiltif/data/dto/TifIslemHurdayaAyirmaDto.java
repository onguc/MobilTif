package com.uniyaz.mobiltif.data.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by İrfan Öngüç on 23.07.2019
 */

public class TifIslemHurdayaAyirmaDto {

    @SerializedName("islemTarihi")
    private String islemTarihi;
    @SerializedName("dayanakBelgeTarihi")
    private String dayanakBelgeTarihi;
    @SerializedName("kayittanDusmeNedeni")
    private String kayittanDusmeNedeni;
    @SerializedName("digerKayittanDusmeNedeni")
    private String digerKayittanDusmeNedeni;
    @SerializedName("imhaOlurAciklamasi")
    private String imhaOlurAciklamasi;
    @SerializedName("digerImhaOlurAciklamasi")
    private String digerImhaOlurAciklamasi;
    @SerializedName("komisyonBaskaniId")
    private Long komisyonBaskaniId;
    @SerializedName("komisyonUyesiBirId")
    private Long komisyonUyesiBirId;
    @SerializedName("komisyonUyesiIkiId")
    private Long komisyonUyesiIkiId;
    @SerializedName("harcamaYetkilisiId")
    private Long harcamaYetkilisiId;

    public String getIslemTarihi() {
        return islemTarihi;
    }

    public void setIslemTarihi(String islemTarihi) {
        this.islemTarihi = islemTarihi;
    }

    public String getDayanakBelgeTarihi() {
        return dayanakBelgeTarihi;
    }

    public void setDayanakBelgeTarihi(String dayanakBelgeTarihi) {
        this.dayanakBelgeTarihi = dayanakBelgeTarihi;
    }

    public String getKayittanDusmeNedeni() {
        return kayittanDusmeNedeni;
    }

    public void setKayittanDusmeNedeni(String kayittanDusmeNedeni) {
        this.kayittanDusmeNedeni = kayittanDusmeNedeni;
    }

    public String getDigerKayittanDusmeNedeni() {
        return digerKayittanDusmeNedeni;
    }

    public void setDigerKayittanDusmeNedeni(String digerKayittanDusmeNedeni) {
        this.digerKayittanDusmeNedeni = digerKayittanDusmeNedeni;
    }

    public String getImhaOlurAciklamasi() {
        return imhaOlurAciklamasi;
    }

    public void setImhaOlurAciklamasi(String imhaOlurAciklamasi) {
        this.imhaOlurAciklamasi = imhaOlurAciklamasi;
    }

    public String getDigerImhaOlurAciklamasi() {
        return digerImhaOlurAciklamasi;
    }

    public void setDigerImhaOlurAciklamasi(String digerImhaOlurAciklamasi) {
        this.digerImhaOlurAciklamasi = digerImhaOlurAciklamasi;
    }

    public Long getKomisyonBaskaniId() {
        return komisyonBaskaniId;
    }

    public void setKomisyonBaskaniId(Long komisyonBaskaniId) {
        this.komisyonBaskaniId = komisyonBaskaniId;
    }

    public Long getKomisyonUyesiBirId() {
        return komisyonUyesiBirId;
    }

    public void setKomisyonUyesiBirId(Long komisyonUyesiBirId) {
        this.komisyonUyesiBirId = komisyonUyesiBirId;
    }

    public Long getKomisyonUyesiIkiId() {
        return komisyonUyesiIkiId;
    }

    public void setKomisyonUyesiIkiId(Long komisyonUyesiIkiId) {
        this.komisyonUyesiIkiId = komisyonUyesiIkiId;
    }

    public Long getHarcamaYetkilisiId() {
        return harcamaYetkilisiId;
    }

    public void setHarcamaYetkilisiId(Long harcamaYetkilisiId) {
        this.harcamaYetkilisiId = harcamaYetkilisiId;
    }
}
