package com.bluedream.user.menuorder;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class customeraActivity extends AppCompatActivity {

    @BindView(R.id.btnMenuOrder)
    Button btnMenuOrder;
    Bundle menuOrder;
    Dialog announcementDlg;
    SharedPreferences mangerPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customera);
        ButterKnife.bind(this);
        mangerPassword = getSharedPreferences("mangerPassword", MODE_PRIVATE);
        Openannouncement();

    }

    private void Openannouncement() {
        announcementDlg = new Dialog(customeraActivity.this);
        announcementDlg.setContentView(R.layout.announcement);
        String AnnContent = new dataChain(mangerPassword).getAnnouncemen();
        Button btnOK = (Button) announcementDlg.findViewById(R.id.btnAnnOK);
        TextView txtAnnContent = (TextView) announcementDlg.findViewById(R.id.txtAnnContent);
        btnOK.setOnClickListener(BtnOK);
        txtAnnContent.setText(AnnContent);
        announcementDlg.show();
    }

    private View.OnClickListener BtnOK = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            announcementDlg.cancel();
        }
    };

    @OnClick(R.id.btnMenuOrder)
    public void onViewClicked() {
        AlertDialog.Builder checkOrder = new AlertDialog.Builder(customeraActivity.this);
        checkOrder.setTitle("訂單確認");
        checkOrder.setMessage("這些可以嗎");
        checkOrder.setIcon(android.R.drawable.ic_dialog_info);
        checkOrder.setCancelable(false);
        checkOrder.setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                menuOrder =new Bundle();
                menuOrder.putIntegerArrayList("orderID", customeFragment.Arr_IDOrder);
                menuOrder.putIntegerArrayList("quantity", customeFragment.Quantity);
                menuOrder.putStringArrayList("orderIDname",customeFragment.ArrNameOrder);
                Intent it =new Intent();
                it.putExtras(menuOrder);
                it.setClass(customeraActivity.this,ClerkActivity.class);
                startActivity(it);
                customeFragment.Arr_IDOrder.clear();
                customeFragment.Quantity.clear();
                customeFragment.ArrNameOrder.clear();
                finish();
            }
        });
        checkOrder.setNeutralButton("否", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        checkOrder.show();


    }
}
