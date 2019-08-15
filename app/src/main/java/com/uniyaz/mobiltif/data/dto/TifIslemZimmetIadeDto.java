package com.uniyaz.mobiltif.data.dto;

/**
 * Created by İrfan Öngüç on 23.07.2019
 */

public class TifIslemZimmetIadeDto {
    private String islemTarihi;
    private String aciklama;

    public String getIslemTarihi() {
        return islemTarihi;
    }

    public void setIslemTarihi(String islemTarihi) {
        this.islemTarihi = islemTarihi;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }
}
