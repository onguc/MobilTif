package com.uniyaz.mobiltif.iface;

import android.app.Activity;

import com.uniyaz.mobiltif.data.domain.Envanter;

import java.util.List;

/**
 * Created by İrfan Öngüç on 19.05.2019
 */

public interface IMain extends ICommon {
    void onSuccess();

    void onSuccessForEnvater(Envanter envanter);

    void onSuccessForRoom(List<Envanter> envanterList);


    void onSuccess(String message);

    void notifyDepartment();

    void notifyTasinir();

    void logOut();

    Activity getActivity();
}