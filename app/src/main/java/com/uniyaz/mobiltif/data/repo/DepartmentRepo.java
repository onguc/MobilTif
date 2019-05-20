package com.uniyaz.mobiltif.data.repo;

import android.content.ContentValues;
import android.database.Cursor;

import com.uniyaz.mobiltif.data.domain.Department;
import com.uniyaz.mobiltif.utils.TranslateDateFormat;

/**
 * Created by İrfan Öngüç on 19.05.2019
 */

public class DepartmentRepo extends BaseRepo<Department> {

    public static String createTable() {
        return "CREATE TABLE " + Department.TABLE_NAME + " (" +
                Department.keyId + " INTEGER PRIMARY KEY, " +
                Department.keyName + " TEXT, " +
                Department.keyCode + " TEXT, " +
                Department.KEY_RecordDateTime + " TEXT, " +
                Department.KEY_UpdateDateTime + " TEXT " +
                ")";
    }

    public static String createIndex() {
        return "CREATE INDEX " + "IX_" + Department.TABLE_NAME + "_UPDATE_DATE ON " + Department.TABLE_NAME + "(" + Department.KEY_UpdateDateTime + ")";
    }

    @Override
    public void fillContentValues(Department department, ContentValues contentValues) {
        contentValues.put(Department.keyId, department.getIdString());
        contentValues.put(Department.keyName, department.getName());
        contentValues.put(Department.keyCode, department.getCode());
    }

    @Override
    public String[] getColumns() {
        String[] columns = {Department.keyId, Department.keyName, Department.keyCode, Department.KEY_RecordDateTime, Department.KEY_UpdateDateTime};
        return columns;
    }

    @Override
    public Department getObjectByCursor(Cursor cursor) {
        Department department = new Department();
        department.setId(cursor.getInt(0));
        department.setName(cursor.getString(1));
        department.setCode(cursor.getString(2));
        department.setRecordDateTime(TranslateDateFormat.getConvertedDateFromString(cursor.getString(3)));
        department.setUpdateDateTime(TranslateDateFormat.getConvertedDateFromString(cursor.getString(4)));
        return department;
    }

    @Override
    public String getTableName() {
        return Department.TABLE_NAME;
    }
}
