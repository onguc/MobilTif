package com.uniyaz.mobiltif.ui.adapters;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

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
    PhotoAdapterPresenter presenter;

    public PhotoAdapter(Activity activity, List<String> imageUrlList) {
        if (imageUrlList == null) imageUrlList = new ArrayList<>();
        this.imageUrlList = imageUrlList;
        this.activity = activity;
        presenter = new PhotoAdapterPresenter();
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

            imgViewChapture.setOnClickListener(v -> {
                int width = LinearLayout.LayoutParams.MATCH_PARENT;
                int height = LinearLayout.LayoutParams.MATCH_PARENT;
                boolean focusable = true; // lets taps outside the popup also dismiss it

                BitmapDrawable bitmapDrawable = (BitmapDrawable) imgViewChapture.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();

                View popupView = activity.getLayoutInflater().inflate(R.layout.popup_show_image, null);
                TouchImageView imageView = popupView.findViewById(R.id.ivShowImageFragment);
                imageView.setImageBitmap(bitmap);

                final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
                popupWindow.showAtLocation(imgViewChapture, Gravity.CENTER, 0, 0);
            });
        }

        public void setData(String imageUrl) {
            if (imageUrl != null) {
                presenter.loadImage(imageUrl, imgViewChapture);
//                GlideUrl glideUrl = new GlideUrl(imageUrl, new LazyHeaders.Builder()
//                        .addHeader("Authorization", getAuthorizationForTest())
//                        .build());
//                Glide.with(activity).load(glideUrl).into(imgViewChapture);
            }
        }
    }
}
