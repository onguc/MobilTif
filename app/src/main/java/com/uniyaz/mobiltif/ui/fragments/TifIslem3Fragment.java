package com.uniyaz.mobiltif.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.uniyaz.mobiltif.R;
import com.uniyaz.mobiltif.databinding.FragmentTifIslem3Binding;
import com.uniyaz.mobiltif.viewmodel.TifIslem3ViewModel;

public class TifIslem3Fragment extends Fragment {

    public TifIslem3Fragment() {
        // Required empty public constructor
    }


    public static TifIslem3Fragment newInstance() {
        TifIslem3Fragment fragment = new TifIslem3Fragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TifIslem3ViewModel viewModel = new TifIslem3ViewModel();
        FragmentTifIslem3Binding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tif_islem3, container, false);
        binding.setViewModel(viewModel);
        View view = binding.getRoot();

        return view;
    }

}
