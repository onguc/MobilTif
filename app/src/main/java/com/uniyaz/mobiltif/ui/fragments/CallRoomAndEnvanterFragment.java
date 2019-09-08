package com.uniyaz.mobiltif.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.uniyaz.mobiltif.R;
import com.uniyaz.mobiltif.listeners.RightDrawableOnClickListener;
import com.uniyaz.mobiltif.ui.activities.MainActivity;
import com.uniyaz.mobiltif.ui.components.CustomButtonSearch;
import com.uniyaz.mobiltif.utils.PermissionUtils;
import com.uniyaz.mobiltif.utils.TextCustomUtils;

public class CallRoomAndEnvanterFragment extends Fragment {

    private MainActivity mainActivity;
    private final int REQUEST_CODE_QR_FOR_ROOM = 1;
    private final int REQUEST_CODE_QR_FOR_ENVANTER = 2;

    public static CallRoomAndEnvanterFragment getNewInstance(MainActivity mainActivity) {
        CallRoomAndEnvanterFragment odaFragment = new CallRoomAndEnvanterFragment();
        odaFragment.mainActivity = mainActivity;
        return odaFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.popup_call_room_and_envanter, container, false);

        CustomButtonSearch btnCallRoom = view.findViewById(R.id.btnCallRoom);
        CustomButtonSearch btnCallEnvanter = view.findViewById(R.id.btnCallEnvanter);
        EditText etCallQrCodeOda = view.findViewById(R.id.etCallQrCodeOda);
        EditText etCallQrCodeDemirbas = view.findViewById(R.id.etCallQrCodeDemirbas);


        View.OnClickListener onClickListener = v -> {
            switch (v.getId()) {
                case R.id.btnCallRoom:
                    if (PermissionUtils.checkQrCodeRoomPermission(getActivity())) {
                        mainActivity.goScanActivity(REQUEST_CODE_QR_FOR_ROOM);
                    }
                    break;
                case R.id.btnCallEnvanter:
                    if (PermissionUtils.checkQrCodeEnvanterPermission(getActivity())) {
                        mainActivity.goScanActivity(REQUEST_CODE_QR_FOR_ENVANTER);
                    }
            }
        };
        btnCallRoom.setOnClickListener(onClickListener);
        btnCallEnvanter.setOnClickListener(onClickListener);

        etCallQrCodeOda.setOnTouchListener(new RightDrawableOnClickListener() {
            @Override
            public boolean onDrawableTouch(MotionEvent event, String qrCodeString) {
                if (TextCustomUtils.isDigitsOnly(qrCodeString)) {
                    int qrCode = Integer.parseInt(qrCodeString);
                    mainActivity.callByQrCode(REQUEST_CODE_QR_FOR_ROOM, qrCode);
                    return true;
                } else
                    return false;

            }
        });

        etCallQrCodeDemirbas.setOnTouchListener(new RightDrawableOnClickListener() {
            @Override
            public boolean onDrawableTouch(MotionEvent event, String qrCodeString) {
                if (TextCustomUtils.isDigitsOnly(qrCodeString)) {
                    int qrCode = Integer.parseInt(qrCodeString);
                    mainActivity.callByQrCode(REQUEST_CODE_QR_FOR_ENVANTER, qrCode);
                    return true;
                } else
                    return false;

            }
        });
        return view;
    }


}
