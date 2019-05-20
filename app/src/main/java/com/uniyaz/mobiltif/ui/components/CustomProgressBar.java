package com.uniyaz.mobiltif.ui.components;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

/**
 * Created by İrfan Öngüç on 19.05.2019
 */

public class CustomProgressBar extends ProgressBar {
    Window window;

    public CustomProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        Activity activity= (Activity) context;
        window=activity.getWindow();
    }

    public void setBackgroundDrawable(boolean visible) {
        if (visible) {
            setVisibility(View.VISIBLE);
            window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        } else {
            setVisibility(View.GONE);
            window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }
    }
}
