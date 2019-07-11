package com.uniyaz.mobiltif.data.domain;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by İrfan Öngüç on 19.05.2019
 */

public class Room extends BaseModel {
    public static final String TABLE_NAME = "Oda";
    //    public final static String keyReferanceCode ="referans_kodu";
//    public final static String keyId = "id";
    public final static String keyQrCode = "qr_kod";
    public final static String keyName = "name";
    public final static String keyDepartment = "id_department";
    public final static String keyIsSentToServer = "sunucuya_gonderildi_mi";

    public Room() {
        isSentToServer = false;
    }

    /**
     * etiket kod diye geçmektedir. Ayrıca id olarak kullanılmaktadır.
     */
    @SerializedName("etiketNo")
    private Integer qrCode; //etiket Kod  ayrıca id'dir
    @SerializedName("odaAdi")
    private String name;
    private Department department;
    private Boolean isSentToServer;
    @SerializedName("servisAdi")
    private String servisAdi;

    @SerializedName("odaResimUrlSet")
    private List<String> urlResimList;

    @SerializedName("demirbasAdedi")
    private Integer demirbasAdet;

    @SerializedName("vysTasinirDemirbasDtoList")
    List<Envanter> envanterList;

    public Integer getQrCode() {
        return qrCode;
    }

    public void setQrCode(Integer qrCode) {
        this.qrCode = qrCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Boolean getSentToServer() {
        return isSentToServer;
    }

    public void setSentToServer(Boolean sentToServer) {
        isSentToServer = sentToServer;
    }

    public void setSentToServer(int sentToServer) {
        this.isSentToServer = sentToServer == 1 ? true : false;
    }

    public List<String> getUrlResimList() {
        return urlResimList;
    }

    public void setUrlResimList(List<String> urlResimList) {
        this.urlResimList = urlResimList;
    }

    public String getServisAdi() {
        return servisAdi;
    }

    public void setServisAdi(String servisAdi) {
        this.servisAdi = servisAdi;
    }

    public Integer getDemirbasAdet() {
        return demirbasAdet;
    }

    public void setDemirbasAdet(Integer demirbasAdet) {
        this.demirbasAdet = demirbasAdet;
    }

    public List<Envanter> getEnvanterList() {
        if (envanterList == null)
            envanterList = new ArrayList<>();
        return envanterList;
    }

    public void setEnvanterList(List<Envanter> envanterList) {
        this.envanterList = envanterList;
    }

    @Override
    public String getIdString() {
        if (qrCode == null) return null;
        return qrCode.toString();
    }


}
