package com.uniyaz.mobiltif.presenter;

import android.util.Log;

import com.uniyaz.mobiltif.data.domain.ResponseInfo;
import com.uniyaz.mobiltif.data.dto.TifDto;
import com.uniyaz.mobiltif.data.dto.TifIslemResponseDto;
import com.uniyaz.mobiltif.iface.ITif;
import com.uniyaz.mobiltif.retrofit.RetrofitInterface;
import com.uniyaz.mobiltif.utils.StaticUtils;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.uniyaz.mobiltif.utils.StaticUtils.getAuthorizationTicket;

/**
 * Created by İrfan Öngüç on 19.05.2019
 */

public class TifPresenter {
    ITif view;

    public TifPresenter(ITif view) {
        this.view = view;
    }


    public void saveVysTasinirTransferIslem(TifDto dto) {
        String body = "vysTifIslemRequestDto=" + dto.getJson();
        Log.d("TifPresenter", "body = " + body);
        RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain"), body);
        //RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain"), "vysTasinirTransferRequestDto=" + jsonDto);
        Call<ResponseInfo<TifIslemResponseDto>> responseInfoCall = RetrofitInterface.retrofitInterface.saveVysTasinirTransferIslem(getAuthorizationTicket(), requestBody);
        responseInfoCall.enqueue(new Callback<ResponseInfo<TifIslemResponseDto>>() {
            @Override
            public void onResponse(Call<ResponseInfo<TifIslemResponseDto>> call, Response<ResponseInfo<TifIslemResponseDto>> response) {
                ResponseResult<TifIslemResponseDto> responseResult = responseDto -> {
                    view.onSuccessForSaveTasinirTransferIslem(responseDto);
                    Log.d("TifPresenter", "saveVysTasinirTransferIslem -> responseDto : " + responseDto);
                };
                responseResult.onReult(response);
                Log.d("TifPresenter", "saveVysTasinirTransferIslem -> response : " + response);
            }

            @Override
            public void onFailure(Call<ResponseInfo<TifIslemResponseDto>> call, Throwable t) {
                Log.e("TifPresenter", "saveVysTasinirTransferIslem-->" + t.getMessage());
                StaticUtils.iMain.showWarningDialog("Error", "saveVysTasinirTransferIslem-->" + t.getMessage());
            }
        });
    }

}
