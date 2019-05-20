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
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

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
import com.uniyaz.mobiltif.utils.StaticUtils;
import com.uniyaz.mobiltif.utils.TextCustomUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


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
    @BindView(R.id.spnrDurumu)
    Spinner spnrDurumu;
    @BindView(R.id.spnrBirimi)
    Spinner spnrBirimi;
    @BindView(R.id.etMiktar)
    EditText etMiktar;
    @BindView(R.id.etAciklama)
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


//    private void defineViews() {
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        departmenAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, StaticUtils.getDepartmentList());
//        spnrMudurluk.setAdapter(departmenAdapter);
//        TasinirAdapter tasinirAdapter = new TasinirAdapter(actvTasinirKdMlzmAdi, getTasinirList(), this);
//        actvTasinirKdMlzmAdi.setAdapter(tasinirAdapter);
//        actvTasinirKdMlzmAdi.setOnItemClickListener(((parent, view, position, id) -> {
//            selectedTasinir = tasinirAdapter.getItem(position);
//            String text = getSelectedTasinirValue();
//            actvTasinirKdMlzmAdi.setText(text);
//        }));
//        actvTasinirKdMlzmAdi.addTextChangedListener((EditableTextWatcher) text -> {
//            if (text == null || "".equals(text)) {
//                actvTasinirKdMlzmAdi.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
//            } else {
//                actvTasinirKdMlzmAdi.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_clear, 0);
//            }
//        });
//        actvTasinirKdMlzmAdi.setOnTouchListener(new RightDrawableOnClickListener() {
//            @Override
//            public boolean onDrawableTouch(MotionEvent event) {
//                actvTasinirKdMlzmAdi.setText(null);
//                return true;
//            }
//        });
//
//
//        durumuAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, EnumAP.values());
//        spnrDurumu.setAdapter(durumuAdapter);
//        birimAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, EnumBirim.values());
//        spnrBirimi.setAdapter(birimAdapter);
//
//        setupViewPager(viewPager);
//        tabLayout.setupWithViewPager(viewPager);
//    }

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
                        setVisibleProgressBar(true);
//                        presenter.sendToRemote();
                    }
                })
                .setNegativeButton("İptal", null)
                .show();

        return true;
    }


//    boolean onClickBtnShowEnvanterList(MenuItem menuItem) {
//        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
//        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
//        boolean focusable = true; // lets taps outside the popup also dismiss it
//
//        List<Envanter> envanterList = presenter.getEnvanterList();
//        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
//        View popupView = inflater.inflate(R.layout.popup_recycler_envanter, null);
//        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
//
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity.getApplicationContext());
//
//        RecyclerView recyclerView = popupView.findViewById(R.id.recylerviewEnvanter);
//
//        EnvanterAdapterListener listener = getEnvanterAdapterListener(envanterList, popupWindow);
//
//        EnvanterAdapter adapter = new EnvanterAdapter(listener);
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDeleteCallback(adapter));
//        itemTouchHelper.attachToRecyclerView(recyclerView);
//
//
//        popupWindow.showAtLocation(actvTasinirKdMlzmAdi, Gravity.CENTER, 0, 0);
//
////        final View popupView = LayoutInflater.from(this).inflate(R.layout.popup_recycler_envanter, null);
////        final PopupWindow popupWindow = new PopupWindow(popupView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
////        RecyclerView recyclerView = popupView.findViewById(R.id.recylerviewEnvanter);
////        EnvanterAdapter adapter = new EnvanterAdapter(envanterList, this);
////        recyclerView.setAdapter(adapter);
////        popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);
//        return true;
//    }

    boolean onClickLogOut(MenuItem menuItem) {
        logOut();
        return true;
    }


    private void goLoginActivity() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private EnvanterAdapterListener getEnvanterAdapterListener(List<Envanter> envanterList, PopupWindow popupWindow) {
        return new EnvanterAdapterListener() {
            @Override
            public List<Envanter> getEnvanterList() {
                return envanterList;
            }

            @Override
            public Activity getActivity() {
                return MainActivity.this.getActivity();
            }

            @Override
            public void fillEnvanter(Envanter envanter) {
                fillClickedEnvanter(envanter);
            }

            @Override
            public void dismissPopupWindow() {
                popupWindow.dismiss();
            }
        };
    }

//    public class SwipeToDeleteCallback extends ItemTouchHelper.SimpleCallback {
//        private EnvanterAdapter mAdapter;
//
//        public SwipeToDeleteCallback(EnvanterAdapter adapter) {
//            super(0, ItemTouchHelper.RIGHT);
//            mAdapter = adapter;
//            Drawable icon = ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_trash);
//        }
//
//        @Override
//        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//            return false;
//        }
//
//        @Override
//        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//            int position = viewHolder.getAdapterPosition();
//            mAdapter.deleteItem(position);
//        }
//    }


