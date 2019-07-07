package com.uniyaz.mobiltif.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.uniyaz.mobiltif.data.domain.Envanter;
import com.uniyaz.mobiltif.databinding.ItemTifEnvanterCardBinding;
import com.uniyaz.mobiltif.viewmodel.TifEnvanterCardViewModel;

import java.util.List;

public class TifEnvanterAdapter extends BaseAdapter {
    List<Envanter> envanterList;

    public TifEnvanterAdapter(List<Envanter> envanters) {
        this.envanterList = envanters;
    }

    @Override
    public int getCount() {
        return envanterList.size();
    }

    @Override
    public Object getItem(int position) {
        Envanter envanter = envanterList.get(position);
        TifEnvanterCardViewModel viewModel = new TifEnvanterCardViewModel(envanter, position + 1);
        return viewModel;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemTifEnvanterCardBinding binding;
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            binding = ItemTifEnvanterCardBinding.inflate(layoutInflater, parent, false);

//            convertView = LayoutInflater.from(activity).inflate(R.layout.item_tif_envanter_card, null);
//            binding = DataBindingUtil.bind(convertView);
//            convertView.setTag(binding);
        } else {
            binding = (ItemTifEnvanterCardBinding) convertView.getTag();
        }
        binding.setViewModel((TifEnvanterCardViewModel) getItem(position));

        return binding.getRoot();
    }
}
