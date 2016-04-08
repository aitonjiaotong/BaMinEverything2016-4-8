package com.aiton.bamin.changtukepiao.Cdachezuche.QiYeZuChe;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aiton.administrator.shane_library.shane.utils.HTTPUtils;
import com.aiton.administrator.shane_library.shane.utils.UILUtils;
import com.aiton.administrator.shane_library.shane.utils.VolleyListener;
import com.aiton.bamin.changtukepiao.Zeverything.SmsLoginActivity;
import com.aiton.bamin.changtukepiao.Cdachezuche.DaCheZhuCheActivity.DaCheZuCheMainActivity;
import com.aiton.bamin.changtukepiao.Cdachezuche.DaCheZhuCheActivity.StoresMapActivity;
import com.aiton.bamin.changtukepiao.Cdachezuche.constant_dachezuche.ConstantDaCheZuChe;
import com.aiton.bamin.changtukepiao.Cdachezuche.models.ChooseFristInfo;
import com.aiton.bamin.changtukepiao.R;
import com.android.volley.VolleyError;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class ZuCheCheckOrderDetailActivity extends AppCompatActivity implements View.OnClickListener
{

    private ImageView mIv_back;
    private Button mBtn_dache_commit_order;
    private LinearLayout mLl_dache_jg_order_get_car;
    private LinearLayout mLl_dache_jg_order_return_car;
    private TextView mTv_dache_jg_store_name_return;
    private TextView mTv_dache_jg_store_name_get;
    private ChooseFristInfo mChooseFristInfo;
    private TextView mTv_dache_get_time_date;
    private TextView mTv_dache_get_time_time;
    private TextView mTv_dache_how_long;
    private TextView mTv_dache_return_time_date;
    private TextView mTv_dache_return_time_time;
    private TextView mTv_car_name;
    private TextView mTv_carriage_count;
    private TextView mTv_displacement;
    private TextView mTv_car_seat_count;
    //    private SingleCarInfo mSingleCarInfo;
    private ImageView mIv_car_img;
    private int mGetCarStoresId = 1;
    private int mReturnCarStoresId = 1;
    private double mTotalPrice = 0.0;
    private double mInsurancePrice = 0.0;
    private TextView mTv_dachezuche_order_detail_price;
    private TextView mTv_dachezuche_order_detail_insurance_price;
    private TextView mTv_dachezuche_order_detail_other;
    private TextView mTv_dachezuche_order_detail_other_price;
    private TextView mTv_dachezuche_order_detail_totals_price;
    private AlertDialog mAlertDialog;
    private AlertDialog.Builder mBuilder;
    private View mDialogLayout;
    private LinearLayout mLl_commint_order_remind;
    private LinearLayout mLl_commint_order_failure;
    private LinearLayout mLl_commint_order_comfire;
    private boolean mIsSuccessful;
    private TextView mTv_dachezuche_order_detail;
    private double mBasePrice;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zu_che_order_detail);
        initGetIntent();
        findViewID();
        initUI();
        setListener();
//        initData();

    }

