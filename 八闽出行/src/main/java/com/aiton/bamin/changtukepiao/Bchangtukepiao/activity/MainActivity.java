package com.aiton.bamin.changtukepiao.Bchangtukepiao.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.aiton.administrator.shane_library.shane.utils.HTTPUtils;
import com.aiton.administrator.shane_library.shane.utils.VolleyListener;
import com.aiton.bamin.changtukepiao.Bchangtukepiao.fragment.Fragment01;
import com.aiton.bamin.changtukepiao.Bchangtukepiao.fragment.Fragment0201;
import com.aiton.bamin.changtukepiao.Bchangtukepiao.fragment.MoreFragment;
import com.aiton.bamin.changtukepiao.R;
import com.aiton.bamin.changtukepiao.Zeverything.constant.EverythingConstant;
import com.aiton.bamin.changtukepiao.Zeverything.SmsLoginActivity;
import com.android.volley.VolleyError;
import com.umeng.analytics.MobclickAgent;

import java.util.HashMap;
import java.util.Map;

/**
 * 优化1.0
 */
public class MainActivity extends AppCompatActivity {
    private String[] tabsItem = new String[]{
            "查询",
            "订单",
            "更多"
    };
    private Class[] fragment = new Class[]{
            Fragment01.class,
            Fragment0201.class,
            MoreFragment.class
    };
    private int[] imgRes = new int[]{
            R.drawable.ic_home_search_selector,
            R.drawable.dingdan_selector,
            R.drawable.gengduo_selector
    };
    private FragmentTabHost mTabHost;
    private String mDeviceId;
    private String mId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initSp();
        checkIsLoginOnOtherDevice();
        mTabHost = (FragmentTabHost) findViewById(R.id.tabHost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtab);
        for (int i = 0; i < tabsItem.length; i++) {
            View inflate = getLayoutInflater().inflate(R.layout.tabs_item, null);
            TextView tabs_text = (TextView) inflate.findViewById(R.id.tabs_text);
            ImageView tabs_img = (ImageView) inflate.findViewById(R.id.tabs_img);
            tabs_text.setText(tabsItem[i]);
            tabs_img.setImageResource(imgRes[i]);
            mTabHost.addTab(mTabHost.newTabSpec(tabsItem[i]).setIndicator(inflate), fragment[i], null);
        }
        //设置当前tabs
        Intent intent = getIntent();
        String orderDeatilActivity = intent.getStringExtra("OrderDeatilActivity");
        if ("OrderDeatilActivity".equals(orderDeatilActivity)) {
            mTabHost.setCurrentTab(1);
        }
    }

    private void initSp() {
        SharedPreferences sp = getSharedPreferences("isLogin", Context.MODE_PRIVATE);
        mId = sp.getString("id", "");
        mDeviceId = sp.getString("DeviceId", "");
    }

    /**
     * 检查是否在其他设备上登陆
     */
    private void checkIsLoginOnOtherDevice() {
        if (!"".equals(mDeviceId)) {
            String url = EverythingConstant.HOST + "/bmpw/account/findLogin_id";
            Map<String, String> map = new HashMap<>();
            map.put("account_id", mId);
            HTTPUtils.post(MainActivity.this, url, map, new VolleyListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                }

                @Override
                public void onResponse(String s) {
                    if (!s.equals(mDeviceId)) {
                        setDialog("你的账号登录异常\n请重新登录", "确定");
                    }
                }
            });
        }
    }

    /**
     * 弹出未登录按钮跳转登录界面并清除登录信息
     * @param messageTxt
     * @param iSeeTxt
     */
    private void setDialog(String messageTxt, String iSeeTxt) {
        View commit_dialog = getLayoutInflater().inflate(R.layout.commit_dialog, null);
        TextView message = (TextView) commit_dialog.findViewById(R.id.message);
        Button ISee = (Button) commit_dialog.findViewById(R.id.ISee);
        message.setText(messageTxt);
        ISee.setText(iSeeTxt);
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        final AlertDialog dialog = builder.setView(commit_dialog)
                .create();
        dialog.setCancelable(false);
        dialog.show();
        commit_dialog.findViewById(R.id.ISee).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                //清除用户登录信息
                SharedPreferences sp = getSharedPreferences("isLogin", MODE_PRIVATE);
                SharedPreferences.Editor edit = sp.edit();
                edit.clear();
                edit.commit();
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, SmsLoginActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            animFromBigToSmallOUT();
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 从大到小结束动画
     */
    private void animFromBigToSmallOUT() {
        overridePendingTransition(R.anim.fade_in, R.anim.big_to_small_fade_out);
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
