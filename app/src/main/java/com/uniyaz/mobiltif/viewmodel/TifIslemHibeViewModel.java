package com.uniyaz.mobiltif.viewmodel;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.uniyaz.mobiltif.BR;
import com.uniyaz.mobiltif.data.dto.MuhatapDto;
import com.uniyaz.mobiltif.utils.TranslateDateFormat;

import java.util.Date;

public class TifIslemHibeViewModel extends BaseObservable {
    private String islemTarihi;
    @Bindable
    private String muhatapName;
    private String dayanakBelgeTarihi;
    private String aciklama;
    private MuhatapDto muhatap;

    public TifIslemHibeViewModel() {
        String convertedStringFromDate = TranslateDateFormat.getConvertedStringFromDate(new Date());
        setIslemTarihi(convertedStringFromDate);
    }


    public String getIslemTarihi() {
        return islemTarihi;
    }

    public void setIslemTarihi(String islemTarihi) {
        this.islemTarihi = islemTarihi;
    }

    public String getMuhatapName() {
        return muhatapName;
    }

    public void setMuhatapName(String muhatapName) {
        this.muhatapName = muhatapName;
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

    public MuhatapDto getMuhatap() {
        return muhatap;
    }

    public void setMuhatap(MuhatapDto muhatap) {
        this.muhatap = muhatap;
        if (muhatap != null)
            muhatapName = muhatap.getIsim();
        notifyPropertyChanged(BR.muhatapName);
    }

    public String getMuhatapIsim() {
        if (muhatap != null)
            return muhatap.getIsim();
        else return "";
    }
}
