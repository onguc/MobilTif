package com.uniyaz.mobiltif.viewmodel;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.uniyaz.mobiltif.data.domain.Envanter;
import com.uniyaz.mobiltif.data.domain.Room;

import java.util.List;

public class DemirbasListViewModel extends BaseObservable {
    private Room room;

    public DemirbasListViewModel(List<Envanter> envanterList, Room room) {
        this.room = room;
    }


    @Bindable
    public Room getRoom() {
        return room;
    }

    public String getServisAmbar(){
        return room.getServisAdi() + " / Ambar İsmi Demirbaşta var ama odada yok!";
    }

    public String getEtiketNoOdaAdi(){
        return room.getQrCode()+"/"+room.getName();
    }

    public String getAdetBilgi(){
        return room.getDemirbasAdet()+" Adet Demirbaş Bulunmaktadır.";
    }
}
