package com.uniyaz.mobiltif.ui.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.uniyaz.mobiltif.data.domain.ImageInfo;
import com.uniyaz.mobiltif.databinding.ItemPhotoCardBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by İrfan Öngüç on 19.05.2019
 */

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.MyViewHolder> {

    List<ImageInfo> imageInfoList;

    public PhotoAdapter(List<ImageInfo> imageInfoList) {
        if (imageInfoList == null) imageInfoList = new ArrayList<>();
        this.imageInfoList = imageInfoList;
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

//            imgViewChapture.setImageBitmap();
//            imgViewChapture.setOnClickListener(v -> {
//                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
//                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
//                boolean focusable = true; // lets taps outside the popup also dismiss it
//
//                View popupView = inflater.inflate(R.layout.popup_show_image, null);
////                    Bitmap imageInfo = BitmapInfo.handleSamplingAndRotationBitmap(listener.getActivity(), photoUri);
//                Bitmap imageInfo = null;
//                TouchImageView imageView = popupView.findViewById(R.id.ivShowImageFragment);
//                imageView.setImageBitmap(imageInfo);
//                final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
//                popupWindow.showAtLocation(imgViewChapture, Gravity.CENTER, 0, 0);
//            });
        }

        public void setData(ImageInfo imageInfo) {
            this.imageInfo = imageInfo;
            if (this.imageInfo != null && imageInfo.getBitmap() != null)
                imgViewChapture.setImageBitmap(this.imageInfo.getBitmap());
        }
    }
}
