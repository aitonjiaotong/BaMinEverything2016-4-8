package com.aiton.bamin.changtukepiao.Cdachezuche.ZiJiaZuChe;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.aiton.administrator.shane_library.shane.utils.GsonUtils;
import com.aiton.administrator.shane_library.shane.utils.HTTPUtils;
import com.aiton.administrator.shane_library.shane.utils.UILUtils;
import com.aiton.administrator.shane_library.shane.utils.VolleyListener;
import com.aiton.bamin.changtukepiao.Cdachezuche.models.CarInfoList;
import com.aiton.bamin.changtukepiao.Cdachezuche.models.ZiJiaZuCheChooseCityDate;
import com.aiton.bamin.changtukepiao.R;
import com.aiton.bamin.changtukepiao.Zeverything.SmsLoginActivity;
import com.aiton.bamin.changtukepiao.Zeverything.constant.EverythingConstant;
import com.android.volley.VolleyError;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ZuCheChooseCarTypeActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mIv_back;
    private ListView mLv_zuche_choose_car_type;
    private CarTypeAdapter mCarTypeAdapter;
    private int mPages = 0;
    private List<CarInfoList.ContainsEntity> mCarInfoListContains = new ArrayList<>();
    private int mPagesMAX;
    private TextView mTextView_noneCarInfo;
    private ZiJiaZuCheChooseCityDate mZiJiaZuCheChooseCityDate;
    private String mPhoneNum;
    private String mId;
    private boolean isLogin = false;
    private String mDeviceId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_car_type);
        initIntent();
        findViewID();
        initUI();
