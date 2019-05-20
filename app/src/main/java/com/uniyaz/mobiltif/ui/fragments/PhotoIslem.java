package com.uniyaz.mobiltif.ui.fragments;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
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
import com.uniyaz.mobiltif.utils.PermissionUtils;

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
    Activity activity;
    Fragment fragment;
    Uri fileUri;
    PhotoAdapter adapter;
    EnumPhotoLacation enumPhotoLacation;


    public PhotoIslem(EnumPhotoLacation enumPhotoLacation) {
        this.enumPhotoLacation = enumPhotoLacation;
    }

    public void createView(View view, Activity activity, Fragment fragment, List<IPhoto> photoList) {
        this.activity = activity;
        this.fragment = fragment;

        RecyclerView recyclerView = view.findViewById(R.id.recylerview);

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
            public void capture() {
                startImageCapture();
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

    public void startImageCaptureAfterPermission(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults.length > 0) {
            boolean isGranted = isGranted(grantResults);
            if (isGranted) {
                if (requestCode == PermissionUtils.REQUEST_ID_CAMERA_PERMISSION) {
                    startImageCapture();
                }
            }
        }
    }

    private void startImageCapture() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        this.fileUri = createAndGetFileUri();
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        fragment.startActivityForResult(intent, CAPTURE_IMAGE);
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


    private Uri createAndGetFileUri() {
        try {
            File photoFile = createImageFile();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                fileUri = FileProvider.getUriForFile(activity.getApplicationContext(),
                        BuildConfig.APPLICATION_ID + ".provider",
                        photoFile);
            } else {
                fileUri = Uri.fromFile(photoFile);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileUri;
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

        void capture();
    }

}
