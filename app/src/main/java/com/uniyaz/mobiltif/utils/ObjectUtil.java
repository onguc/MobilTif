package com.uniyaz.mobiltif.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class ObjectUtil<T> {

    Context context;

    public ObjectUtil(Context context) {
        this.context = context;
    }

    public T getObjectFromSharedPreferences(String keyUser, Class<T> clazz) {
        SharedPreferences preferences = getSharedPreferences();
        String jsonList = preferences.getString(keyUser, null);
        if (jsonList != null) {
            Gson gson = new Gson();
            Type typeOfT = new TypeToken<T>() {
            }.getType();
            T t1 = gson.fromJson(jsonList, typeOfT);
            T t = gson.fromJson(jsonList, clazz);
            return t;
        }
        return null;
    }

    public void saveObjectToSharedPreferences(String keyUser, T t) {
        SharedPreferences preferences = getSharedPreferences();
        SharedPreferences.Editor editor = preferences.edit();

        Gson gson = new Gson();
        String jsonList = gson.toJson(t);
        editor.putString(keyUser, jsonList);
        editor.commit();
    }

    private SharedPreferences getSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
}