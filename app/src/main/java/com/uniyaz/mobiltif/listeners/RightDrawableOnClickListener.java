package com.uniyaz.mobiltif.listeners;

import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

/**
 * Created by İrfan Öngüç on 19.05.2019
 */

public abstract class RightDrawableOnClickListener implements View.OnTouchListener {

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        final int DRAWABLE_LEFT = 0;
        final int DRAWABLE_TOP = 1;
        final int DRAWABLE_RIGHT = 2;
        final int DRAWABLE_BOTTOM = 3;

        EditText editText = (EditText) v;
        final Drawable[] drawables = editText.getCompoundDrawables();
        if (drawables != null && drawables.length == 4) {
            Drawable drawable = drawables[DRAWABLE_RIGHT];
            if (event.getAction() == MotionEvent.ACTION_DOWN && drawable != null) {
                if (event.getRawX() >= (editText.getRight() - drawable.getBounds().width())) {
                    return onDrawableTouch(event);
                }
            }
        }
        return false;
    }

    public abstract boolean onDrawableTouch(final MotionEvent event);
}
