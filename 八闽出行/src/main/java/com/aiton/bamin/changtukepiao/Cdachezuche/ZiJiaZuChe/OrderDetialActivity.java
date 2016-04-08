package com.aiton.bamin.changtukepiao.Cdachezuche.ZiJiaZuChe;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.aiton.bamin.changtukepiao.Cdachezuche.DaCheZhuCheActivity.DaCheZuCheMainActivity;
import com.aiton.bamin.changtukepiao.Cdachezuche.constant_dachezuche.ConstantDaCheZuChe;
import com.aiton.bamin.changtukepiao.Cdachezuche.models.CarInfoList;
import com.aiton.bamin.changtukepiao.Cdachezuche.models.ZiJiaZuCheChooseCityDate;
import com.aiton.bamin.changtukepiao.Cdachezuche.models.ZiJiaZuCheOrderDetial;
import com.aiton.bamin.changtukepiao.R;

public class OrderDetialActivity extends AppCompatActivity implements View.OnClickListener {

    private ZiJiaZuCheOrderDetial mZiJiaZuCheOrderDetial;
    private TextView mTextView_price;
    private TextView mTextView_yushouquan;
    private TextView mTv_car_name;
    private TextView mTv_carriage_count;
    private TextView mTv_displacement;
    private TextView mTv_car_seat_count;
    private TextView mTv_dache_get_time_date;
    private TextView mTv_dache_get_time_time;
    private TextView mTv_dache_return_time_date;
    private TextView mTv_dache_return_time_time;
    private TextView mTv_dache_jg_store_name_get;
    private TextView mTv_dache_jg_store_name_return;
    private TextView mTextView_cheliangPrice;
    private TextView mTextView_jibenbaoxianfei;
    private TextView mTextView_allprice;
    private CarInfoList.ContainsEntity mCarInfoList_containsEntity;
    private ZiJiaZuCheChooseCityDate mZiJiaZuCheChooseCityDate;
    private double mCarPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detial);
        initIntent();
        findID();
        initUI();
        setListener();
    }

    private void initUI() {
        mTextView_price.setText("¥" + mZiJiaZuCheOrderDetial.getPrice());
        mTextView_yushouquan.setText("¥" + mCarInfoList_containsEntity.getPlan().getUnitMileage());
        mTv_car_name.setText(mCarInfoList_containsEntity.getModel() + mCarInfoList_containsEntity.getType());
        mTv_carriage_count.setText(mCarInfoList_containsEntity.getBox());
        if (mCarInfoList_containsEntity.getZidong() == 0) {
            mTv_displacement.setText(mCarInfoList_containsEntity.getPailiang() + "自动");
        } else {
            mTv_displacement.setText(mCarInfoList_containsEntity.getPailiang() + "手动");
        }
        mTv_car_seat_count.setText("乘坐" + mCarInfoList_containsEntity.getSeat() + "人");
        mTv_dache_get_time_date.setText(mZiJiaZuCheChooseCityDate.getStartDate());
        mTv_dache_get_time_time.setText(mZiJiaZuCheChooseCityDate.getStartTime());
        mTv_dache_return_time_date.setText(mZiJiaZuCheChooseCityDate.getEndDate());
        mTv_dache_return_time_time.setText(mZiJiaZuCheChooseCityDate.getEndTime());
        mTv_dache_jg_store_name_get.setText(mZiJiaZuCheChooseCityDate.getTakeCarStore());
        mTv_dache_jg_store_name_return.setText(mZiJiaZuCheChooseCityDate.getReturnCarStore());
        mTextView_cheliangPrice.setText("¥" + mCarPrice);
        mTextView_jibenbaoxianfei.setText(mCarInfoList_containsEntity.getPlan().getInsurance() + "");
        mTextView_allprice.setText(mZiJiaZuCheOrderDetial.getPrice() + "");
    }

    private void findID() {
        mTextView_price = (TextView) findViewById(R.id.textView_price);
        mTextView_yushouquan = (TextView) findViewById(R.id.textView_yushouquan);
        mTv_car_name = (TextView) findViewById(R.id.tv_car_name);
        mTv_carriage_count = (TextView) findViewById(R.id.tv_carriage_count);
        mTv_displacement = (TextView) findViewById(R.id.tv_displacement);
        mTv_car_seat_count = (TextView) findViewById(R.id.tv_car_seat_count);
        mTv_dache_get_time_date = (TextView) findViewById(R.id.tv_dache_get_time_date);
        mTv_dache_get_time_time = (TextView) findViewById(R.id.tv_dache_get_time_time);
        mTv_dache_return_time_date = (TextView) findViewById(R.id.tv_dache_return_time_date);
        mTv_dache_return_time_time = (TextView) findViewById(R.id.tv_dache_return_time_time);
        mTv_dache_jg_store_name_get = (TextView) findViewById(R.id.tv_dache_jg_store_name_get);
        mTv_dache_jg_store_name_return = (TextView) findViewById(R.id.tv_dache_jg_store_name_return);
        mTextView_cheliangPrice = (TextView) findViewById(R.id.textView_cheliangPrice);
        mTextView_jibenbaoxianfei = (TextView) findViewById(R.id.textView_jibenbaoxianfei);
        mTextView_allprice = (TextView) findViewById(R.id.textView_Allprice);
    }

    private void initIntent() {
        Intent intent = getIntent();
        mCarPrice = intent.getDoubleExtra("mCarPrice", 0);
        mZiJiaZuCheOrderDetial = (ZiJiaZuCheOrderDetial) intent.getSerializableExtra("ziJiaZuCheOrderDetial");
        mCarInfoList_containsEntity = (CarInfoList.ContainsEntity) intent.getSerializableExtra("mCarInfoList_containsEntity");
        mZiJiaZuCheChooseCityDate = (ZiJiaZuCheChooseCityDate) intent.getSerializableExtra("mZiJiaZuCheChooseCityDate");
    }

    private void setListener() {
        findViewById(R.id.button_cancle_order).setOnClickListener(this);
        findViewById(R.id.imageView_back).setOnClickListener(this);
        findViewById(R.id.button_orderList).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_cancle_order:
                View doublebuttondialog = getLayoutInflater().inflate(R.layout.doublebuttondialog, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                Button ISee = (Button) doublebuttondialog.findViewById(R.id.ISee);
                Button button_cancle = (Button) doublebuttondialog.findViewById(R.id.button_cancle);
                TextView message = (TextView) doublebuttondialog.findViewById(R.id.message);
                message.setText("您确认取消订单？");
                ISee.setText("确认取消");
                button_cancle.setText("暂不取消");
                builder.setView(doublebuttondialog);
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();
                ISee.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO: 2016/3/23 确认取消订单操作
                    }
                });
                button_cancle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
                break;
            case R.id.imageView_back:
                startToMainActivity();
                break;
            case R.id.button_orderList:
                startToMainActivity();
                break;
        }
    }

    private void startToMainActivity() {
        Intent intent= new Intent();
        intent.putExtra(ConstantDaCheZuChe.IntentKey.BACK_TO_ORDER_LIST_KEY,ConstantDaCheZuChe.IntentKey.ZI_JIA_ZU_CHE_BACK_INT);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setClass(this, DaCheZuCheMainActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            startToMainActivity();
        }
        return super.onKeyDown(keyCode, event);
    }
}
