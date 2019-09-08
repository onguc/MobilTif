package com.uniyaz.mobiltif.data.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by İrfan Öngüç on 23.07.2019
 */

public class TifIslemZimmetDevriDto {
    @SerializedName("islemTarihi")
    private String islemTarihi;
    @SerializedName("zimmetlenecekPersonelId")
    private Long zimmetlenecekPersonelId;
    @SerializedName("aciklama")
    private String aciklama;

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
}
