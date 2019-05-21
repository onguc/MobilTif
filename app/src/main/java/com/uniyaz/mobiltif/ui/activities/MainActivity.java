package com.uniyaz.mobiltif.ui.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.zxing.integration.android.IntentIntegrator;
import com.uniyaz.mobiltif.R;
import com.uniyaz.mobiltif.data.domain.Department;
import com.uniyaz.mobiltif.data.domain.Envanter;
import com.uniyaz.mobiltif.data.domain.Room;
import com.uniyaz.mobiltif.data.domain.Tasinir;
import com.uniyaz.mobiltif.data.enums.EnumAP;
import com.uniyaz.mobiltif.data.enums.EnumBirim;
import com.uniyaz.mobiltif.iface.IMain;
import com.uniyaz.mobiltif.listeners.EditableTextWatcher;
import com.uniyaz.mobiltif.listeners.RightDrawableOnClickListener;
import com.uniyaz.mobiltif.presenter.MainPresenter;
import com.uniyaz.mobiltif.ui.components.CustomProgressBar;
import com.uniyaz.mobiltif.ui.fragments.EnvanterPhotoFragment;
import com.uniyaz.mobiltif.ui.fragments.RoomPhotFragment;
import com.uniyaz.mobiltif.utils.PermissionUtils;
import com.uniyaz.mobiltif.utils.TextCustomUtils;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by İrfan Öngüç on 19.05.2019
 */

public class MainActivity extends AppCompatActivity implements IMain {

    @BindView(R.id.etQrCodeRoom)
    EditText etQrCodeRoom;
    @BindView(R.id.spnrMudurluk)
    Spinner spnrMudurluk;
    @BindView(R.id.actvTasinirKoduVeMalzAdi)
    AutoCompleteTextView actvTasinirKdMlzmAdi;
    @BindView(R.id.etSayimNo)
    EditText etSayimNo;
    @BindView(R.id.etQrCodeEnvanter)
    EditText etQrCodeEnvanter;

    EditText etAciklama;
    @BindView(R.id.progressBar)
    CustomProgressBar progressBar;
//    @BindView(R.id.viewpager)
//    ViewPager viewPager;
//    @BindView(R.id.tabLayout)
//    TabLayout tabLayout;

    MainPresenter presenter;
    RoomPhotFragment roomPhotFragment;
    EnvanterPhotoFragment envanterPhotoFragment;

    private ArrayAdapter<EnumAP> durumuAdapter;
    private ArrayAdapter<EnumBirim> birimAdapter;
    private ArrayAdapter<Department> departmenAdapter;

    private Room registeredRoom;
    private Tasinir selectedTasinir = null;
    private Envanter selectedEnvanter;
    boolean isClickedEnvanterList = false;

