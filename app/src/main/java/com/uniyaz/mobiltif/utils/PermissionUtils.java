package com.uniyaz.mobiltif.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

/**
 * Created by İrfan Öngüç on 19.05.2019
 */

public class PermissionUtils {

    public static final int REQUEST_ID_CAMERA_PERMISSION = 100;
    public static final int REQUEST_ID_QRCODE_ROOM_PERMISSION = 101;
    public static final int REQUEST_ID_READ_EXTERNAL_STORAGE = 102;
    public static final int REQUEST_ID_QRCODE_ENVANTER_PERMISSION = 103;


    public static void checkInternetPermission(Context context) {
        int permissionINTERNET = ContextCompat.checkSelfPermission(context, Manifest.permission.INTERNET);
        if (permissionINTERNET != PackageManager.PERMISSION_GRANTED) {
            AlertDialog.Builder builder = new AlertDialog
                    .Builder(context)
                    .setTitle("Hata")
                    .setMessage("İnternete erişim izni verilmemiş!!!")
                    .setPositiveButton("TAMAM", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    });
            builder.show();
        }
    }

    public static boolean checkCameraPermission(Activity activity, Fragment fragment) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (activity.checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED || activity.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                fragment.requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_ID_CAMERA_PERMISSION);  // fragment içindeki onActivityResult içine duşmesi için aşaıdaki ile değiştirildi.
//                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA}, REQUEST_ID_CAMERA_PERMISSION);
            } else {
                return true;
            }
        }
        return false;
    }

    public static boolean checkQrCodeRoomPermission(Activity activity) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (activity.checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                activity.requestPermissions(new String[]{Manifest.permission.CAMERA}, REQUEST_ID_QRCODE_ROOM_PERMISSION);  // fragment içindeki onActivityResult içine duşmesi için aşaıdaki ile değiştirildi.
//                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA}, REQUEST_ID_QRCODE_ROOM_PERMISSION);
            } else {
                return true;
            }
        }
        return false;
    }

    public static boolean checkQrCodeEnvanterPermission(Activity activity) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (activity.checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                activity.requestPermissions(new String[]{Manifest.permission.CAMERA}, REQUEST_ID_QRCODE_ENVANTER_PERMISSION);  // fragment içindeki onActivityResult içine duşmesi için aşaıdaki ile değiştirildi.
//                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA}, REQUEST_ID_QRCODE_ROOM_PERMISSION);
            } else {
                return true;
            }
        }
        return false;
    }

    public static boolean checkReadExternalStorage(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (activity.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                activity.requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_ID_READ_EXTERNAL_STORAGE);  // fragment içindeki onActivityResult içine duşmesi için aşaıdaki ile değiştirildi.
//                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_ID_READ_EXTERNAL_STORAGE);
            } else {
                return true;
            }
        }
        return false;
    }

    public static int getVersion() {
        return Build.VERSION.SDK_INT;
    }
}
