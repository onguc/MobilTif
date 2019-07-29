package com.uniyaz.mobiltif.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.uniyaz.mobiltif.R;
import com.uniyaz.mobiltif.data.domain.Envanter;
import com.uniyaz.mobiltif.data.domain.Room;
import com.uniyaz.mobiltif.data.dto.AmbarDto;
import com.uniyaz.mobiltif.data.dto.PersonelDto;
import com.uniyaz.mobiltif.data.dto.TifIslemTransferDto;
import com.uniyaz.mobiltif.databinding.FragmentTifTransferBinding;
import com.uniyaz.mobiltif.iface.ITif;
import com.uniyaz.mobiltif.iface.ITifIslem;
import com.uniyaz.mobiltif.presenter.TifPresenter;
import com.uniyaz.mobiltif.ui.adapters.AmbarAdapter;
import com.uniyaz.mobiltif.ui.adapters.PersonelAdapter;
import com.uniyaz.mobiltif.viewmodel.TifIslemTransferViewModel;

import java.util.List;

import gr.escsoft.michaelprimez.searchablespinner.SearchableSpinner;

public class TifIslemTransferFragment extends Fragment implements ITif, ITifIslem<TifIslemTransferDto> {

    private TifIslemTransferViewModel islemTransferViewModel;
    private AutoCompleteTextView actGirisYapilanAmbar;
    private SearchableSpinner sspnrGirisYapilanAmbar;
    private AutoCompleteTextView actAmbarSorumlusu;
    private Spinner spnrAmbarSorumlusu;
    private TifPresenter presenter;
    private boolean isSelected;

    public TifIslemTransferFragment() {
        // Required empty public constructor
    }


    public static TifIslemTransferFragment newInstance() {
        TifIslemTransferFragment fragment = new TifIslemTransferFragment();
        TifPresenter presenter = new TifPresenter(fragment);
        presenter.getAllAmbarDtoList();
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
        actGirisYapilanAmbar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AmbarDto selectedItem = (AmbarDto) parent.getAdapter().getItem(position);
                islemTransferViewModel.setSelectedAmbarDto(selectedItem);
                presenter.getAllPersonelDtoList(selectedItem.getId());
                spnrAmbarSorumlusu.setSelected(false);
                isSelected = true;
            }
        });

        actGirisYapilanAmbar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                actGirisYapilanAmbar.setText("");
                return false;
            }
        });
        actGirisYapilanAmbar.setOnDismissListener(new AutoCompleteTextView.OnDismissListener() {
            @Override
            public void onDismiss() {
                if ("".equals(actGirisYapilanAmbar.getText().toString())) {
                    if (islemTransferViewModel.getSelectedAmbarDto() != null) {
                        islemTransferViewModel.setGirisYapilanAmbar(islemTransferViewModel.getSelectedAmbarDto().getAdi());
                    }
                }

                if (!isSelected) {
                    if (islemTransferViewModel.getSelectedAmbarDto() != null) {
                        islemTransferViewModel.setGirisYapilanAmbar(islemTransferViewModel.getSelectedAmbarDto().getAdi());
                    }
                }
            }
        });

        actGirisYapilanAmbar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int x = 0;
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
    public void onSuccess() {

    }

    @Override
    public void onSuccessForEnvater(Envanter envanter) {

    }

    @Override
    public void onSuccessForRoom(Room room) {

    }

    @Override
    public void onSuccessForAmbarDtoList(List<AmbarDto> ambarDtoList) {
        AmbarAdapter ambarAdapter = new AmbarAdapter(ambarDtoList, getActivity());
        actGirisYapilanAmbar.setAdapter(ambarAdapter);
//        sspnrGirisYapilanAmbar.setAdapter(ambarAdapter);
    }

    @Override
    public void onSuccessforPersonelDtoList(List<PersonelDto> personelDtoList) {
        PersonelAdapter personelAdapter = new PersonelAdapter(personelDtoList, getActivity());
        spnrAmbarSorumlusu.setAdapter(personelAdapter);
    }

    @Override
    public void onSuccessForSaveTasinirTransferIslem() {

    }

    @Override
    public void onSuccess(String message) {

    }

    @Override
    public void notifyDepartment() {

    }

    @Override
    public void notifyTasinir() {

    }

    @Override
    public void logOut() {

    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void showWarningDialog(String title, String explanation) {

    }

    @Override
    public Context getApplicationContext() {
        return null;
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
