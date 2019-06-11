package com.uniyaz.mobiltif.viewmodel;

import android.app.Activity;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.uniyaz.mobiltif.BR;
import com.uniyaz.mobiltif.R;
import com.uniyaz.mobiltif.ui.activities.MainActivity;

public class MainViewModel extends BaseObservable {

    private ContentMainViewModel contentMainViewModel;
    private String titleToolbar;

    public MainViewModel(MainActivity mainActivity) {
        contentMainViewModel = new ContentMainViewModel(mainActivity);
        String appName = mainActivity.getString(R.string.app_name);
        setTitleToolbar(appName);
    }


    @Bindable
    public ContentMainViewModel getContentMainViewModel() {
        return contentMainViewModel;
    }

    @Bindable
    public String getTitleToolbar() {
        return titleToolbar;
    }

    public void setTitleToolbar(String titleToolbar) {
        this.titleToolbar = titleToolbar;
        notifyPropertyChanged(BR.titleToolbar);
    }

    public class ContentMainViewModel extends BaseObservable {
        ProgressBarViewModel progressBarViewModel;

        public ContentMainViewModel(Activity activity) {
            progressBarViewModel = new ProgressBarViewModel(activity);
        }

        @Bindable
        public ProgressBarViewModel getProgressBarViewModel() {
            return progressBarViewModel;
        }
    }

    public void showProgressBar() {
        contentMainViewModel.getProgressBarViewModel().showProgressBar();
    }

    public void hideProgressBar() {
        contentMainViewModel.getProgressBarViewModel().hideProgressBar();
    }
}
