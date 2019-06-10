package com.uniyaz.mobiltif.ui.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.uniyaz.mobiltif.R;
import com.uniyaz.mobiltif.data.domain.UserDto;
import com.uniyaz.mobiltif.databinding.ActivityLoginBinding;
import com.uniyaz.mobiltif.iface.ILogin;
import com.uniyaz.mobiltif.presenter.LoginPresenter;
import com.uniyaz.mobiltif.utils.PermissionUtils;
import com.uniyaz.mobiltif.utils.StaticUtils;
import com.uniyaz.mobiltif.viewmodel.LoginViewModel;
import com.uniyaz.mobiltif.viewmodel.ProgressBarViewModel;


public class LoginActivity extends AppCompatActivity implements ILogin {
//    private AccessTokenTracker accessTokenTracker;

//    @BindView(R.id.etUsername)
//    EditText etUsername;
//    @BindView(R.id.etPassword)
//    EditText etPassword;
//    TextView tvResult;
//    @BindView(R.id.cbRememberMe)
//    CheckBox cbRememberMe;

    private ProgressDialog progressDialog;

//    @BindView(R.id.progressBar)
//    ProgressBar progressBar;

//    private TextView tvSignUp;
//    private TextView tvForgetPassword;


    private LoginPresenter presenter;
    private LoginViewModel loginViewModel;
    private ProgressBarViewModel progressBarViewModel;
    ActivityLoginBinding activityLoginBinding;
    UserDto userDto;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        loginViewModel = new LoginViewModel(this);
        loginViewModel.showProgressBar();
//        progressBarViewModel = new ProgressBarViewModel();
        userDto = loginViewModel.getUserDto();
        activityLoginBinding.setViewModel(loginViewModel);
        activityLoginBinding.setActivity(this);
        activityLoginBinding.executePendingBindings();

        PermissionUtils.checkInternetPermission(this);
//        //keyhash = GYudxhKbpIR7rolceo2k3tS2Xig=
        presenter = new LoginPresenter(this);
//
//
////        String authTicket = presenter.getAuthTicket();
        UserDto userDto = presenter.getUserDto();
        if (userDto != null) {
            presenter.loginControl(userDto);

        }
//
//        String savedUserName = presenter.getSavedUserName();
//        if (savedUserName != null && !"".equals(savedUserName.trim()))
//            cbRememberMe.setChecked(true);
//
//        etUsername.setText(savedUserName);
    }

    public void onBtnLoginClicked() {
        if (!validateUsername()) {
            return;
        }
        if (!validatePassword()) {
            return;
        }
        loginViewModel.hideProgressBar();
//        setVisibleProgressBar(true);

        presenter.loginControl(userDto);
    }

//    @BindingAdapter({"toastMessage"})
//    public static void runMe(View view, String message) {
//        if (message != null){
//            Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
//        }
//    }

    //    private void setVisibleProgressBar(boolean visible) {
//        if (visible) {
//            progressBar.setVisibility(View.VISIBLE);
//            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
//                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
//        } else {
//            progressBar.setVisibility(View.GONE);
//            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
//        }
//    }
//
//
//    @OnClick(R.id.btnLogin)
//    void onClickBtnLogin() {
//        if (!validateUsername()) {
//            return;
//        }
//        if (!validatePassword()) {
//            return;
//        }
//        setVisibleProgressBar(true);
//
//        UserDto userDto = new UserDto();
//        userDto.setUsername(etUsername.getText().toString());
//        userDto.setPassword(etPassword.getText().toString());
//        presenter.loginControl(userDto);
//    }
//
//
//    private void goMainActivity() {
//        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        startActivity(intent);
//    }
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
//        goMainActivity();
    }

    @Override
    public void showWarningDialog(String title, String statusInfo) {
//        progressBar.setVisibility(View.GONE);
        loginViewModel.showProgressBar();
//        setVisibleProgressBar(false);
        new AlertDialog
                .Builder(LoginActivity.this)
                .setTitle(title)
                .setMessage(statusInfo)
                .setPositiveButton("TAMAM", null)
                .show();
    }
}
