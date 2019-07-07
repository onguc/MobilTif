package com.uniyaz.mobiltif.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.uniyaz.mobiltif.R;
import com.uniyaz.mobiltif.databinding.FragmentTifIslem1Binding;
import com.uniyaz.mobiltif.viewmodel.TifIslem1ViewModel;

public class TifIslem1Fragment extends Fragment {

    public TifIslem1Fragment() {
        // Required empty public constructor
    }


    public static TifIslem1Fragment newInstance() {
        TifIslem1Fragment fragment = new TifIslem1Fragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TifIslem1ViewModel viewModel = new TifIslem1ViewModel();
        FragmentTifIslem1Binding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_tif_islem1,container,false);
        binding.setViewModel(viewModel);
        View view = binding.getRoot();

        return view;
    }

}
