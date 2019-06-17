package com.uniyaz.mobiltif.ui.adapters;

import android.app.Activity;
import android.graphics.Bitmap;
import android.util.Base64;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.uniyaz.mobiltif.R;
import com.uniyaz.mobiltif.data.domain.ImageInfo;
import com.uniyaz.mobiltif.databinding.ItemPhotoCardBinding;
import com.uniyaz.mobiltif.ui.components.TouchImageView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by İrfan Öngüç on 19.05.2019
 */

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.MyViewHolder> {

    List<ImageInfo> imageInfoList;
    Activity activity;

    public PhotoAdapter(Activity activity, List<ImageInfo> imageInfoList) {
        if (imageInfoList == null) imageInfoList = new ArrayList<>();
        this.imageInfoList = imageInfoList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        ItemPhotoCardBinding binding = ItemPhotoCardBinding.inflate(inflater, parent, false);
        MyViewHolder holder = new MyViewHolder(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ImageInfo bitmap = imageInfoList.get(position);
        holder.setData(bitmap);
    }

    @Override
    public int getItemCount() {
        return imageInfoList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        AppCompatImageView imgViewChapture;
        ItemPhotoCardBinding binding;
        ImageInfo imageInfo;

        public MyViewHolder(ItemPhotoCardBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            imgViewChapture = binding.imgViewChapture;

            if (imageInfo != null && imageInfo.getBitmap() != null) {
                imgViewChapture.setImageBitmap(imageInfo.getBitmap());
            }

            imgViewChapture.setOnClickListener(v -> {
                int width = LinearLayout.LayoutParams.MATCH_PARENT;
                int height = LinearLayout.LayoutParams.MATCH_PARENT;
                boolean focusable = true; // lets taps outside the popup also dismiss it




                View popupView = activity.getLayoutInflater().inflate(R.layout.popup_show_image, null);
                TouchImageView imageView = popupView.findViewById(R.id.ivShowImageFragment);
                imageView.setImageBitmap(imageInfo.getBitmap());

                GlideUrl glideUrl = new GlideUrl("", new LazyHeaders.Builder()
                        .addHeader("Authorization", "")
                        .build());
                Glide.with(activity).load(glideUrl).into(imageView);

                final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
                popupWindow.showAtLocation(imgViewChapture, Gravity.CENTER, 0, 0);
                byte[] denemes = Base64.decode("deneme", Base64.DEFAULT);
            });

//            Glide.with(activity).load(activity).centerCrop().placeholder(R.drawable.background_fragment_demirbas).into(imgViewChapture);
        }

        public void setData(ImageInfo imageInfo) {
            this.imageInfo = imageInfo;
            if (this.imageInfo != null && imageInfo.getBitmap() != null)
                imgViewChapture.setImageBitmap(this.imageInfo.getBitmap());
        }
    }
}