//    private boolean isPhotoSizeCorrect() {
//        List<IPhoto> photoRoomList = roomPhotFragment.getPhotoList();
//        if (photoRoomList.size() <= PhotoAdapter.MIN_PHOTO_ROOM_COUNT) {
//            showWarningDialog("Oda İçin En Az 1 Fotoğraf Çekmelisiniz!");
//            return false;
//        }
//        if (photoRoomList.size() > PhotoAdapter.MAX_PHOTO_ROOM_COUNT+1) {
//            showWarningDialog("Oda İçin En Fazla 2 Fotoğraf Çekebilirsiniz!");
//            return false;
//        }
//
//        List<IPhoto> photoEnvanterList = envanterPhotoFragment.getPhotoList();
//        if (photoEnvanterList.size() <= PhotoAdapter.MIN_PHOTO_ENVATNER_COUNT) {
//            showWarningDialog("Demirbaş İçin En Az 2 Fotoğraf Çekmelisiniz!");
//            return false;
//        } else if (photoEnvanterList.size() > PhotoAdapter.MAX_PHOTO_ENVANTER_COUNT + 1) {
//            showWarningDialog("Demirbaş İçin En En Fazla 5 Fotoğraf Çekebilirsiniz!");
//            return false;
//        }
//        return true;
//    }

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

    @OnClick(R.id.btnKaydet)
    void onClickBtnSave() {

        if (!isValidateAllFields()) return;

        registeredRoom = saveAndGetRoom();


        String sayimNoString = etSayimNo.getText().toString();
        Long sayimNo = null;
        if (sayimNoString != null && !"".equals(sayimNoString.trim())) {
            sayimNo = Long.parseLong(sayimNoString);
        }

        EnumAP durumu = (EnumAP) spnrDurumu.getSelectedItem();
        EnumBirim birim = (EnumBirim) spnrBirimi.getSelectedItem();
        Date sayimTarihi = new Date();
        long idPersonel = 66;

        String countString = etMiktar.getText().toString();
        int count = 1;
        if (TextCustomUtils.isDigitsOnly(countString))
            count = Integer.parseInt(countString);

        String qrCodeEnvanterString = etQrCodeEnvanter.getText().toString();
        if (!TextCustomUtils.isDigitsOnly(qrCodeEnvanterString)) {
            return;
        }
        long qrCodeEnvanter = Long.parseLong(qrCodeEnvanterString);

        selectedEnvanter.setIdRoom(registeredRoom.getQrCode());
        selectedEnvanter.setKodTasinir(selectedTasinir.getTasinirKodu());
        selectedEnvanter.setSayimNo(sayimNo);
        selectedEnvanter.setCount(count);
        selectedEnvanter.setQrCode(qrCodeEnvanterString);
        selectedEnvanter.setDurumu(durumu);
        selectedEnvanter.setBirimi(birim);
        selectedEnvanter.setSayimTarihi(sayimTarihi);
        selectedEnvanter.setIdSayimYapanPersonel(0l);
        selectedEnvanter.setAciklama(etAciklama.getText().toString());
        selectedEnvanter.setIdSayimYapanPersonel(idPersonel);
//        long idEnvanter = presenter.saveEnvanter(selectedEnvanter);
//        List<IPhoto> photoList1 = envanterPhotoFragment.getPhotoList();
//        for (IPhoto photo : photoList1) {
//            if (photo.isFull() && photo.getId() == null) {
//                PhotoEnvanter photoEnvanter = (PhotoEnvanter) photo;
//                photoEnvanter.setIdEnvanter(idEnvanter);
//                presenter.savePhotoEnvanter(photoEnvanter);
//            }
//        }
        clearFields();
        showSnackbar("Kayıt Başarılı");
    }

    @OnClick(R.id.btnTemizle)
    void clearFields() {
//        etQrCodeRoom.setText("");
//        spnrMudurluk.setSelection(0);
        actvTasinirKdMlzmAdi.setText(null);
        etSayimNo.setText("");
        etQrCodeEnvanter.setText("");
        spnrDurumu.setSelection(durumuAdapter.getPosition(EnumAP.AKTIF));
        spnrBirimi.setSelection(birimAdapter.getPosition(EnumBirim.ADET));
        etMiktar.setText("");
        etAciklama.setText("");
        selectedEnvanter = new Envanter();
        envanterPhotoFragment.clearPhotoAndRefresh();
    }

    @OnClick(R.id.btnDoldur)
    void fillFieldsGecici() {


        Department department = new Department();
        department.setCode("010304");
        department.setName("YAZI İŞLERİ MÜDÜRLÜĞÜ");
        Room room = new Room();
        room.setDepartment(department);
        room.setQrCode(24);

        Tasinir tasinir = new Tasinir();
        tasinir.setTasinirKodu(25302010101l);
        tasinir.setName("Toprak İşleme Makineleri");
        selectedTasinir = tasinir;
        Envanter envanter = new Envanter();
        envanter.setIdRoom(room.getQrCode());
        envanter.setKodTasinir(25302010104l);
        envanter.setSayimNo(2l);
        envanter.setQrCode("2");
        envanter.setDurumu(EnumAP.AKTIF);
        envanter.setBirimi(EnumBirim.ADET);
        envanter.setSayimTarihi(new Date());
        envanter.setIdSayimYapanPersonel(0l);
        envanter.setAciklama("Açıklama ");

        etQrCodeRoom.setText(String.valueOf(room.getQrCode()));
        spnrMudurluk.setSelection(departmenAdapter.getPosition(room.getDepartment()));
        actvTasinirKdMlzmAdi.setText(getSelectedTasinirValue());
        etSayimNo.setText(String.valueOf(envanter.getSayimNo()));
        etQrCodeEnvanter.setText(envanter.getQrCode());
        spnrDurumu.setSelection(durumuAdapter.getPosition(envanter.getDurumu()));
        spnrBirimi.setSelection(birimAdapter.getPosition(envanter.getBirimi()));
        etMiktar.setText("1");
        etAciklama.setText(envanter.getAciklama());
    }


    void fillClickedEnvanter(Envanter envanter) {
        isClickedEnvanterList = true;
        selectedEnvanter = envanter;

        Room room = this.selectedEnvanter.getRoom();
        selectedTasinir = selectedEnvanter.getTasinir();
        Integer count = selectedEnvanter.getCount();
        String countString = "1";
        if (count != null)
            countString = String.valueOf(count);


        etQrCodeRoom.setText(String.valueOf(room.getQrCode()));
        spnrMudurluk.setSelection(departmenAdapter.getPosition(room.getDepartment()));
        actvTasinirKdMlzmAdi.setText(getSelectedTasinirValue());
        etSayimNo.setText(String.valueOf(selectedEnvanter.getSayimNo()));
        etQrCodeEnvanter.setText(selectedEnvanter.getQrCode());
        spnrDurumu.setSelection(durumuAdapter.getPosition(selectedEnvanter.getDurumu()));
        spnrBirimi.setSelection(birimAdapter.getPosition(selectedEnvanter.getBirimi()));
        etMiktar.setText(countString);
        etAciklama.setText(selectedEnvanter.getAciklama());

//        List<PhotoEnvanter> departmentPhotoList = presenter.getDepartmentPhotoListByEnvanterId(selectedEnvanter.getIdString());
//        envanterPhotoFragment.refreshPhotoList(departmentPhotoList);

    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        roomPhotFragment = RoomPhotFragment.getInstance();
        envanterPhotoFragment = EnvanterPhotoFragment.getInstance();
        adapter.addFragment(roomPhotFragment, "Oda");
        adapter.addFragment(envanterPhotoFragment, "Demirbaş");
        viewPager.setAdapter(adapter);
    }

