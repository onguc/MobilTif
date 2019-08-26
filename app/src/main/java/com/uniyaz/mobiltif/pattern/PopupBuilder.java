package com.uniyaz.mobiltif.pattern;

import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

/**
 * Created by İrfan Öngüç on 26.08.2019
 */

public class PopupBuilder {

    boolean focusable = true; // lets taps outside the popup also dismiss it
    int width = LinearLayout.LayoutParams.MATCH_PARENT;
    int height = LinearLayout.LayoutParams.MATCH_PARENT;

    View popupView;
    View parentView;
    PopupWindow popupWindow;

    public PopupBuilder(View popupView, View parentView){
        this.popupView=popupView;
        popupWindow = new PopupWindow(popupView, width, height, focusable);
        this.parentView=parentView;
    }

    public void show() {
        popupWindow.showAtLocation(parentView, Gravity.CENTER, 0, 0);
    }

    public void hide() {
        popupWindow.dismiss();
    }
}
