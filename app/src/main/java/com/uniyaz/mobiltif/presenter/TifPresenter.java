package com.uniyaz.mobiltif.presenter;

import android.util.Log;

import com.uniyaz.mobiltif.data.domain.ResponseInfo;
import com.uniyaz.mobiltif.data.dto.TifDto;
import com.uniyaz.mobiltif.iface.ITif;
import com.uniyaz.mobiltif.retrofit.RetrofitInterface;
import com.uniyaz.mobiltif.utils.StaticUtils;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.uniyaz.mobiltif.utils.StaticUtils.getAuthorizationForTest;

/**
 * Created by İrfan Öngüç on 19.05.2019
 */

public class TifPresenter {
    ITif view;

    public TifPresenter(ITif view) {
        this.view = view;
    }


    public void saveVysTasinirTransferIslem(TifDto dto) {
        String jsonDto = dto.getJson();
        Log.d("TifPresenter", "jsonTifDto = " + jsonDto);
        RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain"), "vysTifIslemRequestDto=" + jsonDto);
        //RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain"), "vysTasinirTransferRequestDto=" + jsonDto);
        Call<ResponseInfo> responseInfoCall = RetrofitInterface.retrofitInterface.saveVysTasinirTransferIslem(getAuthorizationForTest(), requestBody);
        responseInfoCall.enqueue(new Callback<ResponseInfo>() {
            @Override
            public void onResponse(Call<ResponseInfo> call, Response<ResponseInfo> response) {
                ResponseResult responseResult = personelDtos -> {
                    view.onSuccessForSaveTasinirTransferIslem();
                };
                responseResult.onReult(response);
            }

            @Override
            public void onFailure(Call<ResponseInfo> call, Throwable t) {
                StaticUtils.iMain.showWarningDialog("Error", "saveVysTasinirTransferIslem-->" + t.getMessage());
            }
        });
    }
}
