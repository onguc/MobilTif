package com.uniyaz.mobiltif.ui.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uniyaz.mobiltif.data.domain.Envanter;
import com.uniyaz.mobiltif.databinding.ItemEnvanterCardBinding;

import java.util.ArrayList;
import java.util.List;

public class EnvanterAdapter extends RecyclerView.Adapter<EnvanterAdapter.EnvanterViewHolder> {

    private List<Envanter> envanterList;

    public EnvanterAdapter(List<Envanter> envanterList) {
        if (envanterList == null) envanterList = new ArrayList<>();
        this.envanterList = envanterList;
    }

    @NonNull
    @Override
    public EnvanterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        ItemEnvanterCardBinding cardBinding = ItemEnvanterCardBinding.inflate(inflater, parent, false);
        return new EnvanterViewHolder(cardBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull EnvanterViewHolder holder, int position) {
        holder.bindDto(envanterList.get(position));
    }

    @Override
    public int getItemCount() {
        return envanterList.size();
    }



    public class EnvanterViewHolder extends RecyclerView.ViewHolder {

        ItemEnvanterCardBinding cardBinding;

        public EnvanterViewHolder(ItemEnvanterCardBinding cardBinding) {
            super(cardBinding.getRoot());
            this.cardBinding = cardBinding;
        }

        public void bindDto(Envanter envanter) {
            cardBinding.setDemirbas(envanter);
            cardBinding.executePendingBindings();
        }
    }
}
