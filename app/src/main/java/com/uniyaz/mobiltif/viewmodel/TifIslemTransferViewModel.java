package com.uniyaz.mobiltif.viewmodel;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.uniyaz.mobiltif.data.dto.AmbarDto;
import com.uniyaz.mobiltif.data.dto.PersonelDto;
import com.uniyaz.mobiltif.utils.TranslateDateFormat;

import java.util.Date;

public class TifIslemTransferViewModel extends BaseObservable {

    public TifIslemTransferViewModel() {
        String convertedStringFromDate = TranslateDateFormat.getConvertedStringFromDate(new Date());
        setIslemTarihi(convertedStringFromDate);
    }

    private String islemTarihi;
    @Bindable
    private String girisYapilanAmbar;
    private String ambarSorumlusu;
    @Bindable
    private String aciklama;
    private AmbarDto selectedAmbarDto;
    private PersonelDto selectedPersonelDto;


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

    public AmbarDto getSelectedAmbarDto() {
        return selectedAmbarDto;
    }

    public void setSelectedAmbarDto(AmbarDto selectedAmbarDto) {
        this.selectedAmbarDto = selectedAmbarDto;
    }

    public PersonelDto getSelectedPersonelDto() {
        return selectedPersonelDto;
    }

    public void setSelectedPersonelDto(PersonelDto selectedPersonelDto) {
        this.selectedPersonelDto = selectedPersonelDto;
    }

    public void setIslemTarihi(String islemTarihi) {
        this.islemTarihi = islemTarihi;
    }

    public void setGirisYapilanAmbar(String girisYapilanAmbar) {
        this.girisYapilanAmbar = girisYapilanAmbar;
    }

    public void setAmbarSorumlusu(String ambarSorumlusu) {
        this.ambarSorumlusu = ambarSorumlusu;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }
}