//    private void initData()
//    {
//        initCarInfoData();
//    }
//
//
//    private void initCarInfoData()
//    {
//        Map<String, String> params = new HashMap<>();
//        params.put("lei", mChooseFristInfo.getCarType() + "");
//        HTTPUtils.post(ZuCheCheckOrderDetailActivity.this, ConstantDaCheZuChe.URL.GET_CAR_INFO, params, new VolleyListener()
//        {
//            @Override
//            public void onErrorResponse(VolleyError volleyError)
//            {
//            }
//
//            @Override
//            public void onResponse(String s)
//            {
//                mSingleCarInfo = GsonUtils.parseJSON(s, SingleCarInfo.class);
//                if (mSingleCarInfo != null)
//                {
//                    mTv_car_name.setText(mSingleCarInfo.getCar().getModel() + mSingleCarInfo.getCar().getType());
//                    mTv_carriage_count.setText(mSingleCarInfo.getCar().getBox());
//                    if (mSingleCarInfo.getCar().getZidong() == 0)
//                    {
//                        mTv_displacement.setText(mSingleCarInfo.getCar().getPailiang() + "自动");
//                    } else
//                    {
//                        mTv_displacement.setText(mSingleCarInfo.getCar().getPailiang() + "手动");
//                    }
//                    mTv_displacement.setText(mSingleCarInfo.getCar().getPailiang());
//                    mTv_car_seat_count.setText("乘坐" + mSingleCarInfo.getCar().getSeat() + "人");
//                    if (mSingleCarInfo.getCar().getImage() != null && !"".equals(mSingleCarInfo.getCar().getImage()))
//                    {
//                        UILUtils.displayImageNoAnim(mSingleCarInfo.getCar().getImage(), mIv_car_img);
//                    }
//                    mBasePrice = mSingleCarInfo.getPlan().getPrice() * getHowLongDayLong(mChooseFristInfo.getGetCarTime(), mChooseFristInfo.getReturnCarTime());
//                    mTv_dachezuche_order_detail_price.setText("￥" + mBasePrice);
//                    mTv_dachezuche_order_detail.setText("￥" + mSingleCarInfo.getPlan().getPrice() + " X " + getHowLongDay(mChooseFristInfo.getGetCarTime(), mChooseFristInfo.getReturnCarTime()));
//                    mTv_dachezuche_order_detail_insurance_price.setText("￥" + mSingleCarInfo.getPlan().getInsurance());
//                    switch (mChooseFristInfo.getHasDriver())
//                    {
//                        case 1:
//                            mTv_dachezuche_order_detail_other.setText("暂无其它费用！");
//                            mTv_dachezuche_order_detail_other_price.setText("￥0");
//                            mTotalPrice = toCalculateResult(mBasePrice, mSingleCarInfo.getPlan().getInsurance(), 0, mSingleCarInfo.getPlan().getOthers());
//                            break;
//                        case 0:
//                            mTv_dachezuche_order_detail_other.setText("司机服务费用");
//                            mTv_dachezuche_order_detail_other_price.setText("￥" + mSingleCarInfo.getPlan().getHasDriver());
//                            mTotalPrice = toCalculateResult(mBasePrice, mSingleCarInfo.getPlan().getInsurance(), mSingleCarInfo.getPlan().getHasDriver(), mSingleCarInfo.getPlan().getOthers());
//                            break;
//                        default:
//                            break;
//                    }
//                    mInsurancePrice = mSingleCarInfo.getPlan().getInsurance();
//                    mTv_dachezuche_order_detail_totals_price.setText("￥" + mTotalPrice);
//                }
//            }
//        });
//
//    }

    /**
     * 计算总价费用
     *
     * @return
     */
    private double toCalculateResult(double price, double insurance, double hasDriver, double others)
    {
        double total = price + insurance + hasDriver + others;
        return total;
    }

    private void initUI()
    {
        mTv_dache_get_time_date.setText(getDateToString(mChooseFristInfo.getGetCarTime()));
        mTv_dache_get_time_time.setText(getTimeToString(mChooseFristInfo.getGetCarTime()));
        mTv_dache_how_long.setText(getHowLong(mChooseFristInfo.getGetCarTime(), mChooseFristInfo.getReturnCarTime()));
        mTv_dache_return_time_date.setText(getDateToString(mChooseFristInfo.getReturnCarTime()));
        mTv_dache_return_time_time.setText(getTimeToString(mChooseFristInfo.getReturnCarTime()));

        //根据用户选择的车辆信息，设置控件的显示数据
        if (mChooseFristInfo != null && mChooseFristInfo.getCar() != null)
        {
            mTv_car_name.setText(mChooseFristInfo.getCar().getModel() + mChooseFristInfo.getCar().getType());
            mTv_carriage_count.setText(mChooseFristInfo.getCar().getBox());
            if (mChooseFristInfo.getCar().getZidong() == 0)
            {
                mTv_displacement.setText(mChooseFristInfo.getCar().getPailiang() + "自动");
            } else
            {
                mTv_displacement.setText(mChooseFristInfo.getCar().getPailiang() + "手动");
            }
            mTv_displacement.setText(mChooseFristInfo.getCar().getPailiang());
            mTv_car_seat_count.setText("乘坐" + mChooseFristInfo.getCar().getSeat() + "人");
            if (mChooseFristInfo.getCar().getImage() != null && !"".equals(mChooseFristInfo.getCar().getImage()))
            {
                UILUtils.displayImageNoAnim(mChooseFristInfo.getCar().getImage(), mIv_car_img);
            }
            mBasePrice = mChooseFristInfo.getPlan().getPrice() * getHowLongDayLong(mChooseFristInfo.getGetCarTime(), mChooseFristInfo.getReturnCarTime());
            mTv_dachezuche_order_detail_price.setText("￥" + mBasePrice);
            mTv_dachezuche_order_detail.setText("￥" + mChooseFristInfo.getPlan().getPrice() + " X " + getHowLongDay(mChooseFristInfo.getGetCarTime(), mChooseFristInfo.getReturnCarTime()));
            mTv_dachezuche_order_detail_insurance_price.setText("￥" + mChooseFristInfo.getPlan().getInsurance());
            switch (mChooseFristInfo.getHasDriver())
            {
                case 1:
                    mTv_dachezuche_order_detail_other.setText("暂无其它费用！");
                    mTv_dachezuche_order_detail_other_price.setText("￥0");
                    mTotalPrice = toCalculateResult(mBasePrice, mChooseFristInfo.getPlan().getInsurance(), 0, mChooseFristInfo.getPlan().getOthers());
                    break;
                case 0:
                    mTv_dachezuche_order_detail_other.setText("司机服务费用");
                    mTv_dachezuche_order_detail_other_price.setText("￥" + mChooseFristInfo.getPlan().getHasDriver());
                    mTotalPrice = toCalculateResult(mBasePrice, mChooseFristInfo.getPlan().getInsurance(), mChooseFristInfo.getPlan().getHasDriver(), mChooseFristInfo.getPlan().getOthers());
                    break;
                default:
                    break;
            }
            mInsurancePrice = mChooseFristInfo.getPlan().getInsurance();
            mTv_dachezuche_order_detail_totals_price.setText("￥" + mTotalPrice);
        }
    }

    private String getHowLong(long starttime, long endting)
    {
        long howLong = (endting + (2 * 3600 * 1000)) - starttime;
        long l = howLong / (24 * 3600 * 1000);//得到多少天
        if (l > 30)
        {
            long month = l / 30;
            long day = l % 30;
            Log.e("getHowLong ", "getHowLong " + day);
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

    private long getHowLongDayLong(long starttime, long endting)
    {
        long howLong = (endting + (2 * 3600 * 1000)) - starttime;
        long l = howLong / (24 * 3600 * 1000);//得到多少天
        return l;
    }


    private String getHowLongDay(long starttime, long endting)
    {
        long howLong = (endting + (2 * 3600 * 1000)) - starttime;
        long l = howLong / (24 * 3600 * 1000);//得到多少天
        return l + "天";
    }

    private String getDateToString(long l)
    {
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("MM-dd");
        String date_format = mSimpleDateFormat.format(l);
        return date_format;
    }

    private String getTimeToString(long l)
    {
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("EE HH:mm");
        String time_format = mSimpleDateFormat.format(l);
        return time_format;
    }

    private void initGetIntent()
    {
        Intent data = getIntent();
        mChooseFristInfo = (ChooseFristInfo) data.getSerializableExtra(ConstantDaCheZuChe.IntentKey.CHOOSE_FRIST_INFO);
    }

    private void setListener()
    {
        mIv_back.setOnClickListener(this);
        mBtn_dache_commit_order.setOnClickListener(this);
        mLl_dache_jg_order_get_car.setOnClickListener(this);
        mLl_dache_jg_order_return_car.setOnClickListener(this);
    }

    private void findViewID()
    {
        mIv_back = (ImageView) findViewById(R.id.iv_zuche_choose_car_type_back);
        mBtn_dache_commit_order = (Button) findViewById(R.id.btn_dache_commit_order);
        mLl_dache_jg_order_get_car = (LinearLayout) findViewById(R.id.ll_dache_jg_order_get_car);
        mLl_dache_jg_order_return_car = (LinearLayout) findViewById(R.id.ll_dache_jg_order_return_car);
        mTv_dache_jg_store_name_return = (TextView) findViewById(R.id.tv_dache_jg_store_name_return);
        mTv_dache_jg_store_name_get = (TextView) findViewById(R.id.tv_dache_jg_store_name_get);

        mTv_dache_get_time_date = (TextView) findViewById(R.id.tv_dache_get_time_date);
        mTv_dache_get_time_time = (TextView) findViewById(R.id.tv_dache_get_time_time);
        mTv_dache_how_long = (TextView) findViewById(R.id.tv_dache_how_long);
        mTv_dache_return_time_date = (TextView) findViewById(R.id.tv_dache_return_time_date);
        mTv_dache_return_time_time = (TextView) findViewById(R.id.tv_dache_return_time_time);

        mIv_car_img = (ImageView) findViewById(R.id.iv_car_img);
        mTv_car_name = (TextView) findViewById(R.id.tv_car_name);
        mTv_carriage_count = (TextView) findViewById(R.id.tv_carriage_count);
        mTv_displacement = (TextView) findViewById(R.id.tv_displacement);
        mTv_car_seat_count = (TextView) findViewById(R.id.tv_car_seat_count);

        mTv_dachezuche_order_detail_price = (TextView) findViewById(R.id.tv_dachezuche_order_detail_price);
        mTv_dachezuche_order_detail_insurance_price = (TextView) findViewById(R.id.tv_dachezuche_order_detail_insurance_price);
        mTv_dachezuche_order_detail_other = (TextView) findViewById(R.id.tv_dachezuche_order_detail_other);
        mTv_dachezuche_order_detail_other_price = (TextView) findViewById(R.id.tv_dachezuche_order_detail_other_price);
        mTv_dachezuche_order_detail_totals_price = (TextView) findViewById(R.id.tv_dachezuche_order_detail_totals_price);
        mTv_dachezuche_order_detail = (TextView) findViewById(R.id.tv_dachezuche_order_detail);


    }

    @Override
    public void onClick(View v)
    {
        Intent intent = new Intent();
        switch (v.getId())
        {
            case R.id.iv_zuche_choose_car_type_back:
                finish();
                break;
            case R.id.btn_dache_commit_order:
                //提交订单
                if (isLogin())
                {
                    //用户登陆的状态
                    Map map = constructionOrderParams();
                    commitOrder(map);
                } else
                {
                    //用户未登陆的状态
                    startActivity(new Intent(ZuCheCheckOrderDetailActivity.this, SmsLoginActivity.class));
                }
                break;
            case R.id.ll_dache_jg_order_get_car:
                if (ContextCompat.checkSelfPermission(ZuCheCheckOrderDetailActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                {
                    //申请WRITE_EXTERNAL_STORAGE权限
                    ActivityCompat.requestPermissions(ZuCheCheckOrderDetailActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
                } else
                {
                    intent.setClass(ZuCheCheckOrderDetailActivity.this, StoresMapActivity.class);
                    intent.putExtra(ConstantDaCheZuChe.IntentKey.GET_MAP_LOC_KEY, ConstantDaCheZuChe.IntentKey.GET_MAP_LOC_GET);//取车:判断地图上显示为取
                    intent.putExtra(ConstantDaCheZuChe.IntentKey.CITY, mChooseFristInfo.getCityName());
                    startActivityForResult(intent, ConstantDaCheZuChe.RequestCode.JIGOUZUCHE_TAKE_CAR_MAP);
                }

                break;
            case R.id.ll_dache_jg_order_return_car:
                if (ContextCompat.checkSelfPermission(ZuCheCheckOrderDetailActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                {
                    //申请WRITE_EXTERNAL_STORAGE权限
                    ActivityCompat.requestPermissions(ZuCheCheckOrderDetailActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
                } else
                {
                    intent.setClass(ZuCheCheckOrderDetailActivity.this, StoresMapActivity.class);
                    intent.putExtra(ConstantDaCheZuChe.IntentKey.GET_MAP_LOC_KEY, ConstantDaCheZuChe.IntentKey.GET_MAP_LOC_RETURN);//取车:判断地图上显示为还
                    intent.putExtra(ConstantDaCheZuChe.IntentKey.CITY, mChooseFristInfo.getCityName());
                    startActivityForResult(intent, ConstantDaCheZuChe.RequestCode.JIGOUZUCHE_RETURN_CAR_MAP);
                }
                break;
            case R.id.tv_btn_comfire_look_list:
                //订单提交成功后点击查看跳转到订单列表
                intentToOrderList();
                break;
            case R.id.tv_btn_failure_back:
                mAlertDialog.dismiss();
                break;
        }
    }

    /**
     * 向服务端提交订单信息
     */
    private void commitOrder(Map orderparams)
    {
        commintOrderSecussDialog();
        HTTPUtils.post(ZuCheCheckOrderDetailActivity.this, ConstantDaCheZuChe.URL.COMMIT_ORDER, orderparams, new VolleyListener()
        {
            @Override
            public void onErrorResponse(VolleyError volleyError)
            {
                mAlertDialog.dismiss();
                Toast.makeText(ZuCheCheckOrderDetailActivity.this, "订单提交失败，请重新提交！", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String s)
            {
                Log.e("commitOrder", "企业租车提交订单" + s);

                if (!"0".equals(s))
                {
                    //订单提交成功
                    mIsSuccessful = true;
                    mLl_commint_order_remind.setVisibility(View.GONE);
                    mLl_commint_order_failure.setVisibility(View.GONE);
                    mLl_commint_order_comfire.setVisibility(View.VISIBLE);
                    Toast.makeText(ZuCheCheckOrderDetailActivity.this, "订单提交成功！", Toast.LENGTH_SHORT).show();
                } else
                {
                    //订单提交失败
                    mIsSuccessful = false;
                    mLl_commint_order_remind.setVisibility(View.GONE);
                    mLl_commint_order_failure.setVisibility(View.VISIBLE);
                    mLl_commint_order_comfire.setVisibility(View.GONE);
                    Toast.makeText(ZuCheCheckOrderDetailActivity.this, "订单提交失败，请重新提交！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * 订单提交后的对话框提示
     */
    public void commintOrderSecussDialog()
    {
        mDialogLayout = getLayoutInflater().inflate(R.layout.jigouzuche_commint_order_secuss_dialog, null);
        mLl_commint_order_remind = (LinearLayout) mDialogLayout.findViewById(R.id.ll_commint_order_remind);
        mLl_commint_order_failure = (LinearLayout) mDialogLayout.findViewById(R.id.ll_commint_order_failure);
        mLl_commint_order_comfire = (LinearLayout) mDialogLayout.findViewById(R.id.ll_commint_order_comfire);
        mDialogLayout.findViewById(R.id.tv_btn_failure_back).setOnClickListener(this);
        mDialogLayout.findViewById(R.id.tv_btn_comfire_look_list).setOnClickListener(this);
        mBuilder = new AlertDialog.Builder(ZuCheCheckOrderDetailActivity.this).setView(mDialogLayout);
        mAlertDialog = mBuilder.create();
        mAlertDialog.setCanceledOnTouchOutside(false);
        mAlertDialog.setOnKeyListener(new DialogInterface.OnKeyListener()
        {
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event)
            {
                if (mIsSuccessful && keyCode == KeyEvent.KEYCODE_BACK)
                {
                    dialog.dismiss();
                    //此处把dialog dismiss掉，然后把本身的activity finish掉
                    finish();
                    intentToOrderList();
                    return true;
                } else
                {
                    return false;
                }
            }
        });
        mAlertDialog.show();
    }

    /**
     * 订单提交完成后跳转到主界面的订单列表界面
     */
    private void intentToOrderList()
    {
        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setClass(ZuCheCheckOrderDetailActivity.this, DaCheZuCheMainActivity.class);
        intent.putExtra(ConstantDaCheZuChe.IntentKey.BACK_TO_ORDER_LIST_KEY, ConstantDaCheZuChe.IntentKey.JI_GUO_ZU_CHE_BACK_INT);
        startActivity(intent);
    }

    /**
     * 判断用户是否有登陆
     */
    private boolean isLogin()
    {
        SharedPreferences sp = getSharedPreferences("isLogin", Context.MODE_PRIVATE);
        String phoneNum = sp.getString("phoneNum", "");
        if ("".equals(phoneNum))
        {
            return false;
        } else
        {
            return true;
        }
    }

    /**
     * 构造订单的参数
     */
    private Map constructionOrderParams()
    {
        /** 参数：Integer plan_id;//租赁计划id     Timestamp zuchuDate; //租出时间     Timestamp planReturnDate;//计划还车时间
         Double price;//总价     Integer status;//0：企业租车 1；个人租车     Double insurance;//保险金额     Integer getCar;//取车地点（门店ID）
         Integer returnCar;//还车地点（门店ID）     Integer hasDriver;//0:带 1：不带     Integer driverId;//司机的id     Integer carId;//汽车的id
         Integer lei;//套餐的类型     String institutionsCode;//企业账号     Integer accountId;//用户id
         */
        Map<String, String> orderParams = new HashMap<>();
//        orderParams.put("plan_id", mChooseFristInfo.getPlan().getId() + "");//租赁计划id
        orderParams.put("zuchuDate", mChooseFristInfo.getGetCarTime() + "");//租出时间
        orderParams.put("planReturnDate", mChooseFristInfo.getReturnCarTime() + "");//计划还车时间
        orderParams.put("price", mTotalPrice + "");//总价
        orderParams.put("status", 0 + "");//0：企业租车 1；个人租车
        orderParams.put("insurance", mInsurancePrice + "");//保险金额
        orderParams.put("getCar", mGetCarStoresId + "");//取车地点（门店ID）
        orderParams.put("returnCar", mReturnCarStoresId + "");//还车地点（门店ID）
        orderParams.put("hasDriver", mChooseFristInfo.getHasDriver() + "");//是否携带司机 0:带 1：不带
//        orderParams.put("driverId", mChooseFristInfo.getDriverID() + "");//司机的id
        orderParams.put("carId", mChooseFristInfo.getCar().getId() + "");//汽车的id
        orderParams.put("lei", mChooseFristInfo.getCarType() + "");//套餐的类型
        orderParams.put("institutionsCode", mChooseFristInfo.getUnitOfAccount());//企业账号
        orderParams.put("accountId", getSharedPreferences("isLogin", Context.MODE_PRIVATE).getString("id", ""));//用户id
        return orderParams;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null)
        {
            switch (requestCode)
            {
                //取车门店
                case ConstantDaCheZuChe.RequestCode.JIGOUZUCHE_TAKE_CAR_MAP:
                    String stringExtra_get = data.getStringExtra(ConstantDaCheZuChe.IntentKey.STORES_MAP_KEY);
                    mGetCarStoresId = data.getIntExtra(ConstantDaCheZuChe.IntentKey.STORES_ID_KEY, -1);
                    Log.e("onActivityResult ", "mGetCarStoresId " + mGetCarStoresId);
                    mTv_dache_jg_store_name_get.setText(stringExtra_get);
                    break;
                //还车门店
                case ConstantDaCheZuChe.RequestCode.JIGOUZUCHE_RETURN_CAR_MAP:
                    String stringExtra_return = data.getStringExtra(ConstantDaCheZuChe.IntentKey.STORES_MAP_KEY);
                    mReturnCarStoresId = data.getIntExtra(ConstantDaCheZuChe.IntentKey.STORES_ID_KEY, -1);
                    Log.e("onActivityResult ", "mGetCarStoresId " + mReturnCarStoresId);
                    mTv_dache_jg_store_name_return.setText(stringExtra_return);
                    break;
            }

        }
    }
}
