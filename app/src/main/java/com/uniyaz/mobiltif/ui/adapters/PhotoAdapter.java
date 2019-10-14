package com.uniyaz.mobiltif.ui.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.uniyaz.mobiltif.data.domain.ImageViewBitmap;
import com.uniyaz.mobiltif.databinding.ItemPhotoCardBinding;
import com.uniyaz.mobiltif.presenter.PhotoAdapterPresenter;
import com.uniyaz.mobiltif.ui.activities.MainActivity;
import com.uniyaz.mobiltif.ui.fragments.ImageListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by İrfan Öngüç on 19.05.2019
 */

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.MyViewHolder> {

    List<String> imageUrlList;
    Activity activity;
    boolean isForImageListEnvanter = false;
    List<ImageViewBitmap> imageBitmapList;

    public PhotoAdapter(Activity activity, List<String> imageUrlList) {
        if (imageUrlList == null) imageUrlList = new ArrayList<>();
        this.imageUrlList = imageUrlList;
        this.activity = activity;
        imageBitmapList = new ArrayList<>();
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
        ImageViewBitmap viewBitmap;

        public MyViewHolder(ItemPhotoCardBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            imgViewChapture = binding.imgViewChapture;
            viewBitmap = new ImageViewBitmap(imgViewChapture);
            imageBitmapList.add(viewBitmap);

            if (isForImageListEnvanter) {
                imgViewChapture.getLayoutParams().height = LinearLayout.LayoutParams.WRAP_CONTENT;
                imgViewChapture.getLayoutParams().width = LinearLayout.LayoutParams.WRAP_CONTENT;

                ViewGroup.MarginLayoutParams marginParams = (ViewGroup.MarginLayoutParams) binding.cardViewChapture.getLayoutParams();
                marginParams.bottomMargin = 25;
                marginParams.topMargin = 25;
            }

            imgViewChapture.setOnClickListener(v -> {
                if (!viewBitmap.isSuccesLoad()) {
                    ((MainActivity) activity).showSnackbar("Resim Boş!");
                    return;
                }

                String titleDemirbarDetay = "Fotoğraflar";

                ImageListFragment fragment = ImageListFragment.getNewInstance(imageBitmapList, viewBitmap);
                MainActivity mainActivity = (MainActivity) activity;
                mainActivity.startFragmentByBackStack(fragment, titleDemirbarDetay);
//                ImageListPopupWindow window = new ImageListPopupWindow(this, activityMainBinding.toolbar, envanter.getUrlResimList());
//                window.show();

            });
        }

        public void setData(String imageUrl) {
            if (imageUrl != null) {
                if (!viewBitmap.isSuccesLoad())
                    new PhotoAdapterPresenter().loadImage(imageUrl, viewBitmap);
            }
        }
    }

    public void setForImageListEnvanter(boolean forImageListEnvanter) {
        isForImageListEnvanter = forImageListEnvanter;
    }
}
