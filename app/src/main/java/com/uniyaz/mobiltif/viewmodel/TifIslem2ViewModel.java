package com.uniyaz.mobiltif.viewmodel;

import androidx.databinding.BaseObservable;

public class TifIslem2ViewModel extends BaseObservable {
    private String islemTarihi;
    private String muhatap;
    private String dayanakBelgeTarihi;
    private String aciklama;

    public String getIslemTarihi() {
        return islemTarihi;
    }

    public String getMuhatap() {
        return muhatap;
    }

    public String getDayanakBelgeTarihi() {
        return dayanakBelgeTarihi;
    }

    public String getAciklama() {
        return aciklama;
    }
}
