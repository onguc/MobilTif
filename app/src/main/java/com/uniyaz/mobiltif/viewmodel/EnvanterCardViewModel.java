package com.uniyaz.mobiltif.viewmodel;

import android.view.View;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.uniyaz.mobiltif.BR;
import com.uniyaz.mobiltif.data.domain.Envanter;
import com.uniyaz.mobiltif.ui.adapters.EnvanterAdapter;

public class EnvanterCardViewModel extends BaseObservable {
    private Envanter envanter;

    @Bindable
    boolean selected = false;
    private int index;
    private EnvanterAdapter adapter;


    public EnvanterCardViewModel(EnvanterAdapter adapter, Envanter envanter, int index) {
        this.adapter = adapter;
        this.envanter = envanter;
        this.index = index;
    }

    public void onCheckedChanged(View radioGroup, boolean isVis) {
        int x = 0;
    }

    public boolean onClick() {
        if (selected) {
            adapter.removeSelectedEnvanter(index);
        } else {
            adapter.addSelectedEnvanter(index, envanter);
        }
        setSelected(!selected);
        return true;
    }

    public String getUst() {
        return envanter.getIdString() + " / " + envanter.getTasinirAdi() + " / " + envanter.getTasinirKodu();
    }

    public String getAlt() {
        return "Masaüstü alcatel Lucent Siyah Dijital";
//        return envanter.getTasinirAdi();
//        return envanter.getAdi();
    }

    public Envanter getEnvanter() {
        return envanter;
    }

    @Bindable
    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        notifyPropertyChanged(BR.selected);
    }
}
