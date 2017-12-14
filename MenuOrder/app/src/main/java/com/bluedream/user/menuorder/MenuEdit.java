package com.bluedream.user.menuorder;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bluedream.user.menuorder.providers.MenuDataBase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.bluedream.user.menuorder.providers.MenuDataBase.DB_TABLE;


/**
 * A simple {@link Fragment} subclass.
 */
public class MenuEdit extends Fragment {


    @BindView(R.id.textview)
    TextView textview;
    @BindView(R.id.edtMenuName)
    EditText edtMenuName;
    @BindView(R.id.edtPrice)
    EditText edtPrice;
    @BindView(R.id.btnAddMenu)
    Button btnAddMenu;
    Unbinder unbinder;
    @BindView(R.id.btnDeleteMenu)
    Button btnDeleteMenu;
    SQLiteDatabase mMenuDB;
    @BindView(R.id.edtCost)
    EditText edtCost;

    @Override
    public void onPause() {
        super.onPause();
    }

    public MenuEdit() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu_edit, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
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
        salecount salecount =new salecount();
        salecount.onPause();
        Menu Menu =new Menu();
        Menu.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btnAddMenu)
    public void onViewClickedbtnAddMenu() {
        mMenuDB = new MenuDataBase(getActivity()).OpenDatabase();
        if (edtMenuName.getText().toString().equals("") || edtPrice.getText().toString().equals("") || edtCost.getText().toString().equals("")) {
            Toast.makeText(getContext(), "有欄位為空", Toast.LENGTH_LONG).show();
        } else {
            ContentValues newRow = new ContentValues();
            newRow.put("name", edtMenuName.getText().toString());
            newRow.put("price", Integer.valueOf(edtPrice.getText().toString()));
            newRow.put("cost", Integer.valueOf(edtCost.getText().toString()));
            newRow.put("sales",0);
            mMenuDB.insert(DB_TABLE, null, newRow);
        }

        edtMenuName.setText("");
        edtPrice.setText("");
        edtCost.setText("");
        mMenuDB.close();

    }

    @OnClick(R.id.btnDeleteMenu)
    public void onViewClickedbtnDeleteMenu() {
        final String DROP_TABLE = "DROP TABLE IF EXISTS " + DB_TABLE;
        mMenuDB.execSQL(DROP_TABLE);
        mMenuDB.execSQL("CREATE TABLE " + DB_TABLE + " (" +
                "_id INTEGER PRIMARY KEY," +
                "name TEXT NOT NULL," +
                "price INTEGER NOT NULL," +
                "cost INTEGER NOT NULL," +
                "sales INTEGER NOT NULL);");
    }


}
