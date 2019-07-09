package com.uniyaz.mobiltif.viewmodel;

import androidx.databinding.BaseObservable;

import com.uniyaz.mobiltif.data.domain.Envanter;

import java.util.List;

public class TifViewModel extends BaseObservable {
    private List<Envanter> envanterList;

    public List<Envanter> getEnvanterList() {
        return envanterList;
    }
}
