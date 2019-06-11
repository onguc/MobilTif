package com.uniyaz.mobiltif.viewmodel;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

public class ProgressBarViewModel extends BaseObservable {
    private boolean showProgressBar;
    private int showProgressBar2;


    public ProgressBarViewModel() {
        showProgressBar();
    }

    @Bindable
    public boolean isShowProgressBar() {
        return showProgressBar;
    }

    public void setShowProgressBar(boolean showProgressBar) {
        this.showProgressBar = showProgressBar;
        notifyPropertyChanged(BR._all);
    }


    public void showProgressBar() {
        showProgressBar = true;
        notifyPropertyChanged(BR.showProgressBar);
    }

    public void hideProgressBar() {
        showProgressBar = false;
        notifyPropertyChanged(BR.showProgressBar);
    }
}
