package com.uniyaz.mobiltif.utils;

import android.app.Activity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.uniyaz.mobiltif.data.domain.Department;
import com.uniyaz.mobiltif.data.domain.Room;
import com.uniyaz.mobiltif.data.domain.Tasinir;
import com.uniyaz.mobiltif.data.domain.UserDto;
import com.uniyaz.mobiltif.data.dto.PersonelDto;
import com.uniyaz.mobiltif.iface.IMain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by İrfan Öngüç on 19.05.2019
 */

public class StaticUtils {

    public static IMain iMain;
    public static String kullaniciAdi = "uniyaz";
    private static List<Tasinir> tasinirList = new ArrayList<>();
    private static List<Department> departmentList = new ArrayList<>();
    private static List<Room> roomList = new ArrayList<>();
    public static String authTicket = null;
    public static UserDto userDto = null;

    public static SuccessControl successControl = new SuccessControl();

    public static List<Department> getDepartmentList() {
        if (departmentList == null)
            departmentList = new ArrayList<>();
        if (departmentList.size() == 0) {
            Department department = new Department();
            department.setName("Seçiniz...");
            department.setId(0);
            department.setCode("0");
            departmentList.add(department);
        }
        return departmentList;
    }

    public static List<Tasinir> getTasinirList() {
        if (tasinirList == null)
            tasinirList = new ArrayList<>();
        return tasinirList;
    }

    public static List<Room> getRoomList() {
        if (roomList == null)
            roomList = new ArrayList<>();
        return roomList;
    }


    public static void refreshDepartmentList(List<Department> newDepartmentList) {
        clearDepartment();
        getDepartmentList().addAll(newDepartmentList);
    }


    public static void refreshTasinirList(List<Tasinir> newTasinirList) {
        clearTasinir();
        getTasinirList().addAll(newTasinirList);
    }

    public static void refreshRoomList(List<Room> newRoomList) {
        clearRoom();
        getRoomList().addAll(newRoomList);
    }

    private static void clearDepartment() {
        if (departmentList == null) departmentList = new ArrayList<>();
        departmentList.clear();
    }

    private static void clearTasinir() {
        if (tasinirList == null) tasinirList = new ArrayList<>();
        tasinirList.clear();
    }

    private static void clearRoom() {
        if (roomList == null) roomList = new ArrayList<>();
        roomList.clear();
    }

    public static String getAuthorizationForTest() {
        return "applicationkey=FLX_EBELEDIYE,requestdate=2014-10-01T2:32:50+02:00,md5hashcode=61411bbfbd3675953aa1e3738ce8a5c0";
    }

    public static String getAuthorizationTicket() {
        return authTicket;
    }

    public static boolean isEmptyPersonelDto(PersonelDto personelDto) {
        return personelDto == null || personelDto.getId() == null;
    }

    public static void showProgressBar(ProgressBar progressBar) {
        progressBar.setVisibility(View.VISIBLE);
        Activity activity = (Activity) progressBar.getContext();
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    public static void hideProgressBar(ProgressBar progressBar) {
        progressBar.setVisibility(View.GONE);
        Activity activity = (Activity) progressBar.getContext();
        activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

}
