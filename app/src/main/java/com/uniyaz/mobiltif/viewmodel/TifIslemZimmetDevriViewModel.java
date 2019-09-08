package com.uniyaz.mobiltif.viewmodel;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.uniyaz.mobiltif.BR;
import com.uniyaz.mobiltif.data.dto.PersonelDto;
import com.uniyaz.mobiltif.utils.DateUtils;

import java.util.Date;

public class TifIslemZimmetDevriViewModel extends BaseObservable {
    private String islemTarihi;
    private String muhatapName;
    private String aciklama;
    @Bindable
    private PersonelDto devirYapilacakPersonel;

    public TifIslemZimmetDevriViewModel() {
        String convertedStringFromDate = DateUtils.getConvertedStringFromDate(new Date());
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

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public PersonelDto getDevirYapilacakPersonel() {
        return devirYapilacakPersonel;
    }

    public void setDevirYapilacakPersonel(PersonelDto devirYapilacakPersonel) {
        this.devirYapilacakPersonel = devirYapilacakPersonel;
        notifyPropertyChanged(BR.devirYapilacakPersonel);
    }
}
