package com.uniyaz.mobiltif.data.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by İrfan Öngüç on 23.07.2019
 */

public class TifIslemZimmetDto {
    @SerializedName("islemTarihi")
    private String islemTarihi;
    @SerializedName("zimmetlenecekPersonelId")
    private Long zimmetlenecekPersonelId;
    @SerializedName("aciklama")
    private String aciklama;
    @SerializedName("kullanacakPersonelId")
    private Long kullanacakPersonelId;

    public String getIslemTarihi() {
        return islemTarihi;
    }

    public void setIslemTarihi(String islemTarihi) {
        this.islemTarihi = islemTarihi;
    }

    public Long getZimmetlenecekPersonelId() {
        return zimmetlenecekPersonelId;
    }

    public void setZimmetlenecekPersonelId(Long zimmetlenecekPersonelId) {
        this.zimmetlenecekPersonelId = zimmetlenecekPersonelId;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public Long getKullanacakPersonelId() {
        return kullanacakPersonelId;
    }

    public void setKullanacakPersonelId(Long kullanacakPersonelId) {
        this.kullanacakPersonelId = kullanacakPersonelId;
    }
}
