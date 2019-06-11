package com.uniyaz.mobiltif.viewmodel;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.uniyaz.mobiltif.data.domain.Envanter;
import com.uniyaz.mobiltif.data.domain.Room;

import java.util.List;

public class DemirbasListViewModel extends BaseObservable {
    private Room room;
    private List<Envanter> demirbasList;

    public DemirbasListViewModel(List<Envanter> envanterList, Room room){
        this.demirbasList = envanterList;
    }

    @Bindable
    public List<Envanter> getDemirbasList() {
        return demirbasList;
    }

    public Room getRoom() {
        return room;
    }
}
