package com.uniyaz.mobiltif.utils;

/**
 * Created by İrfan Öngüç on 19.05.2019
 */

public class SuccessControl {
    private boolean isSuccessDepartment;
    private boolean isSuccessTasinir;
    private boolean isSuccessRoom;


    public void setSuccessDepartment(boolean successDepartment) {
        isSuccessDepartment = successDepartment;
    }

    public void setSuccessTasinir(boolean successTasinir) {
        isSuccessTasinir = successTasinir;
    }

    public void setSuccessRoom(boolean successRoom) {
        isSuccessRoom = successRoom;
    }

    public boolean isSuccessAll() {
        if (isSuccessDepartment && isSuccessTasinir && isSuccessRoom)
            return true;
        else
            return false;

    }
}
