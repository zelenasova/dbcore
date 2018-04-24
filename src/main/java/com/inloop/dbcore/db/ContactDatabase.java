/*
 * Copyright 2015, Yahoo Inc.
 * Copyrights licensed under the Apache 2.0 License.
 * See the accompanying LICENSE file for terms.
 */
package com.inloop.dbcore.db;

import com.inloop.dbcore.model.Contact;
import com.yahoo.squidb.data.ISQLiteDatabase;
import com.yahoo.squidb.data.ISQLiteOpenHelper;
import com.yahoo.squidb.data.SquidDatabase;
import com.yahoo.squidb.sql.Index;
import com.yahoo.squidb.sql.Table;

/**
 * Implementation of SquidDatabase for this tasks app. Remember--instances of your SquidDatabase
 * subclass should always be singletons!
 */
public class ContactDatabase extends SquidDatabase {

    private static final int VERSION = 2;

    private static ContactDatabase instance = null;

    public static ContactDatabase getInstance() {
        if (instance == null) {
            synchronized (ContactDatabase.class) {
                if (instance == null) {
                    instance = new ContactDatabase();
                }
            }
        }
        return instance;
    }

    private ContactDatabase() {
        super();
    }

    @Override
    public String getName() {
        return "contact.db";
    }

    @Override
    protected int getVersion() {
        return VERSION;
    }

    @Override
    protected Table[] getTables() {
        return new Table[]{
                Contact.TABLE
        };
    }

    @Override
    protected Index[] getIndexes() {
        return new Index[]{

        };
    }

    @Override
    protected boolean onUpgrade(ISQLiteDatabase db, int oldVersion, int newVersion) {
        // Example DB migration if the tags table and tasks.priority columns were added in version 2
        switch (oldVersion) {
            case 1:
                tryCreateTable(Contact.TABLE);
        }
        return false;
    }

    @Override
    protected ISQLiteOpenHelper createOpenHelper(String databaseName, OpenHelperDelegate delegate,
                                                 int version) {
        return OpenHelperCreator.getCreator().createOpenHelper(databaseName, delegate, version);
    }
}
