package com.aiton.bamin.changtukepiao.Abusline.busline_aiton;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.aiton.bamin.changtukepiao.R;
import com.aiton.bamin.changtukepiao.Abusline.busline_aition_fragment.OffRemindFragment;
import com.aiton.bamin.changtukepiao.Abusline.busline_aition_fragment.RealTimeRemoteFragment;
import com.aiton.bamin.changtukepiao.Abusline.busline_aition_fragment.RechargeFragment;
import com.aiton.bamin.changtukepiao.Abusline.busline_aition_fragment.RoutePlaneFragment;

public class MainBusLineActivity extends AppCompatActivity {

    private FragmentTabHost mTabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_busline);

        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        View tab_menu_layout_nearby = getLayoutInflater().inflate(R.layout.tab_menu_layout_transfer, null);
        View tab_menu_layout_collection = getLayoutInflater().inflate(R.layout.tab_menu_layout_nearby, null);
        View tab_menu_layout_transfer = getLayoutInflater().inflate(R.layout.tab_menu_layout_collection, null);
        View tab_menu_layout_me = getLayoutInflater().inflate(R.layout.tab_menu_layout_me, null);

        mTabHost.addTab(mTabHost.newTabSpec("simple").setIndicator(tab_menu_layout_nearby), RoutePlaneFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("contacts").setIndicator(tab_menu_layout_collection), RealTimeRemoteFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("custom").setIndicator(tab_menu_layout_transfer), RechargeFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("throttle").setIndicator(tab_menu_layout_me), OffRemindFragment.class, null);

    }

}
