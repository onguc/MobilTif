package com.uniyaz.mobiltif.ui.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.uniyaz.mobiltif.R;
import com.uniyaz.mobiltif.data.domain.IPhoto;
import com.uniyaz.mobiltif.data.enums.EnumPhotoLacation;
import com.uniyaz.mobiltif.ui.components.TouchImageView;
import com.uniyaz.mobiltif.ui.fragments.PhotoIslem;
import com.uniyaz.mobiltif.utils.PermissionUtils;

import java.io.FileDescriptor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by İrfan Öngüç on 19.05.2019
 */

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.MyViewHolder> {

    private List<IPhoto> photoList;
    private LayoutInflater inflater;
    private PhotoIslem.Listener listener;

    public static final int MIN_PHOTO_ROOM_COUNT = 1;
    public static final int MAX_PHOTO_ROOM_COUNT = 2;
    public static final int MAX_PHOTO_ENVANTER_COUNT = 5;
    public static final int MIN_PHOTO_ENVATNER_COUNT = 2;

    public PhotoAdapter(PhotoIslem.Listener listener) {
        if (listener == null) {
            throw new IllegalStateException("listener null olamaz!");
        }
        this.listener = listener;
        inflater = LayoutInflater.from(listener.getActivity().getApplicationContext());
        this.photoList = listener.getData();
        if (photoList == null) photoList = new ArrayList<>();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_photo_card, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        IPhoto selectedPhoto = photoList.get(position);
        if (selectedPhoto == null) {
            throw new IllegalStateException("photo null olamaz!");
        }
        holder.setData(selectedPhoto);
    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        AppCompatImageView imgViewChapture;
        AppCompatImageView imgBtnRemove;
        Uri photoUri;
        IPhoto photo;

        public MyViewHolder(View itemView) {
            super(itemView);
            imgViewChapture = itemView.findViewById(R.id.imgViewChapture);
            imgBtnRemove = itemView.findViewById(R.id.imgBtnRemove);

//            imgViewChapture.setImageBitmap();
            imgViewChapture.setOnClickListener(v -> {
                if (!photo.isFull()) {
                    if (photo.getPhotoLocation() == EnumPhotoLacation.ROOM) {
                        if (photoList.size() > MAX_PHOTO_ROOM_COUNT) {
                            Toast.makeText(listener.getActivity(), "En Fazla 2 Fotoğraf Çekebilirsiniz!", Toast.LENGTH_LONG).show();
                            return;
                        }
                    } else if (photo.getPhotoLocation() == EnumPhotoLacation.ENVANTER) {
                        if (photoList.size() > MAX_PHOTO_ENVANTER_COUNT) {
                            Toast.makeText(listener.getActivity(), "En Fazla 5 Fotoğraf Çekebilirsiniz!", Toast.LENGTH_LONG).show();
                            return;
                        }
                    }

//                    if (PermissionUtils.checkCameraPermission(listener.getActivity(), listener.getFragment())) {
//                        listener.capture();
//                    }
//                    if (PermissionUtils.checkReadExternalStorage(listener.getActivity())) {
//                        int x = 0;
//                    }
                } else {
                    int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                    int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                    boolean focusable = true; // lets taps outside the popup also dismiss it

                    View popupView = inflater.inflate(R.layout.popup_show_image, null);
//                    Bitmap bitmap = BitmapInfo.handleSamplingAndRotationBitmap(listener.getActivity(), photoUri);
                    Bitmap bitmap = null;
                    TouchImageView imageView = popupView.findViewById(R.id.ivShowImageFragment);
                    imageView.setImageBitmap(bitmap);
                    final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
                    popupWindow.showAtLocation(imgViewChapture, Gravity.CENTER, 0, 0);

                }
            });

            imgViewChapture.setOnLongClickListener(v -> {
                if (photo.isFull()) {
                    imgBtnRemove.setVisibility(View.VISIBLE);
                    imgBtnRemove.postDelayed(new FieldGoneThread(), 5000);
                }
                return true;
            });


            imgBtnRemove.setOnClickListener(v -> {
                photoList.remove(photo);
                notifyDataSetChanged();
            });
            if (photoUri == null) return;


        }

        private Bitmap getBitmapFromUri() {

            try {
                ParcelFileDescriptor parcelFileDescriptor = listener.getActivity().getContentResolver().openFileDescriptor(photoUri, "r");
                FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
                Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
                parcelFileDescriptor.close();
                return image;
            } catch (IOException e) {
                e.printStackTrace();
//                throw new ExceptionInInitializerError("Dosya Yolu Bulunamadı!");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }


        public void setData(IPhoto photo) {
            this.photo = photo;
            if (photo.isFull()) {
                photoUri = photo.getUri();
                Bitmap bitmap = null;
//                bitmap = BitmapInfo.handleSamplingAndRotationBitmap(listener.getActivity(), photoUri);
                imgViewChapture.setImageBitmap(bitmap);

//                Bitmap bitmap = BitmapInfo.getBitmap(photoUri,listener.getActivity().getContentResolver());
            }
        }


        class FieldGoneThread implements Runnable {
            @Override
            public void run() {
                imgBtnRemove.setVisibility(View.GONE);
            }
        }
    }
}
