package com.uniyaz.mobiltif.iface;

import android.content.Context;

public interface ICommon {
    void showWarningDialog(String title, String explanation);

    Context getApplicationContext();

    default void showWarningDialog(String explanation) {
        showWarningDialog("Uyarı", explanation);
    }
}