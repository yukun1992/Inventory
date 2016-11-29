package com.example.android.inventory.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.android.inventory.data.productContract.ProductEntry.COLUMN_PRODUCT_SELL_TIMES;

/**
 * Created by cheny on 2016/11/26.
 */

public class productDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = productDbHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "product.db";

    private static final int DATABASE_VERSION = 1;

    public productDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_PRODUCT_TABLE =  "CREATE TABLE " + productContract.ProductEntry.TABLE_NAME + " ("
                + productContract.ProductEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + productContract.ProductEntry.COLUMN_PRODUCT_NAME + " TEXT NOT NULL, "
                + productContract.ProductEntry.COLUMN_PRODUCT_PRICE + " REAL NOT NULL, "
                + productContract.ProductEntry.COLUMN_PRODUCT_QUANTITY + " INTEGER NOT NULL, "
                + productContract.ProductEntry.COLUMN_PRODUCT_SELL_TIMES + " INTEGER NOT NULL,"
                + productContract.ProductEntry.COLUMN_PRODUCT_IMAGE + " TEXT NOT NULL);";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_PRODUCT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
