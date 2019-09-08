package com.uniyaz.mobiltif.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.uniyaz.mobiltif.R;
import com.uniyaz.mobiltif.data.dto.TifIslemZimmetIadeDto;
import com.uniyaz.mobiltif.databinding.FragmentTifZimmetIadeBinding;
import com.uniyaz.mobiltif.iface.ITifIslem;
import com.uniyaz.mobiltif.viewmodel.TifIslemZimmetIadeViewModel;

public class TifIslemZimmetIadeFragment extends Fragment implements ITifIslem<TifIslemZimmetIadeDto> {
    TifIslemZimmetIadeViewModel viewModel;

    public TifIslemZimmetIadeFragment() {
        // Required empty public constructor
    }


    public static TifIslemZimmetIadeFragment newInstance() {
        TifIslemZimmetIadeFragment fragment = new TifIslemZimmetIadeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel = new TifIslemZimmetIadeViewModel();

        FragmentTifZimmetIadeBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tif_zimmet_iade, container, false);
        binding.setViewModel(viewModel);
        View view = binding.getRoot();

        return view;
    }

    @Override
    public TifIslemZimmetIadeDto getIslemDto() {
        TifIslemZimmetIadeDto dto = new TifIslemZimmetIadeDto();
        dto.setIslemTarihi(viewModel.getIslemTarihi());
        dto.setAciklama(viewModel.getAciklama());
        return dto;
    }
}
