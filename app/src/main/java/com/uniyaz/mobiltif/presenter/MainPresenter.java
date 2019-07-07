package com.uniyaz.mobiltif.presenter;

import android.widget.ProgressBar;

import com.uniyaz.mobiltif.data.domain.Department;
import com.uniyaz.mobiltif.data.domain.Envanter;
import com.uniyaz.mobiltif.data.domain.ResponseInfo;
import com.uniyaz.mobiltif.data.repo.DepartmentRepo;
import com.uniyaz.mobiltif.data.repo.RoomRepo;
import com.uniyaz.mobiltif.data.repo.TasinirRepo;
import com.uniyaz.mobiltif.iface.IMain;
import com.uniyaz.mobiltif.retrofit.RetrofitInterface;
import com.uniyaz.mobiltif.utils.ObjectUtil;
import com.uniyaz.mobiltif.utils.StaticUtils;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.uniyaz.mobiltif.presenter.LoginPresenter.keyAuthorizationTicket;
import static com.uniyaz.mobiltif.utils.StaticUtils.getAuthorizationForTest;

/**
 * Created by İrfan Öngüç on 19.05.2019
 */

public class MainPresenter {
    IMain view;
    ObjectUtil<String> objectUtil;

    TasinirRepo tasinirRepo;
    DepartmentRepo departmentRepo;
    RoomRepo roomRepo;

    private ProgressBar progressBar;

    public MainPresenter(IMain view) {
        this.view = view;
        objectUtil = new ObjectUtil<>(view.getApplicationContext());
    }


    public void getDepartmentList() {
        departmentRepo = new DepartmentRepo();
        List<Department> departmentList = departmentRepo.getAll();
        if (departmentList == null || departmentList.size() == 0) {
            fillDepartmentFromRemote();
        } else {
            StaticUtils.refreshDepartmentList(departmentList);
            view.notifyDepartment();
        }
    }

    public String getAuthTicket() {
        return objectUtil.getObjectFromSharedPreferences(keyAuthorizationTicket, String.class);
    }

//    public String getAuthorizationForTest(){
//        return "applicationkey=FLX_EBELEDIYE,requestdate=2014-10-01T2:32:50+02:00,md5hashcode=61411bbfbd3675953aa1e3738ce8a5c0";
//    }

    private void fillDepartmentFromRemote() {
        RequestBody bodyServisTuru = RequestBody.create(MediaType.parse("text/plain"), "enumKbsServisTuru=MUDURLUK");
        Call<ResponseInfo<List<Department>>> deparmentListCall = RetrofitInterface.retrofitInterface.getDepartmList(getAuthorizationForTest(), bodyServisTuru);
        deparmentListCall.enqueue(new Callback<ResponseInfo<List<Department>>>() {
            @Override
            public void onResponse(Call<ResponseInfo<List<Department>>> call, Response<ResponseInfo<List<Department>>> response) {
                ResponseResult<List<Department>> responseResult = departmentList -> {
                    departmentRepo.synchronizeAll(departmentList);
                    StaticUtils.refreshDepartmentList(departmentList);
                    StaticUtils.successControl.setSuccessDepartment(true);
                    view.notifyDepartment();
                };

                responseResult.onReult(response, view);
            }

            @Override
            public void onFailure(Call<ResponseInfo<List<Department>>> call, Throwable t) {
                view.showWarningDialog("Erro In OnFailure Department:\n " + t.getMessage());
            }
        });
    }


    public void callEnvanterListByQrCodeRoom(String qrCode) {
        RequestBody bodyQrCodeRoom = RequestBody.create(MediaType.parse("text/plain"), "qrCodeRoom=" + qrCode);
        Call<ResponseInfo<List<Envanter>>> callEnvanterList = RetrofitInterface.retrofitInterface.getEnvanterListByQrCodeRoom(getAuthorizationForTest(), bodyQrCodeRoom);
        view.showProgressBar();
        callEnvanterList.enqueue(new Callback<ResponseInfo<List<Envanter>>>() {
            @Override
            public void onResponse(Call<ResponseInfo<List<Envanter>>> call, Response<ResponseInfo<List<Envanter>>> response) {
                ResponseResult<List<Envanter>> responseResult = envanterList -> {
                    view.onSuccessForRoom(envanterList);
                };
                responseResult.onReult(response, view);
            }

            @Override
            public void onFailure(Call<ResponseInfo<List<Envanter>>> call, Throwable t) {
                view.showWarningDialog("Error", "callEnvanterListByQrCodeRoom->message: " + t.getMessage());
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
                responseResult.onReult(response, view);
            }

            @Override
            public void onFailure(Call<ResponseInfo<Envanter>> call, Throwable t) {
                view.showWarningDialog("Error", "callEnvanterByQrCode->message: " + t.getMessage());
            }
        });
    }


    public interface ResponseResult<T> {
        void onSuccess(T t);

        default void onReult(Response<ResponseInfo<T>> response, IMain view) {
            if (response != null) {
                ResponseInfo<T> responseInfo = response.body();
                if (responseInfo == null) {
                    String errorInfo = response.errorBody().source().buffer().readUtf8();
                    view.showWarningDialog("Hata", errorInfo);
                } else if (!responseInfo.getSuccess()) {
                    view.showWarningDialog(responseInfo.getStatusInfo());
                } else {
                    onSuccess(responseInfo.getResponse());
                    if (StaticUtils.successControl.isSuccessAll()) {
                        view.onSuccess();
                    }
                }
            }
        }
    }
}
