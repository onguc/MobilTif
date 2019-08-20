package com.uniyaz.mobiltif.iface;

import com.uniyaz.mobiltif.data.dto.AmbarDto;
import com.uniyaz.mobiltif.data.dto.PersonelDto;

import java.util.List;

/**
 * Created by İrfan Öngüç on 18.08.2019
 */

public interface ITifCommon {
    void onSuccessForAmbarDtoList(List<AmbarDto> ambarDtoList);

    void onSuccessforPersonelDtoList(List<PersonelDto> personelDtoList);
}
