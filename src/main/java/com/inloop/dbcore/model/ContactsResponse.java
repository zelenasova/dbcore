package com.inloop.dbcore.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ContactsResponse {

    @SerializedName("items")
    List<Contact> contacts;

    public List<Contact> getContacts() {
        return contacts;
    }
}
