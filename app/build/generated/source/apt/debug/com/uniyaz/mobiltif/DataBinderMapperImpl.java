package com.uniyaz.mobiltif;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.uniyaz.mobiltif.databinding.ActivityLoginBindingImpl;
import com.uniyaz.mobiltif.databinding.ActivityLoginGelistirBindingImpl;
import com.uniyaz.mobiltif.databinding.ActivityMainBindingImpl;
import com.uniyaz.mobiltif.databinding.ContentMainBindingImpl;
import com.uniyaz.mobiltif.databinding.FragmentDemibasDetayBindingImpl;
import com.uniyaz.mobiltif.databinding.FragmentOdaBindingImpl;
import com.uniyaz.mobiltif.databinding.FragmentTifBindingImpl;
import com.uniyaz.mobiltif.databinding.FragmentTifHibeBindingImpl;
import com.uniyaz.mobiltif.databinding.FragmentTifHurdayaAyirmaBindingImpl;
import com.uniyaz.mobiltif.databinding.FragmentTifTransferBindingImpl;
import com.uniyaz.mobiltif.databinding.FragmentTifZimmetBindingImpl;
import com.uniyaz.mobiltif.databinding.FragmentTifZimmetDevriBindingImpl;
import com.uniyaz.mobiltif.databinding.FragmentTifZimmetIadeBindingImpl;
import com.uniyaz.mobiltif.databinding.ItemEnvanterCardSelectableBindingImpl;
import com.uniyaz.mobiltif.databinding.ItemEnvanterTifCardBindingImpl;
import com.uniyaz.mobiltif.databinding.ItemOdaBilgiBindingImpl;
import com.uniyaz.mobiltif.databinding.ItemPhotoCardBindingImpl;
import com.uniyaz.mobiltif.databinding.ItemTifIslemBindingImpl;
import com.uniyaz.mobiltif.databinding.ProgressBarBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_ACTIVITYLOGIN = 1;

  private static final int LAYOUT_ACTIVITYLOGINGELISTIR = 2;

  private static final int LAYOUT_ACTIVITYMAIN = 3;

  private static final int LAYOUT_CONTENTMAIN = 4;

  private static final int LAYOUT_FRAGMENTDEMIBASDETAY = 5;

  private static final int LAYOUT_FRAGMENTODA = 6;

  private static final int LAYOUT_FRAGMENTTIF = 7;

  private static final int LAYOUT_FRAGMENTTIFHIBE = 8;

  private static final int LAYOUT_FRAGMENTTIFHURDAYAAYIRMA = 9;

  private static final int LAYOUT_FRAGMENTTIFTRANSFER = 10;

  private static final int LAYOUT_FRAGMENTTIFZIMMET = 11;

  private static final int LAYOUT_FRAGMENTTIFZIMMETDEVRI = 12;

  private static final int LAYOUT_FRAGMENTTIFZIMMETIADE = 13;

  private static final int LAYOUT_ITEMENVANTERCARDSELECTABLE = 14;

  private static final int LAYOUT_ITEMENVANTERTIFCARD = 15;

  private static final int LAYOUT_ITEMODABILGI = 16;

  private static final int LAYOUT_ITEMPHOTOCARD = 17;

  private static final int LAYOUT_ITEMTIFISLEM = 18;

  private static final int LAYOUT_PROGRESSBAR = 19;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(19);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.uniyaz.mobiltif.R.layout.activity_login, LAYOUT_ACTIVITYLOGIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.uniyaz.mobiltif.R.layout.activity_login_gelistir, LAYOUT_ACTIVITYLOGINGELISTIR);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.uniyaz.mobiltif.R.layout.activity_main, LAYOUT_ACTIVITYMAIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.uniyaz.mobiltif.R.layout.content_main, LAYOUT_CONTENTMAIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.uniyaz.mobiltif.R.layout.fragment_demibas_detay, LAYOUT_FRAGMENTDEMIBASDETAY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.uniyaz.mobiltif.R.layout.fragment_oda, LAYOUT_FRAGMENTODA);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.uniyaz.mobiltif.R.layout.fragment_tif, LAYOUT_FRAGMENTTIF);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.uniyaz.mobiltif.R.layout.fragment_tif_hibe, LAYOUT_FRAGMENTTIFHIBE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.uniyaz.mobiltif.R.layout.fragment_tif_hurdaya_ayirma, LAYOUT_FRAGMENTTIFHURDAYAAYIRMA);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.uniyaz.mobiltif.R.layout.fragment_tif_transfer, LAYOUT_FRAGMENTTIFTRANSFER);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.uniyaz.mobiltif.R.layout.fragment_tif_zimmet, LAYOUT_FRAGMENTTIFZIMMET);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.uniyaz.mobiltif.R.layout.fragment_tif_zimmet_devri, LAYOUT_FRAGMENTTIFZIMMETDEVRI);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.uniyaz.mobiltif.R.layout.fragment_tif_zimmet_iade, LAYOUT_FRAGMENTTIFZIMMETIADE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.uniyaz.mobiltif.R.layout.item_envanter_card_selectable, LAYOUT_ITEMENVANTERCARDSELECTABLE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.uniyaz.mobiltif.R.layout.item_envanter_tif_card, LAYOUT_ITEMENVANTERTIFCARD);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.uniyaz.mobiltif.R.layout.item_oda_bilgi, LAYOUT_ITEMODABILGI);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.uniyaz.mobiltif.R.layout.item_photo_card, LAYOUT_ITEMPHOTOCARD);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.uniyaz.mobiltif.R.layout.item_tif_islem, LAYOUT_ITEMTIFISLEM);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.uniyaz.mobiltif.R.layout.progress_bar, LAYOUT_PROGRESSBAR);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_ACTIVITYLOGIN: {
          if ("layout/activity_login_0".equals(tag)) {
            return new ActivityLoginBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_login is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYLOGINGELISTIR: {
          if ("layout/activity_login_gelistir_0".equals(tag)) {
            return new ActivityLoginGelistirBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_login_gelistir is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYMAIN: {
          if ("layout/activity_main_0".equals(tag)) {
            return new ActivityMainBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_main is invalid. Received: " + tag);
        }
        case  LAYOUT_CONTENTMAIN: {
          if ("layout/content_main_0".equals(tag)) {
            return new ContentMainBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for content_main is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTDEMIBASDETAY: {
          if ("layout/fragment_demibas_detay_0".equals(tag)) {
            return new FragmentDemibasDetayBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_demibas_detay is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTODA: {
          if ("layout/fragment_oda_0".equals(tag)) {
            return new FragmentOdaBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_oda is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTTIF: {
          if ("layout/fragment_tif_0".equals(tag)) {
            return new FragmentTifBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_tif is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTTIFHIBE: {
          if ("layout/fragment_tif_hibe_0".equals(tag)) {
            return new FragmentTifHibeBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_tif_hibe is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTTIFHURDAYAAYIRMA: {
          if ("layout/fragment_tif_hurdaya_ayirma_0".equals(tag)) {
            return new FragmentTifHurdayaAyirmaBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_tif_hurdaya_ayirma is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTTIFTRANSFER: {
          if ("layout/fragment_tif_transfer_0".equals(tag)) {
            return new FragmentTifTransferBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_tif_transfer is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTTIFZIMMET: {
          if ("layout/fragment_tif_zimmet_0".equals(tag)) {
            return new FragmentTifZimmetBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_tif_zimmet is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTTIFZIMMETDEVRI: {
          if ("layout/fragment_tif_zimmet_devri_0".equals(tag)) {
            return new FragmentTifZimmetDevriBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_tif_zimmet_devri is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTTIFZIMMETIADE: {
          if ("layout/fragment_tif_zimmet_iade_0".equals(tag)) {
            return new FragmentTifZimmetIadeBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_tif_zimmet_iade is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMENVANTERCARDSELECTABLE: {
          if ("layout/item_envanter_card_selectable_0".equals(tag)) {
            return new ItemEnvanterCardSelectableBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_envanter_card_selectable is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMENVANTERTIFCARD: {
          if ("layout/item_envanter_tif_card_0".equals(tag)) {
            return new ItemEnvanterTifCardBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_envanter_tif_card is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMODABILGI: {
          if ("layout/item_oda_bilgi_0".equals(tag)) {
            return new ItemOdaBilgiBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_oda_bilgi is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMPHOTOCARD: {
          if ("layout/item_photo_card_0".equals(tag)) {
            return new ItemPhotoCardBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_photo_card is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMTIFISLEM: {
          if ("layout/item_tif_islem_0".equals(tag)) {
            return new ItemTifIslemBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_tif_islem is invalid. Received: " + tag);
        }
        case  LAYOUT_PROGRESSBAR: {
          if ("layout/progress_bar_0".equals(tag)) {
            return new ProgressBarBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for progress_bar is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(21);

    static {
      sKeys.put(0, "_all");
      sKeys.put(1, "showProgressBar");
      sKeys.put(2, "girisYapilanAmbar");
      sKeys.put(3, "activity");
      sKeys.put(4, "toastMessage");
      sKeys.put(5, "contentMainViewModel");
      sKeys.put(6, "error");
      sKeys.put(7, "titleToolbar");
      sKeys.put(8, "room");
      sKeys.put(9, "ambarName");
      sKeys.put(10, "fragment");
      sKeys.put(11, "password");
      sKeys.put(12, "aciklama");
      sKeys.put(13, "harcamaYetkilisi");
      sKeys.put(14, "viewModel");
      sKeys.put(15, "checked");
      sKeys.put(16, "progressBarViewModel");
      sKeys.put(17, "selected");
      sKeys.put(18, "devirYapilacakPersonel");
      sKeys.put(19, "username");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(19);

    static {
      sKeys.put("layout/activity_login_0", com.uniyaz.mobiltif.R.layout.activity_login);
      sKeys.put("layout/activity_login_gelistir_0", com.uniyaz.mobiltif.R.layout.activity_login_gelistir);
      sKeys.put("layout/activity_main_0", com.uniyaz.mobiltif.R.layout.activity_main);
      sKeys.put("layout/content_main_0", com.uniyaz.mobiltif.R.layout.content_main);
      sKeys.put("layout/fragment_demibas_detay_0", com.uniyaz.mobiltif.R.layout.fragment_demibas_detay);
      sKeys.put("layout/fragment_oda_0", com.uniyaz.mobiltif.R.layout.fragment_oda);
      sKeys.put("layout/fragment_tif_0", com.uniyaz.mobiltif.R.layout.fragment_tif);
      sKeys.put("layout/fragment_tif_hibe_0", com.uniyaz.mobiltif.R.layout.fragment_tif_hibe);
      sKeys.put("layout/fragment_tif_hurdaya_ayirma_0", com.uniyaz.mobiltif.R.layout.fragment_tif_hurdaya_ayirma);
      sKeys.put("layout/fragment_tif_transfer_0", com.uniyaz.mobiltif.R.layout.fragment_tif_transfer);
      sKeys.put("layout/fragment_tif_zimmet_0", com.uniyaz.mobiltif.R.layout.fragment_tif_zimmet);
      sKeys.put("layout/fragment_tif_zimmet_devri_0", com.uniyaz.mobiltif.R.layout.fragment_tif_zimmet_devri);
      sKeys.put("layout/fragment_tif_zimmet_iade_0", com.uniyaz.mobiltif.R.layout.fragment_tif_zimmet_iade);
      sKeys.put("layout/item_envanter_card_selectable_0", com.uniyaz.mobiltif.R.layout.item_envanter_card_selectable);
      sKeys.put("layout/item_envanter_tif_card_0", com.uniyaz.mobiltif.R.layout.item_envanter_tif_card);
      sKeys.put("layout/item_oda_bilgi_0", com.uniyaz.mobiltif.R.layout.item_oda_bilgi);
      sKeys.put("layout/item_photo_card_0", com.uniyaz.mobiltif.R.layout.item_photo_card);
      sKeys.put("layout/item_tif_islem_0", com.uniyaz.mobiltif.R.layout.item_tif_islem);
      sKeys.put("layout/progress_bar_0", com.uniyaz.mobiltif.R.layout.progress_bar);
    }
  }
}
