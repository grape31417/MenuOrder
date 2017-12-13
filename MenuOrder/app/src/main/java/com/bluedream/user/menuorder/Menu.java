package com.bluedream.user.menuorder;


import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.bluedream.user.menuorder.providers.MenuDataBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class Menu extends ListFragment {
    private ArrayList<Integer> Arr_ID = new ArrayList();
    private ArrayList<String> ArrName = new ArrayList();
    private ArrayList<Integer> ArrPrice = new ArrayList();
    List<Map<String, Object>> mList;
    dataChain dataChain;
    SQLiteDatabase mMenudb;
    MenuDataBase MenuDataBase;




    public Menu() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Updata();
        return inflater.inflate(R.layout.fragment_menu, container, false);

    }


    @Override
    public void onResume() {
        Updata();
        ListView listview=getListView();
        super.onResume();
    }

    @Override
    public void onAttachFragment(Fragment childFragment) {
        Updata();
        super.onAttachFragment(childFragment);
    }


    private void Updata()
    {
        mList = new ArrayList<Map<String,Object>>();
        dataChain =new dataChain(getActivity().getApplicationContext());
        Arr_ID =dataChain.getArr_ID();
        ArrName =dataChain.getArrName();
        ArrPrice =dataChain.getArrPrice();


        for (int i =0 ; i < dataChain.getArrName().size(); i++) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("_ID",dataChain.getArr_ID().get(i));
            item.put("name", dataChain.getArrName().get(i));
            item.put("price", dataChain.getArrPrice().get(i));
            mList.add(item);
        }
        SimpleAdapter adapter = new SimpleAdapter(getActivity().getApplicationContext(),
                mList,R.layout.listviewlayout,
                new String[]{"_ID","name","price"},
                new int[]{R.id.listid,R.id.listname,R.id.listprice});
        setListAdapter(adapter);
    }


}
