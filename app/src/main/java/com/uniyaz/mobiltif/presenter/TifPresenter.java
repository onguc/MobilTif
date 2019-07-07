package com.uniyaz.mobiltif.presenter;

import android.widget.ProgressBar;

import com.uniyaz.mobiltif.data.repo.DepartmentRepo;
import com.uniyaz.mobiltif.data.repo.RoomRepo;
import com.uniyaz.mobiltif.data.repo.TasinirRepo;
import com.uniyaz.mobiltif.iface.ITif;
import com.uniyaz.mobiltif.utils.ObjectUtil;

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


}
