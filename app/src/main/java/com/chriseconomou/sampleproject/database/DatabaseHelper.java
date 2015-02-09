package com.chriseconomou.sampleproject.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * SQLite database creator/helper.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "database";
    private static final int DATABASE_VERSION = 1;

    /**
     * Query to create recently categories table.
     */
    private static final String CREATE_CATEGORIES_TABLE = "create table " + CategoriesDatabaseAdapter.RECENTLY_VIEWED_TABLE
            + " (" +
            CategoriesDatabaseAdapter.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +

            CategoriesDatabaseAdapter.COL_CATEGORY_TYPE + " TEXT," +

            CategoriesDatabaseAdapter.COL_DATA + " BLOB"
            + ");";

    /**
     * Query to create bag table.
     */
    private static final String CREATE_BAG_TABLE = "create table " + BagDatabaseAdapter.BAG_TABLE
            + " (" +
            BagDatabaseAdapter.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +

            BagDatabaseAdapter.COL_PRODUCT_ID + " TEXT," +

            BagDatabaseAdapter.COL_PRODUCT_QUANTITY + " INTEGER"
            + ");";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_CATEGORIES_TABLE);
        database.execSQL(CREATE_BAG_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
    }

}