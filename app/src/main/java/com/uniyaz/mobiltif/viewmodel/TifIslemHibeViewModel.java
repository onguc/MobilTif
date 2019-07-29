package com.uniyaz.mobiltif.viewmodel;

import androidx.databinding.BaseObservable;

import com.uniyaz.mobiltif.data.dto.PersonelDto;
import com.uniyaz.mobiltif.utils.TranslateDateFormat;

import java.util.Date;

public class TifIslemHibeViewModel extends BaseObservable {
    private String islemTarihi;
    private String muhatapName;
    private String dayanakBelgeTarihi;
    private String aciklama;
    private PersonelDto muhatap;

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

    public PersonelDto getMuhatap() {
        return muhatap;
    }

    public void setMuhatap(PersonelDto muhatap) {
        this.muhatap = muhatap;
    }
}
