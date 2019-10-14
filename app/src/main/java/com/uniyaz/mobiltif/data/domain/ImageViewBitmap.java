package com.uniyaz.mobiltif.data.domain;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

import com.uniyaz.mobiltif.R;

/**
 * Created by İrfan Öngüç on 20.09.2019
 */

public class ImageViewBitmap {
    private ImageView imageView;
    private Bitmap bitmap;
    private boolean isSuccesLoad = false;

    public ImageViewBitmap() {

    }

    public ImageViewBitmap(ImageView imageView) {
        this.imageView = imageView;
        bitmap = getBitmapFromImageView();
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public Bitmap getBitmap() {
        if (bitmap == null) {
            bitmap = getBitmapFromImageView();
        } else if (imageView != null) {
            Bitmap picture = BitmapFactory.decodeResource(imageView.getContext().getResources(), R.mipmap.picture);
            if (bitmap.getByteCount() == picture.getByteCount()) {
                bitmap = getBitmapFromImageView();
            }
        }
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
        if (imageView != null)
            imageView.setImageBitmap(bitmap);
    }

    private Bitmap getBitmapFromImageView() {
        if (imageView != null) {
            BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
            if (drawable != null)
                return drawable.getBitmap();
        }
        return null;
    }

    public boolean isSuccesLoad() {
        return isSuccesLoad;
    }

    public void setSuccesLoad(boolean succesLoad) {
        isSuccesLoad = succesLoad;
    }
}
