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
public class ClerkFragment extends ListFragment {
    List<Map<String, Object>> mList;
    dataChain dataChain;
    SQLiteDatabase mMenudb;
    com.bluedream.user.menuorder.providers.MenuDataBase MenuDataBase;
    private final static String _ID = "_id";



    public ClerkFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_clerk, container, false);
    }


    @Override
    public void onResume() {
        super.onResume();
        Updata();
        MenuDataBase =new MenuDataBase(getActivity());
        ListView listview = getListView();
        listview.setOnItemClickListener(click);
    }

    private ListView.OnItemClickListener click = new ListView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
            mMenudb = MenuDataBase.OpenDatabase();
            AlertDialog.Builder checkDelete = new AlertDialog.Builder(getActivity());
            checkDelete.setTitle("確認上菜");
            checkDelete.setMessage("上菜後刪除:" + ClerkActivity.ClerkArrNameOrder.get(position) + "嗎?");
            checkDelete.setIcon(android.R.drawable.ic_dialog_info);
            checkDelete.setCancelable(false);
            checkDelete.setPositiveButton("是", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    int i=0;
                    mMenudb = MenuDataBase.OpenDatabase();
                    Cursor c = MenuDataBase.query(true, true, true, true, true);
                    c.moveToFirst();
                    for (int j=1;j<=c.getCount();j++) {
                        if(ClerkActivity.ClerkArr_IDOrder.get(position).equals(c.getInt(0)))
                            i=c.getInt(4);
                        c.moveToNext();
                    }
                    c.close();
                    i=i+ClerkActivity.ClerkQuantity.get(position);

                    String s=String.valueOf(i);
                    String a=String.valueOf(ClerkActivity.ClerkArr_IDOrder.get(position));
                    mMenudb.execSQL("UPDATE "+DB_TABLE +" SET sales = "+s+ " WHERE _ID = "+a+" ;");
                    ClerkActivity.ClerkArrNameOrder.remove(position);
                    ClerkActivity.ClerkArr_IDOrder.remove(position);
                    ClerkActivity.ClerkQuantity.remove(position);
                    Updata();
                    Toast.makeText(getActivity(), "已上菜", Toast.LENGTH_LONG).show();
                    mMenudb.close();
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
        mList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < ClerkActivity.ClerkArr_IDOrder.size(); i++) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("_ID", ClerkActivity.ClerkArr_IDOrder.get(i));
            item.put("name", ClerkActivity.ClerkArrNameOrder.get(i));
            item.put("quantity", ClerkActivity.ClerkQuantity.get(i) + "份");
            mList.add(item);
        }
        SimpleAdapter adapter = new SimpleAdapter(getActivity().getApplicationContext(),
                mList, R.layout.clerkmenulayout,
                new String[]{"_ID", "name", "quantity"},
                new int[]{R.id.Clerklistid, R.id.Clerklistname, R.id.ClerkQuantity});
        setListAdapter(adapter);

    }


}
