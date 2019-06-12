package com.uniyaz.mobiltif.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.uniyaz.mobiltif.R;
import com.uniyaz.mobiltif.data.domain.Envanter;
import com.uniyaz.mobiltif.data.domain.ImageInfo;
import com.uniyaz.mobiltif.databinding.FragmentDemibasDetayBinding;
import com.uniyaz.mobiltif.viewmodel.EnvanterViewModel;

import java.util.ArrayList;
import java.util.List;

public class DemirbasDetayFragment extends Fragment {

    List<ImageInfo> imageInfoList;

    Envanter envanter;

    //    @BindView(R.id.recylerview)
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

        EnvanterViewModel viewModel = new EnvanterViewModel(envanter);
        FragmentDemibasDetayBinding bindig = DataBindingUtil.inflate(inflater, R.layout.fragment_demibas_detay, container, false);
        bindig.setViewModel(viewModel);
        recyclerView = bindig.rvDemirbasPhotoList;
        View root = bindig.getRoot();
        defineView();
        return root;
    }

    private void defineView() {
        imageInfoList = new ArrayList<>();
        ImageInfo imageInfo = new ImageInfo();
        imageInfoList.add(imageInfo);
        new PhotoIslem().createView(recyclerView, getActivity(), imageInfoList);
    }
}
