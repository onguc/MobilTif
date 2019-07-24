package com.uniyaz.mobiltif.ui.activities;

import android.app.Activity;
import android.app.AlertDialog;
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
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.uniyaz.mobiltif.R;
import com.uniyaz.mobiltif.data.domain.Envanter;
import com.uniyaz.mobiltif.data.domain.Room;
import com.uniyaz.mobiltif.databinding.ActivityMainBinding;
import com.uniyaz.mobiltif.iface.IMain;
import com.uniyaz.mobiltif.presenter.MainPresenter;
import com.uniyaz.mobiltif.ui.fragments.DemirbasDetayFragment;
import com.uniyaz.mobiltif.ui.fragments.OdaFragment;
import com.uniyaz.mobiltif.utils.PermissionUtils;
import com.uniyaz.mobiltif.utils.TextCustomUtils;
import com.uniyaz.mobiltif.viewmodel.MainViewModel;

import java.util.Map;


/**
 * Created by İrfan Öngüç on 19.05.2019
 */

public class MainActivity extends AppCompatActivity implements IMain {


//    @BindView(R.id.progressBar)
//    CustomProgressBar progressBar;

//    @BindView(R.id.toolbar)
//    Toolbar toolbar;

    MainPresenter presenter;

    private final int REQUEST_CODE_QR_FOR_ROOM = 1;
    private final int REQUEST_CODE_QR_FOR_ENVANTER = 2;

    private FragmentManager fragmentManager;
    DemirbasDetayFragment demirbasDetayFragment;
    OdaFragment odaFragment;
    PopupWindow popupWindow;
    ActivityMainBinding activityMainBinding;
    MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new MainViewModel(this);
//        ActivityMainBinding activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.setViewModel(viewModel);


        presenter = new MainPresenter(this);
        fragmentManager = getSupportFragmentManager();
        setSupportActionBar(activityMainBinding.toolbar);
        defineCallRoomAndEnvanter();
        /**
         * view hemen yüklemediği için yüklenmesini bekliyoruz.
         * Yükledikten sonra popup gösterilecek.
         */
        activityMainBinding.includedContentMain.clayout.post(() -> {
            showPopupCallRoomAndEnvanter();
        });
    }


    private void defineCallRoomAndEnvanter() {
        boolean focusable = true; // lets taps outside the popup also dismiss it
        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        View popupView = getLayoutInflater().inflate(R.layout.popup_call_room_and_envanter, null);
        popupWindow = new PopupWindow(popupView, width, height, focusable);

        Button btnCallRoom = popupView.findViewById(R.id.btnCallRoom);
        Button btnCallEnvanter = popupView.findViewById(R.id.btnCallEnvanter);

        View.OnClickListener onClickListener = v -> {
            switch (v.getId()) {
                case R.id.btnCallRoom:
                    if (PermissionUtils.checkQrCodeRoomPermission(activity)) {
                        goScanActivity(REQUEST_CODE_QR_FOR_ROOM);
                    }
                    break;
                case R.id.btnCallEnvanter:
                    if (PermissionUtils.checkQrCodeEnvanterPermission(activity)) {
                        goScanActivity(REQUEST_CODE_QR_FOR_ENVANTER);
                    }
            }
        };
        btnCallRoom.setOnClickListener(onClickListener);
        btnCallEnvanter.setOnClickListener(onClickListener);
    }

    private void showPopupCallRoomAndEnvanter() {
        popupWindow.showAtLocation(activityMainBinding.includedContentMain.clayout, Gravity.CENTER, 0, 0);
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
        if (code == REQUEST_CODE_QR_FOR_ENVANTER) {
            presenter.callEnvanterByQrCode("61");
        } else {
            presenter.callEnvanterListByQrCodeRoom("61");
        }
//        new IntentIntegrator(this)
//                .setRequestCode(code)
//                .setBeepEnabled(false)
//                .initiateScan();
    }

    @Override
    protected void onPause() {
        super.onPause();
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

//    private void showSnackbar(String message) {
//        Snackbar.make(findViewById(R.id.constraintLayout), message, Snackbar.LENGTH_LONG)
//                .show();
//    }


    @Override
    public void onSuccess() {
        viewModel.hideProgressBar();
    }

    public void onSuccessForEnvater(Envanter envanter) {
        viewModel.hideProgressBar();

        String titleDemirbarDetay = getString(R.string.toolbar_title_demirbas);
        viewModel.setTitleToolbar(titleDemirbarDetay);

        demirbasDetayFragment = DemirbasDetayFragment.getNewInstance(envanter);
        startFragmentByBackStack(demirbasDetayFragment);
        hidePopupCallRoomAndEnvanter();
    }

    @Override
    public void onSuccessForRoom(Room room) {
        hidePopupCallRoomAndEnvanter();
        viewModel.hideProgressBar();
        String titleDemirbarDetay = getString(R.string.toolbar_title_demirbas_list);
        viewModel.setTitleToolbar(titleDemirbarDetay);

        odaFragment = OdaFragment.getNewInstance(this, room);
        startFragmentByBackStack(odaFragment);
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
        goLoginActivity();
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void showProgressBar() {
        hidePopupCallRoomAndEnvanter();
        viewModel.showProgressBar();
    }

    @Override
    public void showWarningDialog(String title, String explanation) {
        viewModel.hideProgressBar();
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(explanation)
                .show();
    }

    public void startFragmentByBackStack(Fragment fragment) {
        if (fragment != null) {
            final FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction
                    .replace(R.id.frame_layout, fragment, "fragment")
                    .addToBackStack("fragment")
                    .commit();
            fragmentManager.executePendingTransactions();
        }
    }

    public boolean goEnvanterDetayFragment(Envanter envanter) {
        onSuccessForEnvater(envanter);
        return true;
    }

    public boolean goClickOnlineTifIslem(){
        Map<Integer, Envanter> selectedEnvanterlist = null;
        return true;
    }
//    public boolean onClickCallPopup(MenuItem menuItem) {
//        showPopupCallRoomAndEnvanter();
//        return true;
//    }
}
