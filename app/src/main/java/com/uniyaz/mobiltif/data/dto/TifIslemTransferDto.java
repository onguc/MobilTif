package com.uniyaz.mobiltif.data.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by İrfan Öngüç on 23.07.2019
 */

public class TifIslemTransferDto {
    private String islemTarihi;

    @SerializedName("vysTasinirAmbarId")
    private Long idGirisYapilanAmbar;

    @SerializedName("pbsPersonelKayitSorumlusuId")
    private Long idAmbarSorumlusu;

    @SerializedName("islemiYapanPbsPersonelId")
    private Long idIslemiYapanPersonel;

    @SerializedName("aciklama")
    private String aciklama;

    public String getIslemTarihi() {
        return islemTarihi;
    }

    public void setIslemTarihi(String islemTarihi) {
        this.islemTarihi = islemTarihi;
    }

    public Long getIdGirisYapilanAmbar() {
        return idGirisYapilanAmbar;
    }

    public void setIdGirisYapilanAmbar(Long idGirisYapilanAmbar) {
        this.idGirisYapilanAmbar = idGirisYapilanAmbar;
    }

    public Long getIdAmbarSorumlusu() {
        return idAmbarSorumlusu;
    }

    public void setIdAmbarSorumlusu(Long idAmbarSorumlusu) {
        this.idAmbarSorumlusu = idAmbarSorumlusu;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public Long getIdIslemiYapanPersonel() {
        return idIslemiYapanPersonel;
    }

    public void setIdIslemiYapanPersonel(Long idIslemiYapanPersonel) {
        this.idIslemiYapanPersonel = idIslemiYapanPersonel;
    }
}
