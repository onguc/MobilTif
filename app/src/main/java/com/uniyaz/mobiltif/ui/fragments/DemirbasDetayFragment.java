package com.uniyaz.mobiltif.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.uniyaz.mobiltif.R;
import com.uniyaz.mobiltif.data.domain.Envanter;
import com.uniyaz.mobiltif.data.domain.IPhoto;
import com.uniyaz.mobiltif.data.enums.EnumPhotoLacation;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class DemirbasDetayFragment extends Fragment {

    List<IPhoto> photoList;
    PhotoIslem photoIslem;

    Envanter envanter;

    @BindView(R.id.recylerview)
    RecyclerView recyclerView;

    public static DemirbasDetayFragment getNewInstance(Envanter envanter) {
        DemirbasDetayFragment demirbasDetayFragment = new DemirbasDetayFragment();
        demirbasDetayFragment.envanter = envanter;
        return demirbasDetayFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewDataBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_demibas_detay, container, false);

        View root = dataBinding.getRoot();
//        View view = inflater.inflate(R.layout.fragment_demibas_detay, container, false);
//        defineView(view);
        return root;
    }

    private void defineView(View view) {
        photoList = new ArrayList<>();
        photoIslem = new PhotoIslem(EnumPhotoLacation.ENVANTER);
        photoIslem.createView(recyclerView, getActivity(), photoList);
    }
}
