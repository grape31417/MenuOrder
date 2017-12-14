package com.bluedream.user.menuorder;


import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class announcementEdit extends Fragment {


    @BindView(R.id.textview)
    TextView textview;

    @BindView(R.id.btnEditAnnouncement)
    Button btnEditAnnouncement;
    @BindView(R.id.btnView)
    Button btnView;
    Dialog announcementDlg;
    @BindView(R.id.edtAnnouncement)
    EditText edtAnnouncement;
    Unbinder unbinder;

    public announcementEdit() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_announcement_edit, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onResume() {
        super.onResume();
        MenuCost menuCost =new MenuCost();
        menuCost.onPause();
        ManagerPasswordChange ManagerPasswordChange =new ManagerPasswordChange();
        ManagerPasswordChange.onPause();
        salecount salecount =new salecount();
        salecount.onPause();
        MenuEdit MenuEdit =new MenuEdit();
        MenuEdit.onPause();
        Menu Menu =new Menu();
        Menu.onPause();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @OnClick({R.id.btnEditAnnouncement, R.id.btnView})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnEditAnnouncement:
                String InPutAnnContent =edtAnnouncement.getText().toString();
                new dataChain(ManagerMode.mangerPassword).setAnnouncemen(InPutAnnContent);
                Toast.makeText(getActivity(),"修改完畢",Toast.LENGTH_LONG).show();
                break;
            case R.id.btnView:
                announcementDlg = new Dialog(getActivity());
                announcementDlg.setContentView(R.layout.announcement);
                String AnnContent = new dataChain(ManagerMode.mangerPassword).getAnnouncemen();
                Button btnOK =(Button) announcementDlg.findViewById(R.id.btnAnnOK);
                TextView txtAnnContent =(TextView)announcementDlg.findViewById(R.id.txtAnnContent);
                btnOK.setOnClickListener(BtnOK);
                txtAnnContent.setText(AnnContent);
                announcementDlg.show();
                break;
        }
    }

    private View.OnClickListener BtnOK =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            announcementDlg.cancel();
        }
    };

}
