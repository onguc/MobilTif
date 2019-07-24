package com.uniyaz.mobiltif.data.dto;

import java.util.Date;

/**
 * Created by İrfan Öngüç on 23.07.2019
 */

public class TifIslem3Dto {

    private String islemTarihi;
    private String dayanakBelgeTarihi;
    private String kayittanDusmeNedeni;
    private String digerKayittanDusmeNedeni;
    private String imhaOlurAciklamasi;
    private String digerImhaOlurAciklamasi;
    private Long idKomisyonBaskani;
    private Long idKomisyonUyesi1TKY;
    private Long idKomisyonUyesi2;
    private Long idHarcamaYetkilisi;

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

    public Long getIdKomisyonBaskani() {
        return idKomisyonBaskani;
    }

    public void setIdKomisyonBaskani(Long idKomisyonBaskani) {
        this.idKomisyonBaskani = idKomisyonBaskani;
    }

    public Long getIdKomisyonUyesi1TKY() {
        return idKomisyonUyesi1TKY;
    }

    public void setIdKomisyonUyesi1TKY(Long idKomisyonUyesi1TKY) {
        this.idKomisyonUyesi1TKY = idKomisyonUyesi1TKY;
    }

    public Long getIdKomisyonUyesi2() {
        return idKomisyonUyesi2;
    }

    public void setIdKomisyonUyesi2(Long idKomisyonUyesi2) {
        this.idKomisyonUyesi2 = idKomisyonUyesi2;
    }

    public Long getIdHarcamaYetkilisi() {
        return idHarcamaYetkilisi;
    }

    public void setIdHarcamaYetkilisi(Long idHarcamaYetkilisi) {
        this.idHarcamaYetkilisi = idHarcamaYetkilisi;
    }
}
