package com.bluedream.user.menuorder;


import android.content.ContentValues;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bluedream.user.menuorder.providers.MenuContentProvider;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.bluedream.user.menuorder.ManagerMode.mContRes;


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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btnAddMenu)
    public void onViewClicked() {
        ContentValues newRow = new ContentValues();
        newRow.put("name", edtMenuName.getText().toString());
        newRow.put("price", edtPrice.getText().length());
        mContRes.insert(MenuContentProvider.CONTENT_URI, newRow);
    }
}
