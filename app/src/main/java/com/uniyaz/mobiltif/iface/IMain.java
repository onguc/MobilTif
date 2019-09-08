package com.uniyaz.mobiltif.iface;

import android.app.Activity;

import com.uniyaz.mobiltif.data.domain.Envanter;
import com.uniyaz.mobiltif.data.domain.Room;

import java.util.List;

/**
 * Created by İrfan Öngüç on 19.05.2019
 */

public interface IMain extends ICommon {
    void onSuccess();

    void onSuccessForEnvater(Envanter envanter);

    void onSuccessForRoom(Room room);


    void onSuccess(String message);

    void logOut();

    Activity getActivity();

    void showProgressBar();

    void hideProgressBar();
}
