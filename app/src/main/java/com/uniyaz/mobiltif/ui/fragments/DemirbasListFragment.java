package com.uniyaz.mobiltif.ui.fragments;

import android.os.Bundle;
import android.util.Log;
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
import com.uniyaz.mobiltif.data.domain.Room;
import com.uniyaz.mobiltif.databinding.FragmentDemibasListBinding;
import com.uniyaz.mobiltif.ui.activities.MainActivity;
import com.uniyaz.mobiltif.ui.adapters.EnvanterAdapter;
import com.uniyaz.mobiltif.viewmodel.DemirbasListViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class DemirbasListFragment extends Fragment {

    private List<Envanter> envanterList;
    private Room room;
    private MainActivity mainActivity;
    EnvanterAdapter adapter;

    public static DemirbasListFragment getNewInstance(MainActivity mainActivity, Room room) {
        if (room == null) return null;
        DemirbasListFragment demirbasListFragment = new DemirbasListFragment();
        demirbasListFragment.room = room;
        demirbasListFragment.envanterList = room.getEnvanterList();
        demirbasListFragment.mainActivity = mainActivity;
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
        binding.setFragment(this);
        binding.setViewModel(viewModel);
        View root = binding.getRoot();


        defineView(binding);
        return root;
    }

    private void defineView(FragmentDemibasListBinding binding) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);

        adapter = new EnvanterAdapter(mainActivity, envanterList);
        RecyclerView recyclerView = binding.rvDemirbasList;
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);
//        adapter.notifyDataSetChanged();

        List<String> urlResimList = room.getUrlResimList();
        new PhotoIslem().createView(binding.rvOdaPhotoList, getActivity(), urlResimList);
    }

    public void onBtnOnlineTifIslemClicked() {
        Map<Integer, Envanter> selectedEnvanterlist = adapter.getSelectedEnvanterlist();
        if (selectedEnvanterlist == null) {
            Log.e("DemirbasListFragment", "onBtnOnlineTifIslemcliced-> HATA: selectedEnvanterList is Null!");
            return;
        } else if (selectedEnvanterlist.size() == 0) {
            mainActivity.showWarningDialog("Uyarı", "Envanter Seçiniz!");
            return;
        }
        List<Envanter> envanters = new ArrayList<>();
        SortedSet<Integer> sortedSet = new TreeSet<>(selectedEnvanterlist.keySet());
        for (Integer key : sortedSet) {
            envanters.add(selectedEnvanterlist.get(key));
        }
        TifFragment tifFragment = TifFragment.getNewInstance(envanters);
        MainActivity activity = (MainActivity) getActivity();
        activity.startFragmentByBackStack(tifFragment);
    }
}
