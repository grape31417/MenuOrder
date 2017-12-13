package com.bluedream.user.menuorder;

import android.app.Dialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mMenuDB= MenuDataBase.OpenDatabase();

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
            String password = new dataChain(getApplicationContext()).getMangerPassword();
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
