package com.uniyaz.mobiltif.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.uniyaz.mobiltif.BR;
import com.uniyaz.mobiltif.R;
import com.uniyaz.mobiltif.data.domain.UserDto;
import com.uniyaz.mobiltif.databinding.ActivityLoginBinding;
import com.uniyaz.mobiltif.iface.ILogin;
import com.uniyaz.mobiltif.presenter.LoginPresenter;
import com.uniyaz.mobiltif.utils.PermissionUtils;
import com.uniyaz.mobiltif.utils.StaticUtils;
import com.uniyaz.mobiltif.viewmodel.LoginViewModel;


public class LoginActivity extends AppCompatActivity implements ILogin {


    private LoginPresenter presenter;
    private LoginViewModel loginViewModel;
    ActivityLoginBinding activityLoginBinding;
    UserDto userDto;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PermissionUtils.checkInternetPermission(this);

        loginViewModel = new LoginViewModel(this);
        loginViewModel.hideProgressBar();
        userDto = loginViewModel.getUserDto();

        activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        activityLoginBinding.setViewModel(loginViewModel);
        activityLoginBinding.setActivity(this);
        activityLoginBinding.executePendingBindings();

        presenter = new LoginPresenter(this);
//
//
//        UserDto dto = presenter.getUserDto();
//        if (userDto != null) {
//            presenter.loginControl(userDto);
//        }
//
        String savedUserName = presenter.getSavedUserName();
        if (savedUserName != null && !"".equals(savedUserName.trim())) {
            userDto.setUsername(savedUserName);
            loginViewModel.setChecked(true);
            loginViewModel.notifyPropertyChanged(BR.username);
            loginViewModel.notifyPropertyChanged(BR.checked);
        }

    }

    public void onBtnLoginClicked() {
        if (!validateUsername()) {
            return;
        }
        if (!validatePassword()) {
            return;
        }
        loginViewModel.showProgressBar();
        presenter.loginControl(userDto);
    }

//    @BindingAdapter({"toastMessage"})
//    public static void runMe(View view, String message) {
//        if (message != null){
//            Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
//        }
//    }

    private void goMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    //
//
    private boolean validateUsername() {
        String userName = userDto.getUsername();
        if (userName.trim().isEmpty()) {
            activityLoginBinding.etUsername.setError(getString(R.string.err_msg_username));
            requestFocus(activityLoginBinding.etUsername);
            return false;
        } else {
//            tilUserName.setErrorEnabled(false);
            return true;
        }
    }

    //
    private boolean validatePassword() {
        String password = userDto.getPassword();
        if (password.isEmpty() || password.trim().isEmpty()) {
            activityLoginBinding.etPassword.setError(getString(R.string.err_msg_password));
            requestFocus(activityLoginBinding.etPassword);
            return false;
        }
        return true;
    }

    //
//
    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    //
    @Override
    public void onSuccess() {
        if (loginViewModel.isChecked()) { //if cb is checked
            StaticUtils.userDto = loginViewModel.getUserDto();
            presenter.saveUserName(StaticUtils.userDto.getUsername());
        } else {
            presenter.saveUserName(null);
        }
        loginViewModel.hideProgressBar();
        goMainActivity();
    }

    @Override
    public void showWarningDialog(String title, String statusInfo) {
        loginViewModel.hideProgressBar();
        new AlertDialog
                .Builder(LoginActivity.this)
                .setTitle(title)
                .setMessage(statusInfo)
                .setPositiveButton("TAMAM", null)
                .show();
    }
}
