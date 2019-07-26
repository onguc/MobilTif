package com.uniyaz.mobiltif.viewmodel;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.uniyaz.mobiltif.data.domain.Envanter;

public class EnvanterViewModel extends BaseObservable {
    private Envanter envanter;

    public EnvanterViewModel(Envanter envanter){
        this.envanter = envanter;
    }

    public Envanter getEnvanter() {
        return envanter;
    }

    public String getSicilNo(){
        return envanter.getTasinirKodu()+"."+envanter.getYil()+"."+envanter.getSiraNo();
    }
}
