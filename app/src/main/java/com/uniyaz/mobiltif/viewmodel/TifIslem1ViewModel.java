package com.uniyaz.mobiltif.viewmodel;

import androidx.databinding.BaseObservable;

public class TifIslem1ViewModel extends BaseObservable {
    private String islemTarihi;
    private String girisYapilanAmbar;
    private String ambarSorumlusu;
    private String aciklama;

    public String getIslemTarihi() {
        return islemTarihi;
    }

    public String getGirisYapilanAmbar() {
        return girisYapilanAmbar;
    }

    public String getAmbarSorumlusu() {
        return ambarSorumlusu;
    }

    public String getAciklama() {
        return aciklama;
    }
}
