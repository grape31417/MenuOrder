package com.bluedream.user.menuorder.providers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;




/**
 * Created by USER on 2017/11/24.
 */

public class MenuDataBase {
    private SQLiteDatabase mMenuDb;
    private Context context;
    public static final String DB_FILE = "MenuDB.db",
            DB_TABLE = "Menu";
    final String DROP_TABLE = "DROP TABLE IF EXISTS " + DB_TABLE;

    public MenuDataBase(Context applicationContext) {
        context = applicationContext;
    }

    public SQLiteDatabase OpenDatabase() {
        MenuDbOpenHelper SQLiteDatabaseOpenHelper =
                new MenuDbOpenHelper(context, DB_FILE, null, 1);
        mMenuDb = SQLiteDatabaseOpenHelper.getWritableDatabase();

        Cursor cursor = mMenuDb.rawQuery(
                "select DISTINCT tbl_name from sqlite_master where tbl_name = '" +
                        DB_TABLE + "'", null);

        if (cursor != null) {
            if (cursor.getCount() == 0)
                mMenuDb.execSQL("CREATE TABLE " + DB_TABLE + " (" +
                        "_id INTEGER PRIMARY KEY," +
                        "name TEXT NOT NULL," +
                        "price INTEGER NOT NULL);");
            cursor.close();
        }

        return mMenuDb;
    }

    public void CloseDatabase() {
        mMenuDb.close();
    }

    public Cursor query(boolean _id, boolean name, boolean price) {
        String id = "", Name = "", Price = "";
        if (_id == true) id = "_id";
        if (name == true) Name = "name";
        if (price == true) Price = "price";

        Cursor c = mMenuDb.query(true, DB_TABLE, new String[]{id, Name, Price}, null, null, null, null, null, null);
        return c;
    }

    public int DatabaseCount() {
        Cursor c = mMenuDb.query(true, DB_TABLE, new String[]{"_id"}, null, null, null, null, null, null);
        int count = c.getCount();
        c.close();
        return count;
    }

    public boolean dleteDatabase() {
        mMenuDb.execSQL(DROP_TABLE);

        return true;
    }
}
