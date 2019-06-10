package com.uniyaz.mobiltif.ui.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.uniyaz.mobiltif.R;
import com.uniyaz.mobiltif.data.domain.IPhoto;
import com.uniyaz.mobiltif.data.enums.EnumPhotoLacation;
import com.uniyaz.mobiltif.ui.fragments.PhotoIslem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DemirbasActivity extends AppCompatActivity {

    List<IPhoto> photoList;
    PhotoIslem photoIslem;

    @BindView(R.id.recylerview)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_demibas_detay);
        ButterKnife.bind(this);

        photoList = new ArrayList<>();
        photoIslem = new PhotoIslem(EnumPhotoLacation.ENVANTER);
        photoIslem.createView(recyclerView, this, photoList);
    }
}
