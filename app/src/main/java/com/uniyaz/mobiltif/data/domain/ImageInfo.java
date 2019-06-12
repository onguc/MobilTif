package com.uniyaz.mobiltif.data.domain;

import android.graphics.Bitmap;

public class ImageInfo {
    Bitmap bitmap;
    String yol;

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getYol() {
        return yol;
    }

    public void setYol(String yol) {
        this.yol = yol;
    }
}
