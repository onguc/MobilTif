package com.uniyaz.mobiltif.ui.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.uniyaz.mobiltif.R;
import com.uniyaz.mobiltif.data.dto.PersonelDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by İrfan Öngüç on 15.04.2019.
 */

public class PersonelAdapter extends BaseAdapter {

    List<PersonelDto> personelDtoList;
    List<PersonelDto> personelDtoListAll;
    private LayoutInflater layoutInflater;

    public PersonelAdapter(List<PersonelDto> personelDtoList, Context activity) {
        if (personelDtoList == null) personelDtoList = new ArrayList<>();
        layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.personelDtoListAll = personelDtoList;
        this.personelDtoList = personelDtoList;
    }

    @Override
    public int getCount() {
        return personelDtoList.size();
    }

    @Override
    public PersonelDto getItem(int position) {
        return personelDtoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return personelDtoList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = layoutInflater.inflate(R.layout.recipe_auto_list_item, null);
        TextView tvTasinirKodu = rowView.findViewById(R.id.textautocomplete);
        tvTasinirKodu.setText(personelDtoList.get(position).getIsim());
        return rowView;
    }
}
