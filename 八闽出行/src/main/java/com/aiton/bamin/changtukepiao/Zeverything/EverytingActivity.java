package com.aiton.bamin.changtukepiao.Zeverything;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aiton.administrator.shane_library.shane.upgrade.UpgradeUtils;
import com.aiton.administrator.shane_library.shane.utils.GsonUtils;
import com.aiton.administrator.shane_library.shane.utils.HTTPUtils;
import com.aiton.administrator.shane_library.shane.utils.VolleyListener;
import com.aiton.bamin.changtukepiao.Bchangtukepiao.constant.ConstantTicket;
import com.aiton.bamin.changtukepiao.Bchangtukepiao.models.VersionAndHouTaiIsCanUse;
import com.aiton.bamin.changtukepiao.R;
import com.aiton.bamin.changtukepiao.Zeverything.constant.EverythingConstant;
import com.aiton.bamin.changtukepiao.Zeverything.everything_fragment.CardEverytingFragment;
import com.aiton.bamin.changtukepiao.Zeverything.everything_fragment.MainEverytingFragment;
import com.aiton.bamin.changtukepiao.Zeverything.everything_fragment.MineEverythingFragment;
import com.aiton.bamin.changtukepiao.Zeverything.everything_fragment.OrderEverythingFragment;
import com.android.volley.VolleyError;
import com.igexin.sdk.PushManager;

import java.util.HashMap;
import java.util.Map;

public class EverytingActivity extends AppCompatActivity
{
    private Class[] fragment = new Class[]{
            MainEverytingFragment.class,
            CardEverytingFragment.class,
            OrderEverythingFragment.class,
            MineEverythingFragment.class
    };
    private int[] imgRes = new int[]{
            R.drawable.ic_everyting_home_index_selector,
            R.drawable.ic_everyting_home_card_selector,
            R.drawable.ic_everyting_home_order_selector,
            R.drawable.ic_everyting_home_me_selector
    };
    private FragmentTabHost mTabHost;
    private String mId;
    private String mDeviceId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_everyting);
        Log.e("onCreate ", "EverytingActivity ");
        //检查是否在其他设备上登陆登录
        SharedPreferences sp = getSharedPreferences("isLogin", Context.MODE_PRIVATE);
        mId = sp.getString("id", "");
        mDeviceId = sp.getString("DeviceId", "");
        Log.e("onCreate ", "mDeviceId "+mDeviceId);
        //检查服务器是否存活和当前版本是否可用
        checkVersionAndHouTaiIsCanUse();
//        开启个推推送
        PushManager.getInstance().initialize(this.getApplicationContext());
        mTabHost = (FragmentTabHost) findViewById(R.id.tabHost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtab);

        for (int i = 0; i < imgRes.length; i++)
        {
            View inflate = getLayoutInflater().inflate(R.layout.tabs_item_everyting, null);
            ImageView tabs_img = (ImageView) inflate.findViewById(R.id.tabs_img);
            tabs_img.setImageResource(imgRes[i]);
            mTabHost.addTab(mTabHost.newTabSpec(""+i).setIndicator(inflate), fragment[i], null);
        }
    }

    private void checkVersionAndHouTaiIsCanUse() {
        String url = EverythingConstant.HOST + "/bmpw/check/live";
        Map<String, String> map = new HashMap<>();
        HTTPUtils.post(EverytingActivity.this, url, map, new VolleyListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                setDialogCkeck("服务器正在升级，暂停服务","确认");
            }

            @Override
            public void onResponse(String s) {
                VersionAndHouTaiIsCanUse versionAndHouTaiIsCanUse = GsonUtils.parseJSON(s, VersionAndHouTaiIsCanUse.class);
                int ableVersion = versionAndHouTaiIsCanUse.getAbleVersion();
                if (versionAndHouTaiIsCanUse.isLive()){
                    if (EverythingConstant.ABLEVERSION <ableVersion){
                        setDialogCkeck("当前版本不可用，请去应用商店下载最新版本","确认");
                    }else {
                        checkUpGrade();
                        //        检查是否在其他设备上登陆
                        checkIsLoginOnOtherDevice();
                    }
                }else {
                    setDialogCkeck(versionAndHouTaiIsCanUse.getMessage(),"确认");
                }
            }
        });
    }
    private void checkUpGrade()
    {
        UpgradeUtils.checkUpgrade(EverytingActivity.this, ConstantTicket.URL.UP_GRADE);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode== 0){
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                UpgradeUtils.checkUpgrade(this, ConstantTicket.URL.UP_GRADE);
            }else{
            }
        }
    }
/**
 * 双击退出应用
 */
    private long currentTime = 0;
    public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
        if(keyCode== KeyEvent.KEYCODE_BACK){
            if(System.currentTimeMillis()-currentTime>1000){
                Toast toast = Toast.makeText(EverytingActivity.this, "双击退出应用", Toast.LENGTH_SHORT);
                toast.show();
                currentTime=System.currentTimeMillis();
                return false;
            }else{
                return super.onKeyDown(keyCode, event);
            }
        }
        return super.onKeyDown(keyCode, event);
    };
    /**
     * 检查是否在其他设备上登陆
     */
    private void checkIsLoginOnOtherDevice() {
        if (!"".equals(mDeviceId)) {
            String url = EverythingConstant.HOST + "/bmpw/account/findLogin_id";
//            String url = "https://218.5.80.24:3061/api/Busline/Get";
            Map<String, String> map = new HashMap<>();
            map.put("account_id", mId);
            HTTPUtils.post(EverytingActivity.this, url, map, new VolleyListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                }

                @Override
                public void onResponse(String s) {
                    Log.e("onResponse ", "服务器上的mDeviceId"+s);
                    if (!s.equals(mDeviceId)) {
                        setDialog("你的账号登录异常\n请重新登录", "确定");
                    }
                }
            });
        }
    }
    //dialog提示
    private void setDialog(String messageTxt, String iSeeTxt) {
        View commit_dialog = getLayoutInflater().inflate(R.layout.commit_dialog, null);
        TextView message = (TextView) commit_dialog.findViewById(R.id.message);
        Button ISee = (Button) commit_dialog.findViewById(R.id.ISee);
        message.setText(messageTxt);
        ISee.setText(iSeeTxt);
        AlertDialog.Builder builder = new AlertDialog.Builder(EverytingActivity.this);
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
                intent.setClass(EverytingActivity.this, SmsLoginActivity.class);
                startActivity(intent);
            }
        });
    }//dialog提示
    private void setDialogCkeck(String messageTxt, String iSeeTxt) {
        View commit_dialog = getLayoutInflater().inflate(R.layout.commit_dialog, null);
        TextView message = (TextView) commit_dialog.findViewById(R.id.message);
        Button ISee = (Button) commit_dialog.findViewById(R.id.ISee);
        message.setText(messageTxt);
        ISee.setText(iSeeTxt);
        AlertDialog.Builder builder = new AlertDialog.Builder(EverytingActivity.this);
        final AlertDialog dialog = builder.setView(commit_dialog)
                .create();
        dialog.setCancelable(false);
        dialog.show();
        commit_dialog.findViewById(R.id.ISee).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
