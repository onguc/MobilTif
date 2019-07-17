package com.uniyaz.mobiltif.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.uniyaz.mobiltif.data.domain.Envanter;
import com.uniyaz.mobiltif.databinding.ItemEnvanterTifCardBinding;
import com.uniyaz.mobiltif.databinding.ItemIslemBinding;
import com.uniyaz.mobiltif.ui.fragments.TifFragment;
import com.uniyaz.mobiltif.viewmodel.TifEnvanterCardViewModel;

import java.util.ArrayList;
import java.util.List;

public class TifEnvanterAdapter extends BaseAdapter {
    List<Object> envanterList;
    TifFragment fragment;

    public TifEnvanterAdapter(TifFragment fragment, List<Envanter> envanters) {
        this.envanterList = new ArrayList<>(envanters);
        this.fragment = fragment;
        envanterList.add(fragment);
    }

    @Override
    public int getCount() {
        return envanterList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (envanterList.get(position) instanceof Envanter) {
            return 0;
        } else
            return 1;
    }


    @Override
    public Object getItem(int position) {
        Object o = envanterList.get(position);
        if (o instanceof Envanter) {
            Envanter envanter = (Envanter) o;
            TifEnvanterCardViewModel viewModel = new TifEnvanterCardViewModel(envanter, position + 1);
            return viewModel;
        } else {
            return null;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int itemViewType = getItemViewType(position);

        LayoutInflater layoutInflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (itemViewType == 0) {
            ItemEnvanterTifCardBinding binding;
            binding = ItemEnvanterTifCardBinding.inflate(layoutInflater, parent, false);
//         if (convertView == null) {
//
//
//            convertView = LayoutInflater.from(activity).inflate(R.layout.item_tif_envanter_card, null);
//            binding = DataBindingUtil.bind(convertView);
//            convertView.setTag(binding);
//       } else {
//             binding = (ItemTifEnvanterCardBinding) convertView.getTag();
//         }
            binding.setViewModel((TifEnvanterCardViewModel) getItem(position));

            return binding.getRoot();
        } else {
            ItemIslemBinding binding = ItemIslemBinding.inflate(layoutInflater, parent, false);
            binding.setFragment(fragment);
            return binding.getRoot();
        }

    }
}
