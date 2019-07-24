package com.uniyaz.mobiltif.iface;

import android.app.Activity;

import com.uniyaz.mobiltif.data.domain.Envanter;
import com.uniyaz.mobiltif.data.dto.AmbarDto;
import com.uniyaz.mobiltif.data.dto.PersonelDto;

import java.util.List;

/**
 * Created by İrfan Öngüç on 19.05.2019
 */

public interface ITif extends IMain {
    void onSuccess();
    void onSuccessForAmbarDtoList(List<AmbarDto> ambarDtoList);
    void onSuccessforPersonelDtoList(List<PersonelDto> personelDtoList);
    void onSuccessForSaveTasinirTransferIslem();
    void onSuccess(String message);

    Activity getActivity();

    void showProgressBar();
}
