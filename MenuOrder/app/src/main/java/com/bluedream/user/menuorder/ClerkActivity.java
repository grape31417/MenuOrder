package com.bluedream.user.menuorder;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bluedream.user.menuorder.providers.MenuDataBase;

import java.util.ArrayList;

public class ClerkActivity extends AppCompatActivity {
    protected static ArrayList<Integer> ClerkArr_IDOrder = new ArrayList();
    protected static  ArrayList<Integer> ClerkQuantity = new ArrayList();
    protected static  ArrayList<String> ClerkArrNameOrder = new ArrayList();

    dataChain dataChain;
    SQLiteDatabase mMenudb;
    com.bluedream.user.menuorder.providers.MenuDataBase MenuDataBase;
    private final static String _ID = "_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clerk);
        Intent it = getIntent();
        Bundle bundle =new Bundle();
        bundle =it.getExtras();
        ClerkArr_IDOrder=bundle.getIntegerArrayList("orderID");
        ClerkQuantity =bundle.getIntegerArrayList("quantity");
        ClerkArrNameOrder =bundle.getStringArrayList("orderIDname");
        MenuDataBase = new MenuDataBase(ClerkActivity.this);

    }

}