//    private void setupTabIcons() {
//        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
//        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
//    }

    @Override
    public void onSuccess() {
        notifyDepartment();
        setVisibleProgressBar(false);
    }

    @Override
    public void onSuccess(String message) {
        showWarningDialog("Başarılı", message);
        setVisibleProgressBar(false);
    }

    @Override
    public void notifyDepartment() {
        departmenAdapter.notifyDataSetChanged();
    }

    @Override
    public void notifyTasinir() {

    }

    @Override
    public void logOut() {
        StaticUtils.authTicket = null;
        StaticUtils.userDto = null;
        goLoginActivity();
    }


    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void showWarningDialog(String title, String statusInfo) {
        setVisibleProgressBar(false);
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(statusInfo)
                .show();

    }

    private boolean isValidateAllFields() {
        if (!isValidateQrCodeRoom()) return false;
        if (!isValidateSpnrMudurluk()) return false;
        if (!isValidateTasinirKdMlzmAdi()) return false;

        return true;
    }


    private boolean isValidateQrCodeRoom() {
        if (TextCustomUtils.isFullEmpty(etQrCodeRoom.getText())) {
            etQrCodeRoom.setError("Oda No Qr Kod Boş Olamaz!");
            requestFocus(etQrCodeRoom);
            showSnackbar("Oda No QRCode seçiniz!");
            return false;
        } else {
            etQrCodeRoom.setError(null);
            return true;
        }
    }

    private boolean isValidateSpnrMudurluk() {
        Object selectedItem = spnrMudurluk.getSelectedItem();
        Object itemZero = spnrMudurluk.getAdapter().getItem(0);
        TextView textView = (TextView) spnrMudurluk.getSelectedView();
        if (itemZero == selectedItem) {
            textView.setError("Lütfen Müdürlük Seçiniz!");
            requestFocus(spnrMudurluk);
            return false;
        } else {
            textView.setError(null);
            return true;
        }
    }

    private boolean isValidateTasinirKdMlzmAdi() {
        if (TextCustomUtils.isFullEmpty(actvTasinirKdMlzmAdi.getText())) {
            actvTasinirKdMlzmAdi.setError("Lütfen Taşınır Seçiniz!");
            requestFocus(actvTasinirKdMlzmAdi);
            return false;
        } else {
            actvTasinirKdMlzmAdi.setError(null);
            return true;
        }
    }


    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private void setVisibleProgressBar(boolean visible) {
        if (visible) {
            progressBar.setVisibility(View.VISIBLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        } else {
            progressBar.setVisibility(View.GONE);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


    public interface EnvanterAdapterListener {
        List<Envanter> getEnvanterList();

        Activity getActivity();

        void fillEnvanter(Envanter envanter);

        void dismissPopupWindow();
    }


}
