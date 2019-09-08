package com.uniyaz.mobiltif.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.uniyaz.mobiltif.R;
import com.uniyaz.mobiltif.data.dto.PersonelDto;
import com.uniyaz.mobiltif.data.dto.TifIslemHurdayaAyirmaDto;
import com.uniyaz.mobiltif.databinding.FragmentTifHurdayaAyirmaBinding;
import com.uniyaz.mobiltif.iface.IPersonel;
import com.uniyaz.mobiltif.iface.ITifIslem;
import com.uniyaz.mobiltif.ui.components.PersonelComponent;
import com.uniyaz.mobiltif.viewmodel.TifIslemHurdayaAyirmaViewModel;

public class TifIslemHurdayaAyirmaFragment extends Fragment implements ITifIslem<TifIslemHurdayaAyirmaDto>, IPersonel {
    TifIslemHurdayaAyirmaViewModel viewModel;


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
        viewModel = new TifIslemHurdayaAyirmaViewModel();
        FragmentTifHurdayaAyirmaBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tif_hurdaya_ayirma, container, false);
        PersonelComponent tilKomisyonBaskani = binding.pcKomisyonBaskani;
        PersonelComponent tilKomisyonUyesi1TKYYetkilisi = binding.pcKomisyonUyesi1TKYYetkilisi;
        PersonelComponent tilKomisyonUyesi2 = binding.pcKomisyonUyesi2;
        PersonelComponent tilHarcamaYetkilisi = binding.pcHarcamaYetkilisi;
        viewModel.setKomisyonBaskani(tilKomisyonBaskani.getSelectedPersonelDto());
        viewModel.setKomisyonUyesi1TKY(tilKomisyonUyesi1TKYYetkilisi.getSelectedPersonelDto());
        viewModel.setKomisyonUyesi2(tilKomisyonUyesi2.getSelectedPersonelDto());
        viewModel.setHarcamaYetkilisi(tilHarcamaYetkilisi.getSelectedPersonelDto());


        binding.setViewModel(viewModel);
        binding.setFragment(this);
        View view = binding.getRoot();

        return view;
    }

    @Override
    public TifIslemHurdayaAyirmaDto getIslemDto() {
        PersonelDto komisyonBaskani = viewModel.getKomisyonBaskani();
        PersonelDto komisyonUyesi1TKY = viewModel.getKomisyonUyesi1TKY();
        PersonelDto komisyonUyesi2 = viewModel.getKomisyonUyesi2();
        PersonelDto harcamaYetkilisi = viewModel.getHarcamaYetkilisi();

        if (komisyonBaskani == null) {
            viewModel.setError("Komisyon Başkanı Seçiniz!");
            return null;
        }
        if (komisyonUyesi1TKY == null) {
            viewModel.setError("Komisyon Üyesi Seçiniz!");
            return null;
        }

        if (komisyonUyesi2 == null) {
            viewModel.setError("Komisyon Üyesi 2 Seçiniz!");
            return null;
        }

        if (harcamaYetkilisi == null) {
            viewModel.setError("Harcama Yetkilisi Seçiniz!");
            return null;
        }
        TifIslemHurdayaAyirmaDto dto = new TifIslemHurdayaAyirmaDto();
        dto.setIslemTarihi(viewModel.getIslemTarihi());
        dto.setDayanakBelgeTarihi(viewModel.getDayanakBelgeTarihi());
        dto.setKayittanDusmeNedeni(viewModel.getKayittanDusmeNedeni());
        dto.setDigerKayittanDusmeNedeni(viewModel.getDigerKayittanDusmeNedeni());
        dto.setImhaOlurAciklamasi(viewModel.getImhaOlurAciklamasi());
        dto.setDigerImhaOlurAciklamasi(viewModel.getDigerImhaOlurAciklamasi());
        dto.setKomisyonBaskaniId(viewModel.getKomisyonBaskani().getId());
        dto.setKomisyonUyesiBirId(viewModel.getKomisyonUyesi1TKY().getId());
        dto.setKomisyonUyesiIkiId(viewModel.getKomisyonUyesi2().getId());
        dto.setHarcamaYetkilisiId(viewModel.getHarcamaYetkilisi().getId());
        return dto;
    }

    private ListView lvPersonel;

    //public void onClicEtMuhatap() {
    //PersonelComponent personelComponent = new PersonelComponent(this, this);
    //  personelComponent.showPopup();
    //}

    IPersonel iPersonel = this;


    @Override
    public void setPersonelDto(PersonelDto personelDto) {
        viewModel.setHarcamaYetkilisi(personelDto);
    }
}
