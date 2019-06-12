package com.uniyaz.mobiltif.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.uniyaz.mobiltif.R;
import com.uniyaz.mobiltif.data.domain.Envanter;
import com.uniyaz.mobiltif.data.domain.ImageInfo;
import com.uniyaz.mobiltif.data.domain.Room;
import com.uniyaz.mobiltif.databinding.FragmentDemibasListBinding;
import com.uniyaz.mobiltif.ui.adapters.EnvanterAdapter;
import com.uniyaz.mobiltif.viewmodel.DemirbasListViewModel;

import java.util.ArrayList;
import java.util.List;

public class DemirbasListFragment extends Fragment {

    private List<Envanter> envanterList;
    private Room room;

    public static DemirbasListFragment getNewInstance(List<Envanter> envanterList, Room room) {
        DemirbasListFragment demirbasListFragment = new DemirbasListFragment();
        demirbasListFragment.envanterList = envanterList;
        demirbasListFragment.room = room;
        return demirbasListFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        DemirbasListViewModel viewModel = new DemirbasListViewModel(envanterList, room);
        FragmentDemibasListBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_demibas_list, container, false);
        binding.setViewModel(viewModel);
        View root = binding.getRoot();


        defineView(binding);
        return root;
    }

    private void defineView(FragmentDemibasListBinding binding) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);

        EnvanterAdapter adapter = new EnvanterAdapter(envanterList);
        RecyclerView recyclerView  = binding.rvDemirbasList;
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);
//        adapter.notifyDataSetChanged();

        List<ImageInfo> imageInfoList = new ArrayList<>();
        new PhotoIslem().createView(binding.rvOdaPhotoList, getActivity(), imageInfoList);
    }
}
