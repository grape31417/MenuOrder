package com.bluedream.user.menuorder;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.bluedream.user.menuorder.providers.MenuDataBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class MenuCost extends ListFragment {
    private ArrayList<Integer> Arr_ID = new ArrayList();
    private ArrayList<String> ArrName = new ArrayList();
    private ArrayList<Integer> ArrPrice = new ArrayList();
    private ArrayList<Integer> ArrCost = new ArrayList();
    private ArrayList<Integer> ArrSale = new ArrayList();
    private ArrayList<Integer> getMoney = new ArrayList();
    public int allMoney = 0;
    List<Map<String, Object>> mList;
    dataChain dataChain;
    SQLiteDatabase mMenudb;
    com.bluedream.user.menuorder.providers.MenuDataBase MenuDataBase;
    private final static String _ID = "_id";


    public MenuCost() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        MenuDataBase=new MenuDataBase(getActivity());
        Updata();
        return inflater.inflate(R.layout.fragment_menu_cost, container, false);

    }

    @Override
    public void onResume() {
        MenuCost menuCost =new MenuCost();
        menuCost.onPause();
        announcementEdit announcementEdit =new announcementEdit();
        announcementEdit.onPause();
        ManagerPasswordChange ManagerPasswordChange =new ManagerPasswordChange();
        ManagerPasswordChange.onPause();
        MenuEdit MenuEdit =new MenuEdit();
        MenuEdit.onPause();
        Menu Menu =new Menu();
        Menu.onPause();
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    private void Updata() {
        mMenudb = MenuDataBase.OpenDatabase();
        Arr_ID.clear();
        ArrName.clear();
        ArrPrice.clear();
        ArrSale.clear();
        ArrCost.clear();
        getMoney.clear();
        Cursor c = MenuDataBase.query(true, true, true, true, true);
        mList = new ArrayList<Map<String, Object>>();
        if (c.getCount() == 0) {
            Toast.makeText(getActivity(), "沒有資料", Toast.LENGTH_LONG).show();
        } else {
            c.moveToFirst();
            for (int i = 1; i <= c.getCount(); i++) {
                Arr_ID.add(c.getInt(0));
                ArrName.add(c.getString(1));
                ArrSale.add(c.getInt(4));
                getMoney.add((c.getInt(2) - c.getInt(3)) * c.getInt(4));
                c.moveToNext();
            }
        }


        for (int i = 0; i < Arr_ID.size(); i++) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("_ID", Arr_ID.get(i));
            item.put("name", ArrName.get(i));
            item.put("sale", "售" + ArrSale.get(i) + "份");
            item.put("getMoney", "收益" + getMoney.get(i) + "元");
            mList.add(item);
        }
        SimpleAdapter adapter = new SimpleAdapter(getActivity().getApplicationContext(),
                mList, R.layout.salelistview,
                new String[]{"_ID", "name", "sale", "getMoney"},
                new int[]{R.id.Salelistid, R.id.Salelistname, R.id.Salesale, R.id.SaleGetMoney});
        setListAdapter(adapter);
        mMenudb.close();
        c.close();

    }
}
