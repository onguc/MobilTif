package com.uniyaz.mobiltif.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.uniyaz.mobiltif.R;
import com.uniyaz.mobiltif.data.dto.AmbarDto;
import com.uniyaz.mobiltif.data.dto.PersonelDto;
import com.uniyaz.mobiltif.data.dto.TifIslemTransferDto;
import com.uniyaz.mobiltif.databinding.FragmentTifTransferBinding;
import com.uniyaz.mobiltif.iface.ITifCommon;
import com.uniyaz.mobiltif.iface.ITifIslem;
import com.uniyaz.mobiltif.presenter.TifIslemTransferPresenter;
import com.uniyaz.mobiltif.ui.adapters.AmbarAdapter;
import com.uniyaz.mobiltif.ui.adapters.PersonelAdapter;
import com.uniyaz.mobiltif.viewmodel.TifIslemTransferViewModel;

import java.util.List;

public class TifIslemTransferFragment extends Fragment implements ITifCommon, ITifIslem<TifIslemTransferDto> {

    private TifIslemTransferViewModel islemTransferViewModel;
    private Spinner actGirisYapilanAmbar;
    private Spinner spnrAmbarSorumlusu;
    private TifIslemTransferPresenter presenter;


    public static TifIslemTransferFragment newInstance() {
        TifIslemTransferFragment fragment = new TifIslemTransferFragment();
        TifIslemTransferPresenter presenter = new TifIslemTransferPresenter(fragment);
        presenter.fillAllAmbarDtoList();
        fragment.presenter = presenter;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        islemTransferViewModel = new TifIslemTransferViewModel();

        FragmentTifTransferBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tif_transfer, container, false);
        binding.setViewModel(islemTransferViewModel);
//        this.sspnrGirisYapilanAmbar = binding.actGirisYapilanAmbar;
        this.actGirisYapilanAmbar = binding.actGirisYapilanAmbar;


        actGirisYapilanAmbar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //actGirisYapilanAmbar.setText("");
                return false;
            }
        });


        actGirisYapilanAmbar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int x = 0;
                AmbarDto selectedItem = (AmbarDto) parent.getAdapter().getItem(position);
                islemTransferViewModel.setSelectedAmbarDto(selectedItem);
                presenter.fillAllPersonelDtoListByAmbarId(selectedItem.getId());
                spnrAmbarSorumlusu.setSelected(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                int y = 0;
            }
        });


//        this.actAmbarSorumlusu = binding.actAmbarSorumlusu;
        this.spnrAmbarSorumlusu = binding.actAmbarSorumlusu;
//        this.actAmbarSorumlusu.setOnItemClickListener((parent, view, position, id) -> {
//            PersonelDto selectedItem = (PersonelDto) parent.getSelectedItem();
//            PersonelDto item = (PersonelDto) parent.getAdapter().getItem(position);
//            viewModel.setSelectedPersonelDto(selectedItem);
//        });

        this.spnrAmbarSorumlusu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                PersonelDto selectedItem = (PersonelDto) parent.getSelectedItem();
                islemTransferViewModel.setSelectedPersonelDto(selectedItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        View view = binding.getRoot();

        return view;
    }


    @Override
    public void onSuccessForAmbarDtoList(List<AmbarDto> ambarDtoList) {
        AmbarAdapter ambarAdapter = new AmbarAdapter(ambarDtoList, getActivity());
        actGirisYapilanAmbar.setAdapter(ambarAdapter);
//        sspnrGirisYapilanAmbar.setAdapter(ambarAdapter);
    }

    @Override
    public void onSuccessforPersonelDtoList(List<PersonelDto> personelDtoList) {
        PersonelAdapter personelAdapter = new PersonelAdapter(personelDtoList,getActivity());
        spnrAmbarSorumlusu.setAdapter(personelAdapter);
    }

    @Override
    public TifIslemTransferDto getIslemDto() {
        TifIslemTransferDto dto = new TifIslemTransferDto();
        dto.setIslemTarihi(islemTransferViewModel.getIslemTarihi());
        if (islemTransferViewModel.getSelectedPersonelDto() != null)
            dto.setIdAmbarSorumlusu(islemTransferViewModel.getSelectedPersonelDto().getId());
        if (islemTransferViewModel.getSelectedAmbarDto() != null)
            dto.setIdGirisYapilanAmbar(islemTransferViewModel.getSelectedAmbarDto().getId());
        dto.setAciklama(islemTransferViewModel.getAciklama());
        return dto;
    }
}
