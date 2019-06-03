package com.uniyaz.mobiltif.ui.fragments;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.uniyaz.mobiltif.BuildConfig;
import com.uniyaz.mobiltif.R;
import com.uniyaz.mobiltif.data.domain.IPhoto;
import com.uniyaz.mobiltif.data.enums.EnumPhotoLacation;
import com.uniyaz.mobiltif.ui.adapters.PhotoAdapter;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by İrfan Öngüç on 19.05.2019
 */

public class PhotoIslem {
    String currentPhotoPath = "";
    public static final int CAPTURE_IMAGE = 222;
    Fragment fragment;
    Uri fileUri;
    PhotoAdapter adapter;
    EnumPhotoLacation enumPhotoLacation;


    public PhotoIslem(EnumPhotoLacation enumPhotoLacation) {
        this.enumPhotoLacation = enumPhotoLacation;
    }

    public void createView(RecyclerView recyclerView, Activity activity, List<IPhoto> photoList) {

        Listener listener = new Listener() {
            @Override
            public List<IPhoto> getData() {
                return photoList;
            }

            @Override
            public Activity getActivity() {
                return activity;
            }

            @Override
            public Fragment getFragment() {
                return fragment;
            }
        };

        adapter = new PhotoAdapter(listener);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity.getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    public void createView(RecyclerView recyclerView, Activity activity, Fragment fragment, List<IPhoto> photoList) {
        this.fragment = fragment;
        createView(recyclerView, activity, photoList);
    }


    private boolean isGranted(@NonNull int[] grantResults) {
        boolean isGranted = true;
        for (int grantResult : grantResults) {
            if (grantResult != PackageManager.PERMISSION_GRANTED) {
                isGranted = false;
                break;
            }
        }
        return isGranted;
    }



    private File createImageFile() throws IOException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String format = dateFormat.format(new Date());
        String imageFileName = enumPhotoLacation + "_" + format + ".jpg";

        File storageDemirbas = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "DEMIRBAS_SAYIM");
        if (!storageDemirbas.exists())
            storageDemirbas.mkdir();

        String directoryName = "FOTO";
        if (enumPhotoLacation == EnumPhotoLacation.ROOM) {
            directoryName = "ODA";
        } else if (enumPhotoLacation == EnumPhotoLacation.ENVANTER) {
            directoryName = "DEMIRBAS";
        }

        File storage = new File(storageDemirbas, directoryName);
        if (!storage.exists())
            storage.mkdir();

        File imageFile = new File(storage, imageFileName);

        currentPhotoPath = "file:" + imageFile.getAbsolutePath();
        return imageFile;
    }

    public Uri getFileUri() {
        return fileUri;
    }

    public void refresh() {
        if (adapter != null)
            adapter.notifyDataSetChanged();
    }

    public interface Listener {
        List<IPhoto> getData();

        Activity getActivity();

        Fragment getFragment();

    }

}
