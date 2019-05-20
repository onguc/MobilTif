package com.uniyaz.mobiltif.presenter;

import android.widget.ProgressBar;

import com.uniyaz.mobiltif.data.domain.Department;
import com.uniyaz.mobiltif.data.domain.ResponseInfo;
import com.uniyaz.mobiltif.data.domain.Room;
import com.uniyaz.mobiltif.data.domain.Tasinir;
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


    public void getTasinirList() {
        tasinirRepo = new TasinirRepo();
        List<Tasinir> tasinirList = tasinirRepo.getAll();
        if (tasinirList == null || tasinirList.size() == 0) {
            fillTasinirFromRemote();
        } else {
            StaticUtils.refreshTasinirList(tasinirList);
            view.notifyTasinir();
        }
    }

    public void getRoomList() {
        roomRepo = new RoomRepo();
        List<Room> roomList = roomRepo.getAll();
        if (roomList == null || roomList.size() == 0) {
            fillRoomFromRemote();
        } else {
            StaticUtils.refreshRoomList(roomList);
        }
    }

    public Room getRoomByReferanceCode(String referanceCode) {
        if (roomRepo == null) roomRepo = new RoomRepo();
        return roomRepo.getById(referanceCode);
    }

    public Tasinir getTasinirById(Long idTasinir) {
        return tasinirRepo.getById(String.valueOf(idTasinir));
    }

    public String getAuthTicket() {
        return objectUtil.getObjectFromSharedPreferences(keyAuthorizationTicket, String.class);
    }

    public void synch() {
        fillTasinirFromRemote();
        fillDepartmentFromRemote();
//        fillRoomFromRemote();
    }


    private void fillDepartmentFromRemote() {
        RequestBody bodyServisTuru = RequestBody.create(MediaType.parse("text/plain"), "enumKbsServisTuru=MUDURLUK");
        Call<ResponseInfo<List<Department>>> deparmentListCall = RetrofitInterface.retrofitInterface.getDepartmList(getAuthTicket(), bodyServisTuru);
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

    private void fillRoomFromRemote() {
        Call<ResponseInfo<List<Room>>> roomListCall = RetrofitInterface.retrofitInterface.getRoomList(getAuthTicket());
        roomListCall.enqueue(new Callback<ResponseInfo<List<Room>>>() {
            @Override
            public void onResponse(Call<ResponseInfo<List<Room>>> call, Response<ResponseInfo<List<Room>>> response) {
                ResponseResult<List<Room>> responseResult = roomList -> {
                    roomRepo.synchronizeAll(roomList);
                    StaticUtils.refreshRoomList(roomList);
                    StaticUtils.successControl.setSuccessRoom(true);
                };
                responseResult.onReult(response, view);
            }

            @Override
            public void onFailure(Call<ResponseInfo<List<Room>>> call, Throwable t) {
                view.showWarningDialog("Erro In OnFailure Room:\n " + t.getMessage());
            }
        });
    }

    private void fillTasinirFromRemote() {
        Call<ResponseInfo<List<Tasinir>>> tasinirListCall = RetrofitInterface.retrofitInterface.getTasinirList(getAuthTicket());
        tasinirListCall.enqueue(new Callback<ResponseInfo<List<Tasinir>>>() {
            @Override
            public void onResponse(Call<ResponseInfo<List<Tasinir>>> call, Response<ResponseInfo<List<Tasinir>>> response) {
                ResponseResult<List<Tasinir>> responseResult = tasinirList -> {
                    if (tasinirRepo == null) tasinirRepo = new TasinirRepo();
                    tasinirRepo.synchronizeAll(tasinirList);
                    StaticUtils.refreshTasinirList(tasinirList);
                    StaticUtils.successControl.setSuccessTasinir(true);
                };

                responseResult.onReult(response, view);
            }

            @Override
            public void onFailure(Call<ResponseInfo<List<Tasinir>>> call, Throwable t) {
                view.showWarningDialog("Error In OnFailure Tasinir:\n " + t.getMessage());
            }
        });
    }

    public int saveRoom(Room room) {
        return new RoomRepo().synchronize(room);
    }


    public Room getRoomByQrCode(Integer qrCodeValue) {
        return new RoomRepo().getRoomByQrCode(qrCodeValue);
    }


    private interface ResponseResult<T> {
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
