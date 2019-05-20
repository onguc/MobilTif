package com.uniyaz.mobiltif.data.domain;

import com.uniyaz.mobiltif.utils.TranslateDateFormat;

import java.util.Date;

/**
 * Created by İrfan Öngüç on 19.05.2019
 */

public abstract class BaseModel implements IBase {
    public static final String KEY_RecordDateTime = "record_date_time";
    public static final String KEY_UpdateDateTime = "update_date_time";

    protected Date recordDateTime;
    protected Date updateDateTime;

    public Date getRecordDateTime() {
        return recordDateTime;
    }

    public void setRecordDateTime(Date recordDateTime) {
        this.recordDateTime = recordDateTime;
    }

    public void setRecordDateTime(String recordDateTimeString) {
        recordDateTime = getDateFromString(recordDateTimeString);
    }


    public Date getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(Date updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    public void setUpdateDateTime(String updateDateTimeString) {
        updateDateTime = getDateFromString(updateDateTimeString);
    }

    public void fillRecordAndUpdateDate() {
        recordDateTime = new Date();
        updateDateTime = new Date();
    }

    public String getDateAsString(Date date) {
        if (date == null) return null;
        return TranslateDateFormat.getConvertedStringFromDate(date);
    }

    public Date getDateFromString(String stringDate) {
        return TranslateDateFormat.getConvertedDateFromString(stringDate);
    }

    public void fillUpdateDates() {
        updateDateTime = new Date();
    }
}
