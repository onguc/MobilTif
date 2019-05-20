package com.uniyaz.mobiltif.iface;

import android.app.Activity;

/**
 * Created by İrfan Öngüç on 19.05.2019
 */

public interface IMain extends ICommon {
    void onSuccess();

    void onSuccess(String message);

    void notifyDepartment();

    void notifyTasinir();

    void logOut();

    Activity getActivity();
}
