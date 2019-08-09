package com.uniyaz.mobiltif.ui.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.uniyaz.mobiltif.R;

/**
 * Created by İrfan Öngüç on 31.07.2019
 */

public class CustomButtonSearch extends CoordinatorLayout {

    private TextView tvBtnText;

    public CustomButtonSearch(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.custom_button_search, this);
        tvBtnText = findViewById(R.id.tvBtnText);
        int[] sets = {R.attr.buttonText};
        TypedArray typedArray = context.obtainStyledAttributes(attrs, sets);
        CharSequence text = typedArray.getText(0);
        setButtonText(text);
    }

    public void setButtonText(CharSequence text){
        tvBtnText.setText(text);
    }

    public CharSequence getButtonText(){
        return tvBtnText.getText();
    }

}
