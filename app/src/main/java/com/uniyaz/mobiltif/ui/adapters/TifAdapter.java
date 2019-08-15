package com.uniyaz.mobiltif.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.uniyaz.mobiltif.R;
import com.uniyaz.mobiltif.data.domain.Envanter;
import com.uniyaz.mobiltif.data.enums.EnumIslemTuru;
import com.uniyaz.mobiltif.databinding.ItemEnvanterTifCardBinding;
import com.uniyaz.mobiltif.databinding.ItemTifIslemBinding;
import com.uniyaz.mobiltif.ui.fragments.TifFragment;
import com.uniyaz.mobiltif.viewmodel.TifEnvanterCardViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TifAdapter extends RecyclerView.Adapter<TifAdapter.TifViewHolder> {
    List<Object> envanterList;
    List<Envanter> envanters;
    TifFragment fragment;
    int countZimmetliEnvanter = 0;

    private final int TYPE_ENVANTER = 0;
    private final int TYPE_BACKGROUND = 1;
    private final int TYPE_FRAGMENT = 2;

    public TifAdapter(TifFragment fragment, List<Envanter> envanters) {
        this.envanterList = new ArrayList<>(envanters);
        this.envanters = envanters;
        this.fragment = fragment;
        envanterList.add(TYPE_BACKGROUND);
        envanterList.add(fragment);

        for (Envanter envanter : envanters) {
            if (envanter.getZimmetliMi()) {
                countZimmetliEnvanter++;
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        Object o = envanterList.get(position);
        if (o instanceof Envanter) {
            return TYPE_ENVANTER;
        } else if (o instanceof TifFragment) {
            return TYPE_FRAGMENT;
        } else {
            return TYPE_BACKGROUND;
        }
    }

    @NonNull
    @Override
    public TifViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == TYPE_ENVANTER) {
            ItemEnvanterTifCardBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_envanter_tif_card, parent, false);
            return new TifViewHolder(binding);

        } else if (viewType == TYPE_FRAGMENT) {
            ItemTifIslemBinding islemBinding = DataBindingUtil.inflate(inflater, R.layout.item_tif_islem, parent, false);
            List<EnumIslemTuru> listEnum = Arrays.asList(EnumIslemTuru.values());
            if (countZimmetliEnvanter == 0) {
                listEnum.remove(EnumIslemTuru.ZIMMET);
            } else if (countZimmetliEnvanter == envanters.size()) {
                listEnum.remove(EnumIslemTuru.ZIMMET_DEVRI);
                listEnum.remove(EnumIslemTuru.ZIMMET_IADE);
            } else {
                listEnum.remove(EnumIslemTuru.ZIMMET);
                listEnum.remove(EnumIslemTuru.ZIMMET_DEVRI);
                listEnum.remove(EnumIslemTuru.ZIMMET_IADE);
            }
            ArrayAdapter<EnumIslemTuru> islemTuruAdapter = new ArrayAdapter<EnumIslemTuru>(fragment.getContext(), android.R.layout.simple_list_item_1, listEnum);

            islemBinding.spinner.setAdapter(islemTuruAdapter);
            islemBinding.executePendingBindings(); // ilk spinner geldiğind onItemSelected içine girmiyordu. olması için eklendi.
            islemBinding.setFragment(fragment);
            return new TifViewHolder(islemBinding);

        } else if (viewType == TYPE_BACKGROUND) {
            View view = inflater.inflate(R.layout.bg_arrow_bottom, parent, false);
            return new TifViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull TifViewHolder holder, int position) {
        holder.bindDto(envanterList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return envanterList.size();
    }


    public class TifViewHolder extends RecyclerView.ViewHolder {

        ViewDataBinding viewDataBinding;
        int x;

        private TifViewHolder(View view) {
            super(view);
        }

        private TifViewHolder(ViewDataBinding viewDataBinding) {
            super(viewDataBinding.getRoot());
            this.viewDataBinding = viewDataBinding;
        }

        public void bindDto(Object object, int index) {
            if (object instanceof Envanter) {
                Envanter envanter = (Envanter) object;
                TifEnvanterCardViewModel viewModel = new TifEnvanterCardViewModel(envanter, index + 1);
                ItemEnvanterTifCardBinding binding = (ItemEnvanterTifCardBinding) this.viewDataBinding;
                binding.setViewModel(viewModel);
                binding.executePendingBindings();
            }
        }
    }
}
