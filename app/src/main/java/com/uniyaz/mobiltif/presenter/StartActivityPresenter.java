package com.uniyaz.mobiltif.presenter;

import com.uniyaz.mobiltif.data.domain.User;
import com.uniyaz.mobiltif.iface.IStartActivity;
import com.uniyaz.mobiltif.utils.ObjectUtil;

/**
 * Created by İrfan Öngüç on 19.05.2019
 */

public class StartActivityPresenter {

    IStartActivity view;
    public static final String keyUser = "KEY_USER";
    ObjectUtil<User> objectUtil;

    public StartActivityPresenter(IStartActivity view) {
        this.view = view;
        objectUtil = new ObjectUtil<>(view.getApplicationContext());
    }

    public User getSavedUser() {
        return objectUtil.getObjectFromSharedPreferences(keyUser, User.class);
    }

    public void saveUser(User user) {
        objectUtil.saveObjectToSharedPreferences(keyUser, user);
    }
}
