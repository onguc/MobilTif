package com.uniyaz.mobiltif.viewmodel;

import android.text.TextUtils;
import android.view.WindowManager;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.lifecycle.MutableLiveData;

import com.uniyaz.mobiltif.BR;
import com.uniyaz.mobiltif.data.domain.UserDto;
import com.uniyaz.mobiltif.ui.activities.LoginActivity;

public class LoginViewModel extends BaseObservable {
    private UserDto userDto;
    private boolean checked;
    LoginActivity loginActivity;

    private boolean showProgressBar;


    public LoginViewModel(LoginActivity loginActivity) {
        userDto = new UserDto();
        userDto.setPassword("1q24et");
        userDto.setUsername("uniyaz");
        userDto.setChecked(true);
        checked = true;
        this.loginActivity = loginActivity;
    }

    @Bindable
    private String toastMessage = null;

    public String getToastMessage() {
        return toastMessage;
    }

    private void setToastMessage(String toastMessage) {

        this.toastMessage = toastMessage;
        notifyPropertyChanged(BR.toastMessage);
    }

    @Bindable
    public String getUsername() {
        return userDto.getUsername();
    }

    public void setUsername(String username) {
        userDto.setUsername(username);
        notifyPropertyChanged(BR.username);
    }

    @Bindable
    public String getPassword() {
        return userDto.getPassword();
    }

    @Bindable
    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean isChecked) {
        this.checked = isChecked;
    }

    public void setPassword(String password) {
        userDto.setPassword(password);
        notifyPropertyChanged(BR.password);
    }

    public UserDto getUserDto() {
        return userDto;
    }



    public void showProgressBar() {
        showProgressBar = true;
        notifyPropertyChanged(BR.showProgressBar);
        loginActivity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    public void hideProgressBar() {
        showProgressBar = false;
        notifyPropertyChanged(BR.showProgressBar);
        loginActivity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    @Bindable
    public boolean isShowProgressBar() {
        return showProgressBar;
    }
}
