package com.uniyaz.mobiltif.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.uniyaz.mobiltif.R;
import com.uniyaz.mobiltif.databinding.FragmentTifIslem2Binding;
import com.uniyaz.mobiltif.viewmodel.TifIslem2ViewModel;

public class TifIslem2Fragment extends Fragment {

    public TifIslem2Fragment() {
        // Required empty public constructor
    }


    public static TifIslem2Fragment newInstance() {
        TifIslem2Fragment fragment = new TifIslem2Fragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TifIslem2ViewModel viewModel = new TifIslem2ViewModel();
        FragmentTifIslem2Binding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tif_islem2, container, false);
        binding.setViewModel(viewModel);
        View view = binding.getRoot();

        return view;
    }

}
