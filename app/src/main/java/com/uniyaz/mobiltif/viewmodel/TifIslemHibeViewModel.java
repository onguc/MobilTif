package com.uniyaz.mobiltif.viewmodel;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.uniyaz.mobiltif.BR;
import com.uniyaz.mobiltif.data.dto.MuhatapDto;
import com.uniyaz.mobiltif.utils.DateUtils;

import java.util.Date;

public class TifIslemHibeViewModel extends BaseObservable {
    private String islemTarihi;

    private String dayanakBelgeTarihi;
    private String aciklama;
    private MuhatapDto muhatap;
    @Bindable
    private String error;

    public TifIslemHibeViewModel() {
        String convertedStringFromDate = DateUtils.getConvertedStringFromDate(new Date());
        setIslemTarihi(convertedStringFromDate);
        setDayanakBelgeTarihi(convertedStringFromDate);
    }

    public String getIslemTarihi() {
        return islemTarihi;
    }

    public void setIslemTarihi(String islemTarihi) {
        this.islemTarihi = islemTarihi;
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
    }

    public void setError(CharSequence error) {
        if (error != null){
            this.error = error.toString();
            notifyPropertyChanged(BR.error);
        }
    }

    public CharSequence getError() {
        return error;
    }
}
