package com.uniyaz.mobiltif.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.uniyaz.mobiltif.R;
import com.uniyaz.mobiltif.data.dto.TifIslemHibeDto;
import com.uniyaz.mobiltif.databinding.FragmentTifHibeBinding;
import com.uniyaz.mobiltif.iface.ITifIslem;
import com.uniyaz.mobiltif.viewmodel.TifIslemHibeViewModel;

public class TifIslemHibeFragment extends Fragment implements ITifIslem<TifIslemHibeDto> {
    TifIslemHibeViewModel islemHibeViewModel;

    public TifIslemHibeFragment() {
        // Required empty public constructor
    }


    public static TifIslemHibeFragment newInstance() {
        TifIslemHibeFragment fragment = new TifIslemHibeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        islemHibeViewModel = new TifIslemHibeViewModel();

        FragmentTifHibeBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tif_hibe, container, false);
        binding.setViewModel(islemHibeViewModel);
        View view = binding.getRoot();

        return view;
    }

    @Override
    public TifIslemHibeDto getIslemDto() {
        TifIslemHibeDto dto = new TifIslemHibeDto();
        dto.setIslemTarihi(islemHibeViewModel.getIslemTarihi());
        dto.setIdMuhatap(islemHibeViewModel.getMuhatap().getId());
        dto.setDayanakBelgeTarihi(islemHibeViewModel.getDayanakBelgeTarihi());
        dto.setAciklama(islemHibeViewModel.getAciklama());
        return dto;
    }
}
