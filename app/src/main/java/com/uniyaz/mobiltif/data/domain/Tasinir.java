package com.uniyaz.mobiltif.data.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Created by İrfan Öngüç on 19.05.2019
 */

public class Tasinir extends BaseModel {
    public final static String TABLE_NAME = "Tasinir";
    public final static String keyId = "id";
    public final static String keyName = "adi";
    public final static String keyTasinirKodu = "taisinir_kodu";

    private String id;
    @SerializedName("adi")
    private String name;
    @SerializedName("tasinirKodu")
    private Long tasinirKodu;

    public String getIdString() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTasinirKodu() {
        return tasinirKodu;
    }

    public void setTasinirKodu(Long tasinirKodu) {
        this.tasinirKodu = tasinirKodu;
    }

    @Override
    public String toString() {
        return name;
    }
}
