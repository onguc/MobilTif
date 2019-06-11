package com.uniyaz.mobiltif.viewmodel;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.uniyaz.mobiltif.data.domain.Envanter;

public class EnvanterViewModel extends BaseObservable {
    private Envanter envanter;

    public EnvanterViewModel(Envanter envanter){
        this.envanter = envanter;
    }

    @Bindable
    public Envanter getEnvanter() {
        return envanter;
    }
}
