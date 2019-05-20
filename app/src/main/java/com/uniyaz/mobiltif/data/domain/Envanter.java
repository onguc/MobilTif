package com.uniyaz.mobiltif.data.domain;

import com.uniyaz.mobiltif.data.enums.EnumAP;
import com.uniyaz.mobiltif.data.enums.EnumBirim;

import java.util.Date;

/**
 * Created by İrfan Öngüç on 19.05.2019
 */

public class Envanter extends BaseModel implements Cloneable {
    public static final String TABLE_NAME = "Envanter";
    public final static String keyId = "id";
    public final static String keyIdRoom = "id_oda";
    public final static String keyKodTasinir = "kod_tasinir";
    public final static String keySayimNo = "sayim_no";
    public final static String keyQrCode = "etiket_kod";
    public final static String keyBirimi = "birim";
    public final static String keyDurumu = "durumu";
    public final static String keySayimTairhi = "sayim_tarihi";
    public final static String keyIdSayimYapanPersonel = "id_sayim_yapan_personel";
    public final static String keyAciklama = "aciklama";
    public final static String keyIsSentToServer = "sunucuya_gonderildi_mi";
    public final static String keyCount = "adet";


    public Envanter() {
        isSentToServer = false;
    }

    private Long id;
    private Integer idRoom;
    private Integer count;
    private Long kodTasinir;
    private Long sayimNo;
    private String qrCode;
    private EnumBirim birimi; // adet, kg, litre vs.  sunucudan listesi çekilecek
    private EnumAP durumu;
    private Date sayimTarihi;
    private Long idSayimYapanPersonel;
    private String aciklama;
    private Boolean isSentToServer;
    private Room room;
    private Tasinir tasinir;

    @Override
    public String getIdString() {
        return String.valueOf(id);
    }

    public Long getIdLong() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(Integer idRoom) {
        this.idRoom = idRoom;
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

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public EnumBirim getBirimi() {
        return birimi;
    }

    public void setBirimi(EnumBirim birimi) {
        this.birimi = birimi;
    }

    public EnumAP getDurumu() {
        return durumu;
    }

    public void setDurumu(EnumAP durumu) {
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

    public Boolean getSentToServer() {
        return isSentToServer;
    }

    public void setSentToServer(Boolean sentToServer) {
        isSentToServer = sentToServer;
    }

    public void setSentToServer(int sentToServer) {
        this.isSentToServer = sentToServer == 1 ? true : false;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Tasinir getTasinir() {
        return tasinir;
    }

    public void setTasinir(Tasinir tasinir) {
        this.tasinir = tasinir;
    }
}
