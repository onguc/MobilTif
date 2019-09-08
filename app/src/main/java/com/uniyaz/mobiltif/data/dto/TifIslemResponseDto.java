package com.uniyaz.mobiltif.data.dto;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * Created by İrfan Öngüç on 20.08.2019
 */

public class TifIslemResponseDto {
    @SerializedName(value = "dosyaNo")
    private String dosyaNo;

    @SerializedName(value = "zimmetSiraNo")
    private String zimmetSiraNo;

    public String getDosyaNo() {
        if (dosyaNo == null)
            return zimmetSiraNo;
        return dosyaNo;
    }

    public void setDosyaNo(String dosyaNo) {
        this.dosyaNo = dosyaNo;
    }

    public String getZimmetSiraNo() {
        return zimmetSiraNo;
    }

    public void setZimmetSiraNo(String zimmetSiraNo) {
        this.zimmetSiraNo = zimmetSiraNo;
    }

    @NonNull
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
