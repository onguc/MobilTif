package com.uniyaz.mobiltif.presenter;

import com.uniyaz.mobiltif.data.domain.ResponseInfo;
import com.uniyaz.mobiltif.data.dto.AmbarDto;
import com.uniyaz.mobiltif.data.dto.PersonelDto;
import com.uniyaz.mobiltif.iface.ITifCommon;
import com.uniyaz.mobiltif.retrofit.RetrofitInterface;
import com.uniyaz.mobiltif.utils.StaticUtils;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.uniyaz.mobiltif.utils.StaticUtils.getAuthorizationTicket;

/**
 * Created by İrfan Öngüç on 19.05.2019
 */

public class TifIslemTransferPresenter {
    ITifCommon view;

    public TifIslemTransferPresenter(ITifCommon view) {
        this.view = view;
    }


    public void fillAllAmbarDtoList() {
        Call<ResponseInfo<List<AmbarDto>>> responseInfoCall = RetrofitInterface.retrofitInterface.getAllAmbarDtoList(getAuthorizationTicket());
        responseInfoCall.enqueue(new Callback<ResponseInfo<List<AmbarDto>>>() {
            @Override
            public void onResponse(Call<ResponseInfo<List<AmbarDto>>> call, Response<ResponseInfo<List<AmbarDto>>> response) {
                ResponseResult<List<AmbarDto>> responseResult = ambarDtoList -> {
                    view.onSuccessForAmbarDtoList(ambarDtoList);
                };
                responseResult.onReult(response);
            }

            @Override
            public void onFailure(Call<ResponseInfo<List<AmbarDto>>> call, Throwable t) {
                StaticUtils.iMain.showWarningDialog("Error", "fillAllAmbarDtoList-->" + t.getMessage());
            }
        });
    }

    public void fillAllPersonelDtoListByAmbarId(Long vysTasinirAmbarId) {
        RequestBody bodyQrCode = RequestBody.create(MediaType.parse("text/plain"), "vysTasinirAmbarId=" + vysTasinirAmbarId);
        Call<ResponseInfo<List<PersonelDto>>> responseInfoCall = RetrofitInterface.retrofitInterface.getAllPersonelDtoListByAmbarId(getAuthorizationTicket(), bodyQrCode);
        responseInfoCall.enqueue(new Callback<ResponseInfo<List<PersonelDto>>>() {
            @Override
            public void onResponse(Call<ResponseInfo<List<PersonelDto>>> call, Response<ResponseInfo<List<PersonelDto>>> response) {
                ResponseResult<List<PersonelDto>> responseResult = personelDtos -> {
                    view.onSuccessforPersonelDtoList(personelDtos);
                };
                responseResult.onReult(response);
            }

            @Override
            public void onFailure(Call<ResponseInfo<List<PersonelDto>>> call, Throwable t) {
                StaticUtils.iMain.showWarningDialog("Error", "fillAllAmbarDtoList-->" + t.getMessage());
            }
        });
    }
}
