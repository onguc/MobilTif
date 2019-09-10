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
import com.uniyaz.mobiltif.utils.StaticUtils;
import com.uniyaz.mobiltif.viewmodel.TifIslemZimmetDevriViewModel;

public class TifIslemZimmetDevriFragment extends Fragment implements ITifIslem<TifIslemZimmetDevriDto>, IPersonel {
    TifIslemZimmetDevriViewModel viewModel;
    FragmentTifZimmetDevriBinding binding;
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
        viewModel = new TifIslemZimmetDevriViewModel();

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tif_zimmet_devri, container, false);
        viewModel.setDevirYapilacakPersonel(binding.pcDevirYapilacakPersonel.getSelectedPersonelDto());
        binding.setViewModel(viewModel);
        View view = binding.getRoot();

        return view;
    }

    @Override
    public TifIslemZimmetDevriDto getIslemDto() {
        PersonelDto devirYapilacakPersonel = viewModel.getDevirYapilacakPersonel();
        if(StaticUtils.isEmptyPersonelDto(devirYapilacakPersonel)){
            binding.pcDevirYapilacakPersonel.setError("Zimmet Yapılacak Personel Seçiniz!");
            return null;
        }
        TifIslemZimmetDevriDto dto = new TifIslemZimmetDevriDto();
        dto.setIslemTarihi(viewModel.getIslemTarihi());
        dto.setZimmetlenecekPersonelId(devirYapilacakPersonel.getId());
        dto.setAciklama(viewModel.getAciklama());
        return dto;
    }

    @Override
    public void setPersonelDto(PersonelDto personelDto) {
        viewModel.setDevirYapilacakPersonel(personelDto);
    }
}
