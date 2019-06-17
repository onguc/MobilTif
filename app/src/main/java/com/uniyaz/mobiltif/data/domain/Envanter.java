package com.uniyaz.mobiltif.data.domain;

import java.util.List;

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
    private List<byte[]> resimler;
    //    private EnumBirim birimi; // adet, kg, litre vs.  sunucudan listesi çekilecek
    private String birimi; // adet, kg, litre vs.  sunucudan listesi çekilecek

    //    private EnumAP durumu;
    private String durumu;
    //    private Date sayimTarihi;
    private String sayimTarihi;
    private Long idSayimYapanPersonel;
    private String aciklama;
    private Boolean isSentToServer;
    private Room room;
    private Tasinir tasinir;

    private String adi;
    private String sicilNo;
    private String servisAmbar;
    private String tutar;
    private String zimmetliPersonel;
    private String teminEdilenFirma;
    private String faturaTarihi;
    private String faturaNo;


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

    //    public EnumBirim getBirimi() {
//        return birimi;
//    }
    public String getBirimi() {
        return "ADET";
    }

    public void setBirimi(String birimi) {
        this.birimi = birimi;
    }

//    public EnumAP getDurumu() {
//        return durumu;
//    }
//
//    public void setDurumu(EnumAP durumu) {
//        this.durumu = durumu;
//    }


    public String getDurumu() {
        return durumu;
    }

    public void setDurumu(String durumu) {
        this.durumu = durumu;
    }

    public String getSayimTarihi() {
        return sayimTarihi;
    }

    public void setSayimTarihi(String sayimTarihi) {
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

    public String getAdi() {
        return adi;
    }

    public void setAdi(String adi) {
        this.adi = adi;
    }

    public String getSicilNo() {
        return sicilNo;
    }

    public void setSicilNo(String sicilNo) {
        this.sicilNo = sicilNo;
    }

    public String getServisAmbar() {
        return servisAmbar;
    }

    public void setServisAmbar(String servisAmbar) {
        this.servisAmbar = servisAmbar;
    }

    public String getTutar() {
        return tutar;
    }

    public void setTutar(String tutar) {
        this.tutar = tutar;
    }

    public String getZimmetliPersonel() {
        return zimmetliPersonel;
    }

    public void setZimmetliPersonel(String zimmetliPersonel) {
        this.zimmetliPersonel = zimmetliPersonel;
    }

    public String getTeminEdilenFirma() {
        return teminEdilenFirma;
    }

    public void setTeminEdilenFirma(String teminEdilenFirma) {
        this.teminEdilenFirma = teminEdilenFirma;
    }

    public String getFaturaTarihi() {
        return faturaTarihi;
    }

    public void setFaturaTarihi(String faturaTarihi) {
        this.faturaTarihi = faturaTarihi;
    }

    public String getFaturaNo() {
        return faturaNo;
    }

    public void setFaturaNo(String faturaNo) {
        this.faturaNo = faturaNo;
    }

    public List<byte[]> getResimler() {
        return resimler;
    }

    public void setResimler(List<byte[]> resimler) {
        this.resimler = resimler;
    }
}
