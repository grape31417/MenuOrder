package com.bluedream.user.menuorder;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.bluedream.user.menuorder.providers.MenuDataBase;

import java.util.ArrayList;

import static com.bluedream.user.menuorder.providers.MenuDataBase.DB_TABLE;

/**
 * Created by USER on 2017/12/12.
 */

public class dataChain {
    private ArrayList<Integer> Arr_ID = new ArrayList();
    private ArrayList<String> ArrName = new ArrayList();
    private ArrayList<Integer> ArrPrice = new ArrayList();
    private Context context;
    private SQLiteDatabase mMenuDB;
    SharedPreferences mangerPassword;


    public dataChain(Context context) {
        this.context = context;
    }
    public dataChain (SharedPreferences mangerPassword)
    {
        this.mangerPassword=mangerPassword;
    }



    public String getMangerPassword() {
        return mangerPassword.getString("mangerPassword","");
    }

    public void setMangerPassword(String Password) {
        mangerPassword.edit().putString("mangerPassword",Password).commit();
    }
    public void setAnnouncemen(String Announcemen) {
        mangerPassword.edit().putString("announcemen",Announcemen).commit();
    }

    public String getAnnouncemen() {
        return mangerPassword.getString("announcemen","");
    }

    public ArrayList<Integer> getArr_ID() {
        mMenuDB =new MenuDataBase(context).OpenDatabase();

        Arr_ID.clear();

        Cursor c = mMenuDB.query(true, DB_TABLE, new String[]{"_ID"}, null, null, null, null, null, null);

        if (c.getCount() == 0) {
            Toast.makeText(context,"沒有資料", Toast.LENGTH_LONG).show();
        } else {
            c.moveToFirst();
            for(int i=1;i<=c.getCount();i++) {
                Arr_ID.add(c.getInt(0));
                c.moveToNext();
            }

        }
        mMenuDB.close();
        c.close();
        return Arr_ID;
}


    public ArrayList<String> getArrName() {
        mMenuDB =new MenuDataBase(context).OpenDatabase();
        ArrName.clear();
        Cursor c = mMenuDB.query(true, DB_TABLE, new String[]{"name"}, null, null, null, null, null, null);

        if (c.getCount() == 0) {
            Toast.makeText(context,"沒有資料", Toast.LENGTH_LONG).show();
        } else {
            c.moveToFirst();
            for(int i=1;i<=c.getCount();i++) {
                ArrName.add(c.getString(0));
                c.moveToNext();
            }

        }
        mMenuDB.close();
        c.close();
        return ArrName;
    }


    public ArrayList<Integer> getArrPrice() {
        mMenuDB =new MenuDataBase(context).OpenDatabase();
        ArrPrice.clear();
        Cursor c = mMenuDB.query(true, DB_TABLE, new String[]{"price"}, null, null, null, null, null, null);

        int rowcount =c.getCount();

        if (c.getCount() == 0) {
            Toast.makeText(context,"沒有資料", Toast.LENGTH_LONG).show();
        } else {
            c.moveToFirst();
            for(int i=1;i<=c.getCount();i++) {
                ArrPrice.add(c.getInt(0));
                c.moveToNext();
            }
        }
        c.close();
        mMenuDB.close();
        return ArrPrice;
    }

}
