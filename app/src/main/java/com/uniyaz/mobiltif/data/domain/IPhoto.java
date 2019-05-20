package com.uniyaz.mobiltif.data.domain;

import android.graphics.Bitmap;
import android.net.Uri;

import com.uniyaz.mobiltif.data.enums.EnumPhotoLacation;

/**
 * Created by İrfan Öngüç on 19.05.2019
 */

public interface IPhoto {

    Integer getId();

    Bitmap getBitmap();

    Uri getUri();

    String getUriString();

    boolean isFull();

    EnumPhotoLacation getPhotoLocation();

    String getName();

    default String getNameDefauld() {
        if (getName() == null) {
            if (getUriString() != null) {
                String uriString = getUriString();
                return uriString.substring(uriString.lastIndexOf('/') + 1, uriString.length());
            } else {
                return null;
            }
        } else {
            return getName();
        }
    }
}
