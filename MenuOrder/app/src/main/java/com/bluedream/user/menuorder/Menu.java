package com.bluedream.user.menuorder;


import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.bluedream.user.menuorder.providers.MenuDataBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.bluedream.user.menuorder.providers.MenuDataBase.DB_TABLE;


/**
 * A simple {@link Fragment} subclass.
 */
public class Menu extends ListFragment {
    private ArrayList<Integer> Arr_ID = new ArrayList();
    private ArrayList<String> ArrName = new ArrayList();
    private ArrayList<Integer> ArrPrice = new ArrayList();
    private ArrayList<Integer> ArrCost = new ArrayList();
    private ArrayList<Integer> ArrSale = new ArrayList();
    List<Map<String, Object>> mList;
    dataChain dataChain;
    SQLiteDatabase mMenudb;
    MenuDataBase MenuDataBase;
    private final static String _ID = "_id";


    public Menu() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false);


    }
    @Override
    public void onPause() {
        super.onPause();
    }


    @Override
    public void onResume() {
        MenuDataBase = new MenuDataBase(getActivity());

        MenuCost menuCost =new MenuCost();
        menuCost.onPause();
        announcementEdit announcementEdit =new announcementEdit();
        announcementEdit.onPause();
        ManagerPasswordChange ManagerPasswordChange =new ManagerPasswordChange();
        ManagerPasswordChange.onPause();
        salecount salecount =new salecount();
        salecount.onPause();
        MenuEdit MenuEdit =new MenuEdit();
        MenuEdit.onPause();


        Updata();
        ListView listview = getListView();
        listview.setOnItemClickListener(click);
        super.onResume();
    }

    private ListView.OnItemClickListener click = new ListView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
            mMenudb = MenuDataBase.OpenDatabase();
            AlertDialog.Builder checkDelete = new AlertDialog.Builder(getActivity());
            checkDelete.setTitle("刪除");
            checkDelete.setMessage("要從菜單刪除:" + ArrName.get(position) + "嗎?");
            checkDelete.setIcon(android.R.drawable.ic_dialog_info);
            checkDelete.setCancelable(false);
            checkDelete.setPositiveButton("是", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mMenudb.delete(DB_TABLE, _ID+ "=" + Arr_ID.get(position), null);
                    Toast.makeText(getActivity(), "已刪除", Toast.LENGTH_LONG).show();
                    mMenudb.close();
                    Updata();
                }
            });
            checkDelete.setNeutralButton("否", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });

            checkDelete.show();

        }
    };


    private void Updata() {
        mMenudb = MenuDataBase.OpenDatabase();
        Arr_ID.clear();
        ArrName.clear();
        ArrPrice.clear();
        ArrSale.clear();
        ArrCost.clear();
        Cursor c = MenuDataBase.query(true, true, true, true, true);
        mList = new ArrayList<Map<String, Object>>();
        if (c.getCount() == 0) {
            Toast.makeText(getActivity(), "沒有資料", Toast.LENGTH_LONG).show();
        } else {
            c.moveToFirst();
            for (int i = 1; i <= c.getCount(); i++) {
                Arr_ID.add(c.getInt(0));
                ArrName.add(c.getString(1));
                ArrPrice.add(c.getInt(2));
                ArrCost.add(c.getInt(3));
                ArrSale.add(c.getInt(4));
                c.moveToNext();
            }
        }


        for (int i = 0; i < Arr_ID.size(); i++) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("_ID", Arr_ID.get(i));
            item.put("name", ArrName.get(i));
            item.put("price", ArrPrice.get(i) + "元");
            item.put("cost", ArrCost.get(i) + "元");
            item.put("sale", "售" + ArrSale.get(i) + "份");
            mList.add(item);
        }
        SimpleAdapter adapter = new SimpleAdapter(getActivity().getApplicationContext(),
                mList, R.layout.listviewlayout,
                new String[]{"_ID", "name", "price", "cost", "sale"},
                new int[]{R.id.listid, R.id.listname, R.id.listprice, R.id.cost, R.id.sale});
        setListAdapter(adapter);
        mMenudb.close();

        c.close();

    }


}
