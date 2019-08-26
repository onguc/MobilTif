package com.uniyaz.mobiltif.presenter;

import android.util.Log;

import com.uniyaz.mobiltif.data.domain.ResponseInfo;
import com.uniyaz.mobiltif.data.dto.MuhatapDto;
import com.uniyaz.mobiltif.data.dto.TifDto;
import com.uniyaz.mobiltif.data.dto.TifIslemResponseDto;
import com.uniyaz.mobiltif.iface.ITif;
import com.uniyaz.mobiltif.retrofit.RetrofitInterface;
import com.uniyaz.mobiltif.utils.StaticUtils;

import java.util.List;

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
        Call<ResponseInfo<TifIslemResponseDto>> responseInfoCall = RetrofitInterface.retrofitInterface.saveVysTasinirTransferIslem(getAuthorizationForTest(), requestBody);
        responseInfoCall.enqueue(new Callback<ResponseInfo<TifIslemResponseDto>>() {
            @Override
            public void onResponse(Call<ResponseInfo<TifIslemResponseDto>> call, Response<ResponseInfo<TifIslemResponseDto>> response) {
                ResponseResult<TifIslemResponseDto> responseResult = responseDto -> {
                    view.onSuccessForSaveTasinirTransferIslem(responseDto);
                };
                responseResult.onReult(response);
            }

            @Override
            public void onFailure(Call<ResponseInfo<TifIslemResponseDto>> call, Throwable t) {
                Log.e("TifPresenter", "saveVysTasinirTransferIslem-->" + t.getMessage());
                StaticUtils.iMain.showWarningDialog("Error", "saveVysTasinirTransferIslem-->" + t.getMessage());
            }
        });
    }

    public void fillAllMuhatapDto() {
        RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain"), "adi=İRFAN HACI");
        Call<ResponseInfo<List<MuhatapDto>>> responseInfoCall = RetrofitInterface.retrofitInterface.findAllSbsMuhatap(getAuthorizationForTest(),requestBody);
        responseInfoCall.enqueue(new Callback<ResponseInfo<List<MuhatapDto>>>() {
            @Override
            public void onResponse(Call<ResponseInfo<List<MuhatapDto>>> call, Response<ResponseInfo<List<MuhatapDto>>> response) {
                ResponseResult<List<MuhatapDto>> responseResult = responseDto -> {
                    StaticUtils.muhatapDtoList = responseDto;
                };
                responseResult.onReult(response);
            }

            @Override
            public void onFailure(Call<ResponseInfo<List<MuhatapDto>>> call, Throwable t) {
                Log.e("TifPresenter", "findAllSbsMuhatap-->" + t.getMessage());
                StaticUtils.iMain.showWarningDialog("Error", "findAllSbsMuhatap-->" + t.getMessage());
            }
        });
    }
}
