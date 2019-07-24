package com.uniyaz.mobiltif.viewmodel;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.uniyaz.mobiltif.data.dto.AmbarDto;

/**
 * Created by İrfan Öngüç on 22.07.2019
 */

public class AutoListItemViewModel extends BaseObservable {
    private AmbarDto ambarDto;

    AutoListItemViewModel(AmbarDto ambarDto) {
        this.ambarDto = ambarDto;
    }

    @Bindable
    public String getAmbarName() {
        return ambarDto.getAdi();
    }
}
