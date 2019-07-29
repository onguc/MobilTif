package com.uniyaz.mobiltif.viewmodel;

import androidx.databinding.BaseObservable;

import com.uniyaz.mobiltif.data.dto.PersonelDto;

public class TifIslemHurdayaAyirmaViewModel extends BaseObservable {
    private String islemTarihi;
    private String dayanakBelgeTarihi;
    private String kayittanDusmeNedeni;
    private String digerKayittanDusmeNedeni;
    private String imhaOlurAciklamasi;
    private String digerImhaOlurAciklamasi;
    private String komisyonBaskaniAdi;
    private String komisyonUyesi1TKY_YetkilisiAdi;
    private String komisyonUyesi2Adi;
    private String harcamaYetkilisiAdi;

    private PersonelDto komisyonBaskani;
    private PersonelDto komisyonUyesi1TKY;
    private PersonelDto komisyonUyesi2;
    private PersonelDto harcamaYetkilisi;

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

    public String getKomisyonBaskaniAdi() {
        return komisyonBaskaniAdi;
    }

    public void setKomisyonBaskaniAdi(String komisyonBaskaniAdi) {
        this.komisyonBaskaniAdi = komisyonBaskaniAdi;
    }

    public String getKomisyonUyesi1TKY_YetkilisiAdi() {
        return komisyonUyesi1TKY_YetkilisiAdi;
    }

    public void setKomisyonUyesi1TKY_YetkilisiAdi(String komisyonUyesi1TKY_YetkilisiAdi) {
        this.komisyonUyesi1TKY_YetkilisiAdi = komisyonUyesi1TKY_YetkilisiAdi;
    }

    public String getKomisyonUyesi2Adi() {
        return komisyonUyesi2Adi;
    }

    public void setKomisyonUyesi2Adi(String komisyonUyesi2Adi) {
        this.komisyonUyesi2Adi = komisyonUyesi2Adi;
    }

    public String getHarcamaYetkilisiAdi() {
        return harcamaYetkilisiAdi;
    }

    public void setHarcamaYetkilisiAdi(String harcamaYetkilisiAdi) {
        this.harcamaYetkilisiAdi = harcamaYetkilisiAdi;
    }

    public PersonelDto getKomisyonBaskani() {
        return komisyonBaskani;
    }

    public void setKomisyonBaskani(PersonelDto komisyonBaskani) {
        this.komisyonBaskani = komisyonBaskani;
    }

    public PersonelDto getKomisyonUyesi1TKY() {
        return komisyonUyesi1TKY;
    }

    public void setKomisyonUyesi1TKY(PersonelDto komisyonUyesi1TKY) {
        this.komisyonUyesi1TKY = komisyonUyesi1TKY;
    }

    public PersonelDto getKomisyonUyesi2() {
        return komisyonUyesi2;
    }

    public void setKomisyonUyesi2(PersonelDto komisyonUyesi2) {
        this.komisyonUyesi2 = komisyonUyesi2;
    }

    public PersonelDto getHarcamaYetkilisi() {
        return harcamaYetkilisi;
    }

    public void setHarcamaYetkilisi(PersonelDto harcamaYetkilisi) {
        this.harcamaYetkilisi = harcamaYetkilisi;
    }
}
