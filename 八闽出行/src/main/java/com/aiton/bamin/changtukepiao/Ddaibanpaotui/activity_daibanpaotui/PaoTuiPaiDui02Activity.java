package com.aiton.bamin.changtukepiao.Ddaibanpaotui.activity_daibanpaotui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.aiton.bamin.changtukepiao.R;

public class PaoTuiPaiDui02Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pao_tui_pai_dui02);
    }
    public void zhifu(View view){
        Intent intent=new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setClass(PaoTuiPaiDui02Activity.this, PaoTuiActivity.class);
        startActivity(intent);
    }
}
