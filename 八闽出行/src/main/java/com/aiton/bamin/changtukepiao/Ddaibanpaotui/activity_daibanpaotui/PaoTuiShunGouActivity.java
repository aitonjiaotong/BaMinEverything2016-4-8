package com.aiton.bamin.changtukepiao.Ddaibanpaotui.activity_daibanpaotui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.aiton.bamin.changtukepiao.R;

public class PaoTuiShunGouActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pao_tui_shun_gou);
    }
    public void gouwuche(View view){
        Intent intent=new Intent();
        intent.setClass(PaoTuiShunGouActivity.this, GouWuCheActivity.class);
        startActivity(intent);
    }
}
