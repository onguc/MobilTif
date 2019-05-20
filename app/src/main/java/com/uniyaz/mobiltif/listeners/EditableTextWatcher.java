package com.uniyaz.mobiltif.listeners;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * Created by İrfan Öngüç on 19.05.2019
 */

public interface EditableTextWatcher extends TextWatcher {
    @Override
    default void beforeTextChanged(CharSequence s, int start, int count, int after) {
        int x = 0;
    }

    @Override
    default void onTextChanged(CharSequence s, int start, int before, int count) {
        int y = 0;
    }

    @Override
    default void afterTextChanged(Editable s) {
        if (s == null || s.toString() == null || "".equals(s.toString()))
            onTextChanged(null);
        else
            onTextChanged(s.toString());
    }

    void onTextChanged(String text);
}
