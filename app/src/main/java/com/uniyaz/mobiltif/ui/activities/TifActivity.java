package com.uniyaz.mobiltif.ui.activities;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.uniyaz.mobiltif.R;
import com.uniyaz.mobiltif.data.domain.Envanter;
import com.uniyaz.mobiltif.databinding.ActivityTifBinding;
import com.uniyaz.mobiltif.iface.ITif;
import com.uniyaz.mobiltif.ui.adapters.TifEnvanterAdapter;
import com.uniyaz.mobiltif.viewmodel.TifViewModel;

import java.util.ArrayList;
import java.util.List;

public class TifActivity extends AppCompatActivity implements ITif {

    private TifViewModel viewModel;
    ActivityTifBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new TifViewModel();

        binding = DataBindingUtil.setContentView(this, R.layout.activity_tif);
        binding.setViewModel(viewModel);

        Envanter envanter = new Envanter();
        Envanter envanter1 = new Envanter();
        List<Envanter> envanters = new ArrayList<>();
        envanters.add(envanter);
        envanters.add(envanter1);
        TifEnvanterAdapter adapter = new TifEnvanterAdapter(envanters);
        binding.lvTifList.setAdapter(adapter);

    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onSuccess(String message) {

    }

    @Override
    public Activity getActivity() {
        return getActivity();
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void showWarningDialog(String title, String explanation) {

    }
}
