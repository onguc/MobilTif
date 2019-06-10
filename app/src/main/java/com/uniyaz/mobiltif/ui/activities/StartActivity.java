package com.uniyaz.mobiltif.ui.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.uniyaz.mobiltif.data.db.DbHelper;
import com.uniyaz.mobiltif.data.db.DbManager;
import com.uniyaz.mobiltif.data.domain.User;
import com.uniyaz.mobiltif.iface.IStartActivity;
import com.uniyaz.mobiltif.presenter.StartActivityPresenter;
import com.uniyaz.mobiltif.utils.StaticUtils;


/**
 * Created by İrfan Öngüç on 19.05.2019
 */

public class StartActivity extends AppCompatActivity implements IStartActivity {

    StartActivityPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initializeInstanceDb();


        goMainActivity();
//        goControlLoginActivity();
        finish();
    }

    private void goControlLoginActivity() {
        presenter = new StartActivityPresenter(this);
        User user = presenter.getSavedUser();
        if (user == null || user.isNull()) {
            goLoginActivity();
        } else {
            StaticUtils.user = user;
            goMainActivity();
        }
    }


    private void goLoginActivity() {
        Intent intent = new Intent(StartActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    private void goMainActivity() {
        Intent intent = new Intent(StartActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void showWarningDialog(String title, String statusInfo) {

    }

    private void initializeInstanceDb() {
        DbHelper dbHelper = new DbHelper(getApplicationContext());
        DbManager.initializeInstance(dbHelper);
    }
}
