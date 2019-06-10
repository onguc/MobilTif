package com.uniyaz.mobiltif.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.uniyaz.mobiltif.data.domain.Envanter;

import java.util.List;

public class DemirbasListFragment extends Fragment {

    private List<Envanter> envanterList;

    public static DemirbasListFragment getNewInstance(List<Envanter> envanterList) {
        DemirbasListFragment demirbasListFragment = new DemirbasListFragment();
        demirbasListFragment.envanterList = envanterList;
        return demirbasListFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
