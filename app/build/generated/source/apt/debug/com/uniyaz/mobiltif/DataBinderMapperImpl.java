package com.uniyaz.mobiltif;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.uniyaz.mobiltif.databinding.ActivityLoginBindingImpl;
import com.uniyaz.mobiltif.databinding.ActivityMainBindingImpl;
import com.uniyaz.mobiltif.databinding.ContentMainBindingImpl;
import com.uniyaz.mobiltif.databinding.FragmentDemibasDetayBindingImpl;
import com.uniyaz.mobiltif.databinding.FragmentDemibasListBindingImpl;
import com.uniyaz.mobiltif.databinding.FragmentTifBindingImpl;
import com.uniyaz.mobiltif.databinding.FragmentTifIslem1BindingImpl;
import com.uniyaz.mobiltif.databinding.FragmentTifIslem2BindingImpl;
import com.uniyaz.mobiltif.databinding.FragmentTifIslem3BindingImpl;
import com.uniyaz.mobiltif.databinding.ItemEnvanterCardSelectableBindingImpl;
import com.uniyaz.mobiltif.databinding.ItemPhotoCardBindingImpl;
import com.uniyaz.mobiltif.databinding.ItemTifEnvanterCardBindingImpl;
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

  private static final int LAYOUT_ACTIVITYMAIN = 2;

  private static final int LAYOUT_CONTENTMAIN = 3;

  private static final int LAYOUT_FRAGMENTDEMIBASDETAY = 4;

  private static final int LAYOUT_FRAGMENTDEMIBASLIST = 5;

  private static final int LAYOUT_FRAGMENTTIF = 6;

  private static final int LAYOUT_FRAGMENTTIFISLEM1 = 7;

  private static final int LAYOUT_FRAGMENTTIFISLEM2 = 8;

  private static final int LAYOUT_FRAGMENTTIFISLEM3 = 9;

  private static final int LAYOUT_ITEMENVANTERCARDSELECTABLE = 10;

  private static final int LAYOUT_ITEMPHOTOCARD = 11;

  private static final int LAYOUT_ITEMTIFENVANTERCARD = 12;

  private static final int LAYOUT_PROGRESSBAR = 13;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(13);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.uniyaz.mobiltif.R.layout.activity_login, LAYOUT_ACTIVITYLOGIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.uniyaz.mobiltif.R.layout.activity_main, LAYOUT_ACTIVITYMAIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.uniyaz.mobiltif.R.layout.content_main, LAYOUT_CONTENTMAIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.uniyaz.mobiltif.R.layout.fragment_demibas_detay, LAYOUT_FRAGMENTDEMIBASDETAY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.uniyaz.mobiltif.R.layout.fragment_demibas_list, LAYOUT_FRAGMENTDEMIBASLIST);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.uniyaz.mobiltif.R.layout.fragment_tif, LAYOUT_FRAGMENTTIF);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.uniyaz.mobiltif.R.layout.fragment_tif_islem1, LAYOUT_FRAGMENTTIFISLEM1);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.uniyaz.mobiltif.R.layout.fragment_tif_islem2, LAYOUT_FRAGMENTTIFISLEM2);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.uniyaz.mobiltif.R.layout.fragment_tif_islem3, LAYOUT_FRAGMENTTIFISLEM3);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.uniyaz.mobiltif.R.layout.item_envanter_card_selectable, LAYOUT_ITEMENVANTERCARDSELECTABLE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.uniyaz.mobiltif.R.layout.item_photo_card, LAYOUT_ITEMPHOTOCARD);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.uniyaz.mobiltif.R.layout.item_tif_envanter_card, LAYOUT_ITEMTIFENVANTERCARD);
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
        case  LAYOUT_FRAGMENTDEMIBASLIST: {
          if ("layout/fragment_demibas_list_0".equals(tag)) {
            return new FragmentDemibasListBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_demibas_list is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTTIF: {
          if ("layout/fragment_tif_0".equals(tag)) {
            return new FragmentTifBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_tif is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTTIFISLEM1: {
          if ("layout/fragment_tif_islem1_0".equals(tag)) {
            return new FragmentTifIslem1BindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_tif_islem1 is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTTIFISLEM2: {
          if ("layout/fragment_tif_islem2_0".equals(tag)) {
            return new FragmentTifIslem2BindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_tif_islem2 is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTTIFISLEM3: {
          if ("layout/fragment_tif_islem3_0".equals(tag)) {
            return new FragmentTifIslem3BindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_tif_islem3 is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMENVANTERCARDSELECTABLE: {
          if ("layout/item_envanter_card_selectable_0".equals(tag)) {
            return new ItemEnvanterCardSelectableBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_envanter_card_selectable is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMPHOTOCARD: {
          if ("layout/item_photo_card_0".equals(tag)) {
            return new ItemPhotoCardBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_photo_card is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMTIFENVANTERCARD: {
          if ("layout/item_tif_envanter_card_0".equals(tag)) {
            return new ItemTifEnvanterCardBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_tif_envanter_card is invalid. Received: " + tag);
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
    static final SparseArray<String> sKeys = new SparseArray<String>(17);

    static {
      sKeys.put(0, "_all");
      sKeys.put(1, "showProgressBar");
      sKeys.put(2, "demirbasList");
      sKeys.put(3, "activity");
      sKeys.put(4, "toastMessage");
      sKeys.put(5, "contentMainViewModel");
      sKeys.put(6, "envanter");
      sKeys.put(7, "titleToolbar");
      sKeys.put(8, "room");
      sKeys.put(9, "fragment");
      sKeys.put(10, "password");
      sKeys.put(11, "viewModel");
      sKeys.put(12, "checked");
      sKeys.put(13, "progressBarViewModel");
      sKeys.put(14, "selected");
      sKeys.put(15, "username");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(13);

    static {
      sKeys.put("layout/activity_login_0", com.uniyaz.mobiltif.R.layout.activity_login);
      sKeys.put("layout/activity_main_0", com.uniyaz.mobiltif.R.layout.activity_main);
      sKeys.put("layout/content_main_0", com.uniyaz.mobiltif.R.layout.content_main);
      sKeys.put("layout/fragment_demibas_detay_0", com.uniyaz.mobiltif.R.layout.fragment_demibas_detay);
      sKeys.put("layout/fragment_demibas_list_0", com.uniyaz.mobiltif.R.layout.fragment_demibas_list);
      sKeys.put("layout/fragment_tif_0", com.uniyaz.mobiltif.R.layout.fragment_tif);
      sKeys.put("layout/fragment_tif_islem1_0", com.uniyaz.mobiltif.R.layout.fragment_tif_islem1);
      sKeys.put("layout/fragment_tif_islem2_0", com.uniyaz.mobiltif.R.layout.fragment_tif_islem2);
      sKeys.put("layout/fragment_tif_islem3_0", com.uniyaz.mobiltif.R.layout.fragment_tif_islem3);
      sKeys.put("layout/item_envanter_card_selectable_0", com.uniyaz.mobiltif.R.layout.item_envanter_card_selectable);
      sKeys.put("layout/item_photo_card_0", com.uniyaz.mobiltif.R.layout.item_photo_card);
      sKeys.put("layout/item_tif_envanter_card_0", com.uniyaz.mobiltif.R.layout.item_tif_envanter_card);
      sKeys.put("layout/progress_bar_0", com.uniyaz.mobiltif.R.layout.progress_bar);
    }
  }
}