    private final int REQUEST_CODE_QR_FOR_ROOM = 1;
    private final int REQUEST_CODE_QR_FOR_ENVANTER = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        selectedEnvanter = new Envanter();
        presenter = new MainPresenter(this);
        presenter.getTasinirList();

//        defineViews();
        addListeners();
        presenter.getDepartmentList();
    }

    Activity activity = this;

    private void addListeners() {

        etQrCodeRoom.setOnTouchListener(new RightDrawableOnClickListener() {
            @Override
            public boolean onDrawableTouch(MotionEvent event) {
                if (PermissionUtils.checkQrCodeRoomPermission(activity)) {
                    goScanActivity(REQUEST_CODE_QR_FOR_ROOM);
                }
                return true;
            }
        });

        etQrCodeRoom.addTextChangedListener((EditableTextWatcher) text -> {
            if (TextCustomUtils.isDigitsOnly(text)) {
                int qrCode = Integer.parseInt(text);
                registeredRoom = presenter.getRoomByQrCode(qrCode);

                if (registeredRoom == null || registeredRoom.getQrCode() == null) {
                    registeredRoom = null;
                    spnrMudurluk.setSelection(0);
                    spnrMudurluk.setEnabled(true);
                    roomPhotFragment.addAndRefreshPhotoList(null);
                } else {
                    spnrMudurluk.setSelection(departmenAdapter.getPosition(registeredRoom.getDepartment()));
                    spnrMudurluk.setEnabled(false);

//                    List<PhotoRoom> iPhotos = presenter.getPhotoRoomListByRoomId(qrCode);
//                    roomPhotFragment.addAndRefreshPhotoList(iPhotos);
                }
            }
        });

        etQrCodeEnvanter.setOnTouchListener(new RightDrawableOnClickListener() {
            @Override
            public boolean onDrawableTouch(MotionEvent event) {
                if (PermissionUtils.checkQrCodeEnvanterPermission(activity)) {
                    goScanActivity(REQUEST_CODE_QR_FOR_ENVANTER);
                }
                return true;
            }
        });

        etQrCodeEnvanter.addTextChangedListener((EditableTextWatcher) text -> {
            if (TextCustomUtils.isDigitsOnly(text)) {
                int qrCode = Integer.parseInt(text);
                if (!isClickedEnvanterList) {
//                    if (presenter.isEnvaterQrCodeExist(qrCode)) {
//                        showWarningDialog("Uyarı", qrCode + " QR Kodlu Demirbaş Zaten Kayıtlı!");
//                        etQrCodeEnvanter.setText("");
//                    }
                }
            }
            isClickedEnvanterList = false;
        });
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
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {

        if (requestCode == REQUEST_CODE_QR_FOR_ROOM || requestCode == REQUEST_CODE_QR_FOR_ENVANTER) {
            String qrCode = intent.getStringExtra("SCAN_RESULT");
            if (qrCode == null) {
                Toast.makeText(this, "uyarı boş", Toast.LENGTH_LONG).show();
            } else {
                if (TextCustomUtils.isDigitsOnly(qrCode)) {
                    if (requestCode == REQUEST_CODE_QR_FOR_ROOM) {
                        etQrCodeRoom.setText(qrCode);
                    } else if (requestCode == REQUEST_CODE_QR_FOR_ENVANTER) {
                        etQrCodeEnvanter.setText(qrCode);
                    }
                } else {
                    showWarningDialog("Uyumsuz QR Kodu!");
                }
            }
        }

        super.onActivityResult(requestCode, resultCode, intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    final int CAMERA_ID = 0;
    final String PROMPT = "Barkod Tara";

    private void goScanActivity(int code) {
        new IntentIntegrator(this).setRequestCode(code).setBeepEnabled(false).initiateScan();
//        IntentIntegrator intentIntegrator = new IntentIntegrator(this);
//        intentIntegrator
//                .setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
//                .setPrompt(PROMPT)
//                .setCameraId(CAMERA_ID)
//                .setBeepEnabled(false)
//                .setBarcodeImageEnabled(false)
//                .setRequestCode(code)
//                .initiateScan();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private String getSelectedTasinirValue() {
        return selectedTasinir.getTasinirKodu() + "     -       " + selectedTasinir.getName();
    }

    public boolean onClickBtnSync(MenuItem menuItem) {
        presenter.synch();
        return true;
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


    boolean onClickLogOut(MenuItem menuItem) {
        logOut();
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


    private Room saveAndGetRoom() {
        if (registeredRoom == null || registeredRoom.getQrCode() == null) {
            Department department = (Department) spnrMudurluk.getSelectedItem();
            String qrCodeString = etQrCodeRoom.getText().toString();
            Integer qrCode = Integer.parseInt(qrCodeString);

            Room room = new Room();
            room.setDepartment(department);
            room.setQrCode(qrCode);
            int returnedValue = presenter.saveRoom(room);
            if (returnedValue > 0)
                return room;
        }
        return registeredRoom;
    }


    @Override
    public void onSuccess() {

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

    }
}
