package com.inloop.dbcore.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.inloop.dbcore.model.ContactsResponse;

public class GsonUtils {

    private static GsonUtils sInstance;
    private Gson gson;

    private GsonUtils() {
        gson = new GsonBuilder().create();
    }

    public static GsonUtils getInstance() {
        if (sInstance == null) {
            sInstance = new GsonUtils();
        }
        return sInstance;
    }

    public String toJson(ContactsResponse profile) {
        return gson.toJson(profile);
    }

    public ContactsResponse fromJson(String jsonString) {
        return gson.fromJson(jsonString, ContactsResponse.class);
    }

    public Gson getGson() {
        return gson;
    }
}
