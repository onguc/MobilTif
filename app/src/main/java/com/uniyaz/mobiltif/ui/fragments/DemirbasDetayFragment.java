package com.uniyaz.mobiltif.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.uniyaz.mobiltif.R;
import com.uniyaz.mobiltif.data.domain.Envanter;
import com.uniyaz.mobiltif.databinding.FragmentDemibasDetayBinding;
import com.uniyaz.mobiltif.ui.activities.MainActivity;
import com.uniyaz.mobiltif.viewmodel.EnvanterViewModel;

import java.util.ArrayList;
import java.util.List;

public class DemirbasDetayFragment extends Fragment {

//    List<ImageInfo> imageInfoList;

    Envanter envanter;

    //    @BindView(R.id.recylerview)
    RecyclerView recyclerView;

    public static DemirbasDetayFragment getNewInstance(Envanter envanter) {
        if (envanter == null)
            return null;
        DemirbasDetayFragment demirbasDetayFragment = new DemirbasDetayFragment();
        demirbasDetayFragment.envanter = envanter;
        return demirbasDetayFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        MainActivity mainActivity = (MainActivity) getActivity();
        EnvanterViewModel viewModel = new EnvanterViewModel(envanter);
        FragmentDemibasDetayBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_demibas_detay, container, false);
        binding.setViewModel(viewModel);
        binding.setFragment(this);

        recyclerView = binding.rvDemirbasPhotoList;
        View root = binding.getRoot();
        defineView(envanter);
        return root;
    }

    private void defineView(Envanter envanter) {
        new PhotoIslem().createView(recyclerView, getActivity(), envanter.getUrlResimList());
    }

    public void onClickBtnOnlineTifIslemleri() {
        List<Envanter> envanters = new ArrayList<>();
        envanters.add(envanter);
        TifFragment tifFragment = TifFragment.getNewInstance(envanters);
        MainActivity activity = (MainActivity) getActivity();

        activity.startFragmentByBackStack(tifFragment,"Online Tif İşlemleri Demirbaş");
    }
}
