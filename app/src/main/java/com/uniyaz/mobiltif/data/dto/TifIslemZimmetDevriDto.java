package com.uniyaz.mobiltif.data.dto;

/**
 * Created by İrfan Öngüç on 23.07.2019
 */

public class TifIslemZimmetDevriDto {
    private String islemTarihi;
    private Long idDevirYapilacakPersonel;
    private String aciklama;

    public String getIslemTarihi() {
        return islemTarihi;
    }

    public void setIslemTarihi(String islemTarihi) {
        this.islemTarihi = islemTarihi;
    }

    public Long getIdDevirYapilacakPersonel() {
        return idDevirYapilacakPersonel;
    }

    public void setIdDevirYapilacakPersonel(Long idDevirYapilacakPersonel) {
        this.idDevirYapilacakPersonel = idDevirYapilacakPersonel;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }
}
