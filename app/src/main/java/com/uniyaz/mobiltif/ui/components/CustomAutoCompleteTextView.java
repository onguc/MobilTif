package com.uniyaz.mobiltif.ui.components;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatAutoCompleteTextView;

public class CustomAutoCompleteTextView extends AppCompatAutoCompleteTextView {

    public CustomAutoCompleteTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setThreshold(int threshold) {
        if (threshold < 0) {
            threshold = 0;
        }
    }

    @Override
    public int getThreshold() {
        return 0;
    }

    @Override
    public boolean enoughToFilter() {
       return getText().length()>=getThreshold();
    }
}
