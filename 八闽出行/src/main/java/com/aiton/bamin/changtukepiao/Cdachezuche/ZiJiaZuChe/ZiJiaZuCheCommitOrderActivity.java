package com.aiton.bamin.changtukepiao.Cdachezuche.ZiJiaZuChe;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aiton.administrator.shane_library.shane.utils.HTTPUtils;
import com.aiton.administrator.shane_library.shane.utils.UILUtils;
import com.aiton.administrator.shane_library.shane.utils.VolleyListener;
import com.aiton.bamin.changtukepiao.Cdachezuche.QiYeZuChe.ZuCheJiGuoOrderDetailActivity;
import com.aiton.bamin.changtukepiao.Cdachezuche.models.CarInfoList;
import com.aiton.bamin.changtukepiao.Cdachezuche.models.ZiJiaZuCheChooseCityDate;
import com.aiton.bamin.changtukepiao.R;
import com.aiton.bamin.changtukepiao.Zeverything.constant.EverythingConstant;
import com.android.volley.VolleyError;

import java.util.HashMap;
import java.util.Map;

public class ZiJiaZuCheCommitOrderActivity extends AppCompatActivity implements View.OnClickListener {

    private CarInfoList.ContainsEntity mCarInfoList_containsEntity;
    private ZiJiaZuCheChooseCityDate mZiJiaZuCheChooseCityDate;
    private ImageView mIv_car_img;
    private TextView mTv_car_name;
    private TextView mTv_carriage_count;
    private TextView mTv_displacement;
    private TextView mTv_car_seat_count;
    private TextView mTv_dache_get_time_date;
    private TextView mTv_dache_get_time_time;
    private TextView mTv_dache_how_long;
    private TextView mTv_dache_return_time_date;
    private TextView mTv_dache_return_time_time;
    private TextView mTv_dache_jg_store_name_get;
    private TextView mTv_dache_jg_store_name_return;
    //手续费
    private double shouxufei;
    //基本保险费
    private double jibenbaoxianfe;
    //不挤免赔服务费
    private double bujimianpei;
    //是否不计免赔
    private boolean isBujimianpei = true;
    private String mPhoneNum;
    private String mId;
    private double mCarPrice;
    private TextView mTextView_shouxufei;
    private TextView mTextView_jibenbaoxianfei;
    private TextView mTextView_bujimianpei;
    //防止多次提交订单
//    private boolean isCommitOrder = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zi_jia_zu_che_commit_order);
        initSharedPreferences();
        initIntent();
        findID();
        initUI();
        setListener();
    }

    private void initSharedPreferences() {
        /**
         * 获取用户id
         */
        SharedPreferences sp = getSharedPreferences("isLogin", Context.MODE_PRIVATE);
        mPhoneNum = sp.getString("phoneNum", "");
        mId = sp.getString("id", "");
    }

    private void initUI() {
        UILUtils.displayImageNoAnim(mCarInfoList_containsEntity.getImage(), mIv_car_img);
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
        mTv_dache_how_long.setText(mZiJiaZuCheChooseCityDate.getDayCounts() + "");
        mTv_dache_return_time_date.setText(mZiJiaZuCheChooseCityDate.getEndDate());
        mTv_dache_return_time_time.setText(mZiJiaZuCheChooseCityDate.getEndTime());
        mTv_dache_jg_store_name_get.setText(mZiJiaZuCheChooseCityDate.getTakeCarStore());
        mTv_dache_jg_store_name_return.setText(mZiJiaZuCheChooseCityDate.getReturnCarStore());
        mTextView_shouxufei.setText("¥" + shouxufei);
        mTextView_jibenbaoxianfei.setText("¥" + jibenbaoxianfe);
        mTextView_bujimianpei.setText("¥" + bujimianpei);
    }

    private void findID() {
        mIv_car_img = (ImageView) findViewById(R.id.iv_car_img);
        mTv_car_name = (TextView) findViewById(R.id.tv_car_name);
        mTv_carriage_count = (TextView) findViewById(R.id.tv_carriage_count);
        mTv_displacement = (TextView) findViewById(R.id.tv_displacement);
        mTv_car_seat_count = (TextView) findViewById(R.id.tv_car_seat_count);
        mTv_dache_get_time_date = (TextView) findViewById(R.id.tv_dache_get_time_date);
        mTv_dache_get_time_time = (TextView) findViewById(R.id.tv_dache_get_time_time);
        mTv_dache_how_long = (TextView) findViewById(R.id.tv_dache_how_long);
        mTv_dache_return_time_date = (TextView) findViewById(R.id.tv_dache_return_time_date);
        mTv_dache_return_time_time = (TextView) findViewById(R.id.tv_dache_return_time_time);
        mTv_dache_jg_store_name_get = (TextView) findViewById(R.id.tv_dache_jg_store_name_get);
        mTv_dache_jg_store_name_return = (TextView) findViewById(R.id.tv_dache_jg_store_name_return);
        mTextView_shouxufei = (TextView) findViewById(R.id.textView_shouxufei);
        mTextView_jibenbaoxianfei = (TextView) findViewById(R.id.textView_jibenbaoxianfei);
        mTextView_bujimianpei = (TextView) findViewById(R.id.textView_bujimianpei);
    }

    private void initIntent() {
        Intent intent = getIntent();
        mCarInfoList_containsEntity = (CarInfoList.ContainsEntity) intent.getSerializableExtra("CarInfoList.ContainsEntity");
        mZiJiaZuCheChooseCityDate = (ZiJiaZuCheChooseCityDate) intent.getSerializableExtra("ZiJiaZuCheChooseCityDate");
        shouxufei = mCarInfoList_containsEntity.getPlan().getPoundage();
        jibenbaoxianfe = mCarInfoList_containsEntity.getPlan().getInsurance();
        bujimianpei = mCarInfoList_containsEntity.getPlan().getFranchiseFees();
    }

    private void setListener() {
        findViewById(R.id.button_commit_order).setOnClickListener(this);
        findViewById(R.id.imageView_back).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_commit_order:
//                if (isCommitOrder)
//                {
//                    isCommitOrder = false;
                commitOrder();
//                }
                break;
            case R.id.imageView_back:
                finish();
                break;
        }
    }

    private void commitOrder() {
        mCarPrice = mCarInfoList_containsEntity.getPlan().getPrice() * mZiJiaZuCheChooseCityDate.getDayCounts();
        double price = shouxufei + jibenbaoxianfe + mCarPrice;
        if (isBujimianpei) {
            price = price + bujimianpei;
        }
        String url = EverythingConstant.HOST + "/bmpw/zc/order/person/addorder";
        Map<String, String> map = new HashMap<>();
        map.put("model", mCarInfoList_containsEntity.getModel() + "");
        map.put("type", mCarInfoList_containsEntity.getType() + "");
        map.put("box", mCarInfoList_containsEntity.getBox() + "");
        map.put("pailiang", mCarInfoList_containsEntity.getPailiang() + "");
        map.put("seat", mCarInfoList_containsEntity.getSeat() + "");
        map.put("zidong", mCarInfoList_containsEntity.getZidong() + "");
        map.put("zuchuDate", mZiJiaZuCheChooseCityDate.getZuchuDate() + "");
        map.put("planReturnDate", mZiJiaZuCheChooseCityDate.getPlanReturnDate() + "");
        map.put("price", price + "");
        map.put("getCar", mZiJiaZuCheChooseCityDate.getGetCar() + "");
        map.put("returnCar", mZiJiaZuCheChooseCityDate.getReturnCar() + "");
        map.put("hasDriver", 1 + "");
//        map.put("driverId",null);
//        map.put("carId","");
        map.put("accountId", mId + "");
        if (isBujimianpei) {
            map.put("hasFranchiseFees", 1 + "");
        } else {
            map.put("hasFranchiseFees", 0 + "");
        }
        map.put("plan_id",mCarInfoList_containsEntity.getPlan_id()+"");
        HTTPUtils.post(this, url, map, new VolleyListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }

            @Override
            public void onResponse(String s) {
                Log.e("onResponse ", "自驾租车提交订单" + s);
                if ("0".equals(s)) {
                    Toast.makeText(ZiJiaZuCheCommitOrderActivity.this, "提交订单失败", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent();
                    intent.putExtra("zijiazuche", "zijiazuche");
                    intent.putExtra("order_id", Integer.parseInt(s));
                    intent.setClass(ZiJiaZuCheCommitOrderActivity.this, ZuCheJiGuoOrderDetailActivity.class);
                    startActivity(intent);
                }
            }
        });

    }
}
