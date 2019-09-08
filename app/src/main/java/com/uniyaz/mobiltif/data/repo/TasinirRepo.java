package com.uniyaz.mobiltif.data.repo;

import android.content.ContentValues;
import android.database.Cursor;

import com.uniyaz.mobiltif.data.domain.Tasinir;
import com.uniyaz.mobiltif.utils.DateUtils;

import java.util.List;

/**
 * Created by İrfan Öngüç on 19.05.2019
 */

public class TasinirRepo extends BaseRepo<Tasinir> {

    public static String createTable() {
        return "CREATE TABLE " + Tasinir.TABLE_NAME + " (" +
                Tasinir.keyId + " INTEGER PRIMARY KEY, " +
                Tasinir.keyName + " TEXT, " +
                Tasinir.keyTasinirKodu + " INTEGER, " +
                Tasinir.KEY_RecordDateTime + " TEXT, " +
                Tasinir.KEY_UpdateDateTime + " TEXT " +
                ")";
    }

    public static String createIndex() {
        return "CREATE INDEX " + "IX_" + Tasinir.TABLE_NAME + "_TASINIR_KODU ON " + Tasinir.TABLE_NAME + "(" + Tasinir.keyTasinirKodu + ")";
    }

    @Override
    public void fillContentValues(Tasinir tasinir, ContentValues contentValues) {
        contentValues.put(tasinir.keyId, tasinir.getIdString());
        contentValues.put(tasinir.keyName, tasinir.getName());
        contentValues.put(tasinir.keyTasinirKodu, tasinir.getTasinirKodu());
    }

    @Override
    public String[] getColumns() {
        String[] columns = {Tasinir.keyId, Tasinir.keyName, Tasinir.keyTasinirKodu, Tasinir.KEY_RecordDateTime, Tasinir.KEY_UpdateDateTime};
        return columns;
    }

    @Override
    public Tasinir getObjectByCursor(Cursor cursor) {
        Tasinir tasinir = new Tasinir();
        tasinir.setId(cursor.getString(0));
        tasinir.setName(cursor.getString(1));
        tasinir.setTasinirKodu(cursor.getLong(2));
        tasinir.setRecordDateTime(DateUtils.getConvertedDateFromString(cursor.getString(3)));
        tasinir.setUpdateDateTime(DateUtils.getConvertedDateFromString(cursor.getString(4)));
        return tasinir;
    }

    public Tasinir getByTasinirKodu(Long tasinirKodu) {
        if (tasinirKodu == null) return null;
        List<Tasinir> listByColumn = getListByColumn(Tasinir.keyTasinirKodu, String.valueOf(tasinirKodu));
        if (listByColumn == null || listByColumn.size() == 0)
            return null;
        return listByColumn.get(0);
    }

    @Override
    public String getTableName() {
        return Tasinir.TABLE_NAME;
    }
}
