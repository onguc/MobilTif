package com.uniyaz.mobiltif.data.db;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by İrfan Öngüç on 19.05.2019
 */

public class DbManager {
    private int mOpenCounter = 0;
    private static DbManager instance;
    private static SQLiteOpenHelper sqLiteOpenHelper;
    private SQLiteDatabase mDatabase;

    public static synchronized void initializeInstance(SQLiteOpenHelper helper) {
        if (instance == null) {
            instance = new DbManager();
            sqLiteOpenHelper = helper;
        }
    }

    public static synchronized DbManager getInstance() {
        if (instance == null) {
            throw new IllegalStateException(DbManager.class.getSimpleName() + " Başlatılamadı! initializeInstance(helper) çağrılmalı!");
        }
        return instance;
    }

    public synchronized SQLiteDatabase getDatabase() {
        mOpenCounter++;
        if (mOpenCounter == 1)
            mDatabase = sqLiteOpenHelper.getWritableDatabase();
        return mDatabase;
    }

    public synchronized void closeDatabase() {
        mOpenCounter--;
        if (mOpenCounter == 0)
            mDatabase.close();
    }
}
