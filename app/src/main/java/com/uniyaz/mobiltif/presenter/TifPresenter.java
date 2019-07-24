package com.uniyaz.mobiltif.presenter;

import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.uniyaz.mobiltif.data.domain.ResponseInfo;
import com.uniyaz.mobiltif.data.dto.AmbarDto;
import com.uniyaz.mobiltif.data.dto.PersonelDto;
import com.uniyaz.mobiltif.data.dto.TifDto;
import com.uniyaz.mobiltif.data.repo.DepartmentRepo;
import com.uniyaz.mobiltif.data.repo.RoomRepo;
import com.uniyaz.mobiltif.data.repo.TasinirRepo;
import com.uniyaz.mobiltif.iface.ITif;
import com.uniyaz.mobiltif.retrofit.RetrofitInterface;
import com.uniyaz.mobiltif.ui.fragments.TifFragment;
import com.uniyaz.mobiltif.utils.ObjectUtil;

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
    ObjectUtil<String> objectUtil;

    TasinirRepo tasinirRepo;
    DepartmentRepo departmentRepo;
    RoomRepo roomRepo;

    private ProgressBar progressBar;

    public TifPresenter(ITif view) {
        this.view = view;
        objectUtil = new ObjectUtil<>(view.getApplicationContext());
    }


    public void getAllAmbarDtoList() {
        Call<ResponseInfo<List<AmbarDto>>> responseInfoCall = RetrofitInterface.retrofitInterface.getAllAmbarDtoList(getAuthorizationForTest());
        responseInfoCall.enqueue(new Callback<ResponseInfo<List<AmbarDto>>>() {
            @Override
            public void onResponse(Call<ResponseInfo<List<AmbarDto>>> call, Response<ResponseInfo<List<AmbarDto>>> response) {
                MainPresenter.ResponseResult<List<AmbarDto>> responseResult = ambarDtoList -> {
                    view.onSuccessForAmbarDtoList(ambarDtoList);
                };
                responseResult.onReult(response, view);
            }

            @Override
            public void onFailure(Call<ResponseInfo<List<AmbarDto>>> call, Throwable t) {
                view.showWarningDialog("Error", "getAllAmbarDtoList-->" + t.getMessage());
            }
        });
    }

    public void getAllPersonelDtoList(Long vysTasinirAmbarId) {
        RequestBody bodyQrCode = RequestBody.create(MediaType.parse("text/plain"), "vysTasinirAmbarId=" + vysTasinirAmbarId);
        Call<ResponseInfo<List<PersonelDto>>> responseInfoCall = RetrofitInterface.retrofitInterface.getAllPersonelDtoList(getAuthorizationForTest(), bodyQrCode);
        responseInfoCall.enqueue(new Callback<ResponseInfo<List<PersonelDto>>>() {
            @Override
            public void onResponse(Call<ResponseInfo<List<PersonelDto>>> call, Response<ResponseInfo<List<PersonelDto>>> response) {
                MainPresenter.ResponseResult<List<PersonelDto>> responseResult = personelDtos -> {
                    view.onSuccessforPersonelDtoList(personelDtos);
                };
                responseResult.onReult(response, view);
            }

            @Override
            public void onFailure(Call<ResponseInfo<List<PersonelDto>>> call, Throwable t) {
                view.showWarningDialog("Error", "getAllAmbarDtoList-->" + t.getMessage());
            }
        });
    }

    public void saveVysTasinirTransferIslem(TifDto dto) {
        String jsonDto = new Gson().toJson(dto);
        RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain"), "vysTasinirTransferRequestDto=" + jsonDto);
        Call<ResponseInfo> responseInfoCall = RetrofitInterface.retrofitInterface.saveVysTasinirTransferIslem(getAuthorizationForTest(), requestBody);
        responseInfoCall.enqueue(new Callback<ResponseInfo>() {
            @Override
            public void onResponse(Call<ResponseInfo> call, Response<ResponseInfo> response) {
                MainPresenter.ResponseResult responseResult = personelDtos -> {
                    view.onSuccessForSaveTasinirTransferIslem();
                };
                responseResult.onReult(response, view);
            }

            @Override
            public void onFailure(Call<ResponseInfo> call, Throwable t) {
                view.showWarningDialog("Error", "saveVysTasinirTransferIslem-->" + t.getMessage());
            }
        });
    }
}
