package com.uniyaz.mobiltif.presenter;

import android.util.Log;

import com.google.gson.Gson;
import com.uniyaz.mobiltif.data.domain.Info;
import com.uniyaz.mobiltif.data.domain.ResponseInfo;
import com.uniyaz.mobiltif.iface.IMain;
import com.uniyaz.mobiltif.utils.StaticUtils;

import retrofit2.Response;

public interface ResponseResult<T> {
    void onSuccess(T t);

    default void onReult(Response<ResponseInfo<T>> response) {
        IMain view = StaticUtils.iMain;
        if (response != null) {
            ResponseInfo<T> responseInfo = response.body();
            if (responseInfo == null) {
                String errorInfoJson = response.errorBody().source().buffer().readUtf8();
                Log.e("ResponseResult", "errorInfoJson = " + errorInfoJson);
                if (errorInfoJson != null) {
                    Info info = new Gson().fromJson(errorInfoJson, Info.class);
                    if (info != null) {
                        if (info.getSuccess() == false) {
                            view.showWarningDialog("Hata", info.getResultMessage());
                        } else {
                            view.showWarningDialog("Hata", errorInfoJson);
                        }
                    } else {
                        view.showWarningDialog("Hata", errorInfoJson);
                    }
                } else {
                    view.showWarningDialog("Hata", "errorInfo is NULL");
                }

            } else if (!responseInfo.getSuccess()) {
                view.showWarningDialog(responseInfo.getStatusInfo());
            } else {
                onSuccess(responseInfo.getResponse());
                view.onSuccess();
            }
        }
    }
}