package com.chriseconomou.sampleproject.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.chriseconomou.sampleproject.data.CategoriesResponse;
import com.chriseconomou.sampleproject.util.GsonUtil;

/**
 * SQLite database wrapper for the shopping bag
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


    /**
     * Delete a product from the shopping bag
     * @param productId
     */
    private void delete(String productId) {
        String[] delete = new String[]{productId};
        getDatabase().delete(BAG_TABLE, COL_PRODUCT_ID + " = ?", delete);
    }

    /**
     * Add a product to the shopping bag
     * @param productId - the id of the product to be added to the bag
     * @return
     */
    public long addToBag(String productId) {
        String[] columns = new String[]{COL_ID, COL_PRODUCT_ID, COL_PRODUCT_QUANTITY};
        String[] select = new String[]{productId};
        Cursor cursor = getDatabase().query(BAG_TABLE, columns,  COL_PRODUCT_ID + " = ?", select, null, null, null, "1");
        int currentQuantiy = 0;
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            currentQuantiy = cursor.getInt(cursor.getColumnIndex(COL_PRODUCT_QUANTITY));
            delete(productId);
            cursor.moveToNext();
        }

        cursor.close();
        ContentValues args = new ContentValues();
        args.put(COL_PRODUCT_ID, productId);
        args.put(COL_PRODUCT_QUANTITY, ++currentQuantiy);
        return getDatabase().insert(BAG_TABLE, null, args);

    }

    /**
     * retrieve the number of the items that are currently in the shopping bag
     * @return
     */
    public int getBagSize() {
        int totalItems = 0;
        String[] columns = new String[]{COL_ID, COL_PRODUCT_ID, COL_PRODUCT_QUANTITY};

        Cursor cursor = getDatabase().query(BAG_TABLE, columns, null, null, null, null, null, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            int amount = cursor.getInt(cursor.getColumnIndex(COL_PRODUCT_QUANTITY));
            totalItems = totalItems + amount;
            cursor.moveToNext();
        }

        cursor.close();
        return  totalItems;
    }

    /**
     * clear the shopping bag of all the items
     */
    public void clearBag(){
       getDatabase().execSQL("delete from "+ BAG_TABLE);
    }


}