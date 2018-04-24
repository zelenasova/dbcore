/*
 * Copyright 2015, Yahoo Inc.
 * Copyrights licensed under the Apache 2.0 License.
 * See the accompanying LICENSE file for terms.
 */
package com.inloop.dbcore.utils;

import com.inloop.dbcore.db.ContactDatabase;
import com.inloop.dbcore.model.Contact;
import com.inloop.dbcore.model.ContactsResponse;
import com.yahoo.squidb.data.SquidCursor;
import com.yahoo.squidb.sql.Query;

import java.util.ArrayList;
import java.util.List;

public class ContactUtils {

    private static ContactUtils sInstance = null;

    public static ContactUtils getInstance() {
        if (sInstance == null) {
            synchronized (ContactUtils.class) {
                if (sInstance == null) {
                    sInstance = new ContactUtils();
                }
            }
        }
        return sInstance;
    }

    private ContactDatabase contactDatabase = ContactDatabase.getInstance();

    private ContactUtils() {
    }

    private static final Query CONTACTS_QUERY = Query.select(Contact.PROPERTIES);

    public SquidCursor<Contact> getContactsCursor() {
        return contactDatabase.query(Contact.class, CONTACTS_QUERY);
    }

    public List<Contact>  getContacts() {
        List<Contact> contacts = new ArrayList<>();
        SquidCursor<Contact> contactCursor = getContactsCursor();
        try {
            Contact contact = new Contact();
            while (contactCursor.moveToNext()) {
                contact.readPropertiesFromCursor(contactCursor);
                contacts.add(contact);
            }

        } finally {
            contactCursor.close();
        }
        return contacts;
    }

    public Contact getContact(long id) {
        return contactDatabase.fetch(Contact.class, id);
    }

    public void insertContacts(List<Contact> contacts) {

        contactDatabase.beginTransaction();
        try {
            for (Contact contact : contacts) {
                contactDatabase.persist(contact);
            }
            contactDatabase.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            contactDatabase.endTransaction();
        }
    }

    public void insertContacts(String contactJson) {
        //not implemented yet
    }

    public void insertContact(String contactId, String name, String phone, String pictureUrl) {

        Contact contact = new Contact()
                .setContactId(contactId)
                .setName(name)
                .setPhone(phone)
                .setPictureUrl(pictureUrl);

        contactDatabase.persist(contact);
    }

    public boolean deleteContact(Contact contact) {
        return contactDatabase.delete(Contact.class, contact.getRowId());
    }

}
