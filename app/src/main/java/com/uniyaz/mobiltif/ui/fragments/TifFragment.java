package com.uniyaz.mobiltif.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.uniyaz.mobiltif.R;
import com.uniyaz.mobiltif.data.domain.Envanter;
import com.uniyaz.mobiltif.databinding.FragmentTifBinding;
import com.uniyaz.mobiltif.iface.ITif;
import com.uniyaz.mobiltif.ui.adapters.TifEnvanterAdapter;
import com.uniyaz.mobiltif.viewmodel.TifViewModel;

import java.util.List;

public class TifFragment extends Fragment implements ITif {

    private TifViewModel viewModel;
    private FragmentTifBinding binding;
    private List<Envanter> envanters;

    public static TifFragment getNewInstance(List<Envanter> envanters) {
        TifFragment fragment = new TifFragment();
        fragment.envanters = envanters;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewModel = new TifViewModel();
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tif, container, false);
        binding.setViewModel(viewModel);
        binding.setFragment(this);
        TifEnvanterAdapter adapter = new TifEnvanterAdapter(envanters);
        binding.lvTifList.setAdapter(adapter);

        View view = binding.getRoot();
        return view;
    }

    public boolean onItemSelectedSpinner2() {
        return false;
    }

    public void onItemSelectedSpinner(AdapterView<?> parent, View view, int position, long id) {
        String selectedItem = (String) parent.getSelectedItem();
        if (getString(R.string.islemTuru0).equals(selectedItem)) {

        } else if (getString(R.string.islemTuru1).equals(selectedItem)) {
            TifIslem1Fragment fragment = new TifIslem1Fragment();
            startFragmentByBackStack(fragment);

        } else if (getString(R.string.islemTuru2).equals(selectedItem)) {
            TifIslem2Fragment fragment = new TifIslem2Fragment();
            startFragmentByBackStack(fragment);


        } else if (getString(R.string.islemTuru3).equals(selectedItem)) {
            TifIslem3Fragment fragment = new TifIslem3Fragment();
            startFragmentByBackStack(fragment);

        }
    }

    public void startFragmentByBackStack(Fragment fragment) {
        if (fragment != null) {
            FragmentManager fragmentManager = getFragmentManager();
            final FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction
                    .replace(R.id.f_layout, fragment, "fragment1")
                    .commit();
            fragmentManager.executePendingTransactions();
        }
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onSuccess(String message) {

    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void showWarningDialog(String title, String explanation) {

    }

    @Override
    public Context getApplicationContext() {
        return null;
    }
}
