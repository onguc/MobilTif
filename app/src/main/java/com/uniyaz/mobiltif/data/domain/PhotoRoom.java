package com.uniyaz.mobiltif.data.domain;

import android.graphics.Bitmap;
import android.net.Uri;

import com.uniyaz.mobiltif.data.enums.EnumPhotoLacation;

/**
 * Created by İrfan Öngüç on 19.05.2019
 */

public class PhotoRoom extends BaseModel implements IPhoto {

    public static final String TABLE_NAME = "Photo_Oda";
    public final static String keyId = "id";
    public final static String keyIdRoom = "id_oda";
    public final static String keyUriString = "uri_string";
    public final static String keyName = "name";

    private Integer id;
    private Integer idRoom;
    private String uriString;
    private Boolean isFull;
    private Bitmap bitmap;
    private String name;
    private Uri uri;


    @Override
    public String getIdString() {
        return String.valueOf(id);
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(Integer idRoom) {
        this.idRoom = idRoom;
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

    @Override
    public String getUriString() {
        return uriString == null ? uri.toString() : uriString;
    }

    @Override
    public boolean isFull() {
        return isFull;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }


    public void setFull(Boolean full) {
        if (full == null) isFull = false;
        isFull = full;
    }

    @Override
    public EnumPhotoLacation getPhotoLocation() {
        return EnumPhotoLacation.ROOM;
    }

    public void setUriString(String uriString) {
        this.uriString = uriString;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
