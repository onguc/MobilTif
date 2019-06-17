package com.uniyaz.mobiltif.ui.fragments;

import android.app.Activity;

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
//        for (int i = 0; i < 5; i++) {
//            ImageInfo imageInfo = new ImageInfo();
//            Bitmap bitmap = BitmapFactory.decodeResource(activity.getResources(), R.drawable.test);
//            InputStream inStream = activity.getResources().openRawResource(R.drawable.test);
//            try {
//                byte[] bytes = new byte[inStream.available()];
//                Bitmap  bitmap2 = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
//                activity.getAssets().open("test.png");
//                imageInfo.setBitmap(bitmap2);
//                int y=0;
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            bitmapList.add(imageInfo);
//        }

        adapter = new PhotoAdapter(activity, bitmapList);
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
