package com.uniyaz.mobiltif.ui.adapters;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.annimon.stream.Stream;
import com.uniyaz.mobiltif.R;
import com.uniyaz.mobiltif.data.dto.AmbarDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import gr.escsoft.michaelprimez.searchablespinner.interfaces.ISpinnerSelectedView;

/**
 * Created by İrfan Öngüç on 15.04.2019.
 */

public class AmbarAdapter extends BaseAdapter implements Filterable {

    List<AmbarDto> ambarDtoList;
    List<AmbarDto> ambarDtoListAll;
    private LayoutInflater layoutInflater;

    public AmbarAdapter(List<AmbarDto> ambarDtoList, Activity activity) {
        if (ambarDtoList == null) ambarDtoList = new ArrayList<>();
        layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.ambarDtoListAll = ambarDtoList;
        this.ambarDtoList = ambarDtoList;
    }

    @Override
    public int getCount() {
        return ambarDtoList.size();
    }

    @Override
    public AmbarDto getItem(int position) {
        return ambarDtoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return ambarDtoList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = layoutInflater.inflate(R.layout.recipe_auto_list_item, null);
        TextView tvTasinirKodu = rowView.findViewById(R.id.textautocomplete);
        tvTasinirKodu.setText(ambarDtoList.get(position).getAdi());
        return rowView;
    }

    @Override
    public Filter getFilter() {
        return new TasinirFilter();
    }



    public class TasinirFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            final FilterResults results = new FilterResults();

            if (constraint == null || constraint.toString() == null) {
                results.values = ambarDtoListAll;
                results.count = ambarDtoListAll.size();
            } else {
                if (isSelectedItem(constraint)) {
                    results.values = new ArrayList<>();
                    results.count = 0;
                    return results;
                }
                String searched = constraint.toString().toLowerCase().trim();
                List<AmbarDto> newTasinirList;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    newTasinirList = ambarDtoListAll.stream().filter(p -> p.getAdi() != null && p.getAdi().toLowerCase().startsWith(searched)).collect(Collectors.toList());
                } else {
                    newTasinirList = Stream.of(ambarDtoListAll).filter(p -> p.getAdi() != null && p.getAdi().toLowerCase().startsWith(searched)).collect(com.annimon.stream.Collectors.toList());
                }

                if (newTasinirList == null)
                    newTasinirList = new ArrayList<>();

                results.values = newTasinirList;
                results.count = newTasinirList.size();
            }
            return results;
        }

        private boolean isSelectedItem(CharSequence constraint) {
            if (constraint.length() > 10) {
                return true;
            }
            return false;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (constraint == null)
                ambarDtoList = ambarDtoListAll;
            if (results.count > 0) {
                ambarDtoList = (List<AmbarDto>) results.values;
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
        }
    }
}
