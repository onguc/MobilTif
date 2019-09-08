package com.uniyaz.mobiltif.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.github.andreilisun.swipedismissdialog.SwipeDismissDialog;
import com.uniyaz.mobiltif.R;
import com.uniyaz.mobiltif.data.dto.MuhatapDto;
import com.uniyaz.mobiltif.data.dto.TifIslemHibeDto;
import com.uniyaz.mobiltif.databinding.FragmentTifHibeBinding;
import com.uniyaz.mobiltif.iface.IMuhatap;
import com.uniyaz.mobiltif.iface.ITifIslem;
import com.uniyaz.mobiltif.pattern.PopupBuilder;
import com.uniyaz.mobiltif.viewmodel.TifIslemHibeViewModel;

public class TifIslemHibeFragment extends Fragment implements ITifIslem<TifIslemHibeDto>, IMuhatap {
    TifIslemHibeViewModel islemHibeViewModel;
    ListView lvMuhatap;
    SwipeDismissDialog dismissDialog;
    PopupBuilder popupBuilder;

    public TifIslemHibeFragment() {
        // Required empty public constructor
    }


    public static TifIslemHibeFragment newInstance() {
        TifIslemHibeFragment fragment = new TifIslemHibeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        islemHibeViewModel = new TifIslemHibeViewModel();
        MuhatapDto muhatapDto = new MuhatapDto();
        muhatapDto.setIsim("İRFAN HACI BILGIN OZKORKMAZ");
        muhatapDto.setSbsMuhatapId(30023l);
        islemHibeViewModel.setMuhatap(muhatapDto);
        FragmentTifHibeBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tif_hibe, container, false);
        binding.setViewModel(islemHibeViewModel);
        binding.setFragment(this);
        View view = binding.getRoot();

        return view;
    }

    @Override
    public TifIslemHibeDto getIslemDto() {
        TifIslemHibeDto dto = new TifIslemHibeDto();
        dto.setIslemTarihi(islemHibeViewModel.getIslemTarihi());
        dto.setIdMuhatap(islemHibeViewModel.getMuhatap().getSbsMuhatapId());
        dto.setDayanakBelgeTarihi(islemHibeViewModel.getDayanakBelgeTarihi());
        dto.setAciklama(islemHibeViewModel.getAciklama());
        return dto;
    }


    @Override
    public void setMuhatapDto(MuhatapDto muhatapDto) {
        islemHibeViewModel.setMuhatap(muhatapDto);
    }
}
