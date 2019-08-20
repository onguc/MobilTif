package com.uniyaz.mobiltif.presenter;

import com.uniyaz.mobiltif.data.domain.Envanter;
import com.uniyaz.mobiltif.data.domain.ResponseInfo;
import com.uniyaz.mobiltif.data.domain.Room;
import com.uniyaz.mobiltif.data.repo.DepartmentRepo;
import com.uniyaz.mobiltif.iface.IMain;
import com.uniyaz.mobiltif.retrofit.RetrofitInterface;
import com.uniyaz.mobiltif.utils.ObjectUtil;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.uniyaz.mobiltif.utils.StaticUtils.getAuthorizationForTest;

/**
 * Created by İrfan Öngüç on 19.05.2019
 */

public class MainPresenter {
    IMain view;
    ObjectUtil<String> objectUtil;

    DepartmentRepo departmentRepo;

    public MainPresenter(IMain view) {
        this.view = view;
        objectUtil = new ObjectUtil<>(view.getApplicationContext());
    }


//    public String getAuthorizationForTest(){
//        return "applicationkey=FLX_EBELEDIYE,requestdate=2014-10-01T2:32:50+02:00,md5hashcode=61411bbfbd3675953aa1e3738ce8a5c0";
//    }


    public void callRoomByQrCode(String qrCode) {
        RequestBody bodyQrCodeRoom = RequestBody.create(MediaType.parse("text/plain"), "etiketNo=" + qrCode);
        Call<ResponseInfo<Room>> callEnvanterList = RetrofitInterface.retrofitInterface.getRoomAndEnvanterListByQrCodeRoom(getAuthorizationForTest(), bodyQrCodeRoom);
        view.showProgressBar();
        callEnvanterList.enqueue(new Callback<ResponseInfo<Room>>() {
            @Override
            public void onResponse(Call<ResponseInfo<Room>> call, Response<ResponseInfo<Room>> response) {
                ResponseResult<Room> responseResult = room -> {
                    view.onSuccessForRoom(room);
                };
                responseResult.onReult(response);
            }

            @Override
            public void onFailure(Call<ResponseInfo<Room>> call, Throwable t) {
                view.showWarningDialog("Error", "callRoomByQrCode->message: " + t.getMessage());
            }
        });
    }

    public void callEnvanterByQrCode(String qrCode) {
        RequestBody bodyQrCode = RequestBody.create(MediaType.parse("text/plain"), "etiketNo=" + qrCode);
        Call<ResponseInfo<Envanter>> callEnvanter = RetrofitInterface.retrofitInterface.getEnvanterByQrCode(getAuthorizationForTest(), bodyQrCode);
        view.showProgressBar();
        callEnvanter.enqueue(new Callback<ResponseInfo<Envanter>>() {
            @Override
            public void onResponse(Call<ResponseInfo<Envanter>> call, Response<ResponseInfo<Envanter>> response) {
                ResponseResult<Envanter> responseResult = envanter -> {
                    view.onSuccessForEnvater(envanter);
                };
                responseResult.onReult(response);
            }

            @Override
            public void onFailure(Call<ResponseInfo<Envanter>> call, Throwable t) {
                view.showWarningDialog("Error", "callEnvanterByQrCode->message: " + t.getMessage());
            }
        });
    }
}
