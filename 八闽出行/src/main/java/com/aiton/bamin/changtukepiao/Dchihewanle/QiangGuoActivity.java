package com.aiton.bamin.changtukepiao.Dchihewanle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.aiton.bamin.changtukepiao.R;

public class QiangGuoActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qiang_guo);
    }

    public void btnback(View v)
    {
        finish();
    }

    public void btnconfirm(View v)
    {
        Toast.makeText(QiangGuoActivity.this,"跳转至支付页面",Toast.LENGTH_SHORT).show();
    }
}
