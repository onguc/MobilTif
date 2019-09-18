package com.uniyaz.mobiltif.ui.adapters;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.uniyaz.mobiltif.R;
import com.uniyaz.mobiltif.databinding.ItemPhotoCardBinding;
import com.uniyaz.mobiltif.presenter.PhotoAdapterPresenter;
import com.uniyaz.mobiltif.ui.components.TouchImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by İrfan Öngüç on 19.05.2019
 */

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.MyViewHolder> {

    List<String> imageUrlList;
    Activity activity;
    boolean isForImageListEnvanter = false;

    public PhotoAdapter(Activity activity, List<String> imageUrlList) {
        if (imageUrlList == null) imageUrlList = new ArrayList<>();
        this.imageUrlList = imageUrlList;
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
        String imageUrl = imageUrlList.get(position);
        holder.setData(imageUrl);
    }

    @Override
    public int getItemCount() {
        return imageUrlList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        AppCompatImageView imgViewChapture;
        ItemPhotoCardBinding binding;

        public MyViewHolder(ItemPhotoCardBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            imgViewChapture = binding.imgViewChapture;
            if (isForImageListEnvanter) {
                imgViewChapture.getLayoutParams().height = LinearLayout.LayoutParams.WRAP_CONTENT;
                imgViewChapture.getLayoutParams().width = LinearLayout.LayoutParams.WRAP_CONTENT;

                ViewGroup.MarginLayoutParams marginParams = (ViewGroup.MarginLayoutParams) binding.cardViewChapture.getLayoutParams();
                marginParams.bottomMargin = 25;
                marginParams.topMargin = 25;
            }

            imgViewChapture.setOnClickListener(v -> {
                int width = LinearLayout.LayoutParams.MATCH_PARENT;
                int height = LinearLayout.LayoutParams.MATCH_PARENT;
                boolean focusable = true; // lets taps outside the popup also dismiss it

                BitmapDrawable bitmapDrawable = (BitmapDrawable) imgViewChapture.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                Bitmap picture = BitmapFactory.decodeResource(activity.getResources(), R.mipmap.picture);
                Log.d("Bitmap1", String.valueOf(bitmap));
                Log.d("Bitmap2", String.valueOf(picture));
                if (bitmap == null) {
                    int x = 0;
                }
                if (picture.getByteCount() == bitmap.getByteCount()) {
                    Snackbar.make(activity.findViewById(R.id.constraintLayout), "Resim Boş!", Snackbar.LENGTH_LONG)
                            .show();
                    return;
                }
                View popupView = activity.getLayoutInflater().inflate(R.layout.popup_show_image, null);
                TouchImageView imageView = popupView.findViewById(R.id.ivShowImageFragment);
                imageView.setImageBitmap(bitmap);

                final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
                popupWindow.showAtLocation(imgViewChapture, Gravity.CENTER, 0, 0);
            });
        }

        public void setData(String imageUrl) {
            if (imageUrl != null) {
                new PhotoAdapterPresenter().loadImage(imageUrl, imgViewChapture);
//                GlideUrl glideUrl = new GlideUrl(imageUrl, new LazyHeaders.Builder()
//                        .addHeader("Authorization", getAuthorizationForTest())
//                        .build());
//                Glide.with(activity).load(glideUrl).into(imgViewChapture);
            }
        }
    }

    public void setForImageListEnvanter(boolean forImageListEnvanter) {
        isForImageListEnvanter = forImageListEnvanter;
    }
}
