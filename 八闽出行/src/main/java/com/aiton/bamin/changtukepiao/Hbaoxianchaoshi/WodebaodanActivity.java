package com.aiton.bamin.changtukepiao.Hbaoxianchaoshi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.aiton.bamin.changtukepiao.R;

public class WodebaodanActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wodebaodan);
    }
    public void back(View view){
        finish();
    }
}
