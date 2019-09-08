package com.uniyaz.mobiltif.viewmodel;

import androidx.databinding.BaseObservable;

import com.uniyaz.mobiltif.utils.DateUtils;

import java.util.Date;

public class TifIslemZimmetIadeViewModel extends BaseObservable {
    private String islemTarihi;
    private String aciklama;

    public TifIslemZimmetIadeViewModel() {
        String convertedStringFromDate = DateUtils.getConvertedStringFromDate(new Date());
        setIslemTarihi(convertedStringFromDate);
    }


    public String getIslemTarihi() {
        return islemTarihi;
    }

    public void setIslemTarihi(String islemTarihi) {
        this.islemTarihi = islemTarihi;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }
}
