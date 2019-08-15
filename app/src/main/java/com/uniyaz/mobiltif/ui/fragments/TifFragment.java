package com.uniyaz.mobiltif.ui.fragments;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.annimon.stream.Stream;
import com.github.andreilisun.swipedismissdialog.SwipeDismissDialog;
import com.uniyaz.mobiltif.R;
import com.uniyaz.mobiltif.data.domain.Envanter;
import com.uniyaz.mobiltif.data.domain.Room;
import com.uniyaz.mobiltif.data.dto.AmbarDto;
import com.uniyaz.mobiltif.data.dto.PersonelDto;
import com.uniyaz.mobiltif.data.dto.TifDto;
import com.uniyaz.mobiltif.data.enums.EnumIslemTuru;
import com.uniyaz.mobiltif.databinding.FragmentTifBinding;
import com.uniyaz.mobiltif.iface.ITif;
import com.uniyaz.mobiltif.iface.ITifIslem;
import com.uniyaz.mobiltif.pattern.FragmentCreationFactory;
import com.uniyaz.mobiltif.presenter.TifPresenter;
import com.uniyaz.mobiltif.ui.activities.MainActivity;
import com.uniyaz.mobiltif.ui.adapters.TifAdapter;

import java.util.List;

public class TifFragment extends Fragment implements ITif {

    private TifPresenter presenter;
    private FragmentTifBinding binding;
    private List<Envanter> envanters;
    private TifDto tifDto;
    ITifIslem iTifIslem;
    private EnumIslemTuru enumIslemTuru;
    private MainActivity mainActivity;

    private List<AmbarDto> ambarDtoList;
    private List<PersonelDto> personelDtoList;


    //public static TifFragment getNewInstance(MainActivity mainActivity, List<Envanter> envanters) {
    public static TifFragment getNewInstance(List<Envanter> envanters) {
        TifFragment fragment = new TifFragment();
        fragment.envanters = envanters;
        fragment.mainActivity = (MainActivity) fragment.getActivity();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        presenter = new TifPresenter(this);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tif, container, false);
        binding.setFragment(this);
        TifAdapter adapter = new TifAdapter(this, envanters);
        binding.rvTifList.setAdapter(adapter);
        binding.rvTifList.setLayoutManager(linearLayoutManager);

        View view = binding.getRoot();
        return view;
    }


    public void onItemSelectedSpinner(AdapterView<?> parent, View view, int position, long id) {
        EnumIslemTuru selectedItem = (EnumIslemTuru) parent.getSelectedItem();
        enumIslemTuru = selectedItem;
        Fragment fragment = FragmentCreationFactory.createAndGetFragment(enumIslemTuru);
        startFragmentByBackStack(fragment);
    }

    public void onClicBtnIslemiGerceklestir() {

        View dialog = LayoutInflater.from(getActivity()).inflate(R.layout.popup_basarili_kayit, null);
        new SwipeDismissDialog.Builder(getActivity())
                .setView(dialog)
                .build()
                .show();


        TifDto dto = new TifDto();
        Long[] idEnvanterList = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            idEnvanterList = envanters.stream().map(p -> p.getId()).toArray(Long[]::new);
        } else {
            idEnvanterList = Stream.of(envanters).map(p -> p.getId()).toArray(Long[]::new);
        }
        dto.setIdEnvanterList(idEnvanterList);
        dto.setIslemTuru(enumIslemTuru);
        dto.setVysTasinirTransferRequestDto(iTifIslem.getIslemDto());
        presenter.saveVysTasinirTransferIslem(dto);
    }

    public void startFragmentByBackStack(Fragment fragment) {
        if (fragment != null) {
            iTifIslem = (ITifIslem) fragment;
            FragmentManager fragmentManager = getFragmentManager();
            final FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction
                    .replace(R.id.f_layout, fragment, "fragment1")
                    .commit();
            fragmentManager.executePendingTransactions();
        }
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
        this.ambarDtoList = ambarDtoList;
    }

    @Override
    public void onSuccessforPersonelDtoList(List<PersonelDto> personelDtoList) {
        this.personelDtoList = personelDtoList;
    }

    @Override
    public void onSuccessForSaveTasinirTransferIslem() {
        int x = 0;
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
        return getContext();
    }


}
