package com.uniyaz.mobiltif.data.repo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;

import com.uniyaz.mobiltif.data.db.DbManager;
import com.uniyaz.mobiltif.data.domain.BaseModel;
import com.uniyaz.mobiltif.utils.TranslateDateFormat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by İrfan Öngüç on 19.05.2019
 */

public abstract class BaseRepo<T extends BaseModel> {

    SQLiteDatabase db;
    DbManager dbManager;

    public BaseRepo() {
        dbManager = DbManager.getInstance();
        db = dbManager.getDatabase();
    }


    /**
     * @param t             ekleme ya da güncelleme yapılacak olan obje'yi simgeler. Ekleme yaparken BaseModel içindeki alanlar zaten eklendiği için bir daha eklemeye gerek yoktur
     * @param contentValues ekleme ve güncelleme hangi kolonlar için yapılacaksa o kolonların bu metod içinde doldurulması gerekmektedir
     */
    public abstract void fillContentValues(T t, ContentValues contentValues);

    /**
     * Sorgu esnasında görmek istediğimiz kolonları bu metodda beliliyoruz.
     *
     * @return
     */
    public abstract String[] getColumns();

    /**
     * Sqlite sorgusunda gelen kolonların nesnenin alanlarıyla eşleştirilmesi için cursor kullanılır.
     *
     * @param cursor
     * @return
     */
    public abstract T getObjectByCursor(Cursor cursor);

    /**
     * İşlem yapmak istediğimiz tablonun adını dönmelidir.
     *
     * @return
     */
    public abstract String getTableName();

    public ContentValues getContentValuesBaseModel(BaseModel baseModel) {
        ContentValues values = new ContentValues();
        values.put(baseModel.KEY_RecordDateTime, TranslateDateFormat.getConvertedStringFromDate(baseModel.getRecordDateTime()));
        values.put(baseModel.KEY_UpdateDateTime, TranslateDateFormat.getConvertedStringFromDate(baseModel.getUpdateDateTime()));
        return values;
    }

    private ContentValues getContentValues(T t) {
        ContentValues contentValues = getContentValuesBaseModel(t);
        fillContentValues(t, contentValues);
        return contentValues;
    }


    public long add(T t) {
        t.fillRecordAndUpdateDate();
        ContentValues contentValues = getContentValues(t);
        try {
            long value = db.insert(getTableName(), null, contentValues);
            if (value == -1) {
                throw new IllegalStateException("Kaydedilemedi");
            }
            return value;
        } catch (SQLiteConstraintException e) {
            throw new IllegalStateException("unique uyuşmazlığı");
        }
    }

    public boolean addAll(List<T> tList) {
        db.beginTransaction();
        for (T t : tList) {
            add(t);
        }
        db.setTransactionSuccessful();
        db.endTransaction();
        return true;
    }

    public void synchronizeAll(List<T> tList) {
        if (tList == null) return;
        db.beginTransaction();
        for (T t : tList) {
            synchronize(t);
        }
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public int synchronize(T t) {
        int returnedValue = update(t);
        if (returnedValue == 0) {
            returnedValue = (int) add(t);
        }
        return returnedValue;
    }

    public void delete(T t) {
        db.delete(getTableName(), "rowId=?", new String[]{t.getIdString()});
        dbManager.closeDatabase();
    }

    public void updateByQery(String query) {
        db.execSQL(query);
    }

    public int update(T t) {
        t.fillUpdateDates();
        ContentValues contentValues = getContentValues(t);
        String id = null;
        if (t.getIdString() == null)
            return 0;
        else
            id = t.getIdString();

        int updatedId = db.update(getTableName(), contentValues, "rowId=?", new String[]{id});
        return updatedId == 0 ? 0 : Integer.parseInt(id);
    }

    public T getById(String id) {
        Cursor cursor = db.query(getTableName(), getColumns(), "rowId=?", new String[]{id}, null, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                T objectByCursor = getObjectByCursor(cursor);
                cursor.close();
                return objectByCursor;
            }
        }
        return null;
    }

    public List<T> getListByQuery(String query, String[] list) {
        Cursor cursor = db.rawQuery(query, list);
        return getListByCursor(cursor);
    }

    public List<T> getListByColumn(String columnName, String columnValue) {
        Cursor cursor = db.query(getTableName(), getColumns(), columnName + "=?", new String[]{columnValue}, null, null, T.KEY_UpdateDateTime + " DESC", null);
        return getListByCursor(cursor);
    }


    public List<T> getAll() {
        Cursor cursor = db.query(getTableName(), getColumns(), null, null, null, null, T.KEY_UpdateDateTime + " DESC", null);
        return getListByCursor(cursor);
    }

    private List<T> getListByCursor(Cursor cursor) {
        List<T> objectList = new ArrayList<>();
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    T t = getObjectByCursor(cursor);
                    objectList.add(t);
                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        return objectList;
    }

}
