package com.uniyaz.mobiltif.ui.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.uniyaz.mobiltif.R;
import com.uniyaz.mobiltif.data.domain.Envanter;
import com.uniyaz.mobiltif.databinding.ItemEnvanterCardSelectableBinding;
import com.uniyaz.mobiltif.ui.activities.MainActivity;
import com.uniyaz.mobiltif.viewmodel.EnvanterCardViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnvanterAdapter extends RecyclerView.Adapter<EnvanterAdapter.EnvanterViewHolder> {

    private List<Envanter> envanterList;
    private Map<Integer, Envanter> selectedEnvanterlist;

    MainActivity mainActivity;

    private EnvanterAdapter adapter;


    public EnvanterAdapter(MainActivity mainActivity, List<Envanter> envanterList) {
        if (envanterList == null) envanterList = new ArrayList<>();
        this.envanterList = envanterList;
        this.mainActivity = mainActivity;
        adapter = this;
        selectedEnvanterlist = new HashMap<>();
    }

    @NonNull
    @Override
    public EnvanterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemEnvanterCardSelectableBinding cardBinding = DataBindingUtil.inflate(inflater, R.layout.item_envanter_card_selectable, parent, false);
//        ItemEnvanterCardBinding cardBinding = ItemEnvanterCardBinding.inflate(inflater, parent, false); // bu da olur{
        return new EnvanterViewHolder(cardBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull EnvanterViewHolder holder, int position) {
        holder.bindDto(envanterList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return envanterList.size();
    }


    public class EnvanterViewHolder extends RecyclerView.ViewHolder {

        ItemEnvanterCardSelectableBinding cardBinding;

        public EnvanterViewHolder(ItemEnvanterCardSelectableBinding cardBinding) {
            super(cardBinding.getRoot());
            this.cardBinding = cardBinding;
        }

        public void bindDto(Envanter envanter, int index) {

            EnvanterCardViewModel viewModel = new EnvanterCardViewModel(adapter, envanter, index);
            cardBinding.setViewModel(viewModel);
            cardBinding.setActivity(mainActivity);
            cardBinding.executePendingBindings();
        }
    }

   public void addSelectedEnvanter(int index, Envanter envanter) {
        selectedEnvanterlist.put(index,envanter);
    }

   public void removeSelectedEnvanter(int index) {
        selectedEnvanterlist.remove(index);
    }

    public Map<Integer, Envanter> getSelectedEnvanterlist() {
        return selectedEnvanterlist;
    }
}
