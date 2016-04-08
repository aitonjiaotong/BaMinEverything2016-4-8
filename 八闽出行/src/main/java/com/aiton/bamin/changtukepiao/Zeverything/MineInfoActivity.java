package com.aiton.bamin.changtukepiao.Zeverything;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.aiton.bamin.changtukepiao.R;

import cn.smssdk.SMSSDK;

public class MineInfoActivity extends AppCompatActivity implements View.OnClickListener {

    private String mPhoneNum;
    private TextView mTextView_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_info);
        SMSSDK.initSDK(this, "ee115b63d2b6", "243a1d6a31d6832bb156c85a48da0fea");
        initSp();
        findID();
        initUI();
        setListener();
    }

    private void setListener() {
        findViewById(R.id.findBackPassword).setOnClickListener(this);
        findViewById(R.id.button_zuxiao).setOnClickListener(this);
        findViewById(R.id.imageView_back).setOnClickListener(this);
    }

    private void initUI() {
        mTextView_phone.setText(mPhoneNum);
    }

    private void findID() {
        mTextView_phone = (TextView) findViewById(R.id.textView_phone);
    }

    private void initSp() {
        SharedPreferences sp = getSharedPreferences("isLogin", Context.MODE_PRIVATE);
        mPhoneNum = sp.getString("phoneNum", "");
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.findBackPassword:
                intent.setClass(MineInfoActivity.this,UpdatePasswordActivity.class);
                intent.putExtra("findBackPassword","findBackPassword");
                startActivity(intent);
                break;
            case R.id.button_zuxiao:
                AlertDialog.Builder builder = new AlertDialog.Builder(MineInfoActivity.this);
                builder.setTitle("提醒");
                builder.setMessage("确定要退出吗？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences sp = getSharedPreferences("isLogin", Context.MODE_PRIVATE);
                        SharedPreferences.Editor edit = sp.edit();
                        edit.clear();
                        edit.commit();
                        finish();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                break;
            case R.id.imageView_back:
                finish();
                break;
        }
    }
}
