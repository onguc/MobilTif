package com.uniyaz.mobiltif.ui.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.uniyaz.mobiltif.R;
import com.uniyaz.mobiltif.data.dto.MuhatapDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by İrfan Öngüç on 15.04.2019.
 */

public class MuhatapAdapter extends BaseAdapter {

    List<MuhatapDto> muhatapDtoList;
    List<MuhatapDto> muhatapDtoListAll;
    private LayoutInflater layoutInflater;

    public MuhatapAdapter(List<MuhatapDto> muhatapDtoList, Activity activity) {
        if (muhatapDtoList == null) muhatapDtoList = new ArrayList<>();
        layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.muhatapDtoListAll = muhatapDtoList;
        this.muhatapDtoList = muhatapDtoList;
    }

    @Override
    public int getCount() {
        return muhatapDtoList.size();
    }

    @Override
    public MuhatapDto getItem(int position) {
        return muhatapDtoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return muhatapDtoList.get(position).getSbsMuhatapId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = layoutInflater.inflate(R.layout.recipe_auto_list_item, null);
        TextView tvTasinirKodu = rowView.findViewById(R.id.textautocomplete);
        tvTasinirKodu.setText(muhatapDtoList.get(position).getIsim());
        return rowView;
    }
}
