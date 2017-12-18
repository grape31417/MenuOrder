package com.bluedream.user.menuorder;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.bluedream.user.menuorder.providers.MenuDataBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class customeFragment extends ListFragment {

    private ArrayList<Integer> Arr_ID = new ArrayList();
    private ArrayList<String> ArrName = new ArrayList();
    private ArrayList<Integer> ArrPrice = new ArrayList();
    protected static ArrayList<Integer> Arr_IDOrder = new ArrayList();
    protected static ArrayList<String> ArrNameOrder = new ArrayList();
    protected static ArrayList<Integer> Quantity = new ArrayList();

    List<Map<String, Object>> mList;
    dataChain dataChain;
    SQLiteDatabase mMenudb;
    com.bluedream.user.menuorder.providers.MenuDataBase MenuDataBase;
    private final static String _ID = "_id";
    protected static TextView TxtOrder;
    int price =0;
    private Button btnDeleteOrder;



    public customeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MenuDataBase =new MenuDataBase(getActivity());

        Updata();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_custome, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        ListView listview = getListView();
        listview.setOnItemClickListener(click);
        btnDeleteOrder =(Button) getActivity().findViewById(R.id.btnDeleteOrder);
        btnDeleteOrder.setOnClickListener(deleteOrder);
    }

    private View.OnClickListener deleteOrder =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Arr_IDOrder.clear();
            Quantity.clear();
            ArrNameOrder.clear();
            price=0;
            TxtOrder.setText("你的訂單:");
        }
    };


    private ListView.OnItemClickListener click = new ListView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
            TxtOrder =(TextView)getActivity().findViewById(R.id.txtMenuOrder);
            if(Arr_IDOrder.contains(Arr_ID.get(position)))
            {
                price=price+ArrPrice.get(position);
                int idx = Arr_IDOrder.indexOf(Arr_ID.get(position));
                Quantity.set(idx,Quantity.get(idx)+1);
                TxtOrder.setText("你的訂單:");
                for (int i=0;i<Arr_IDOrder.size();i++) {
                    idx = Arr_ID.indexOf(Arr_IDOrder.get(i));
                    TxtOrder.append("\n" + ArrName.get(idx) + "  " + Quantity.get(i)+"份");
                }
                TxtOrder.append("\n共計: "+price+"元");
            }
            else {
                Arr_IDOrder.add(Arr_ID.get(position));
                ArrNameOrder.add(ArrName.get(position));
                Quantity.add(1);
                TxtOrder.setText("你的訂單:");
                price=price+ArrPrice.get(position);
                for (int i=0;i<Arr_IDOrder.size();i++)
                {
                    int idx = Arr_ID.indexOf(Arr_IDOrder.get(i));
                    TxtOrder.append("\n" + ArrName.get(idx) + "  " + Quantity.get(i)+"份");

                }
                TxtOrder.append("\n共計: "+price+"元");
            }
        }
    };



    private void Updata() {
        mMenudb = MenuDataBase.OpenDatabase();
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
                c.moveToNext();
            }
        }


        for (int i = 0; i < Arr_ID.size(); i++) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("_ID", Arr_ID.get(i));
            item.put("name", ArrName.get(i));
            item.put("price", +ArrPrice.get(i) + "元");
            mList.add(item);
        }
        SimpleAdapter adapter = new SimpleAdapter(getActivity(),
                mList, R.layout.coustomerfragmentlayout,
                new String[]{"_ID", "name", "price"},
                new int[]{R.id.Customerlistid, R.id.Customeristname, R.id.Customerprice});
        setListAdapter(adapter);
        mMenudb.close();
        c.close();
    }

}
