package com.uniyaz.mobiltif.ui.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.uniyaz.mobiltif.R;
import com.uniyaz.mobiltif.data.domain.UserDto;
import com.uniyaz.mobiltif.iface.ILogin;
import com.uniyaz.mobiltif.presenter.LoginPresenter;
import com.uniyaz.mobiltif.utils.PermissionUtils;
import com.uniyaz.mobiltif.utils.StaticUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LoginActivity extends AppCompatActivity implements ILogin {
//    private AccessTokenTracker accessTokenTracker;

    @BindView(R.id.etUsername)
    EditText etUsername;
    @BindView(R.id.etPassword)
    EditText etPassword;
    TextView tvResult;
    @BindView(R.id.cbRememberMe)
    CheckBox cbRememberMe;

    private ProgressDialog progressDialog;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

//    private TextView tvSignUp;
//    private TextView tvForgetPassword;

    private LoginPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);


        PermissionUtils.checkInternetPermission(this);
        //keyhash = GYudxhKbpIR7rolceo2k3tS2Xig=
        presenter = new LoginPresenter(this);


//        String authTicket = presenter.getAuthTicket();
        UserDto userDto = presenter.getUserDto();
        if (userDto != null) {
            presenter.loginControl(userDto);

        }

        String savedUserName = presenter.getSavedUserName();
        if (savedUserName != null && !"".equals(savedUserName.trim()))
            cbRememberMe.setChecked(true);

        etUsername.setText(savedUserName);
    }

    private void setVisibleProgressBar(boolean visible) {
        if (visible) {
            progressBar.setVisibility(View.VISIBLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        } else {
            progressBar.setVisibility(View.GONE);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }
    }


    @OnClick(R.id.btnLogin)
    void onClickBtnLogin() {
        if (!validateUsername()) {
            return;
        }
        if (!validatePassword()) {
            return;
        }
        setVisibleProgressBar(true);

        UserDto userDto = new UserDto();
        userDto.setUsername(etUsername.getText().toString());
        userDto.setPassword(etPassword.getText().toString());
        presenter.loginControl(userDto);
    }


    private void goMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }


    private boolean validateUsername() {
        if (etUsername.getText().toString().trim().isEmpty()) {
            etUsername.setError(getString(R.string.err_msg_username));
            requestFocus(etUsername);
            return false;
        } else {
//            tilUserName.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePassword() {
        if (etPassword.getText().toString().isEmpty() || etPassword.getText().toString().trim().isEmpty()) {
            etPassword.setError(getString(R.string.err_msg_password));
            requestFocus(etPassword);
            return false;
        }
        return true;
    }


    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    @Override
    public void onSuccess() {
        if (cbRememberMe.isChecked()) { //if cb is checked
            UserDto userDto = new UserDto();
            userDto.setUsername(etUsername.getText().toString());
            userDto.setPassword(etPassword.getText().toString());
            StaticUtils.userDto = userDto;
            presenter.saveUserName(userDto.getUsername());
        } else {
            presenter.saveUserName(null);
        }
        goMainActivity();
    }

    @Override
    public void showWarningDialog(String title, String statusInfo) {
        progressBar.setVisibility(View.GONE);
        setVisibleProgressBar(false);
        new AlertDialog
                .Builder(LoginActivity.this)
                .setTitle(title)
                .setMessage(statusInfo)
                .setPositiveButton("TAMAM", null)
                .show();
    }
}
