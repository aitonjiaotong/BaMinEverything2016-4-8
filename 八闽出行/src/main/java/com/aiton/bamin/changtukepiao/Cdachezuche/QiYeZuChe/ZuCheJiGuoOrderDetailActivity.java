package com.aiton.bamin.changtukepiao.Cdachezuche.QiYeZuChe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aiton.administrator.shane_library.shane.utils.GsonUtils;
import com.aiton.administrator.shane_library.shane.utils.HTTPUtils;
import com.aiton.administrator.shane_library.shane.utils.UILUtils;
import com.aiton.administrator.shane_library.shane.utils.VolleyListener;
import com.aiton.bamin.changtukepiao.Cdachezuche.DaCheZhuCheActivity.DaCheZuCheMainActivity;
import com.aiton.bamin.changtukepiao.Cdachezuche.constant_dachezuche.ConstantDaCheZuChe;
import com.aiton.bamin.changtukepiao.Cdachezuche.models.OrderDetailsInfo;
import com.aiton.bamin.changtukepiao.R;
import com.android.volley.VolleyError;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class ZuCheJiGuoOrderDetailActivity extends AppCompatActivity implements View.OnClickListener
{

    private ImageView mIv_back;
    private TextView mTv_jg_order_detail_stage;
    private int mOrder_id;
    private String mZijiazuche;
    private TextView mTv_btn_cancel_order;
    private OrderDetailsInfo mOrderDetailsInfo;
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
    private TextView mTv_dachezuche_order_detail_price;
    private TextView mTv_dachezuche_order_detail_insurance_price;
    private TextView mTv_dachezuche_order_detail_other_price;
    private TextView mTv_dachezuche_order_detail_totals_price;
    private LinearLayout mLl_for_driver;
    private TextView mTv_dachezuche_order_detail_other;
    private TextView mTv_dachezuche_order_detail;
    private LinearLayout mLl_shouxufei;
    private LinearLayout mLl_bujimianpeifei;
    private TextView mTv_zj_shouxufei;
    private TextView mTv_zj_bujimianpeifei;
    private LinearLayout mLl_zj_top;
    private TextView mTv_driver_name;
    private TextView mTv_driver_phone;
    private String mEveryting_order_list;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zu_che_ji_guo_order_detail);

        initGetIntent();
        findViewID();
        setListener();
        initUI();
        initData();
    }

    private void initData()
    {
        Map<String, String> params = new HashMap<>();
        params.put("order_id", mOrder_id + "");
        HTTPUtils.post(ZuCheJiGuoOrderDetailActivity.this, ConstantDaCheZuChe.URL.QUERY_ORDER_DETAIL, params, new VolleyListener()
        {
            @Override
            public void onErrorResponse(VolleyError volleyError)
            {

            }

            @Override
            public void onResponse(String s)
            {
                Log.e("onResponse ", "onResponse " + s);
                mOrderDetailsInfo = GsonUtils.parseJSON(s, OrderDetailsInfo.class);
                if (mOrderDetailsInfo != null)
                {
                    UILUtils.displayImageNoAnim(mOrderDetailsInfo.getCar().getImage(), mIv_car_img);
                    switch (mOrderDetailsInfo.getOrder().getFlag())
                    {
//                        flag:订单状态 0:进行中 1：完成 2:取消 3：等待结算(已还车)
                        case 0:
                            mTv_jg_order_detail_stage.setText("订单进行中");
                            mTv_btn_cancel_order.setVisibility(View.VISIBLE);
                            break;
                        case 1:
                            mTv_jg_order_detail_stage.setText("订单已完成");
                            mTv_btn_cancel_order.setVisibility(View.GONE);
                            break;
                        case 2:
                            mTv_jg_order_detail_stage.setText("订单已取消");
                            mTv_btn_cancel_order.setVisibility(View.GONE);
                            break;
                        case 3:
                            mTv_jg_order_detail_stage.setText("订单等待结算(已还车)");
                            mTv_btn_cancel_order.setVisibility(View.GONE);
                            break;
                    }

                    mTv_car_name.setText(mOrderDetailsInfo.getCar().getModel());
                    mTv_carriage_count.setText(mOrderDetailsInfo.getCar().getBox());
                    switch (mOrderDetailsInfo.getCar().getZidong())
                    {
                        case 0:
                            mTv_displacement.setText(mOrderDetailsInfo.getCar().getPailiang() + "自动");
                            break;
                        case 1:
                            mTv_displacement.setText(mOrderDetailsInfo.getCar().getPailiang() + "手动");
                            break;
                    }
                    mTv_car_seat_count.setText("可乘坐" + mOrderDetailsInfo.getCar().getSeat() + "人");
                    mTv_dache_get_time_date.setText(getDateToString(mOrderDetailsInfo.getOrder().getZuchuDate()));
                    mTv_dache_get_time_time.setText(getTimeToString(mOrderDetailsInfo.getOrder().getZuchuDate()));
                    mTv_dache_how_long.setText(getHowLong(mOrderDetailsInfo.getOrder().getZuchuDate(), mOrderDetailsInfo.getOrder().getPlanReturnDate()));
                    mTv_dache_return_time_date.setText(getDateToString(mOrderDetailsInfo.getOrder().getPlanReturnDate()));
                    mTv_dache_return_time_time.setText(getTimeToString(mOrderDetailsInfo.getOrder().getPlanReturnDate()));
                    mTv_dache_jg_store_name_get.setText(mOrderDetailsInfo.getGetCarStore().getName());
                    mTv_dache_jg_store_name_return.setText(mOrderDetailsInfo.getReturnStore().getName());
                    mTv_dachezuche_order_detail.setText("￥" + mOrderDetailsInfo.getPlan().getPrice() + " X " + getHowLongDay(mOrderDetailsInfo.getOrder().getZuchuDate(), mOrderDetailsInfo.getOrder().getPlanReturnDate()));
                    double basePrice = mOrderDetailsInfo.getPlan().getPrice() * getHowLongDayLong(mOrderDetailsInfo.getOrder().getZuchuDate(), mOrderDetailsInfo.getOrder().getPlanReturnDate());
                    mTv_dachezuche_order_detail_price.setText("￥" + basePrice);
                    mTv_dachezuche_order_detail_insurance_price.setText("￥" + mOrderDetailsInfo.getPlan().getInsurance());
                    switch (mOrderDetailsInfo.getOrder().getHasDriver())
                    {
                        case 0:
                            mLl_for_driver.setVisibility(View.VISIBLE);
                            mTv_dachezuche_order_detail_other.setText("(携带司机费用)");
                            mTv_dachezuche_order_detail_other_price.setText("￥" + mOrderDetailsInfo.getPlan().getHasDriver());
                            mTv_driver_name.setText(mOrderDetailsInfo.getDriver().getName() + " : ");
                            mTv_driver_phone.setText(mOrderDetailsInfo.getDriver().getPhone());
                            break;
                        case 1:
                            mLl_for_driver.setVisibility(View.GONE);
                            mTv_dachezuche_order_detail_other.setText("(无其它费用)");
                            mTv_dachezuche_order_detail_other_price.setText("￥" + 0);
                            break;
                    }
                    mTv_dachezuche_order_detail_totals_price.setText("￥" + mOrderDetailsInfo.getOrder().getShouyajin());

                    mTv_zj_shouxufei.setText("￥" + mOrderDetailsInfo.getPlan().getPoundage());
                    mTv_zj_bujimianpeifei.setText("￥" + mOrderDetailsInfo.getPlan().getFranchiseFees());
                }
            }
        });
    }

    private String getHowLongDay(long starttime, long endting)
    {
        long howLong = (endting + (2 * 3600 * 1000)) - starttime;
        long l = howLong / (24 * 3600 * 1000);//得到多少天
        return l + "天";
    }

    private long getHowLongDayLong(long starttime, long endting)
    {
        long howLong = (endting + (2 * 3600 * 1000)) - starttime;
        long l = howLong / (24 * 3600 * 1000);//得到多少天
        return l;
    }

    private void initGetIntent()
    {
        Intent intent = getIntent();
        mOrder_id = intent.getIntExtra("order_id", -1);
        mZijiazuche = intent.getStringExtra("zijiazuche");
        mEveryting_order_list = intent.getStringExtra("everyting_order_list");
    }

    private void findViewID()
    {
        mIv_back = (ImageView) findViewById(R.id.iv_back);
        mTv_btn_cancel_order = (TextView) findViewById(R.id.tv_btn_cancel_order);
        mTv_jg_order_detail_stage = (TextView) findViewById(R.id.tv_jg_order_detail_stage);//订单状态
        //汽车显示的图片
        mIv_car_img = (ImageView) findViewById(R.id.iv_car_img);
        //汽车的名称
        mTv_car_name = (TextView) findViewById(R.id.tv_car_name);
        //汽车的厢数
        mTv_carriage_count = (TextView) findViewById(R.id.tv_carriage_count);
        //汽车的排量及是否为自动档
        mTv_displacement = (TextView) findViewById(R.id.tv_displacement);
        //汽车可乘的人数
        mTv_car_seat_count = (TextView) findViewById(R.id.tv_car_seat_count);
        //取车的时间_日期
        mTv_dache_get_time_date = (TextView) findViewById(R.id.tv_dache_get_time_date);
        //取车的时间_星期及时间
        mTv_dache_get_time_time = (TextView) findViewById(R.id.tv_dache_get_time_time);
        //取车的总天数
        mTv_dache_how_long = (TextView) findViewById(R.id.tv_dache_how_long);
        //还车的时间_日期
        mTv_dache_return_time_date = (TextView) findViewById(R.id.tv_dache_return_time_date);
        //还车的时间_星期及时间
        mTv_dache_return_time_time = (TextView) findViewById(R.id.tv_dache_return_time_time);
        //取车门店名称
        mTv_dache_jg_store_name_get = (TextView) findViewById(R.id.tv_dache_jg_store_name_get);
        //还车门店名称
        mTv_dache_jg_store_name_return = (TextView) findViewById(R.id.tv_dache_jg_store_name_return);
        //租赁基本费用明细
        mTv_dachezuche_order_detail = (TextView) findViewById(R.id.tv_dachezuche_order_detail);
        //租赁基本费用
        mTv_dachezuche_order_detail_price = (TextView) findViewById(R.id.tv_dachezuche_order_detail_price);
        //租赁基本保险费
        mTv_dachezuche_order_detail_insurance_price = (TextView) findViewById(R.id.tv_dachezuche_order_detail_insurance_price);
        //租赁其它费用
        mTv_dachezuche_order_detail_other_price = (TextView) findViewById(R.id.tv_dachezuche_order_detail_other_price);
        //租赁其它费用描述
        mTv_dachezuche_order_detail_other = (TextView) findViewById(R.id.tv_dachezuche_order_detail_other);
        //租赁合计总费用
        mTv_dachezuche_order_detail_totals_price = (TextView) findViewById(R.id.tv_dachezuche_order_detail_totals_price);
        //是否携带司机
        mLl_for_driver = (LinearLayout) findViewById(R.id.ll_for_driver);
        //司机名字
        mTv_driver_name = (TextView) findViewById(R.id.tv_driver_name);
        //司机电话
        mTv_driver_phone = (TextView) findViewById(R.id.tv_driver_phone);

        //个人租车----
        mLl_shouxufei = (LinearLayout) findViewById(R.id.ll_shouxufei);
        mLl_bujimianpeifei = (LinearLayout) findViewById(R.id.ll_bujimianpeifei);
        mTv_zj_shouxufei = (TextView) findViewById(R.id.tv_zj_shouxufei);
        mTv_zj_bujimianpeifei = (TextView) findViewById(R.id.tv_zj_bujimianpeifei);
        mLl_zj_top = (LinearLayout) findViewById(R.id.ll_zj_top);

    }

    private void setListener()
    {
        mIv_back.setOnClickListener(this);
        mTv_btn_cancel_order.setOnClickListener(this);
        mLl_for_driver.setOnClickListener(this);
    }

    private void initUI()
    {
        if ("zijiazuche".equals(mZijiazuche))
        {
            mLl_shouxufei.setVisibility(View.VISIBLE);
            mLl_bujimianpeifei.setVisibility(View.VISIBLE);
            mLl_zj_top.setVisibility(View.VISIBLE);
        } else
        {
            mLl_shouxufei.setVisibility(View.GONE);
            mLl_bujimianpeifei.setVisibility(View.GONE);
            mLl_zj_top.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.iv_back:
                if ("everyting_order_list".equals(mEveryting_order_list))
                {
                    finish();
                } else
                {
                    startToMainActivity();
                }
                break;
            case R.id.tv_btn_cancel_order:
                //向服务端请求取消订单
                if (mOrderDetailsInfo.getOrder().getFlag() == 0)
                {
                    Map<String, String> params = new HashMap<>();
                    Log.e("--->>", mOrder_id + "");
                    params.put("order_id", mOrder_id + "");
                    HTTPUtils.post(ZuCheJiGuoOrderDetailActivity.this, ConstantDaCheZuChe.URL.CANCEL_ORDER, params, new VolleyListener()
                    {
                        @Override
                        public void onErrorResponse(VolleyError volleyError)
                        {
                        }

                        @Override
                        public void onResponse(String s)
                        {
                            Log.e("onResponse ", "onResponse " + s);
                            if ("true".equals(s))
                            {
//                                finish();
                                startToMainActivity();
                            } else
                            {
                                Toast.makeText(ZuCheJiGuoOrderDetailActivity.this, "订单取消失败，请检查网络信号后重试！", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                break;
            case R.id.ll_for_driver:
                //TODO 给司机评价
                Intent intent = new Intent(ZuCheJiGuoOrderDetailActivity.this, AppraiseDriverActivity.class);
                intent.putExtra("driver", mOrderDetailsInfo.getDriver());
                startActivity(intent);
                break;
        }
    }

    /**
     * 将时间毫秒数转换成日期形式
     */
    private String getDateToString(long l)
    {
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("MM-dd");
        String date_format = mSimpleDateFormat.format(l);
        return date_format;
    }

    /**
     * 将时间毫秒数转换成星期+时间的形式
     */
    private String getTimeToString(long l)
    {
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("EE HH:mm");
        String time_format = mSimpleDateFormat.format(l);
        return time_format;
    }

    /**
     * 计算初始时间与结束时间之间相关的时间天数
     */
    private String getHowLong(long starttime, long endting)
    {
        long howLong = (endting + (2 * 3600 * 1000)) - starttime;
        long l = howLong / (24 * 3600 * 1000);//得到多少天
        if (l > 30)
        {
            long month = l / 30;
            long day = l % 30;
            if (day == 0.0)
            {
                return month + "个月";
            } else
            {
                return month + "个月 + " + day + "天";
            }
        } else
        {
            return l + "天";
        }
    }

    private void startToMainActivity()
    {
        Intent intent = new Intent();
        if ("zijiazuche".equals(mZijiazuche))
        {
            intent.putExtra(ConstantDaCheZuChe.IntentKey.BACK_TO_ORDER_LIST_KEY, ConstantDaCheZuChe.IntentKey.ZI_JIA_ZU_CHE_BACK_INT);
        } else
        {
            intent.putExtra(ConstantDaCheZuChe.IntentKey.BACK_TO_ORDER_LIST_KEY, ConstantDaCheZuChe.IntentKey.JI_GUO_ZU_CHE_BACK_INT);
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setClass(this, DaCheZuCheMainActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ("everyting_order_list".equals(mEveryting_order_list) && keyCode == KeyEvent.KEYCODE_BACK)
        {
            finish();
        } else if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            startToMainActivity();
        }
        return super.onKeyDown(keyCode, event);
    }
}
