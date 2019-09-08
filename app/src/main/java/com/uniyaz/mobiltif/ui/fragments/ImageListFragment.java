package com.uniyaz.mobiltif.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.uniyaz.mobiltif.R;
import com.uniyaz.mobiltif.listeners.RightDrawableOnClickListener;
import com.uniyaz.mobiltif.ui.activities.MainActivity;
import com.uniyaz.mobiltif.ui.components.CustomButtonSearch;
import com.uniyaz.mobiltif.utils.PermissionUtils;
import com.uniyaz.mobiltif.utils.TextCustomUtils;

import java.util.List;

public class ImageListFragment extends Fragment {
    private List<String> urlList;

    public static ImageListFragment getNewInstance(List<String> urlList) {
        ImageListFragment imageListFragment = new ImageListFragment();
        imageListFragment.urlList = urlList;
        return imageListFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.popup_show_image_list, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.rvImageList);
        new PhotoIslem().createView(recyclerView, getActivity(), urlList).setForImageListEnvanter(true);
        return view;
    }


}
