package com.uniyaz.mobiltif.iface;

import android.app.Activity;

import com.uniyaz.mobiltif.data.domain.Envanter;

import java.util.List;

/**
 * Created by İrfan Öngüç on 19.05.2019
 */

public interface ITif extends ICommon {
    void onSuccess();

    void onSuccess(String message);

    Activity getActivity();

    void showProgressBar();
}
