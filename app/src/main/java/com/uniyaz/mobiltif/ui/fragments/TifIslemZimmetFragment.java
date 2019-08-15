package com.uniyaz.mobiltif.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.uniyaz.mobiltif.R;
import com.uniyaz.mobiltif.data.dto.TifIslemZimmetDto;
import com.uniyaz.mobiltif.databinding.FragmentTifZimmetBinding;
import com.uniyaz.mobiltif.iface.ITifIslem;
import com.uniyaz.mobiltif.viewmodel.TifIslemZimmetViewModel;

public class TifIslemZimmetFragment extends Fragment implements ITifIslem<TifIslemZimmetDto> {
    TifIslemZimmetViewModel islemZimmetViewModel;

    public TifIslemZimmetFragment() {
        // Required empty public constructor
    }


    public static TifIslemZimmetFragment newInstance() {
        TifIslemZimmetFragment fragment = new TifIslemZimmetFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        islemZimmetViewModel = new TifIslemZimmetViewModel();

        FragmentTifZimmetBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tif_zimmet, container, false);
        binding.setViewModel(islemZimmetViewModel);
        View view = binding.getRoot();

        return view;
    }

    @Override
    public TifIslemZimmetDto getIslemDto() {
        TifIslemZimmetDto dto = new TifIslemZimmetDto();
        dto.setIslemTarihi(islemZimmetViewModel.getIslemTarihi());
        dto.setIdZimmetYapilacakPersonel(islemZimmetViewModel.getZimmetYapilacakPersonel().getId());
        dto.setAciklama(islemZimmetViewModel.getAciklama());
        return dto;
    }
}
