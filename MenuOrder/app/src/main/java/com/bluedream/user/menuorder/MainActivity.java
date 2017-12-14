package com.bluedream.user.menuorder;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bluedream.user.menuorder.providers.MenuDataBase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.TxtTitle)
    TextView TxtTitle;
    @BindView(R.id.btnCustomer)
    Button btnCustomer;
    @BindView(R.id.btnClerk)
    Button btnClerk;
    @BindView(R.id.btnManagement)
    Button btnManagement;
    Dialog loginDlg;
    EditText mPassword;
    SQLiteDatabase mMenuDB;
    MenuDataBase MenuDataBase= new MenuDataBase(MainActivity.this);
    SharedPreferences mangerPassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mMenuDB= MenuDataBase.OpenDatabase();
        passwordloader ();
    }

    private void passwordloader ()
    {
        mangerPassword =getSharedPreferences("mangerPassword",MODE_PRIVATE);
        dataChain passwordloader= new dataChain(mangerPassword);
        if (passwordloader.getMangerPassword().equals("")) {
            passwordloader.setMangerPassword("admin");
            AlertDialog.Builder firstuse =new AlertDialog.Builder(MainActivity.this);
            firstuse.setTitle("第一次使用");
            firstuse.setMessage("管理者密碼為admin");
            firstuse.setIcon(android.R.drawable.ic_dialog_info);
            firstuse.setCancelable(false);
            firstuse.setPositiveButton("好的", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            firstuse.show();
        }
    }



    @OnClick({R.id.btnCustomer, R.id.btnClerk, R.id.btnManagement})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnCustomer:
                break;
            case R.id.btnClerk:
                break;
            case R.id.btnManagement:
                loginDlg = new Dialog(MainActivity.this);
                loginDlg.setContentView(R.layout.logindialog);
                loginDlg.show();
                Button btnPasswordEnter =(Button)loginDlg.findViewById(R.id.btnPasswordEnter);
                Button btnPasswordCancle=(Button)loginDlg.findViewById(R.id.btnPasswordCancle);
                btnPasswordEnter.setOnClickListener(EnterPassword);
                btnPasswordCancle.setOnClickListener(EnterCancle);
                mPassword = (EditText)loginDlg.findViewById(R.id.edtPassword);
                break;
        }

    }

    private View.OnClickListener EnterPassword =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String password = new dataChain(mangerPassword).getMangerPassword();
            if(mPassword.getText().toString().equals(password)) {
                Intent it=new Intent();
                it.setClass(MainActivity.this,ManagerMode.class);
                startActivity(it);
                Toast.makeText(MainActivity.this, "登入成功", Toast.LENGTH_LONG).show();
            }
            else
                Toast.makeText(MainActivity.this,"密碼錯誤",Toast.LENGTH_LONG).show();

        }
    };

    private View.OnClickListener EnterCancle =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            loginDlg.cancel();
        }
    };


    @Override
    protected void onDestroy() {
        mMenuDB.close();
        super.onDestroy();
    }
}
