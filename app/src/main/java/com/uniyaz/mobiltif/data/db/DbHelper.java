package com.uniyaz.mobiltif.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.uniyaz.mobiltif.data.repo.DepartmentRepo;
import com.uniyaz.mobiltif.data.repo.RoomRepo;
import com.uniyaz.mobiltif.data.repo.TasinirRepo;

/**
 * Created by İrfan Öngüç on 19.05.2019
 */

public class DbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "demirbas_sayim.db";
    private static final String TAG = DbHelper.class.getSimpleName();

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DepartmentRepo.createTable());  //
        db.execSQL(DepartmentRepo.createIndex());
        db.execSQL(RoomRepo.createTable());
//        db.execSQL(EnvanterRepo.createTable());
//        for (String indexQuery : EnvanterRepo.createIndex())
//            db.execSQL(indexQuery);
        db.execSQL(TasinirRepo.createTable());
        db.execSQL(TasinirRepo.createIndex());
//        db.execSQL(PhotoEnvanterRepo.createTable());
//        db.execSQL(PhotoRoomRepo.createTable());
        for (String indexQuery : RoomRepo.getCreatedIndex())
            db.execSQL(indexQuery);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        db.execSQL("PRAGMA foreign_keys=ON"); // on delete cascade vs. aktif olmalı. Standart olarak kapalı geliyor.
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
