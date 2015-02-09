package com.chriseconomou.sampleproject.database;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;


import com.chriseconomou.sampleproject.data.CategoriesResponse;
import com.chriseconomou.sampleproject.util.GsonUtil;

import java.util.List;
/**
 * SQLite database wrapper for recently viewed parcels.
 */
public class CategoriesDatabaseAdapter extends DatabaseAdapter {

    public static final String COL_ID = "_id";
    public static final String COL_CATEGORY_TYPE = "category_type";
    public static final String COL_DATA = "data";
    public static final String RECENTLY_VIEWED_TABLE = "recently_viewed_table";

    public CategoriesDatabaseAdapter(Context context) {
        super(context);
        open();
    }

    public void update(String categoriesType, CategoriesResponse response) {
        String[] columns = new String[]{COL_ID, COL_CATEGORY_TYPE, COL_DATA};
        String[] select = new String[]{categoriesType};
        Cursor cursor = getDatabase().query(RECENTLY_VIEWED_TABLE, columns, COL_CATEGORY_TYPE , select, null, null, null, "1");
        if (cursor.getCount() > 0) {
            delete(categoriesType);
        }
        insert(categoriesType, response);
        cursor.close();
    }

    private void delete(String categoriesType) {
        String[] delete = new String[]{categoriesType};
        getDatabase().delete(RECENTLY_VIEWED_TABLE,COL_CATEGORY_TYPE + " = ?", delete);
    }

    public long insert(String categoryType, CategoriesResponse response) {

        ContentValues args = new ContentValues();
        args.put(COL_CATEGORY_TYPE, categoryType);
        args.put(COL_DATA, GsonUtil.serialize(response).getBytes());
        return getDatabase().insert(RECENTLY_VIEWED_TABLE, null, args);
    }

    public CategoriesResponse getCategory(String categoryType) {
        CategoriesResponse trackParcelResponse;
        String[] columns = new String[]{COL_ID, COL_CATEGORY_TYPE, COL_DATA};
        String[] select = new String[]{ categoryType};
        Cursor cursor = getDatabase().query(RECENTLY_VIEWED_TABLE, columns,COL_CATEGORY_TYPE + " = ?", select, null, null, null, "1");

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            byte[] blob = cursor.getBlob(cursor.getColumnIndex(COL_DATA));
            String json = new String(blob);
            trackParcelResponse = GsonUtil.deserialize(json, CategoriesResponse.class);
            cursor.close();
            return trackParcelResponse;
        }

        cursor.close();
        return null;
    }



}