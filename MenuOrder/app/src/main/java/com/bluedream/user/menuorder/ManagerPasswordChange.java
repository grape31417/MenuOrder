package com.bluedream.user.menuorder;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class ManagerPasswordChange extends Fragment {
    @BindView(R.id.edtOrignalPassword)
    EditText edtOrignalPassword;
    @BindView(R.id.edtNewPassword)
    EditText edtNewPassword;
    @BindView(R.id.edtConfirmNewPassword)
    EditText edtConfirmNewPassword;
    @BindView(R.id.btnAddMenu)
    Button btnAddMenu;
    Unbinder unbinder;


    public ManagerPasswordChange() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_manager_password_change, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        MenuCost menuCost =new MenuCost();
        menuCost.onPause();
        announcementEdit announcementEdit =new announcementEdit();
        announcementEdit.onPause();
        salecount salecount =new salecount();
        salecount.onPause();
        MenuEdit MenuEdit =new MenuEdit();
        MenuEdit.onPause();
        Menu Menu =new Menu();
        Menu.onPause();

    }

    @OnClick(R.id.btnAddMenu)
    public void onViewClicked() {

        String password = new dataChain(ManagerMode.mangerPassword).getMangerPassword();
        String oldpassword = edtOrignalPassword.getText().toString();
        String newpassword = edtNewPassword.getText().toString();
        String confirmnewpassword = edtConfirmNewPassword.getText().toString();


        if (password.equals(oldpassword) && newpassword.equals(confirmnewpassword))
        {

            new dataChain(ManagerMode.mangerPassword).setMangerPassword(newpassword);
            Toast.makeText(getActivity(), "變更成功", Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(getActivity(), "請確認密碼", Toast.LENGTH_LONG).show();


    }
}
