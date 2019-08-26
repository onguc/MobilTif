package com.uniyaz.mobiltif.iface;

import com.uniyaz.mobiltif.data.dto.TifIslemResponseDto;

/**
 * Created by İrfan Öngüç on 19.05.2019
 */

public interface ITif extends ITifCommon {

    void onSuccessForSaveTasinirTransferIslem(TifIslemResponseDto responseDto);

}
