package com.example.android.inventoryapp.data;

import android.provider.BaseColumns;

/**
 * Created by martin on 24.7.17.
 */

public class Contract {

    public Contract() {
    }

    public static final class StockEntry implements BaseColumns {

        public static final String TABLE_NAME = "stock";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_QUANTITY = "quantity";
        public static final String COLUMN_SUPPLIER_NAME = "supplier_name";
        public static final String COLUMN_SUPPLIER_PHONE = "supplier_phone";
        public static final String COLUMN_SUPPLIER_EMAIL = "supplier_email";
        public static final String COLUMN_IMAGE = "image";

        public static final String CREATE_TABLE_STOCK = "CREATE TABLE " +
                Contract.StockEntry.TABLE_NAME + "(" +
                Contract.StockEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                Contract.StockEntry.COLUMN_NAME + " TEXT NOT NULL," +
                Contract.StockEntry.COLUMN_PRICE + " TEXT NOT NULL," +
                Contract.StockEntry.COLUMN_QUANTITY + " INTEGER NOT NULL DEFAULT 0," +
                Contract.StockEntry.COLUMN_SUPPLIER_NAME + " TEXT NOT NULL," +
                Contract.StockEntry.COLUMN_SUPPLIER_PHONE + " TEXT NOT NULL," +
                Contract.StockEntry.COLUMN_SUPPLIER_EMAIL + " TEXT NOT NULL," +
                StockEntry.COLUMN_IMAGE + " TEXT NOT NULL" + ");";
    }
}
