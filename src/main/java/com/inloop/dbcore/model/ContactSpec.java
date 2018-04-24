/*
 * Copyright 2015, Yahoo Inc.
 * Copyrights licensed under the Apache 2.0 License.
 * See the accompanying LICENSE file for terms.
 */
package com.inloop.dbcore.model;

import com.google.gson.annotations.SerializedName;
import com.yahoo.squidb.annotations.ColumnSpec;
import com.yahoo.squidb.annotations.ModelMethod;
import com.yahoo.squidb.annotations.PrimaryKey;
import com.yahoo.squidb.annotations.TableModelSpec;

@TableModelSpec(className = "Contact", tableName = "contacts")
public class ContactSpec {

    @PrimaryKey
    @SerializedName("id")
    @ColumnSpec(name = "contactId", constraints = "NOT NULL")
    String contactId;

    @SerializedName("name")
    @ColumnSpec(name = "name", constraints = "NOT NULL")
    String name;

    @SerializedName("phone")
    @ColumnSpec(name = "phone", constraints = "NOT NULL")
    String phone;

    @SerializedName("pictureUrl")
    @ColumnSpec(name = "pictureUrl")
    String pictureUrl;
}
