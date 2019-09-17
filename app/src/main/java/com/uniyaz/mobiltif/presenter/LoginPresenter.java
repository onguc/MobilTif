package com.uniyaz.mobiltif.presenter;

import android.util.Log;

import com.google.gson.Gson;
import com.uniyaz.mobiltif.data.domain.LoginInfo;
import com.uniyaz.mobiltif.data.domain.UserDto;
import com.uniyaz.mobiltif.iface.ILogin;
import com.uniyaz.mobiltif.retrofit.RetrofitInterface;
import com.uniyaz.mobiltif.utils.ObjectUtil;
import com.uniyaz.mobiltif.utils.StaticUtils;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by İrfan Öngüç on 19.05.2019
 */

public class LoginPresenter {
    ILogin view;
    public static final String keyUserName = "KEY_USER_NAME";
    public static final String keyPassword = "KEY_PASSWORD";
    public static final String keyAuthorizationTicket = "KEY_AUTH_TICKET";
    public static final String keyUserDto = "KEY_USER_DTO";
    ObjectUtil<String> objectUtilString;

    public LoginPresenter(ILogin view) {
        this.view = view;
        objectUtilString = new ObjectUtil<>(view.getApplicationContext());
    }

    public String getSavedUserName() {
        return objectUtilString.getObjectFromSharedPreferences(keyUserName, String.class);
    }

    public String getPassword() {
        return objectUtilString.getObjectFromSharedPreferences(keyPassword, String.class);
    }

    public void savePassword(String password) {
        objectUtilString.saveObjectToSharedPreferences(keyPassword, password);
    }

    public void saveUserName(String userName) {
        objectUtilString.saveObjectToSharedPreferences(keyUserName, userName);
    }


    public String getAuthTicket() {
        return objectUtilString.getObjectFromSharedPreferences(keyAuthorizationTicket, String.class);
    }

    public void saveUserDto(UserDto dto) {
        new ObjectUtil<UserDto>(view.getApplicationContext()).saveObjectToSharedPreferences(keyUserDto, dto);
    }

    public UserDto getUserDto() {
        return new ObjectUtil<UserDto>(view.getApplicationContext()).getObjectFromSharedPreferences(keyUserDto, UserDto.class);
    }


    public void loginControl(UserDto userDto) {
//        String olmasiGereken="servicePrefix=http://abc.com&kullaniciAdi=uniyaz&sifre=1q24et";
        String userNamePassword = "servicePrefix=" + userDto.getServicePrefix() + "&kullaniciAdi=" + userDto.getUsername() + "&sifre=" + userDto.getPassword();
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), userNamePassword);
        Call<LoginInfo> userInfoCall = RetrofitInterface.retrofitInterface.loginAndGetToken(body);
        userInfoCall.enqueue(new Callback<LoginInfo>() {
            @Override
            public void onResponse(Call<LoginInfo> call, Response<LoginInfo> response) {
                LoginInfo loginInfo = null;
                if (response.code() == 200) {
                    loginInfo = response.body();
                } else {
                    ResponseBody errorBody = response.errorBody();
                    if (errorBody == null) {
                        view.showWarningDialog("errorBody = null");
                        return;
                    }
                    String loginInfoJson = errorBody.source().buffer().readUtf8();
                    loginInfo = new Gson().fromJson(loginInfoJson, LoginInfo.class);
                }

                if (loginInfo == null) {
                    view.showWarningDialog("Hata", "Sunucunuz kapalı olabilir.\n Lütfen sunucunuzun aktif olduğundan emin olun!");
                } else {
                    if (loginInfo.getSuccess()) {
                        StaticUtils.authTicket = loginInfo.getAuthorizationTicket();
                        StaticUtils.kullaniciAdi = userDto.getUsername();
                        view.onSuccess();
                    } else if ("0900".equals(loginInfo.getResultCode())) {
                        Log.d("LoginPresenter","loginControl : "+ loginInfo.getResultMessage());
                        view.showWarningDialog("Kullanıcı Adı ya da Şifre Yanlış!");
                    } else {
                        view.showWarningDialog("Hata", "Bilinmeyen Hata! Lütfen Sistem Yöneticisi İle İletişime Geçiniz!\n" + response.message());
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginInfo> call, Throwable t) {
                view.showWarningDialog("Hata", "İnternetinizde kopukluk olabilir. Ya da sunucunuz kapanmış olabilir!\n" + t.getMessage());
            }
        });
    }


}
