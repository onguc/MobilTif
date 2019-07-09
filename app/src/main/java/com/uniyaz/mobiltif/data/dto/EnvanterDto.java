package com.uniyaz.mobiltif.data.dto;

import com.google.gson.annotations.SerializedName;
import com.uniyaz.mobiltif.data.enums.EnumDurum;
import com.uniyaz.mobiltif.data.enums.EnumBirim;

import java.util.Date;
import java.util.Map;

/**
 * Created by İrfan Öngüç on 19.05.2019
 */

public class EnvanterDto {

    private Long id;
    @SerializedName("vysSayimOdaId")
    private Integer idRoom;

    @SerializedName("adet")
    private Integer count;
    @SerializedName("tasinirKodu")
    private Long kodTasinir;
    private Long sayimNo;
    private EnumBirim birim; // adet, kg, litre vs.  sunucudan listesi çekilecek
    private EnumDurum durumu;
    private Date sayimTarihi;
    private Long idSayimYapanPersonel;
    @SerializedName("aciklama")
    private String aciklama;
    @SerializedName("demirbasResimList")
    private Map<String, byte[]> photoList;

    public Integer getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(Integer idRoom) {
        this.idRoom = idRoom;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Long getKodTasinir() {
        return kodTasinir;
    }

    public void setKodTasinir(Long kodTasinir) {
        this.kodTasinir = kodTasinir;
    }

    public Long getSayimNo() {
        return sayimNo;
    }

    public void setSayimNo(Long sayimNo) {
        this.sayimNo = sayimNo;
    }

    public EnumBirim getBirim() {
        return birim;
    }

    public void setBirim(EnumBirim birim) {
        this.birim = birim;
    }

    public EnumDurum getDurumu() {
        return durumu;
    }

    public void setDurumu(EnumDurum durumu) {
        this.durumu = durumu;
    }

    public Date getSayimTarihi() {
        return sayimTarihi;
    }

    public void setSayimTarihi(Date sayimTarihi) {
        this.sayimTarihi = sayimTarihi;
    }

    public Long getIdSayimYapanPersonel() {
        return idSayimYapanPersonel;
    }

    public void setIdSayimYapanPersonel(Long idSayimYapanPersonel) {
        this.idSayimYapanPersonel = idSayimYapanPersonel;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Map<String, byte[]> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(Map<String, byte[]> photoList) {
        this.photoList = photoList;
    }
}
