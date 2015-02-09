package com.chriseconomou.sampleproject.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.chriseconomou.sampleproject.data.CategoriesResponse;
import com.chriseconomou.sampleproject.util.GsonUtil;

/**
 * SQLite database wrapper for recently viewed parcels.
 */
public class BagDatabaseAdapter extends DatabaseAdapter {

    public static final String COL_ID = "_id";
    public static final String COL_PRODUCT_ID = "product_id";
    public static final String COL_PRODUCT_QUANTITY = "product_quantity";
    public static final String BAG_TABLE = "bag_table";

    public BagDatabaseAdapter(Context context) {
        super(context);
        open();
    }

    public void update(String productId, int quantity) {
        String[] columns = new String[]{COL_ID, COL_PRODUCT_ID, COL_PRODUCT_QUANTITY};
        String[] select = new String[]{productId};
        Cursor cursor = getDatabase().query(BAG_TABLE, columns, COL_PRODUCT_ID, select, null, null, null, "1");

        if (cursor.getCount() > 0) {
            delete(productId);
        }
        insert(productId, quantity);
        cursor.close();
    }

    private void delete(String categoriesType) {
        String[] delete = new String[]{categoriesType};
        getDatabase().delete(BAG_TABLE, COL_PRODUCT_ID + " = ?", delete);
    }

    public long insert(String productId, int quantity) {

        ContentValues args = new ContentValues();
        args.put(COL_PRODUCT_ID, productId);
        args.put(COL_PRODUCT_QUANTITY, quantity);
        return getDatabase().insert(BAG_TABLE, null, args);
    }

    public CategoriesResponse getCategory(String categoryType) {
        CategoriesResponse trackParcelResponse;
        String[] columns = new String[]{COL_ID, COL_PRODUCT_ID, COL_PRODUCT_QUANTITY};
        String[] select = new String[]{categoryType};
        Cursor cursor = getDatabase().query(BAG_TABLE, columns, COL_PRODUCT_ID + " = ?", select, null, null, null, "1");

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            byte[] blob = cursor.getBlob(cursor.getColumnIndex(COL_PRODUCT_QUANTITY));
            String json = new String(blob);
            trackParcelResponse = GsonUtil.deserialize(json, CategoriesResponse.class);
            cursor.close();
            return trackParcelResponse;
        }

        cursor.close();
        return null;
    }


}