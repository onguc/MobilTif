package com.uniyaz.mobiltif.data.domain;

import android.graphics.Bitmap;
import android.net.Uri;

import com.uniyaz.mobiltif.data.enums.EnumPhotoLacation;

/**
 * Created by İrfan Öngüç on 19.05.2019
 */

public class PhotoEnvanter extends BaseModel implements IPhoto {

    public static final String TABLE_NAME = "Photo_Envanter";
    public final static String keyId = "id";
    public final static String keyIdEnvanter = "id_envanter";
    public final static String keyUriString = "uri_string";
    public final static String keyName = "name";

    private Integer id;
    private Long idEnvanter;
    private String uriString;
    private Boolean isFull;
    private Bitmap bitmap;
    private String name;
    private Uri uri;


    @Override
    public String getIdString() {
        return String.valueOf(id);
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getIdEnvanter() {
        return idEnvanter;
    }

    public void setIdEnvanter(Long idEnvanter) {
        this.idEnvanter = idEnvanter;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    @Override
    public Uri getUri() {
        if (uri == null) {
            if (uriString != null)
                uri = Uri.parse(uriString);
        }
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }


    @Override
    public EnumPhotoLacation getPhotoLocation() {
        return EnumPhotoLacation.ENVANTER;
    }


    @Override
    public String getUriString() {
        return uriString == null ? uri.toString() : uriString;
    }

    @Override
    public boolean isFull() {
        return isFull;
    }

    public void setUriString(String uriString) {
        this.uriString = uriString;
    }

    public void setFull(Boolean full) {
        isFull = full;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
