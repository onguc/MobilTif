package com.uniyaz.mobiltif.data.repo;

import android.content.ContentValues;
import android.database.Cursor;

import com.uniyaz.mobiltif.data.domain.Department;
import com.uniyaz.mobiltif.data.domain.Room;
import com.uniyaz.mobiltif.utils.TranslateDateFormat;

/**
 * Created by İrfan Öngüç on 19.05.2019
 */

public class RoomRepo extends BaseRepo<Room> {

    public static String createTable() {
        return "CREATE TABLE " + Room.TABLE_NAME + " (" +
                Room.keyQrCode + " INTEGER  PRIMARY KEY, " +
                Room.keyName + " TEXT, " +
                Room.keyDepartment + " INTEGER, " +
                Room.keyIsSentToServer + " INTEGER, " +
                Room.KEY_RecordDateTime + " TEXT, " +
                Room.KEY_UpdateDateTime + " TEXT, " +
                "FOREIGN KEY (" + Room.keyDepartment + ") REFERENCES " + Department.TABLE_NAME + "(" + Department.keyId + ")" +
                ")";
    }

    public static String[] getCreatedIndex() {
        String index1 = "CREATE INDEX " + "IX_" + Room.TABLE_NAME + "_UPDATE_DATE ON " + Room.TABLE_NAME + "(" + Room.KEY_UpdateDateTime + ")";
        return new String[]{index1};
    }

    public Room getRoomByQrCode(Integer qrCode) {
        String id = String.valueOf(qrCode);
        Room room = getById(id);
        return room;
    }

//    public void updateRoomDtoListSendRemote(List<Integer> list) {
//        Integer value = 1;
//        Stream.of(list).map(Objects::toString).collect(Collectors.joining(","));
//        String joined = Stream.of(list).map(Object::toString).collect(Collectors.joining(","));
//        String query = "UPDATE " + Room.TABLE_NAME + " SET " + Room.keyIsSentToServer + " = " + value + " WHERE " + Room.keyQrCode + " IN (" + joined + ")";
//        updateByQery(query);
//    }


    @Override
    public void fillContentValues(Room room, ContentValues contentValues) {
        contentValues.put(room.keyQrCode, room.getQrCode());
        contentValues.put(room.keyName, room.getName());
        contentValues.put(room.keyDepartment, room.getDepartment().getIdString());
        contentValues.put(room.keyIsSentToServer, room.getSentToServer());
        contentValues.put(room.KEY_RecordDateTime, TranslateDateFormat.getConvertedStringFromDate(room.getRecordDateTime()));
        contentValues.put(room.KEY_UpdateDateTime, TranslateDateFormat.getConvertedStringFromDate(room.getUpdateDateTime()));
    }

    @Override
    public String[] getColumns() {
        String[] columns = {
                Room.keyQrCode,
                Room.keyName,
                Room.keyDepartment,
                Room.keyIsSentToServer,
                Room.KEY_RecordDateTime,
                Room.KEY_UpdateDateTime};
        return columns;
    }

    @Override
    public Room getObjectByCursor(Cursor cursor) {
        Room room = new Room();
        room.setQrCode(cursor.getInt(0));
        room.setName(cursor.getString(1));
        Long departmentId = cursor.getLong(2);
        room.setSentToServer(cursor.getInt(3));
        room.setRecordDateTime(TranslateDateFormat.getConvertedDateFromString(cursor.getString(4)));
        room.setUpdateDateTime(TranslateDateFormat.getConvertedDateFromString(cursor.getString(5)));

        Department department = new DepartmentRepo().getById(String.valueOf(departmentId));
        room.setDepartment(department);
        return room;
    }

    @Override
    public String getTableName() {
        return Room.TABLE_NAME;
    }


}
