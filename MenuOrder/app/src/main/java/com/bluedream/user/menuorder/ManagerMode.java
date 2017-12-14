package com.bluedream.user.menuorder;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;


public class ManagerMode extends AppCompatActivity {
    static SharedPreferences mangerPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_mode);
        setScrowview ();
        mangerPassword =getSharedPreferences("mangerPassword",MODE_PRIVATE);
    }

    public void setScrowview ()
    {
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add("菜單預覽", Menu.class)
                .add("公告編輯",announcementEdit.class)
                .add("編輯菜單", MenuEdit.class)
                .add("收益計算",salecount.class)
                .add("重設管理密碼", ManagerPasswordChange.class)
                .create());

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);

        SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);
        viewPagerTab.setViewPager(viewPager);
    }
}