//        查询车辆信息
        queryCarInfoList();
        setListener();
    }
    /**
     * 检查是否在其他设备上登陆
     */
    private void checkIsLoginOnOtherDevice() {
        if (!"".equals(mDeviceId)) {
            String url = EverythingConstant.HOST + "/bmpw/account/findLogin_id";
            Map<String, String> map = new HashMap<>();
            map.put("account_id", mId);
            HTTPUtils.post(ZuCheChooseCarTypeActivity.this, url, map, new VolleyListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                }

                @Override
                public void onResponse(String s) {
                    if (!s.equals(mDeviceId)) {
                        setDialog("你的账号登录异常\n请重新登录", "确定");
                    }
                    {
                        Intent intent = new Intent();
                        intent.setClass(ZuCheChooseCarTypeActivity.this, SmsLoginActivity.class);
                        startActivity(intent);
                    }
                }
            });
        }
    }
    //dialog提示
    private void setDialog(String messageTxt, String iSeeTxt) {
        View commit_dialog = getLayoutInflater().inflate(R.layout.commit_dialog, null);
        TextView message = (TextView) commit_dialog.findViewById(R.id.message);
        Button ISee = (Button) commit_dialog.findViewById(R.id.ISee);
        message.setText(messageTxt);
        ISee.setText(iSeeTxt);
        AlertDialog.Builder builder = new AlertDialog.Builder(ZuCheChooseCarTypeActivity.this);
        final AlertDialog dialog = builder.setView(commit_dialog)
                .create();
        dialog.setCancelable(false);
        dialog.show();
        commit_dialog.findViewById(R.id.ISee).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                //清除用户登录信息
                SharedPreferences sp = getSharedPreferences("isLogin", MODE_PRIVATE);
                SharedPreferences.Editor edit = sp.edit();
                edit.clear();
                edit.commit();
                Intent intent = new Intent();
                intent.setClass(ZuCheChooseCarTypeActivity.this, SmsLoginActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences sp = getSharedPreferences("isLogin", Context.MODE_PRIVATE);
        mPhoneNum = sp.getString("phoneNum", "");
        mDeviceId = sp.getString("DeviceId", "");
        mId = sp.getString("id", "");
        if ("".equals(mPhoneNum)) {
            isLogin = false;
        } else {
            isLogin = true;
        }
        MobclickAgent.onResume(this);
    }
    private void initIntent() {
        Intent intent = getIntent();
        mZiJiaZuCheChooseCityDate = (ZiJiaZuCheChooseCityDate) intent.getSerializableExtra("ZiJiaZuCheChooseCityDate");
    }

    private void queryCarInfoList() {
        String url = EverythingConstant.HOST + "/bmpw/zc/front/loadcanusecar";
        Map<String, String> map = new HashMap<>();
        map.put("page", mPages + "");
        HTTPUtils.post(ZuCheChooseCarTypeActivity.this, url, map, new VolleyListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }

            @Override
            public void onResponse(String s) {
                Log.e("onResponse ", "汽车信息"+s);
                mPages++;
                CarInfoList carInfoList = GsonUtils.parseJSON(s, CarInfoList.class);
                mPagesMAX = carInfoList.getNum();
                List<CarInfoList.ContainsEntity> carInfoListContains = carInfoList.getContains();
                mCarInfoListContains.addAll(carInfoListContains);
                if (mCarInfoListContains.size() > 0) {
                    mLv_zuche_choose_car_type.setVisibility(View.VISIBLE);
                    mCarTypeAdapter.notifyDataSetChanged();
                } else {
                    mLv_zuche_choose_car_type.setVisibility(View.GONE);
                }
            }
        });
    }


    private void findViewID() {
        mIv_back = (ImageView) findViewById(R.id.iv_zuche_choose_car_type_back);
        mLv_zuche_choose_car_type = (ListView) findViewById(R.id.lv_zuche_choose_car_type);
    }

    private void initUI() {
        View carinfo_foot = getLayoutInflater().inflate(R.layout.carinfo_foot, null);
        mTextView_noneCarInfo = (TextView) carinfo_foot.findViewById(R.id.textView_NoneCarInfo);
        mLv_zuche_choose_car_type.addFooterView(carinfo_foot);
        mCarTypeAdapter = new CarTypeAdapter();
        mLv_zuche_choose_car_type.setAdapter(mCarTypeAdapter);
    }

    private void setListener() {
        mIv_back.setOnClickListener(this);
        mLv_zuche_choose_car_type.setOnItemClickListener(new MyItemClickListener());
    }

    class MyItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (position == mCarInfoListContains.size()) {
                if (mPages < mPagesMAX) {
                    queryCarInfoList();
                    mTextView_noneCarInfo.setText("更多车辆信息");
                } else {
                    mTextView_noneCarInfo.setText("没有更多车辆信息了");
                }
            } else {
                if (isLogin) {
                    Intent intent = new Intent();
                    intent.putExtra("CarInfoList.ContainsEntity",mCarInfoListContains.get(position));
                    intent.putExtra("ZiJiaZuCheChooseCityDate",mZiJiaZuCheChooseCityDate);
                    intent.setClass(ZuCheChooseCarTypeActivity.this,ZiJiaZuCheCommitOrderActivity.class);
                    startActivity(intent);
                } else {
                    checkIsLoginOnOtherDevice();
                }

            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_zuche_choose_car_type_back:
                finish();
                break;
        }
    }

    class CarTypeAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mCarInfoListContains.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //sss
            View layout = getLayoutInflater().inflate(R.layout.dachezuche_choose_car_type_list_item, null);
            ImageView iv_car_img = (ImageView) layout.findViewById(R.id.iv_car_img);//车型图片
            TextView tv_car_name = (TextView) layout.findViewById(R.id.tv_car_name);//车型名称
            TextView tv_car_price = (TextView) layout.findViewById(R.id.tv_car_price);//租金
            TextView tv_carriage_count = (TextView) layout.findViewById(R.id.tv_carriage_count);//厢数(三厢)
            TextView tv_displacement = (TextView) layout.findViewById(R.id.tv_displacement);//排量(1.4自动)
            TextView tv_car_seat_count = (TextView) layout.findViewById(R.id.tv_car_seat_count);//可乘坐位数(乘坐5人)
            if (mCarInfoListContains.size() > 0) {
                CarInfoList.ContainsEntity carInfo = mCarInfoListContains.get(position);
                UILUtils.displayImageNoAnim(carInfo.getImage(), iv_car_img);
                tv_car_name.setText(carInfo.getModel() + carInfo.getType());
                tv_car_price.setText(carInfo.getPlan().getPrice() + "");
                tv_carriage_count.setText(carInfo.getBox());
                if (carInfo.getZidong() == 0) {
                    tv_displacement.setText(carInfo.getPailiang() + "自动");
                } else {
                    tv_displacement.setText(carInfo.getPailiang() + "手动");
                }
                tv_car_seat_count.setText("乘坐" + carInfo.getSeat() + "人");
            }
            return layout;
        }
    }
}
