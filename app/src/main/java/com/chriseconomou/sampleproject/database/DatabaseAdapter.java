package com.chriseconomou.sampleproject.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * SQLite database wrapper.
 */
public class DatabaseAdapter {

    private Context mContext;
    private SQLiteDatabase mDatabase;
    private DatabaseHelper mDbHelper;

    public DatabaseAdapter(Context context) {
        mContext = context;
    }

    public DatabaseAdapter open() {
        mDbHelper = new DatabaseHelper(mContext);
        setDatabase(mDbHelper.getWritableDatabase());
        return this;
    }

    public void close() {
        getDatabase().close();
        mDbHelper.close();
    }

    public SQLiteDatabase getDatabase() {
        return mDatabase;
    }

    public void setDatabase(SQLiteDatabase database) {
        mDatabase = database;
    }

    protected Context getContext() {
        return mContext;
    }

}