package com.aiton.bamin.changtukepiao.Ddaibanpaotui.activity_daibanpaotui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.aiton.bamin.changtukepiao.R;

public class PaoTuiPaiDuiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pao_tui_pai_dui);
    }
    public void xiayibu(View view){
        Intent intent=new Intent();
        intent.setClass(PaoTuiPaiDuiActivity.this, PaoTuiQuSongActivity.class);
        startActivity(intent);
    }
}
