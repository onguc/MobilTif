package com.uniyaz.mobiltif.ui.fragments;

import android.app.Activity;
import android.graphics.Bitmap;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.uniyaz.mobiltif.data.domain.ImageInfo;
import com.uniyaz.mobiltif.ui.adapters.PhotoAdapter;

import java.util.List;

/**
 * Created by İrfan Öngüç on 19.05.2019
 */

public class PhotoIslem {
    PhotoAdapter adapter;

    public PhotoIslem() {
    }

    public void createView(RecyclerView recyclerView, Activity activity, List<ImageInfo> bitmapList) {
        for (int i = 0; i < 5; i++) {
            ImageInfo imageInfo = new ImageInfo();
            bitmapList.add(imageInfo);
        }

        adapter = new PhotoAdapter(bitmapList);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity.getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }


    public void refresh() {
        if (adapter != null)
            adapter.notifyDataSetChanged();
    }
}
