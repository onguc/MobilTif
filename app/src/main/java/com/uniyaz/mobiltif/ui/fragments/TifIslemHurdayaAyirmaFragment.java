package com.uniyaz.mobiltif.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.uniyaz.mobiltif.R;
import com.uniyaz.mobiltif.data.dto.TifIslemHurdayaAyirmaDto;
import com.uniyaz.mobiltif.databinding.FragmentTifHurdayaAyirmaBinding;
import com.uniyaz.mobiltif.iface.ITifIslem;
import com.uniyaz.mobiltif.viewmodel.TifIslemHurdayaAyirmaViewModel;

public class TifIslemHurdayaAyirmaFragment extends Fragment implements ITifIslem<TifIslemHurdayaAyirmaDto> {
    TifIslemHurdayaAyirmaViewModel islemHurdayaAyirmaViewModel;

    public TifIslemHurdayaAyirmaFragment() {
        // Required empty public constructor
    }


    public static TifIslemHurdayaAyirmaFragment newInstance() {
        TifIslemHurdayaAyirmaFragment fragment = new TifIslemHurdayaAyirmaFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        islemHurdayaAyirmaViewModel = new TifIslemHurdayaAyirmaViewModel();
        FragmentTifHurdayaAyirmaBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tif_hurdaya_ayirma, container, false);
        binding.setViewModel(islemHurdayaAyirmaViewModel);
        View view = binding.getRoot();

        return view;
    }

    @Override
    public TifIslemHurdayaAyirmaDto getIslemDto() {
        TifIslemHurdayaAyirmaDto dto = new TifIslemHurdayaAyirmaDto();
        dto.setIslemTarihi(islemHurdayaAyirmaViewModel.getIslemTarihi());
        dto.setDayanakBelgeTarihi(islemHurdayaAyirmaViewModel.getDayanakBelgeTarihi());
        dto.setKayittanDusmeNedeni(islemHurdayaAyirmaViewModel.getKayittanDusmeNedeni());
        dto.setDigerKayittanDusmeNedeni(islemHurdayaAyirmaViewModel.getDigerKayittanDusmeNedeni());
        dto.setImhaOlurAciklamasi(islemHurdayaAyirmaViewModel.getImhaOlurAciklamasi());
        dto.setDigerImhaOlurAciklamasi(islemHurdayaAyirmaViewModel.getDigerImhaOlurAciklamasi());
        dto.setIdKomisyonBaskani(islemHurdayaAyirmaViewModel.getKomisyonBaskani().getId());
        dto.setIdKomisyonUyesi1TKY(islemHurdayaAyirmaViewModel.getKomisyonUyesi1TKY().getId());
        dto.setIdKomisyonUyesi2(islemHurdayaAyirmaViewModel.getKomisyonUyesi2().getId());
        dto.setIdHarcamaYetkilisi(islemHurdayaAyirmaViewModel.getHarcamaYetkilisi().getId());
        return dto;
    }
}
