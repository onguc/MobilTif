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
    //    ActivityLoginBinding activityLoginBinding;
    LoginActivity loginActivity;

    private String successMessage = "Login was successful";
    private String errorMessage = "Email or Password not valid";

    public MutableLiveData<Integer> progress = new MutableLiveData<>();


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


    public boolean isInputDataValid() {
        return !TextUtils.isEmpty(getUsername()) && getPassword().length() > 5;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    private boolean validateUsername() {
        if (userDto.getUsername().trim().isEmpty()) {
//            etUsername.setError(getString(R.string.err_msg_username));
//            requestFocus(etUsername);
            return false;
        } else {
            return true;
        }
    }

    private boolean validatePassword() {
        if (userDto.getPassword().isEmpty() || userDto.getPassword().trim().isEmpty()) {
//            etPassword.setError(getString(R.string.err_msg_password));
//            requestFocus(etPassword);
            return false;
        }
        return true;
    }

    @Bindable
    public MutableLiveData<Integer> getProgress() {
        return progress;
    }

    public void showProgressBar() {
        progress.setValue(0); //visible
//        showProgressBar = true;
        notifyPropertyChanged(androidx.databinding.library.baseAdapters.BR.showProgressBar);
        notifyPropertyChanged(androidx.databinding.library.baseAdapters.BR.progress);
        loginActivity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    public void hideProgressBar() {
        progress.setValue(8); //gone
        loginActivity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
//        showProgressBar = false;
        notifyPropertyChanged(androidx.databinding.library.baseAdapters.BR.showProgressBar);
        notifyPropertyChanged(androidx.databinding.library.baseAdapters.BR.progress);
    }

}
