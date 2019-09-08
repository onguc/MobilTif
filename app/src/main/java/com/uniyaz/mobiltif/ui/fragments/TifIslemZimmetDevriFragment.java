package com.uniyaz.mobiltif.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.uniyaz.mobiltif.R;
import com.uniyaz.mobiltif.data.dto.PersonelDto;
import com.uniyaz.mobiltif.data.dto.TifIslemZimmetDevriDto;
import com.uniyaz.mobiltif.databinding.FragmentTifZimmetDevriBinding;
import com.uniyaz.mobiltif.iface.IPersonel;
import com.uniyaz.mobiltif.iface.ITifIslem;
import com.uniyaz.mobiltif.viewmodel.TifIslemZimmetDevriViewModel;

public class TifIslemZimmetDevriFragment extends Fragment implements ITifIslem<TifIslemZimmetDevriDto>, IPersonel {
    TifIslemZimmetDevriViewModel islemZimmetDevriViewModel;

    public static TifIslemZimmetDevriFragment newInstance() {
        TifIslemZimmetDevriFragment fragment = new TifIslemZimmetDevriFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        islemZimmetDevriViewModel = new TifIslemZimmetDevriViewModel();

        FragmentTifZimmetDevriBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tif_zimmet_devri, container, false);
        islemZimmetDevriViewModel.setDevirYapilacakPersonel(binding.pcDevirYapilacakPersonel.getSelectedPersonelDto());
        binding.setViewModel(islemZimmetDevriViewModel);
        View view = binding.getRoot();

        return view;
    }

    @Override
    public TifIslemZimmetDevriDto getIslemDto() {
        TifIslemZimmetDevriDto dto = new TifIslemZimmetDevriDto();
        dto.setIslemTarihi(islemZimmetDevriViewModel.getIslemTarihi());
        dto.setZimmetlenecekPersonelId(islemZimmetDevriViewModel.getDevirYapilacakPersonel().getId());
        dto.setAciklama(islemZimmetDevriViewModel.getAciklama());
        return dto;
    }

    @Override
    public void setPersonelDto(PersonelDto personelDto) {
        islemZimmetDevriViewModel.setDevirYapilacakPersonel(personelDto);
    }
}
