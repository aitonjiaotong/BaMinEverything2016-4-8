package com.aiton.bamin.changtukepiao.Cdachezuche.ZiJiaZuChe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.aiton.bamin.changtukepiao.Cdachezuche.DaCheZhuCheActivity.StoresMapActivity;
import com.aiton.bamin.changtukepiao.Cdachezuche.DaCheZhuCheActivity.ZuCheChooseCityActivity;
import com.aiton.bamin.changtukepiao.Cdachezuche.constant_dachezuche.ConstantDaCheZuChe;
import com.aiton.bamin.changtukepiao.Cdachezuche.models.ZiJiaZuCheChooseCityDate;
import com.aiton.bamin.changtukepiao.R;
import com.github.jjobes.slidedatetimepicker.SlideDateTimeListener;
import com.github.jjobes.slidedatetimepicker.SlideDateTimePicker;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ZiJiaZuCheActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTextView_take_car_city;
    private TextView textView_returnCarCity;
    private TextView textView_takeCarStore;
    private TextView textView_returnCarStore;
    private SimpleDateFormat mDateFormat = new SimpleDateFormat("MM-dd");
    private SimpleDateFormat mTimeFormat = new SimpleDateFormat("EE HH:mm");
    //取车position
    private int takeCarCityPosition = 1;
    //还车position
    private int returnCarCityPosition = 1;
    private SlideDateTimeListener StartDateTimePickerListener = new SlideDateTimeListener() {
        @Override
        public void onDateTimeSet(Date date) {
            boolean before = date.before(mCurrentDate);
            if (!before) {
                mCurrentDate = date;
                mStartDate = mDateFormat.format(date);
                textView_startDate.setText(mStartDate);
                mStartTime = mTimeFormat.format(date);
                textView_startTime.setText(mStartTime);
            } else {
                Toast.makeText(ZiJiaZuCheActivity.this, "预留两小时取车", Toast.LENGTH_SHORT).show();
            }
        }
    };
    private SlideDateTimeListener EndDateTimePickerListener = new SlideDateTimeListener() {
        @Override
        public void onDateTimeSet(Date date) {
            boolean before = date.before(OneDayDate);
            if (!before) {
                mReturnDate = date;
                mEndDate = mDateFormat.format(date);
                textView_endDate.setText(mEndDate);
                mEndTime = mTimeFormat.format(date);
                textView_endTime.setText(mEndTime);
                mDayCounts = (int) ((date.getTime() - mCurrentDate.getTime()) / (24L * 3600L * 1000L));
                long leftTime = (date.getTime() - mCurrentDate.getTime()) % (24L * 3600L * 1000L);
                int leftHour = (int) (leftTime / (3600L * 1000L));
//                多出一个小时外才多算一天
                if (leftHour < 1) {
                } else {
                    mDayCounts = mDayCounts + 1;
                }
                textView_dayCounts.setText((mDayCounts) + "天");
            } else {
                Toast.makeText(ZiJiaZuCheActivity.this, "至少租车一天", Toast.LENGTH_SHORT).show();
            }
        }
    };
    private TextView textView_startDate;
    private String mStartDate;
    private String mStartTime;
    private String mEndDate;
    private String mEndTime;
    private TextView textView_startTime;
    private Date mCurrentDate;
    private Date mReturnDate;
    private TextView textView_endDate;
    private TextView textView_endTime;
    private TextView textView_dayCounts;
    private Date OneDayDate;
    //默认租车两天
    private int mDayCounts = 2;
    private String mTakeCarStore = "三明客运中心店";
    private String mReturenCarStore = "三明客运中心店";
    private String mTakeCarCity = "三明";
    private String mReturnCarCity = "三明";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zi_jia_zu_che);
        findID();
        initTime();
        initUI();
        setListener();
    }

    private void initTime() {
//        默认取车时间推迟两小时
        long currentTimeMillis = System.currentTimeMillis() + 2 * 3600 * 1000;
        mCurrentDate = new Date(currentTimeMillis);
        mStartDate = mDateFormat.format(mCurrentDate);
        mStartTime = mTimeFormat.format(mCurrentDate);
        mReturnDate = new Date(currentTimeMillis + 48 * 3600 * 1000);
        OneDayDate = new Date(currentTimeMillis + 24 * 3600 * 1000);
        mEndDate = mDateFormat.format(mReturnDate);
        mEndTime = mTimeFormat.format(mReturnDate);
    }

    private void initUI() {
        textView_startDate.setText(mStartDate);
        textView_startTime.setText(mStartTime);
        textView_endDate.setText(mEndDate);
        textView_endTime.setText(mEndTime);
        mTextView_take_car_city.setText(mTakeCarCity);
        textView_returnCarCity.setText(mReturnCarCity);
        textView_takeCarStore.setText(mTakeCarStore);
        textView_returnCarStore.setText(mReturenCarStore);
    }

    private void findID() {
        mTextView_take_car_city = (TextView) findViewById(R.id.textView_take_car_city);
        textView_returnCarCity = (TextView) findViewById(R.id.textView_returnCarCity);
        textView_takeCarStore = (TextView) findViewById(R.id.textView_takeCarStore);
        textView_returnCarStore = (TextView) findViewById(R.id.textView_returnCarStore);
        textView_startDate = (TextView) findViewById(R.id.textView_startDate);
        textView_startTime = (TextView) findViewById(R.id.textView_startTime);
        textView_endDate = (TextView) findViewById(R.id.textView_endDate);
        textView_endTime = (TextView) findViewById(R.id.textView_endTime);
        textView_dayCounts = (TextView) findViewById(R.id.textView_dayCounts);
    }

    private void setListener() {
        findViewById(R.id.button_lijixuanche).setOnClickListener(this);
        findViewById(R.id.imageView_back).setOnClickListener(this);
        findViewById(R.id.rela_take_car_city).setOnClickListener(this);
        findViewById(R.id.rela_return_car_city).setOnClickListener(this);
        findViewById(R.id.rela_zijiazuche_takecar_store).setOnClickListener(this);
        findViewById(R.id.rela_zijiazuche_returncar_store).setOnClickListener(this);
        findViewById(R.id.linear_startTime).setOnClickListener(this);
        findViewById(R.id.linear_endDate).setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (requestCode == ConstantDaCheZuChe.RequestCode.ZIJIAZUCHE_TAKE_CAR_CITY && resultCode == ConstantDaCheZuChe.ResultCode.CHOOSE_CITY) {
                mTakeCarCity = data.getStringExtra(ConstantDaCheZuChe.IntentKey.CHOOSE_CITY);
                mTextView_take_car_city.setText(mTakeCarCity);
            }
            if (requestCode == ConstantDaCheZuChe.RequestCode.ZIJIAZUCHE_RETURN_CAR_CITY && resultCode == ConstantDaCheZuChe.ResultCode.CHOOSE_CITY) {
                mReturnCarCity = data.getStringExtra(ConstantDaCheZuChe.IntentKey.CHOOSE_CITY);
                textView_returnCarCity.setText(mReturnCarCity);
            }
            if (requestCode == ConstantDaCheZuChe.RequestCode.JIGOUZUCHE_TAKE_CAR_MAP && resultCode == ConstantDaCheZuChe.ResultCode.CHOOSE_STORE) {
                mTakeCarStore = data.getStringExtra(ConstantDaCheZuChe.IntentKey.STORES_MAP_KEY);
                takeCarCityPosition=data.getIntExtra(ConstantDaCheZuChe.IntentKey.STORES_ID_KEY,1);
                textView_takeCarStore.setText(mTakeCarStore);
            }
            if (requestCode == ConstantDaCheZuChe.RequestCode.JIGOUZUCHE_RETURN_CAR_MAP && resultCode == ConstantDaCheZuChe.ResultCode.CHOOSE_STORE) {
                mReturenCarStore = data.getStringExtra(ConstantDaCheZuChe.IntentKey.STORES_MAP_KEY);
                returnCarCityPosition=data.getIntExtra(ConstantDaCheZuChe.IntentKey.STORES_ID_KEY,1);
                textView_returnCarStore.setText(mReturenCarStore);
            }
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.button_lijixuanche:
                ZiJiaZuCheChooseCityDate ziJiaZuCheChooseCityDate = new ZiJiaZuCheChooseCityDate(mCurrentDate.getTime(), mReturnDate.getTime(), takeCarCityPosition, returnCarCityPosition, mTakeCarStore, mReturenCarStore, mDayCounts, mStartDate, mStartTime, mEndDate, mEndTime);
                intent.setClass(this, ZuCheChooseCarTypeActivity.class);
                intent.putExtra("ZiJiaZuCheChooseCityDate", ziJiaZuCheChooseCityDate);
                startActivity(intent);
                break;
            case R.id.linear_startTime:
                //默认推迟两小时
                mCurrentDate = new Date(System.currentTimeMillis() + 2 * 3600 * 1000);
                new SlideDateTimePicker.Builder(getSupportFragmentManager()).setListener(StartDateTimePickerListener).setInitialDate(mCurrentDate)
//                      .setMinDate(minDate)
//                      .setMaxDate(maxDate)
                        .setIs24HourTime(true)
//                      .setTheme(SlideDateTimePicker.HOLO_DARK)
//                      .setIndicatorColor(Color.parseColor("#990000"))
                        .build().show();
                break;
            case R.id.linear_endDate:
                new SlideDateTimePicker.Builder(getSupportFragmentManager()).setListener(EndDateTimePickerListener).setInitialDate(mReturnDate)
//                      .setMinDate(minDate)
//                      .setMaxDate(maxDate)
                        .setIs24HourTime(true)
//                      .setTheme(SlideDateTimePicker.HOLO_DARK)
//                      .setIndicatorColor(Color.parseColor("#990000"))
                        .build().show();
                break;
            case R.id.imageView_back:
                finish();
                break;
            case R.id.rela_take_car_city:
                //跳转到城市选择列表界面
                intent.setClass(this, ZuCheChooseCityActivity.class);
                startActivityForResult(intent, ConstantDaCheZuChe.RequestCode.ZIJIAZUCHE_TAKE_CAR_CITY);
                break;
            case R.id.rela_return_car_city:
                //跳转到城市选择列表界面
                intent.setClass(this, ZuCheChooseCityActivity.class);
                startActivityForResult(intent, ConstantDaCheZuChe.RequestCode.ZIJIAZUCHE_RETURN_CAR_CITY);
                break;
            case R.id.rela_zijiazuche_takecar_store:
                //跳转到门店地图选择界面
                intent.setClass(this, StoresMapActivity.class);
                intent.putExtra(ConstantDaCheZuChe.IntentKey.CITY, mTakeCarCity);
                intent.putExtra(ConstantDaCheZuChe.IntentKey.GET_MAP_LOC_KEY,ConstantDaCheZuChe.IntentKey.GET_MAP_LOC_GET);
                startActivityForResult(intent, ConstantDaCheZuChe.RequestCode.JIGOUZUCHE_TAKE_CAR_MAP);
                break;
            case R.id.rela_zijiazuche_returncar_store:
                //跳转到门店地图选择界面
                intent.setClass(this, StoresMapActivity.class);
                intent.putExtra(ConstantDaCheZuChe.IntentKey.CITY, mReturnCarCity);
                intent.putExtra(ConstantDaCheZuChe.IntentKey.GET_MAP_LOC_KEY,ConstantDaCheZuChe.IntentKey.GET_MAP_LOC_RETURN);
                startActivityForResult(intent, ConstantDaCheZuChe.RequestCode.JIGOUZUCHE_RETURN_CAR_MAP);
                break;
        }
    }
}
