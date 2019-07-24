package com.uniyaz.mobiltif.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.uniyaz.mobiltif.R;
import com.uniyaz.mobiltif.data.domain.Envanter;
import com.uniyaz.mobiltif.data.domain.Room;
import com.uniyaz.mobiltif.data.dto.AmbarDto;
import com.uniyaz.mobiltif.data.dto.PersonelDto;
import com.uniyaz.mobiltif.data.dto.TifIslem1Dto;
import com.uniyaz.mobiltif.databinding.FragmentTifIslem1Binding;
import com.uniyaz.mobiltif.iface.ITif;
import com.uniyaz.mobiltif.iface.ITifIslem;
import com.uniyaz.mobiltif.presenter.TifPresenter;
import com.uniyaz.mobiltif.ui.adapters.AmbarAdapter;
import com.uniyaz.mobiltif.ui.adapters.PersonelAdapter;
import com.uniyaz.mobiltif.viewmodel.TifIslem1ViewModel;

import java.util.List;

import gr.escsoft.michaelprimez.searchablespinner.SearchableSpinner;
import gr.escsoft.michaelprimez.searchablespinner.interfaces.OnItemSelectedListener;

public class TifIslem1Fragment extends Fragment implements ITif, ITifIslem<TifIslem1Dto> {

    private TifIslem1ViewModel islem1ViewModel;
    private AutoCompleteTextView actGirisYapilanAmbar;
    private SearchableSpinner sspnrGirisYapilanAmbar;
    private AutoCompleteTextView actAmbarSorumlusu;
    private SearchableSpinner sspnrAmbarSorumlusu;
    private TifPresenter presenter;
    private boolean isSelected;

    public TifIslem1Fragment() {
        // Required empty public constructor
    }


    public static TifIslem1Fragment newInstance() {
        TifIslem1Fragment fragment = new TifIslem1Fragment();
        TifPresenter presenter = new TifPresenter(fragment);
        presenter.getAllAmbarDtoList();
        fragment.presenter = presenter;
        return fragment;
    }

    public void onItemSelectedX(View view, int position, long id) {


        int x = 0;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        islem1ViewModel = new TifIslem1ViewModel();
        FragmentTifIslem1Binding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tif_islem1, container, false);
        binding.setViewModel(islem1ViewModel);
//        this.sspnrGirisYapilanAmbar = binding.actGirisYapilanAmbar;
        this.actGirisYapilanAmbar = binding.actGirisYapilanAmbar;
        actGirisYapilanAmbar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AmbarDto selectedItem = (AmbarDto) parent.getAdapter().getItem(position);
                islem1ViewModel.setSelectedAmbarDto(selectedItem);
                presenter.getAllPersonelDtoList(selectedItem.getId());
                sspnrAmbarSorumlusu.setSelected(false);
                isSelected = true;
                sspnrAmbarSorumlusu.setSelected(false);
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
                if("".equals(actGirisYapilanAmbar.getText().toString())){
                    if (islem1ViewModel.getSelectedAmbarDto() != null) {
                        islem1ViewModel.setGirisYapilanAmbar(islem1ViewModel.getSelectedAmbarDto().getAdi());
                    }
                }

                if (!isSelected) {
                    if (islem1ViewModel.getSelectedAmbarDto() != null) {
                        islem1ViewModel.setGirisYapilanAmbar(islem1ViewModel.getSelectedAmbarDto().getAdi());
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
//        sspnrGirisYapilanAmbar.setOnItemSelectedListener(new OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(View view, int position, long id) {
//                AmbarDto selectedItem = (AmbarDto) sspnrGirisYapilanAmbar.getSelectedItem();
//                viewModel.setSelectedAmbarDto(selectedItem);
//                presenter.getAllPersonelDtoList(selectedItem.getId());
//            }
//
//            @Override
//            public void onNothingSelected() {
//                int y = 0;
//            }
//        });
//        actGirisYapilanAmbar.setOnItemClickListener((parent, view, position, id) -> {
////            AmbarDto ambarDto = (AmbarDto) parent.getSelectedItem(); // burdan sonuç null geliyor.
//            AmbarDto ambarDto = (AmbarDto) parent.getAdapter().getItem(position);
//            viewModel.setSelectedAmbarDto(ambarDto);
//            presenter.getAllPersonelDtoList(ambarDto.getId());
//        });

//        this.actAmbarSorumlusu = binding.actAmbarSorumlusu;
        this.sspnrAmbarSorumlusu = binding.actAmbarSorumlusu;
//        this.actAmbarSorumlusu.setOnItemClickListener((parent, view, position, id) -> {
//            PersonelDto selectedItem = (PersonelDto) parent.getSelectedItem();
//            PersonelDto item = (PersonelDto) parent.getAdapter().getItem(position);
//            viewModel.setSelectedPersonelDto(selectedItem);
//        });
        this.sspnrAmbarSorumlusu.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(View view, int position, long id) {
                PersonelDto selectedItem = (PersonelDto) sspnrAmbarSorumlusu.getSelectedItem();
                islem1ViewModel.setSelectedPersonelDto(selectedItem);
            }

            @Override
            public void onNothingSelected() {
                int y = 0;
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
//        actAmbarSorumlusu.setAdapter(personelAdapter);
        sspnrAmbarSorumlusu.setAdapter(personelAdapter);

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
    public TifIslem1Dto getIslemDto() {
        TifIslem1Dto dto = new TifIslem1Dto();
        dto.setIslemTarihi(islem1ViewModel.getIslemTarihi());
        dto.setIdAmbarSorumlusu(islem1ViewModel.getSelectedPersonelDto().getId());
        dto.setIdGirisYapilanAmbar(islem1ViewModel.getSelectedAmbarDto().getId());
        dto.setAciklama(islem1ViewModel.getAciklama());
        return dto;
    }
}
