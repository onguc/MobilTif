package com.uniyaz.mobiltif.ui.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.uniyaz.mobiltif.R;
import com.uniyaz.mobiltif.data.domain.IPhoto;
import com.uniyaz.mobiltif.data.domain.PhotoRoom;
import com.uniyaz.mobiltif.data.enums.EnumPhotoLacation;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;


/**
 * Created by İrfan Öngüç on 19.05.2019
 */

public class RoomPhotFragment extends Fragment {
    List<IPhoto> photoList;
    PhotoIslem photoIslem;


    public static RoomPhotFragment getInstance() {
        RoomPhotFragment roomPhotFragment = new RoomPhotFragment();
        return roomPhotFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        photoList = getEmptyPhotoList();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_oda_fotograf, container, false);
        photoIslem = new PhotoIslem(EnumPhotoLacation.ROOM);
//        photoIslem.createView(view, getActivity(), this, photoList);
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PhotoIslem.CAPTURE_IMAGE && resultCode == RESULT_OK) {
            if (photoIslem.getFileUri() != null) {
                Uri uri = photoIslem.getFileUri();

                PhotoRoom photo = new PhotoRoom();
                photo.setFull(true);
                photo.setUri(uri);
//                photo.setIdRoom(room.getReferanceCode());
//                PhotoRoomRepo repo=new PhotoRoomRepo();
//                repo.add(photo);

                photoList.add(photo);
                photoIslem.refresh();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        photoIslem.startImageCaptureAfterPermission(requestCode, permissions, grantResults);
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//    }

    public List<IPhoto> getPhotoList() {
        return photoList;
    }

    public void addAndRefreshPhotoList(List<PhotoRoom> photoRoomList) {
        photoList = getEmptyPhotoList();
        if (photoRoomList != null && photoRoomList.size() > 0)
            photoList.addAll(photoRoomList);
        photoIslem.refresh();
    }

    public List<IPhoto> getEmptyPhotoList() {
        if (photoList == null) {
            photoList = new ArrayList<>();
            addEmptyPhoto();
        } else if (photoList.size() == 0) {
            addEmptyPhoto();
        } else {
            photoList.clear();
            addEmptyPhoto();
        }
        return photoList;
    }


    private void addEmptyPhoto() {
        if (photoList != null) {
            PhotoRoom photo = new PhotoRoom();
            photo.setFull(false);
            photoList.add(photo);
        }
    }

    public void clearPhotoAndRefresh() {
        getEmptyPhotoList();
        refresh();
    }

    private void refresh() {
        photoIslem.refresh();
    }
}
