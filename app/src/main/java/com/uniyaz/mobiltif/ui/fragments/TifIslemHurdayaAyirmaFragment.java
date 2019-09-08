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
    TifIslemHurdayaAyirmaViewModel islemHurdayaAyirmaViewModel;



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
        PersonelComponent tilKomisyonBaskani = binding.pcKomisyonBaskani;
        PersonelComponent tilKomisyonUyesi1TKYYetkilisi = binding.pcKomisyonUyesi1TKYYetkilisi;
        PersonelComponent tilKomisyonUyesi2 = binding.pcKomisyonUyesi2;
        PersonelComponent tilHarcamaYetkilisi = binding.pcHarcamaYetkilisi;
        islemHurdayaAyirmaViewModel.setKomisyonBaskani(tilKomisyonBaskani.getSelectedPersonelDto());
        islemHurdayaAyirmaViewModel.setKomisyonUyesi1TKY(tilKomisyonUyesi1TKYYetkilisi.getSelectedPersonelDto());
        islemHurdayaAyirmaViewModel.setKomisyonUyesi2(tilKomisyonUyesi2.getSelectedPersonelDto());
        islemHurdayaAyirmaViewModel.setHarcamaYetkilisi(tilHarcamaYetkilisi.getSelectedPersonelDto());


        binding.setViewModel(islemHurdayaAyirmaViewModel);
        binding.setFragment(this);
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
        dto.setKomisyonBaskaniId(islemHurdayaAyirmaViewModel.getKomisyonBaskani().getId());
        dto.setKomisyonUyesiBirId(islemHurdayaAyirmaViewModel.getKomisyonUyesi1TKY().getId());
        dto.setKomisyonUyesiIkiId(islemHurdayaAyirmaViewModel.getKomisyonUyesi2().getId());
        dto.setHarcamaYetkilisiId(islemHurdayaAyirmaViewModel.getHarcamaYetkilisi().getId());
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
        islemHurdayaAyirmaViewModel.setHarcamaYetkilisi(personelDto);
    }
}
