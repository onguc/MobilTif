package com.uniyaz.mobiltif.data.dto;

/**
 * Created by İrfan Öngüç on 23.07.2019
 */

public class TifIslemZimmetDto {
    private String islemTarihi;
    private Long idZimmetYapilacakPersonel;
    private String aciklama;

    public String getIslemTarihi() {
        return islemTarihi;
    }

    public void setIslemTarihi(String islemTarihi) {
        this.islemTarihi = islemTarihi;
    }

    public Long getIdZimmetYapilacakPersonel() {
        return idZimmetYapilacakPersonel;
    }

    public void setIdZimmetYapilacakPersonel(Long idZimmetYapilacakPersonel) {
        this.idZimmetYapilacakPersonel = idZimmetYapilacakPersonel;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }
}
