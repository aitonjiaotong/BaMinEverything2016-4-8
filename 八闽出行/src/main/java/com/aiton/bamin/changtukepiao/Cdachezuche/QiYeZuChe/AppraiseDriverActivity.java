package com.aiton.bamin.changtukepiao.Cdachezuche.QiYeZuChe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aiton.administrator.shane_library.shane.utils.UILUtils;
import com.aiton.bamin.changtukepiao.Cdachezuche.models.OrderDetailsInfo;
import com.aiton.bamin.changtukepiao.R;

public class AppraiseDriverActivity extends AppCompatActivity implements View.OnClickListener
{

    private ImageView mIv_back;
    private ImageView mIv_driver_img;
    private ImageView mIv_pj_driver_sex_img;
    private TextView mTv_driver_name;
    private TextView mTv_driver_driving_years;
    private OrderDetailsInfo.DriverEntity mDriverInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appraise_driver);
        getIntentData();
        findViewID();
        initUI();
        setListener();
    }

    private void getIntentData()
    {
        Intent intent = getIntent();
        mDriverInfo = (OrderDetailsInfo.DriverEntity) intent.getSerializableExtra("driver");
    }

    private void findViewID()
    {
        mIv_back = (ImageView) findViewById(R.id.iv_back);
        mIv_driver_img = (ImageView) findViewById(R.id.iv_driver_img);
        mIv_pj_driver_sex_img = (ImageView) findViewById(R.id.iv_pj_driver_sex_img);
        mTv_driver_name = (TextView) findViewById(R.id.tv_driver_name);
        mTv_driver_driving_years = (TextView) findViewById(R.id.tv_driver_driving_years);
    }

    private void initUI()
    {
        UILUtils.displayImageNoAnim(mDriverInfo.getImage(), mIv_driver_img);
        mTv_driver_name.setText(mDriverInfo.getName());
        if (!"男".equals(mDriverInfo.getSex()))
        {
            mIv_pj_driver_sex_img.setImageResource(R.mipmap.xingbienv_2x);
        }
        mTv_driver_driving_years.setText(mDriverInfo.getDrivingYear() + "");
    }

    private void setListener()
    {
        mIv_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.iv_back:
                finish();
                break;

        }
    }
}
