package com.uniyaz.mobiltif.ui.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.uniyaz.mobiltif.R;
import com.uniyaz.mobiltif.data.domain.Envanter;
import com.uniyaz.mobiltif.ui.activities.MainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by İrfan Öngüç on 19.05.2019
 */

public class EnvanterAdapter extends RecyclerView.Adapter<EnvanterAdapter.MyViewHolder> {

    private List<Envanter> envanterList;
    private Activity activity;
    private MainActivity.EnvanterAdapterListener listener;

    public EnvanterAdapter(MainActivity.EnvanterAdapterListener listener) {
        this.listener = listener;
        if (envanterList == null) envanterList = new ArrayList<>();
        this.envanterList = listener.getEnvanterList();
        this.activity = listener.getActivity();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(activity.getApplicationContext());

        View view = inflater.inflate(R.layout.item_envanter_card, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Envanter selectedEnvanter = envanterList.get(position);
        if (selectedEnvanter == null)
            throw new IllegalStateException("Envanter Null Olamaz!");
        holder.setData(selectedEnvanter);
    }

    @Override
    public int getItemCount() {
        return envanterList.size();
    }

//    public void deleteItem(int position) {
//        new EnvanterRepo().delete(envanterList.get(position));
//        envanterList.remove(position);
//        notifyItemRemoved(position);
//        showSnackbar("Silindi");
//    }


    private void showSnackbar(String message) {
        Snackbar
                .make(activity.findViewById(R.id.constraintLayout), message, Snackbar.LENGTH_LONG)
                .show();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvMudurlukAdi;
        TextView tvTasinirAdi;
        TextView tvTarih;
        TextView tvBirim;
        TextView tvMiktar;


        public MyViewHolder(View itemRow) {
            super(itemRow);

            tvMudurlukAdi = itemRow.findViewById(R.id.tvMudurlukAdi);
            tvTasinirAdi = itemRow.findViewById(R.id.tvTasinirAdi);
            tvTarih = itemRow.findViewById(R.id.tvTarih);
            tvBirim = itemRow.findViewById(R.id.tvBirim);
            tvMudurlukAdi = itemRow.findViewById(R.id.tvMudurlukAdi);
            tvMiktar = itemRow.findViewById(R.id.tvMiktar);

            itemRow.setOnClickListener(v -> {
                int adapterPosition = this.getAdapterPosition();
                Envanter envanter = envanterList.get(adapterPosition);
                listener.fillEnvanter(envanter);
                listener.dismissPopupWindow();
            });
        }

        private void setData(Envanter envanter) {
            tvMudurlukAdi.setText(envanter.getRoom().getDepartment().getName());
            tvTasinirAdi.setText(envanter.getTasinir().getName());
            tvTarih.setText(envanter.getDateAsString(envanter.getSayimTarihi()));
            tvBirim.setText(envanter.getBirimi().name());
            tvMiktar.setText(String.valueOf(envanter.getCount()));
        }
    }
}
