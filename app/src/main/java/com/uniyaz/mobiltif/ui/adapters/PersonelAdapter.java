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
import com.uniyaz.mobiltif.data.dto.PersonelDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by İrfan Öngüç on 15.04.2019.
 */

public class PersonelAdapter extends BaseAdapter implements Filterable {

    List<PersonelDto> personelDtoList;
    private LayoutInflater layoutInflater;

    public PersonelAdapter(List<PersonelDto> personelDtoList, Activity activity) {
        if (personelDtoList == null) personelDtoList = new ArrayList<>();
        layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

    @Override
    public Filter getFilter() {
        return new TasinirFilter();
    }


    public class TasinirFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            final FilterResults results = new FilterResults();

            List<PersonelDto> tasinirListStatic = personelDtoList;

            if (constraint == null || constraint.toString() == null) {
                results.values = personelDtoList;
                results.count = tasinirListStatic.size();
            } else {
                if (isSelectedItem(constraint)) {
                    results.values = new ArrayList<>();
                    results.count = 0;
                    return results;
                }
                String searched = constraint.toString().toLowerCase().trim();
                List<PersonelDto> newTasinirList = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    newTasinirList = tasinirListStatic.stream().filter(p -> String.valueOf(p.getIsim()).startsWith(searched)).collect(Collectors.toList());
                } else {
                    newTasinirList = Stream.of(tasinirListStatic).filter(p -> String.valueOf(p.getIsim()).startsWith(searched)).collect(com.annimon.stream.Collectors.toList());
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
            personelDtoList = (List<PersonelDto>) results.values;
            if (results.count > 0) {
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
        }
    }
}
