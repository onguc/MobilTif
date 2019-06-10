package com.uniyaz.mobiltif.ui.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.snackbar.Snackbar;
import com.google.zxing.integration.android.IntentIntegrator;
import com.uniyaz.mobiltif.BR;
import com.uniyaz.mobiltif.R;
import com.uniyaz.mobiltif.data.domain.Envanter;
import com.uniyaz.mobiltif.iface.IMain;
import com.uniyaz.mobiltif.presenter.MainPresenter;
import com.uniyaz.mobiltif.ui.components.CustomProgressBar;
import com.uniyaz.mobiltif.ui.fragments.DemirbasDetayFragment;
import com.uniyaz.mobiltif.ui.fragments.DemirbasListFragment;
import com.uniyaz.mobiltif.utils.PermissionUtils;
import com.uniyaz.mobiltif.utils.TextCustomUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by İrfan Öngüç on 19.05.2019
 */

public class MainActivity extends AppCompatActivity implements IMain {


    @BindView(R.id.progressBar)
    CustomProgressBar progressBar;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    MainPresenter presenter;

    private final int REQUEST_CODE_QR_FOR_ROOM = 1;
    private final int REQUEST_CODE_QR_FOR_ENVANTER = 2;

    private FragmentManager fragmentManager;
    DemirbasDetayFragment demirbasDetayFragment;
    DemirbasListFragment demirbasListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        ButterKnife.bind(this);
        ViewDataBinding viewDataBinding1 = DataBindingUtil.setContentView(this, R.layout.activity_main);

        presenter = new MainPresenter(this);
        fragmentManager = getSupportFragmentManager();
        setSupportActionBar(toolbar);
        defineCallRoomAndEnvanter();
        presenter.getDepartmentList();
//        showPopupCallRoomAndEnvanter();

    }

    PopupWindow popupWindow;
    private int clickedButton = 0;

    private void defineCallRoomAndEnvanter() {
        boolean focusable = true; // lets taps outside the popup also dismiss it
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        View popupView = getLayoutInflater().inflate(R.layout.popup_call_room_and_envanter, null);
        popupWindow = new PopupWindow(popupView, width, height, focusable);

        Button btnCallRoom = popupView.findViewById(R.id.btnCallRoom);
        Button btnCallEnvanter = popupView.findViewById(R.id.btnCallEnvanter);

        View.OnClickListener onClickListener = v -> {
            switch (v.getId()) {
                case R.id.btnCallRoom:
                    clickedButton = 1;
                    if (PermissionUtils.checkQrCodeRoomPermission(activity)) {
                        goScanActivity(REQUEST_CODE_QR_FOR_ROOM);
                    }
                    break;
                case R.id.btnCallEnvanter:
                    clickedButton = 2;
                    if (PermissionUtils.checkQrCodeEnvanterPermission(activity)) {
                        goScanActivity(REQUEST_CODE_QR_FOR_ENVANTER);
                    }
            }
        };
        btnCallRoom.setOnClickListener(onClickListener);
        btnCallEnvanter.setOnClickListener(onClickListener);
    }

    private void showPopupCallRoomAndEnvanter() {
        popupWindow.showAtLocation(findViewById(R.id.clayout), Gravity.CENTER, 0, 0);
    }

    private void hidePopupCallRoomAndEnvanter() {
        popupWindow.dismiss();
    }

    Activity activity = this;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (resultCode != RESULT_CANCELED)
            if (requestCode == REQUEST_CODE_QR_FOR_ROOM || requestCode == REQUEST_CODE_QR_FOR_ENVANTER) {
                String qrCode = intent.getStringExtra("SCAN_RESULT");
                if (qrCode == null) {
                    Toast.makeText(this, "QrCode Değeri Boş Geliyor", Toast.LENGTH_LONG).show();
                } else {
                    if (TextCustomUtils.isDigitsOnly(qrCode)) {
                        if (requestCode == REQUEST_CODE_QR_FOR_ROOM) {
                            presenter.callEnvanterListByQrCodeRoom(qrCode);
                        } else if (requestCode == REQUEST_CODE_QR_FOR_ENVANTER) {
                            presenter.callEnvanterByQrCode(qrCode);
                        }
                    } else {
                        showWarningDialog("Uyumsuz QR Kodu!");
                    }
                }
            }

        super.onActivityResult(requestCode, resultCode, intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PermissionUtils.REQUEST_ID_QRCODE_ROOM_PERMISSION) {
            if (grantResults.length > 0) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    goScanActivity(REQUEST_CODE_QR_FOR_ROOM);
                }
            }
        } else if (requestCode == PermissionUtils.REQUEST_ID_QRCODE_ENVANTER_PERMISSION) {
            if (grantResults.length > 0) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    goScanActivity(REQUEST_CODE_QR_FOR_ENVANTER);
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

        }
        return super.onOptionsItemSelected(item);
    }

    final int CAMERA_ID = 0;
    final String PROMPT = "Barkod Tara";

    private void goScanActivity(int code) {
        new IntentIntegrator(this)
                .setRequestCode(code)
                .setBeepEnabled(false)
                .initiateScan();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    boolean onClickBtnSendRemote(MenuItem menuItem) {

        new AlertDialog
                .Builder(this)
                .setTitle("Uyarı")
                .setMessage("Sunucuya Göndermek İstediğinizden Emin misiniz?")
                .setPositiveButton("Gönder", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
//                        setVisibleProgressBar(true);
//                        presenter.sendToRemote();
                    }
                })
                .setNegativeButton("İptal", null)
                .show();

        return true;
    }


    public boolean onClickLogOut(MenuItem menuItem) {
        logOut();
        return true;
    }

    public boolean onClickCallPopup(MenuItem menuItem) {
        showPopupCallRoomAndEnvanter();
        return true;
    }


    private void goLoginActivity() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void showSnackbar(String message) {
        Snackbar.make(findViewById(R.id.constraintLayout), message, Snackbar.LENGTH_LONG)
                .show();
    }


    @Override
    public void onSuccess() {

    }

    @Override
    public void onSuccessForEnvater(Envanter envanter) {
        demirbasDetayFragment = DemirbasDetayFragment.getNewInstance(envanter);
        startFragmentByBackStack(demirbasDetayFragment);
    }

    @Override
    public void onSuccessForRoom(List<Envanter> envanterList) {
        demirbasListFragment = DemirbasListFragment.getNewInstance(envanterList);
        startFragmentByBackStack(demirbasListFragment);
    }

    @Override
    public void onSuccess(String message) {

    }

    @Override
    public void notifyDepartment() {

    }

    @Override
    public void notifyTasinir() {

    }

    @Override
    public void logOut() {

    }

    @Override
    public Activity getActivity() {
        return null;
    }

    @Override
    public void showWarningDialog(String title, String explanation) {
//        setVisibleProgressBar(false);
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(explanation)
                .show();
    }

    private void startFragmentByBackStack(Fragment fragment) {
        if (fragment != null) {
            final FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction
                    .replace(R.id.frame_layout, fragment, "fragment")
                    .addToBackStack("fragment")
                    .commit();
            fragmentManager.executePendingTransactions();
        }
    }
}
