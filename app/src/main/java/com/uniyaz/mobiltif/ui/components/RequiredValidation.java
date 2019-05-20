package com.uniyaz.mobiltif.ui.components;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by İrfan Öngüç on 19.05.2019
 */

public class RequiredValidation {

    private Window window;
    public RequiredValidation(Window window){
        this.window=window;
    }

    private boolean validatePassword(TextView etPassword) {
        if (etPassword.getText().toString().isEmpty() || etPassword.getText().toString().trim().isEmpty()) {
//            etPassword.setError(getString(R.string.err_msg_password));
            requestFocus(etPassword);
            return false;
        }
        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
}
