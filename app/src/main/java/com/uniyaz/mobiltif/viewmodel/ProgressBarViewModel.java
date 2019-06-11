package com.uniyaz.mobiltif.viewmodel;

import android.app.Activity;
import android.view.WindowManager;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

public class ProgressBarViewModel extends BaseObservable {
    private boolean showProgressBar;
    private Activity activity;

    public ProgressBarViewModel(Activity activity) {
        this.activity = activity;
        hideProgressBar();
    }

    @Bindable
    public boolean isShowProgressBar() {
        return showProgressBar;
    }

    public void setShowProgressBar(boolean showProgressBar) {
        this.showProgressBar = showProgressBar;
        notifyPropertyChanged(BR.showProgressBar);
    }


    public void showProgressBar() {
        setShowProgressBar(true);
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    public void hideProgressBar() {
        setShowProgressBar(false);
        activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }
}
