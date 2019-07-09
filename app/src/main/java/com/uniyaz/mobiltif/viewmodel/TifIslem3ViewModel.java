package com.uniyaz.mobiltif.viewmodel;

import androidx.databinding.BaseObservable;

public class TifIslem3ViewModel extends BaseObservable {
    private String islemTarihi;
    private String dayanakBelgeTarihi;
    private String kayittanDusmeNedeni;
    private String digerKayittanDusmeNedeni;
    private String imhaOlurAciklamasi;
    private String digerImhaOlurAciklamasi;
    private String komisyonBaskani;
    private String komisyonUyesi1TKY_Yetkilisi;
    private String komisyonUyesi2;
    private String harcamaYetkilisi;

    public String getIslemTarihi() {
        return islemTarihi;
    }

    public String getDayanakBelgeTarihi() {
        return dayanakBelgeTarihi;
    }

    public String getKayittanDusmeNedeni() {
        return kayittanDusmeNedeni;
    }

    public String getDigerKayittanDusmeNedeni() {
        return digerKayittanDusmeNedeni;
    }

    public String getImhaOlurAciklamasi() {
        return imhaOlurAciklamasi;
    }

    public String getDigerImhaOlurAciklamasi() {
        return digerImhaOlurAciklamasi;
    }

    public String getKomisyonBaskani() {
        return komisyonBaskani;
    }

    public String getKomisyonUyesi1TKY_Yetkilisi() {
        return komisyonUyesi1TKY_Yetkilisi;
    }

    public String getKomisyonUyesi2() {
        return komisyonUyesi2;
    }

    public String getHarcamaYetkilisi() {
        return harcamaYetkilisi;
    }
}
