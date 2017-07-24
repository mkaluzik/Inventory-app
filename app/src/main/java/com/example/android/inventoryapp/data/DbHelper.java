package com.example.android.inventoryapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by martin on 24.7.17.
 */

public class DbHelper extends SQLiteOpenHelper {

    public final static String DB_NAME = "instock.db";
    public final static int DB_VERSION = 1;
    public final static String LOG_TAG = DbHelper.class.getCanonicalName();

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Contract.StockEntry.CREATE_TABLE_STOCK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertItem(Commodity item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Contract.StockEntry.COLUMN_NAME, item.getProductName());
        values.put(Contract.StockEntry.COLUMN_PRICE, item.getPrice());
        values.put(Contract.StockEntry.COLUMN_QUANTITY, item.getQuantity());
        values.put(Contract.StockEntry.COLUMN_SUPPLIER_NAME, item.getSupplierName());
        values.put(Contract.StockEntry.COLUMN_SUPPLIER_PHONE, item.getSupplierPhone());
        values.put(Contract.StockEntry.COLUMN_SUPPLIER_EMAIL, item.getSupplierEmail());
        values.put(Contract.StockEntry.COLUMN_IMAGE, item.getImage());
        long id = db.insert(Contract.StockEntry.TABLE_NAME, null, values);
    }

    public Cursor readStock() {
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {
                Contract.StockEntry._ID,
                Contract.StockEntry.COLUMN_NAME,
                Contract.StockEntry.COLUMN_PRICE,
                Contract.StockEntry.COLUMN_QUANTITY,
                Contract.StockEntry.COLUMN_SUPPLIER_NAME,
                Contract.StockEntry.COLUMN_SUPPLIER_PHONE,
                Contract.StockEntry.COLUMN_SUPPLIER_EMAIL,
                Contract.StockEntry.COLUMN_IMAGE
        };
        Cursor cursor = db.query(
                Contract.StockEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );
        return cursor;
    }

    public Cursor readItem(long itemId) {
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {
                Contract.StockEntry._ID,
                Contract.StockEntry.COLUMN_NAME,
                Contract.StockEntry.COLUMN_PRICE,
                Contract.StockEntry.COLUMN_QUANTITY,
                Contract.StockEntry.COLUMN_SUPPLIER_NAME,
                Contract.StockEntry.COLUMN_SUPPLIER_PHONE,
                Contract.StockEntry.COLUMN_SUPPLIER_EMAIL,
                Contract.StockEntry.COLUMN_IMAGE
        };
        String selection = Contract.StockEntry._ID + "=?";
        String[] selectionArgs = new String[] { String.valueOf(itemId) };

        Cursor cursor = db.query(
                Contract.StockEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        return cursor;
    }

    public void updateItem(long currentItemId, int quantity) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Contract.StockEntry.COLUMN_QUANTITY, quantity);
        String selection = Contract.StockEntry._ID + "=?";
        String[] selectionArgs = new String[] { String.valueOf(currentItemId) };
        db.update(Contract.StockEntry.TABLE_NAME,
                values, selection, selectionArgs);
    }

    public void sellOneItem(long itemId, int quantity) {
        SQLiteDatabase db = getWritableDatabase();
        int newQuantity = 0;
        if (quantity > 0) {
            newQuantity = quantity -1;
        }
        ContentValues values = new ContentValues();
        values.put(Contract.StockEntry.COLUMN_QUANTITY, newQuantity);
        String selection = Contract.StockEntry._ID + "=?";
        String[] selectionArgs = new String[] { String.valueOf(itemId) };
        db.update(Contract.StockEntry.TABLE_NAME,
                values, selection, selectionArgs);
    }
}
