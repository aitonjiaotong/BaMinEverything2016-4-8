package com.aiton.bamin.changtukepiao.Cdachezuche.DaCheZhuCheActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.aiton.bamin.changtukepiao.R;

public class UsedAdressActivity extends AppCompatActivity implements View.OnClickListener
{

    private ImageView mImageView_back;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_used_adress);
        findID();
        initUI();
        setListener();
    }

    private void findID()
    {
        mImageView_back = (ImageView) findViewById(R.id.imageView_back);
    }

    private void setListener()
    {
        mImageView_back.setOnClickListener(this);
    }

    private void initUI()
    {

    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.imageView_back:
                finish();
                break;
        }
    }
}
