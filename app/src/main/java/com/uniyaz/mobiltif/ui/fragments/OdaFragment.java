package com.uniyaz.mobiltif.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.uniyaz.mobiltif.R;
import com.uniyaz.mobiltif.data.domain.Envanter;
import com.uniyaz.mobiltif.data.domain.Room;
import com.uniyaz.mobiltif.databinding.FragmentOdaBinding;
import com.uniyaz.mobiltif.ui.activities.MainActivity;
import com.uniyaz.mobiltif.ui.adapters.EnvanterAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class OdaFragment extends Fragment {

    private Room room;
    private MainActivity mainActivity;
    private EnvanterAdapter adapter;
    private Button btnTifIslemleri;
    private FragmentOdaBinding binding;

    public static OdaFragment getNewInstance(MainActivity mainActivity, Room room) {
        if (room == null) return null;
        OdaFragment odaFragment = new OdaFragment();
        odaFragment.room = room;
        odaFragment.mainActivity = mainActivity;
        return odaFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (binding == null) {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_oda, container, false);
            binding.setFragment(this);
            defineView(binding);
        }
        View root = binding.getRoot();
        return root;
    }

    private void defineView(FragmentOdaBinding binding) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        btnTifIslemleri = binding.btnTifIslemleri;
        adapter = new EnvanterAdapter(mainActivity, this, room);
        RecyclerView recyclerView = binding.rvDemirbasList;
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);

//        adapter.notifyDataSetChanged();

        List<String> urlResimList = room.getUrlResimList();
        new PhotoIslem().createView(binding.rvOdaPhotoList, getActivity(), urlResimList);
    }

    public void onClickBtnOnlineTifIslemleri() {
        Map<Integer, Envanter> selectedEnvanterlist = adapter.getSelectedEnvanterlist();
        if (selectedEnvanterlist == null) {
            Log.e("OdaFragment", "onBtnOnlineTifIslemcliced-> HATA: selectedEnvanterList is Null!");
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
        activity.startFragmentByBackStack(tifFragment, "Online Tif İşlemleri Oda");
    }

    public void setBackGroundBtnOnlineTifIslemleri(int resource) {
        btnTifIslemleri.setBackgroundResource(resource);
    }
}
