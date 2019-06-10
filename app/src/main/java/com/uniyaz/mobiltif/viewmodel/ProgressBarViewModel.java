package com.uniyaz.mobiltif.viewmodel;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.MutableLiveData;

public class ProgressBarViewModel extends BaseObservable {
    private boolean showProgressBar;
    private int showProgressBar2;

    public MutableLiveData<Integer> progress = new MutableLiveData<>();

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

    @Bindable
    public MutableLiveData<Integer> getProgress() {
        return progress;
    }

    public void showProgressBar() {
        progress.setValue(0); //visible
        showProgressBar = true;
        notifyPropertyChanged(BR.showProgressBar);
        notifyPropertyChanged(BR.progress);
    }

    public void hideProgressBar() {
        progress.setValue(8); //gone
        showProgressBar = false;
        notifyPropertyChanged(BR.showProgressBar);
        notifyPropertyChanged(BR.progress);
    }
}
