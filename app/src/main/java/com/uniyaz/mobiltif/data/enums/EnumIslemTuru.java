package com.uniyaz.mobiltif.data.enums;

/**
 * Created by İrfan Öngüç on 14.08.2019
 */

public enum EnumIslemTuru {
    TRANSFER("TRANSFER"),
    HIBE("HIBE"),
    HURDAYA_AYIRMA("HURDAYA AYIRMA"),
    ZIMMET("ZIMMET"),
    ZIMMET_DEVRI("ZIMMET DEVRİ"),
    ZIMMET_IADE("ZIMMET İADE");

    private String islemTuru;

    private EnumIslemTuru(String islemTuru) {
        this.islemTuru = islemTuru;
    }

    @Override
    public String toString() {
        return islemTuru;
    }}
