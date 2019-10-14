package com.uniyaz.mobiltif.ui.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.uniyaz.mobiltif.R;
import com.uniyaz.mobiltif.data.domain.ImageViewBitmap;
import com.uniyaz.mobiltif.presenter.PhotoAdapterPresenter;
import com.uniyaz.mobiltif.ui.components.TouchImageView;

import java.util.ArrayList;
import java.util.List;

public class ImageListFragment extends Fragment {
    private List<String> urlList = new ArrayList<>();
    private List<ImageViewBitmap> imageViewBitmapList = new ArrayList<>();
    private boolean isForUrl = false;
    private int selectedPosition = 0;

    public static ImageListFragment getNewInstance(List<String> urlList) {
        ImageListFragment imageListFragment = new ImageListFragment();
        imageListFragment.urlList = urlList;
        imageListFragment.isForUrl = true;
        return imageListFragment;
    }

    public static ImageListFragment getNewInstance(List<ImageViewBitmap> imageViewBitmapList, ImageViewBitmap selectedBitmap) {
        ImageListFragment imageListFragment = new ImageListFragment();
        imageListFragment.urlList = null;
        List<ImageViewBitmap> viewBitmaps = new ArrayList<>();
        int i = 0;
        for (ImageViewBitmap viewBitmap : imageViewBitmapList) {
            if (viewBitmap.isSuccesLoad()) {
                viewBitmaps.add(viewBitmap);
                if (viewBitmap == selectedBitmap) {
                    imageListFragment.selectedPosition = i;
                }
                i++;
            }
        }
        imageListFragment.imageViewBitmapList = viewBitmaps;
        imageListFragment.isForUrl = false;
        return imageListFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.popup_show_view_pager, container, false);
        ViewPager viewPager = view.findViewById(R.id.view_pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity());
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(selectedPosition);
//        new PhotoIslem().createView(recyclerView, getActivity(), urlList).setForImageListEnvanter(true);
        return view;
    }

    public class ViewPagerAdapter extends PagerAdapter {

        Context context;

        public ViewPagerAdapter(Activity activity) {
            context = activity;
        }

        @Override
        public int getCount() {
            if (isForUrl)
                return urlList.size();
            return imageViewBitmapList.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }


        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            TouchImageView imageView = new TouchImageView(context);
//        ImageView imageView = new ImageView(context);
            int padding = 10;
            imageView.setPadding(padding, padding, padding, padding);
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            if (isForUrl) {
                ImageViewBitmap viewBitmap = new ImageViewBitmap(imageView);
                new PhotoAdapterPresenter().loadImage(urlList.get(position), viewBitmap);
            } else {
                imageView.setImageBitmap(imageViewBitmapList.get(position).getBitmap());
            }

            container.addView(imageView, 0);
            return imageView;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((ImageView) object);
        }
    }


}
