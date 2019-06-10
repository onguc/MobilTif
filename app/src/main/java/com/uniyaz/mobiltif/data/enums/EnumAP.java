package com.uniyaz.mobiltif.data.enums;

/**
 * Created by İrfan Öngüç on 19.05.2019
 */

public enum EnumAP {
    AKTIF("Aktif"),
    PASIF("Pasif");

    String enumAp;

    EnumAP(String enumAP) {
        this.enumAp = enumAP;
    }

    @Override
    public String toString() {
        return enumAp;
    }}
