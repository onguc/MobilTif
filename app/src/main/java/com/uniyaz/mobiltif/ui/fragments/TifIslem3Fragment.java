package com.uniyaz.mobiltif.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.uniyaz.mobiltif.R;
import com.uniyaz.mobiltif.data.dto.TifIslem3Dto;
import com.uniyaz.mobiltif.databinding.FragmentTifIslem3Binding;
import com.uniyaz.mobiltif.iface.ITifIslem;
import com.uniyaz.mobiltif.viewmodel.TifIslem3ViewModel;

public class TifIslem3Fragment extends Fragment implements ITifIslem<TifIslem3Dto> {
    TifIslem3ViewModel islem3ViewModel;

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
        islem3ViewModel = new TifIslem3ViewModel();
        FragmentTifIslem3Binding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tif_islem3, container, false);
        binding.setViewModel(islem3ViewModel);
        View view = binding.getRoot();

        return view;
    }

    @Override
    public TifIslem3Dto getIslemDto() {
        TifIslem3Dto dto = new TifIslem3Dto();
        dto.setIslemTarihi(islem3ViewModel.getIslemTarihi());
        dto.setDayanakBelgeTarihi(islem3ViewModel.getDayanakBelgeTarihi());
        dto.setKayittanDusmeNedeni(islem3ViewModel.getKayittanDusmeNedeni());
        dto.setDigerKayittanDusmeNedeni(islem3ViewModel.getDigerKayittanDusmeNedeni());
        dto.setImhaOlurAciklamasi(islem3ViewModel.getImhaOlurAciklamasi());
        dto.setDigerImhaOlurAciklamasi(islem3ViewModel.getDigerImhaOlurAciklamasi());
        dto.setIdKomisyonBaskani(islem3ViewModel.getKomisyonBaskani().getId());
        dto.setIdKomisyonUyesi1TKY(islem3ViewModel.getKomisyonUyesi1TKY().getId());
        dto.setIdKomisyonUyesi2(islem3ViewModel.getKomisyonUyesi2().getId());
        dto.setIdHarcamaYetkilisi(islem3ViewModel.getHarcamaYetkilisi().getId());
        return dto;
    }
}
