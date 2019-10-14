package com.uniyaz.mobiltif.ui.fragments;

import android.app.Activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.uniyaz.mobiltif.ui.adapters.PhotoAdapter;

import java.util.List;

/**
 * Created by İrfan Öngüç on 19.05.2019
 */

public class PhotoIslem {
    private PhotoAdapter adapter;
    private LinearLayoutManager linearLayoutManager = null;

    public PhotoIslem createView(RecyclerView recyclerView, Activity activity, List<String> imageUrlList) {
        adapter = new PhotoAdapter(activity, imageUrlList);
        recyclerView.setAdapter(adapter);
        linearLayoutManager = new LinearLayoutManager(activity.getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        return this;
    }


    public void setForImageListEnvanter(boolean isForImageListEnvanter) {
        if (adapter != null) {
            adapter.setForImageListEnvanter(isForImageListEnvanter);
            if (isForImageListEnvanter)
                linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        }

    }


    public void refresh() {
        if (adapter != null)
            adapter.notifyDataSetChanged();
    }
}
