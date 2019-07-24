package com.uniyaz.mobiltif.data.dto;

import java.util.Date;

/**
 * Created by İrfan Öngüç on 23.07.2019
 */

public class TifIslem2Dto {
    private String islemTarihi;
    private Long idMuhatap;
    private String dayanakBelgeTarihi;
    private String aciklama;

    public String getIslemTarihi() {
        return islemTarihi;
    }

    public void setIslemTarihi(String islemTarihi) {
        this.islemTarihi = islemTarihi;
    }

    public Long getIdMuhatap() {
        return idMuhatap;
    }

    public void setIdMuhatap(Long idMuhatap) {
        this.idMuhatap = idMuhatap;
    }

    public String getDayanakBelgeTarihi() {
        return dayanakBelgeTarihi;
    }

    public void setDayanakBelgeTarihi(String dayanakBelgeTarihi) {
        this.dayanakBelgeTarihi = dayanakBelgeTarihi;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }
}
