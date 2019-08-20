package com.uniyaz.mobiltif.viewmodel;

import androidx.databinding.BaseObservable;

import com.uniyaz.mobiltif.data.domain.Envanter;

public class TifEnvanterCardViewModel extends BaseObservable {

    public TifEnvanterCardViewModel(Envanter envanter, int index) {
        labelUst = envanter.getId() + "/" + envanter.getTasinirAdi() + "/" + envanter.getTasinirKodu();
        labelAlt = envanter.getAmbar();
        if (envanter.isZimmetli())
            labelAlt = labelUst + "\n<b>(Zimmetli Malzeme)</b>";
        this.index = String.valueOf(index);
    }

    private String labelUst;

    private String labelAlt;

    private String index;

    public String getLabelUst() {
        return labelUst;
    }

    public String getLabelAlt() {
        return labelAlt;
    }

    public String getIndex() {
        return index;
    }
}
