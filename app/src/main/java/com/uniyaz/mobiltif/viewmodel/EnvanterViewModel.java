package com.uniyaz.mobiltif.viewmodel;

import androidx.databinding.BaseObservable;

import com.uniyaz.mobiltif.data.domain.Envanter;

public class EnvanterViewModel extends BaseObservable {
    private Envanter envanter;


    public EnvanterViewModel(Envanter envanter) {
        this.envanter = envanter;
    }

    public Envanter getEnvanter() {
        return envanter;
    }

    public String getSicilNo() {
        return envanter.getTasinirKodu() + "." + envanter.getYil() + "." + envanter.getSiraNo();
    }

    public boolean isEnable() {
        if ("PASIF".equals(envanter.getDurumu())) {
            return false;
        } else {
            return true;
        }
    }

//    public boolean isEnable() {
//        if("PASIF".equals(envanter.getDurumu())){
//            isEnable=false;
//        } else {
//            isEnable = true;
//        }
//        return isEnable;
//    }
//
//    public void setEnable(boolean enable) {
//        isEnable = enable;
//    }
}
