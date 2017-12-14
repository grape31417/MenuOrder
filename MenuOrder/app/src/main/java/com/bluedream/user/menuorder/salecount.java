package com.bluedream.user.menuorder;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bluedream.user.menuorder.providers.MenuDataBase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class salecount extends Fragment {
    SQLiteDatabase mMenuDB;
    @BindView(R.id.textview)
    TextView textview;
    @BindView(R.id.txtAllCount)
    TextView txtAllCount;
    @BindView(R.id.btnClearSales)
    Button btnClearSales;
    @BindView(R.id.btnSave)
    Button btnSave;
    Unbinder unbinder;
    SQLiteDatabase mMenudb;
    com.bluedream.user.menuorder.providers.MenuDataBase MenuDataBase;
    int getAllMoney =0;


    public salecount() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_salecount, container, false);
        unbinder = ButterKnife.bind(this, view);
        getAllmoney();

        return view;
    }

    private void getAllmoney()
    {
        getAllMoney=0;
        MenuDataBase = new MenuDataBase(getActivity());
        mMenudb =MenuDataBase.OpenDatabase();
        Cursor c = MenuDataBase.query(true, true, true, true, true);
        if (c.getCount() == 0) {
            Toast.makeText(getActivity(), "沒有資料", Toast.LENGTH_LONG).show();
        } else {
            c.moveToFirst();
            for (int i = 1; i <= c.getCount(); i++) {
                getAllMoney=getAllMoney+((c.getInt(2) - c.getInt(3)) * c.getInt(4));
                c.moveToNext();
            }
        }
        mMenudb.close();
        c.close();
    }

    @Override
    public void onResume() {
        super.onResume();
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

        txtAllCount.setText("總銷售額:"+getAllMoney+"元");

    }
    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.btnClearSales, R.id.btnSave})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnClearSales:
                break;
            case R.id.btnSave:
                break;
        }
    }
}
