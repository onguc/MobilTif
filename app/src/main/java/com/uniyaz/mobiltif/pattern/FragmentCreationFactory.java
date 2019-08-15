package com.uniyaz.mobiltif.pattern;

import androidx.fragment.app.Fragment;

import com.uniyaz.mobiltif.data.enums.EnumIslemTuru;
import com.uniyaz.mobiltif.ui.fragments.TifIslemHibeFragment;
import com.uniyaz.mobiltif.ui.fragments.TifIslemHurdayaAyirmaFragment;
import com.uniyaz.mobiltif.ui.fragments.TifIslemTransferFragment;
import com.uniyaz.mobiltif.ui.fragments.TifIslemZimmetDevriFragment;
import com.uniyaz.mobiltif.ui.fragments.TifIslemZimmetFragment;
import com.uniyaz.mobiltif.ui.fragments.TifIslemZimmetIadeFragment;

/**
 * Created by İrfan Öngüç on 15.08.2019
 */

public class FragmentCreationFactory {
    public static Fragment createAndGetFragment(EnumIslemTuru enumIslemTuru) {
        switch (enumIslemTuru) {
            case TRANSFER:
                return TifIslemTransferFragment.newInstance();
            case HIBE:
                return TifIslemHibeFragment.newInstance();
            case HURDAYA_AYIRMA:
                return TifIslemHurdayaAyirmaFragment.newInstance();
            case ZIMMET:
                return TifIslemZimmetFragment.newInstance();
            case ZIMMET_DEVRI:
                return TifIslemZimmetDevriFragment.newInstance();
            case ZIMMET_IADE:
                return TifIslemZimmetIadeFragment.newInstance();
            default:
                return null;
        }
    }
}
