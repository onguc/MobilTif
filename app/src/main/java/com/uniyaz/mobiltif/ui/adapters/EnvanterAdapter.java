package com.uniyaz.mobiltif.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.uniyaz.mobiltif.R;
import com.uniyaz.mobiltif.data.domain.Envanter;
import com.uniyaz.mobiltif.data.domain.Room;
import com.uniyaz.mobiltif.databinding.ItemEnvanterCardSelectableBinding;
import com.uniyaz.mobiltif.databinding.ItemOdaBilgiBinding;
import com.uniyaz.mobiltif.ui.activities.MainActivity;
import com.uniyaz.mobiltif.ui.fragments.OdaFragment;
import com.uniyaz.mobiltif.viewmodel.EnvanterCardViewModel;
import com.uniyaz.mobiltif.viewmodel.RoomViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnvanterAdapter extends RecyclerView.Adapter<EnvanterAdapter.EnvanterViewHolder> {

    private List<Object> envanterList;
    private Room room;
    private Map<Integer, Envanter> selectedEnvanterlist;
    int count = 0;

    private final int TYPE_ROOM = 0;
    private final int TYPE_BACKGROUND = 1;
    private final int TYPE_ENVANTER = 2;


    MainActivity mainActivity;
    OdaFragment odaFragment;
    private EnvanterAdapter adapter;


    public EnvanterAdapter(MainActivity mainActivity, OdaFragment odaFragment, Room oda) {
        if (oda == null) oda = new Room();
        this.odaFragment = odaFragment;
        this.room = oda;
        envanterList = new ArrayList<>();
        envanterList.add(oda);
        envanterList.add(TYPE_BACKGROUND);
        envanterList.addAll(oda.getEnvanterList());
        if (envanterList == null) envanterList = new ArrayList<>();
        this.mainActivity = mainActivity;
        adapter = this;
        selectedEnvanterlist = new HashMap<>();
    }

    @NonNull
    @Override
    public EnvanterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == TYPE_ROOM) {
            ItemOdaBilgiBinding cardBindingOda = DataBindingUtil.inflate(inflater, R.layout.item_oda_bilgi, parent, false);
            return new EnvanterViewHolder(cardBindingOda);

        } else if (viewType == TYPE_ENVANTER) {
            ItemEnvanterCardSelectableBinding cardBindingEnvanter = DataBindingUtil.inflate(inflater, R.layout.item_envanter_card_selectable, parent, false);
            return new EnvanterViewHolder(cardBindingEnvanter);

        } else if (viewType == TYPE_BACKGROUND) {
            View view = inflater.inflate(R.layout.bg_arrow_bottom, parent, false);
            return new EnvanterViewHolder(view);
        } else {
            return null;
        }

//        ItemEnvanterCardBinding cardBinding = ItemEnvanterCardBinding.inflate(inflater, parent, false); // bu da olur{
    }

    @Override
    public void onBindViewHolder(@NonNull EnvanterViewHolder holder, int position) {

        holder.bindDto(envanterList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return envanterList.size();
    }

    @Override
    public int getItemViewType(int position) {
        Object o = envanterList.get(position);
        if (o instanceof Room) {
            return TYPE_ROOM;
        } else if (o instanceof Envanter) {
            return TYPE_ENVANTER;
        } else
            return TYPE_BACKGROUND;
    }

    public class EnvanterViewHolder extends RecyclerView.ViewHolder {

        ViewDataBinding viewDataBinding;
        int x;

        private EnvanterViewHolder(View view) {
            super(view);
        }

        private EnvanterViewHolder(ViewDataBinding viewDataBinding) {
            super(viewDataBinding.getRoot());
            this.viewDataBinding = viewDataBinding;
        }

        private void bindDto(Object object, int index) {
            if (object instanceof Room) {
                Room room = (Room) object;
                RoomViewModel viewModel = new RoomViewModel(room);
                ItemOdaBilgiBinding binding = (ItemOdaBilgiBinding) this.viewDataBinding;
                binding.setViewModel(viewModel);
                binding.executePendingBindings();
            } else if (object instanceof Envanter) {
                Envanter envanter = (Envanter) object;
                EnvanterCardViewModel viewModel = new EnvanterCardViewModel(adapter, envanter, index);
                ItemEnvanterCardSelectableBinding binding = (ItemEnvanterCardSelectableBinding) this.viewDataBinding;
                binding.setViewModel(viewModel);
                binding.setActivity(mainActivity);
                binding.executePendingBindings();
            }
//            if (viewDataBinding instanceof ItemOdaBilgiBinding && x==0) {
//                count++;
//                if (count > 1) return;
//                RoomViewModel viewModel = new RoomViewModel(room);
//                ItemOdaBilgiBinding binding = (ItemOdaBilgiBinding) this.viewDataBinding;
//                binding.setViewModel(viewModel);
//                binding.executePendingBindings();
//            } else if (viewDataBinding instanceof ItemEnvanterCardSelectableBinding && x==1) {
//                List<Envanter> envanters = room.getEnvanterList();
//                if (envanters == null || envanters.size() == 0)
//                    return;
//
//                EnvanterCardViewModel viewModel = new EnvanterCardViewModel(adapter, envanters.get(index), index);
//                ItemEnvanterCardSelectableBinding binding = (ItemEnvanterCardSelectableBinding) this.viewDataBinding;
//                binding.setViewModel(viewModel);
//                binding.setActivity(mainActivity);
//                binding.executePendingBindings();
//            }
        }
    }

    public void addSelectedEnvanter(int index, Envanter envanter) {
        selectedEnvanterlist.put(index, envanter);
        odaFragment.setBackGroundBtnOnlineTifIslemleri(R.drawable.bg_btn_active);
    }

    public void removeSelectedEnvanter(int index) {
        selectedEnvanterlist.remove(index);
        if (selectedEnvanterlist == null || selectedEnvanterlist.size() == 0)
            odaFragment.setBackGroundBtnOnlineTifIslemleri(R.drawable.bg_btn_passive);
    }

    public Map<Integer, Envanter> getSelectedEnvanterlist() {
        return selectedEnvanterlist;
    }

    public void showSnackBar(String message) {
        mainActivity.showSnackbar(message);
    }
}
