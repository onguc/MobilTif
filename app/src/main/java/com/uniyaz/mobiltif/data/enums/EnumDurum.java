package com.uniyaz.mobiltif.data.enums;

/**
 * Created by İrfan Öngüç on 19.05.2019
 */

public enum EnumDurum {
    AKTIF("Aktif"),
    PASIF("Pasif");

    String enumAp;

    EnumDurum(String enumAP) {
        this.enumAp = enumAP;
    }

    @Override
    public String toString() {
        return enumAp;
    }}
