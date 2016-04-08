package com.aiton.bamin.changtukepiao.Cdachezuche.QiYeZuChe;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aiton.administrator.shane_library.shane.utils.GsonUtils;
import com.aiton.administrator.shane_library.shane.utils.HTTPUtils;
import com.aiton.administrator.shane_library.shane.utils.UILUtils;
import com.aiton.administrator.shane_library.shane.utils.VolleyListener;
import com.aiton.bamin.changtukepiao.Cdachezuche.DaCheZhuCheActivity.ZuCheChooseCityActivity;
import com.aiton.bamin.changtukepiao.Cdachezuche.constant_dachezuche.ConstantDaCheZuChe;
import com.aiton.bamin.changtukepiao.Cdachezuche.models.ChooseFristInfo;
import com.aiton.bamin.changtukepiao.Cdachezuche.models.TypeCarListInfo;
import com.aiton.bamin.changtukepiao.R;
import com.aiton.bamin.changtukepiao.ZcustomView.UISwitchButton;
import com.android.volley.VolleyError;
import com.github.jjobes.slidedatetimepicker.SlideDateTimeListener;
import com.github.jjobes.slidedatetimepicker.SlideDateTimePicker;
import com.github.lguipeng.library.animcheckbox.AnimCheckBox;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ZuChenJiGouYongCheActivity extends AppCompatActivity implements View.OnClickListener
{

    private ImageView mIv_dache_jg_back;
    private LinearLayout mLl_dache_jg_choose_city;
    private LinearLayout mLl_dache_jg_choose_time_get;
    private LinearLayout mLl_dache_jg_return_time;
    private RadioGroup mRg_dache_jg_months;
    private Button mBtn_dache_jg_next;
    private TextView mTv_dache_jg_get_time;
    private SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd EE HH:mm");
    private long mGetCarTimeMillis;
    private long mReturnCarTimeMillis;
    private TextView mTv_dache_jg_return_time;
    private TextView mTv_dache_jg_city_name;
    private Date mGetDate;
    private String mUnitOfAccount;
    //获取取车时间
    private SlideDateTimeListener GetslideDateTimePickerListener = new SlideDateTimeListener()
    {
        @Override
        public void onDateTimeSet(Date date)
        {
            boolean before = date.before(mGetDate);
            if (!before)
            {
                mGetCarTimeMillis = date.getTime();
                Log.e("onDateTimeSet ", "mGetCarTimeMillis:取车时间选择器 " + mGetCarTimeMillis);
                mTv_dache_jg_get_time.setText(mSimpleDateFormat.format(date));
            } else
            {
                Toast.makeText(ZuChenJiGouYongCheActivity.this, "请选择合理的取车时间", Toast.LENGTH_SHORT).show();
            }
        }
    };
    //获取还车时间
    private SlideDateTimeListener slideDateTimeListener = new SlideDateTimeListener()
    {
        @Override
        public void onDateTimeSet(Date date)
        {
            Date date1 = new Date(mGetDate.getTime() + 24 * 3600 + 1000);
            boolean before = date.before(date1);
            if (!before)
            {
                mRg_dache_jg_months.clearCheck();
                mReturnCarTimeMillis = date.getTime();
                Log.e("onDateTimeSet ", "mReturnCarTimeMillis:还车时间选择器" + mReturnCarTimeMillis);
                mTv_dache_jg_return_time.setText(mSimpleDateFormat.format(date) + "");
            } else
            {
                Toast.makeText(ZuChenJiGouYongCheActivity.this, "至少租车一天", Toast.LENGTH_SHORT).show();
            }
        }
    };

    private View mConfirm_order_dialog;
    private EditText mEt_dachezuche_dialog_unit_of_account;
    private EditText mEt_dachezuche_dialog_unit_of_password;
    private LinearLayout mLl_dache_reminder_prog;
    private Button mBtn_dachezuche_dialog_comfire;
    private AlertDialog.Builder mDialog;
    private AlertDialog mAlertDialog;
    private TextView mTv_check_failure_reminder;
    //    private LinearLayout mLl_dache_choos_driver;
    private LinearLayout mLl_dache_jg_choose_type_gongwuche;
    private LinearLayout mLl_dache_jg_choose_type_shangwuche;
    private LinearLayout mLl_dache_jg_choose_type_zhifache;
    private LinearLayout mLl_dache_jg_choose_type_yueyeche;
    private LinearLayout mLl_dache_jg_choose_type_pika;
    private LinearLayout mLl_dache_jg_choose_type_keche;
    private int mCarType = -1;
    private int mHasDriver = 1;
    private int mDriverID;
    private TextView mTv_dache_jg_driver_name;
    private ImageView mIv_dailog_close;
    private AnimCheckBox mCk_remember_account;
    private RadioButton mRb_dache_gongwuche;
    private RadioButton mRb_dache_shangwuche;
    private RadioButton mRb_dache_zhifache;
    private RadioButton mRb_dache_yueyeche;
    private RadioButton mRb_dache_pika;
    private RadioButton mRb_dache_keche;

    private int mPage = 0;
    private int mBasePageNum;
    private List<TypeCarListInfo.ContainsEntity> mCarTypeContainsEntityList = new ArrayList<>();
    private PopupWindow mPopupWindow;
    private carTypeListAdapter mCarTypeListAdapter;
    private TextView mTv_btn_get_more;
    private LinearLayout mLl_car_type_list_pop_close;
    private TextView mTv_no_result;
    private UISwitchButton mSwb_has_driver;
    private ListView mLv_car_type_list_info;
    private LinearLayout mLl_user_choose_car_info;
    private ImageView mIv_car_img;
    private TextView mTv_car_name;
    private TextView mTv_carriage_count;
    private TextView mTv_displacement;
    private TextView mTv_car_seat_count;
    private TextView mTv_car_price;
    private int mCarID;
    private TypeCarListInfo.ContainsEntity.CarEntity mCarInfo;
    private TypeCarListInfo.ContainsEntity.PlanEntity mPlan;
    private RelativeLayout mRl_already_rent;
    private boolean mIsChoose;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ji_gou_yong_che);

        //弹出确认企业账号信息的对话框
        showConfirmOrderDialog();
        findViewID();
        initUI();
        setListener();

    }

    /**
     * 弹出企业账号验证的对话框
     */
    public void showConfirmOrderDialog()
    {
        mConfirm_order_dialog = getLayoutInflater().inflate(R.layout.dachezuche_order_detail_dailog_layout, null);
        mCk_remember_account = (AnimCheckBox) mConfirm_order_dialog.findViewById(R.id.ck_remember_account);
        mDialog = new AlertDialog.Builder(ZuChenJiGouYongCheActivity.this);
        mDialog.setView(mConfirm_order_dialog);
        mAlertDialog = mDialog.create();
        mAlertDialog.show();
        mAlertDialog.setCanceledOnTouchOutside(false);
        mEt_dachezuche_dialog_unit_of_account = (EditText) mConfirm_order_dialog.findViewById(R.id.et_dachezuche_dialog_unit_of_account);
        mEt_dachezuche_dialog_unit_of_account.setText(getUnitAccontID());
        mEt_dachezuche_dialog_unit_of_password = (EditText) mConfirm_order_dialog.findViewById(R.id.et_dachezuche_dialog_unit_of_password);
        mLl_dache_reminder_prog = (LinearLayout) mConfirm_order_dialog.findViewById(R.id.ll_dache_reminder_prog);
        mBtn_dachezuche_dialog_comfire = (Button) mConfirm_order_dialog.findViewById(R.id.btn_dachezuche_dialog_comfire);
        mTv_check_failure_reminder = (TextView) mConfirm_order_dialog.findViewById(R.id.tv_check_failure_reminder);
        mIv_dailog_close = (ImageView) mConfirm_order_dialog.findViewById(R.id.iv_dailog_close);
        mIv_dailog_close.setOnClickListener(this);

        mAlertDialog.setOnKeyListener(new DialogInterface.OnKeyListener()
        {
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event)
            {
                if (keyCode == KeyEvent.KEYCODE_BACK)
                {
                    dialog.dismiss();
                    //此处把dialog dismiss掉，然后把本身的activity finish掉
                    finish();
                    return true;
                } else
                {
                    return false;
                }
            }
        });


    }


    /**
     * 验证企业账号信息
     *
     * @param unitOfAccount  企业账号
     * @param unitOfpassword 企业账号密码
     */
    private void verifyTheUnitOfAccount(final String unitOfAccount, String unitOfpassword)
    {
        Map<String, String> params = new HashMap<>();
        params.put("code", unitOfAccount);
        params.put("password", unitOfpassword);
        HTTPUtils.post(ZuChenJiGouYongCheActivity.this, ConstantDaCheZuChe.URL.DACHEZUCHE_COMFIRE_UNIT_INFO, params, new VolleyListener()
        {
            @Override
            public void onResponse(String s)
            {
                if (s.equals("true"))
                {
                    mUnitOfAccount = unitOfAccount;
                    mLl_dache_reminder_prog.setVisibility(View.GONE);
                    SetEditTextClickable(true);
                    mAlertDialog.dismiss();
                    if (mCk_remember_account.isChecked())
                    {
                        saveUnitAccontID(mUnitOfAccount);
                    } else
                    {
                        removeUnitAccontID();
                    }
                } else
                {
                    mLl_dache_reminder_prog.setVisibility(View.GONE);
                    SetEditTextClickable(true);
                    mTv_check_failure_reminder.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onErrorResponse(VolleyError volleyError)
            {

            }
        });
    }

    /**
     * 保存企业账号到本地
     */
    private void saveUnitAccontID(String unitOfAccount)
    {
        SharedPreferences sp = getSharedPreferences("unitAccontID", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("unitAccontID", unitOfAccount);
        edit.commit();
    }

    /**
     * 获取本地保存的企业账号
     */
    public String getUnitAccontID()
    {
        SharedPreferences sp = getSharedPreferences("unitAccontID", Context.MODE_PRIVATE);
        String unitAccontID = sp.getString("unitAccontID", "");
        return unitAccontID;
    }

    /**
     * 清除本地保存的企业账号
     */
    public void removeUnitAccontID()
    {
        SharedPreferences sp = getSharedPreferences("unitAccontID", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.clear();
        edit.commit();
    }

    private void findViewID()
    {
        mIv_dache_jg_back = (ImageView) findViewById(R.id.iv_dache_jg_back);
        mLl_dache_jg_choose_city = (LinearLayout) findViewById(R.id.ll_dache_jg_choose_city);
        mLl_dache_jg_choose_time_get = (LinearLayout) findViewById(R.id.ll_dache_jg_choose_time_get);
        mLl_dache_jg_return_time = (LinearLayout) findViewById(R.id.ll_dache_jg_return_time);
//        mLl_dache_choos_driver = (LinearLayout) findViewById(R.id.ll_dache_choos_driver);//选择司机
        mRg_dache_jg_months = (RadioGroup) findViewById(R.id.rg_dache_jg_months);
        mBtn_dache_jg_next = (Button) findViewById(R.id.btn_dache_jg_next);
        mTv_dache_jg_get_time = (TextView) findViewById(R.id.tv_dache_jg_get_time);
        mTv_dache_jg_return_time = (TextView) findViewById(R.id.tv_dache_jg_return_time);
        mTv_dache_jg_city_name = (TextView) findViewById(R.id.tv_dache_jg_city_name);
        mTv_dache_jg_driver_name = (TextView) findViewById(R.id.tv_dache_jg_driver_name);

        mLl_dache_jg_choose_type_gongwuche = (LinearLayout) findViewById(R.id.ll_dache_jg_choose_type_gongwuche);
        mLl_dache_jg_choose_type_shangwuche = (LinearLayout) findViewById(R.id.ll_dache_jg_choose_type_shangwuche);
        mLl_dache_jg_choose_type_zhifache = (LinearLayout) findViewById(R.id.ll_dache_jg_choose_type_zhifache);
        mLl_dache_jg_choose_type_yueyeche = (LinearLayout) findViewById(R.id.ll_dache_jg_choose_type_yueyeche);
        mLl_dache_jg_choose_type_pika = (LinearLayout) findViewById(R.id.ll_dache_jg_choose_type_pika);
        mLl_dache_jg_choose_type_keche = (LinearLayout) findViewById(R.id.ll_dache_jg_choose_type_keche);

        mRb_dache_gongwuche = (RadioButton) findViewById(R.id.rb_dache_gongwuche);
        mRb_dache_shangwuche = (RadioButton) findViewById(R.id.rb_dache_shangwuche);
        mRb_dache_zhifache = (RadioButton) findViewById(R.id.rb_dache_zhifache);
        mRb_dache_yueyeche = (RadioButton) findViewById(R.id.rb_dache_yueyeche);
        mRb_dache_pika = (RadioButton) findViewById(R.id.rb_dache_pika);
        mRb_dache_keche = (RadioButton) findViewById(R.id.rb_dache_keche);

        mSwb_has_driver = (UISwitchButton) findViewById(R.id.swb_has_driver);
        mLl_user_choose_car_info = (LinearLayout) findViewById(R.id.ll_user_choose_car_info);//显示用户选择的车辆信息布局
        mIv_car_img = (ImageView) findViewById(R.id.iv_car_img);
        mTv_car_name = (TextView) findViewById(R.id.tv_car_name);
        mTv_carriage_count = (TextView) findViewById(R.id.tv_carriage_count);
        mTv_displacement = (TextView) findViewById(R.id.tv_displacement);
        mTv_car_seat_count = (TextView) findViewById(R.id.tv_car_seat_count);
        mTv_car_price = (TextView) findViewById(R.id.tv_car_price);

    }

    private void initUI()
    {
        mTv_dache_jg_get_time.setText(getCurrentTimeMillisForGetCarTimeToString());
        mRg_dache_jg_months.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                switch (checkedId)
                {
                    case R.id.rb_dache_jg_three_months:

                        mTv_dache_jg_return_time.setText(getCurrentTimeMillisForReturnCarTimeToString(3L, 30L));
                        break;
                    case R.id.rb_dache_jg_six_months:
                        mTv_dache_jg_return_time.setText(getCurrentTimeMillisForReturnCarTimeToString(6L, 30L));
                        break;
                    case R.id.rb_dache_jg_twelve_months:
                        mTv_dache_jg_return_time.setText(getCurrentTimeMillisForReturnCarTimeToString(12L, 30L));
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void setListener()
    {
        mIv_dache_jg_back.setOnClickListener(this);
        mLl_dache_jg_choose_city.setOnClickListener(this);
        mLl_dache_jg_choose_time_get.setOnClickListener(this);
        mLl_dache_jg_return_time.setOnClickListener(this);
        mBtn_dache_jg_next.setOnClickListener(this);
        mBtn_dachezuche_dialog_comfire.setOnClickListener(this);
//        mLl_dache_choos_driver.setOnClickListener(this);//选择司机
        mLl_dache_jg_choose_type_gongwuche.setOnClickListener(this);
        mLl_dache_jg_choose_type_shangwuche.setOnClickListener(this);
        mLl_dache_jg_choose_type_zhifache.setOnClickListener(this);
        mLl_dache_jg_choose_type_yueyeche.setOnClickListener(this);
        mLl_dache_jg_choose_type_pika.setOnClickListener(this);
        mLl_dache_jg_choose_type_keche.setOnClickListener(this);

    }

    /**
     * 设置编辑框是否可点击
     */
    public void SetEditTextClickable(boolean b)
    {
        if (b)
        {
            mEt_dachezuche_dialog_unit_of_account.setEnabled(true);
            mEt_dachezuche_dialog_unit_of_password.setEnabled(true);
        } else
        {
            mEt_dachezuche_dialog_unit_of_account.setEnabled(false);
            mEt_dachezuche_dialog_unit_of_password.setEnabled(false);
        }
    }

    /**
     * 获取系统时间并转换时间格式 "yy-MM-dd EE HH:mm:ss"
     * 获取系统时间并推迟两个小时作为:取车时间
     *
     * @param
     */
    public String getCurrentTimeMillisForGetCarTimeToString()
    {
        //默认推迟2小时
        mGetCarTimeMillis = System.currentTimeMillis() + 2 * 3600 * 1000;
        mGetDate = new Date(mGetCarTimeMillis);
        String format = mSimpleDateFormat.format(mGetCarTimeMillis);
        Log.e("onDateTimeSet ", "mGetCarTimeMillis:默认系统时间-->>取车时间 + 2小时" + mGetCarTimeMillis);
        return format;
    }

    /**
     * 获取系统时间并转换时间格式 "yy-MM-dd EE HH:mm:ss"
     * 根据系统时间自动增加相应的时间天数作为:还车时间
     *
     * @param months      多少个月
     * @param daysofmonth 每个月的天数
     * @return
     */
    public String getCurrentTimeMillisForReturnCarTimeToString(Long months, Long daysofmonth)
    {
        mReturnCarTimeMillis = mGetCarTimeMillis + (24L * 3600L * 1000L) * daysofmonth * months;
        String format = mSimpleDateFormat.format(mReturnCarTimeMillis);
        Log.e("onDateTimeSet ", "mReturnCarTimeMillis:默认系统时间-->>还车时间 + 2天  where" + months);
        Log.e("onDateTimeSet ", "mReturnCarTimeMillis:默认系统时间-->>还车时间 + 2天" + mReturnCarTimeMillis);
        return format;
    }

    @Override
    public void onClick(View v)
    {
        Intent intent = new Intent();
        switch (v.getId())
        {
            case R.id.iv_dache_jg_back:
                finish();
                break;
            case R.id.ll_dache_jg_choose_city:
                //跳转到城市选择列表界面
                intent.setClass(ZuChenJiGouYongCheActivity.this, ZuCheChooseCityActivity.class);
                startActivityForResult(intent, ConstantDaCheZuChe.RequestCode.JIGOUZUCHE_TAKE_CAR_CITY);
                break;
            case R.id.ll_dache_jg_choose_time_get:
                //默认推迟两小时
                mGetDate = new Date(System.currentTimeMillis() + 2 * 3600 * 1000);
                new SlideDateTimePicker.Builder(getSupportFragmentManager()).setListener(GetslideDateTimePickerListener).setInitialDate(mGetDate)
//                      .setMinDate(minDate)
//                      .setMaxDate(maxDate)
                        .setIs24HourTime(true)
//                      .setTheme(SlideDateTimePicker.HOLO_DARK)
//                      .setIndicatorColor(Color.parseColor("#990000"))
                        .build().show();
                break;
            case R.id.ll_dache_jg_return_time:
                //默认两天后还车
                Date date = new Date(System.currentTimeMillis() + 24 * 2 * 3600 * 1000);
                new SlideDateTimePicker.Builder(getSupportFragmentManager()).setListener(slideDateTimeListener).setInitialDate(date)
//                      .setMinDate(minDate)
//                      .setMaxDate(maxDate)
                        .setIs24HourTime(true)
//                      .setTheme(SlideDateTimePicker.HOLO_DARK)
//                      .setIndicatorColor(Color.parseColor("#990000"))
                        .build().show();
                break;
            case R.id.btn_dache_jg_next:
                // 跳转到确认订单详情界面
                if ("".equals(mTv_dache_jg_return_time.getText().toString()))
                {
                    Toast.makeText(ZuChenJiGouYongCheActivity.this, "请选择还车时间", Toast.LENGTH_SHORT).show();
                }
                if (!mIsChoose)
                {
                    Toast.makeText(ZuChenJiGouYongCheActivity.this, "请选择租用车型", Toast.LENGTH_SHORT).show();
                }
                if (!"".equals(mTv_dache_jg_return_time.getText().toString()) && mIsChoose)
                {
                    String city_name = mTv_dache_jg_city_name.getText().toString();
                    intent.setClass(ZuChenJiGouYongCheActivity.this, ZuCheCheckOrderDetailActivity.class);
                    mHasDriver = hasDriver();
                    ChooseFristInfo chooseFristInfo = new ChooseFristInfo(mUnitOfAccount, city_name, mGetCarTimeMillis, mReturnCarTimeMillis, mHasDriver, mDriverID, mCarType, mCarID, mCarInfo, mPlan);
                    intent.putExtra(ConstantDaCheZuChe.IntentKey.CHOOSE_FRIST_INFO, chooseFristInfo);
                    startActivity(intent);
                }
                break;
            case R.id.btn_dachezuche_dialog_comfire:
                //企业认证确认按钮
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                String unitOfAccount = mEt_dachezuche_dialog_unit_of_account.getText().toString();
                String unitOfpassword = mEt_dachezuche_dialog_unit_of_password.getText().toString();
                if ("".equals(unitOfAccount) || "".equals(unitOfpassword))
                {
                    Toast.makeText(ZuChenJiGouYongCheActivity.this, "请填写完整的企业账号及密码！", Toast.LENGTH_SHORT).show();
                } else
                {
                    mLl_dache_reminder_prog.setVisibility(View.VISIBLE);
                    SetEditTextClickable(false);
                    verifyTheUnitOfAccount(unitOfAccount, unitOfpassword);
                }
                break;
//            case R.id.ll_dache_choos_driver:
//                //选择司机
//                intent.setClass(ZuChenJiGouYongCheActivity.this, ZuCheChooseDriverActivity.class);
//                startActivityForResult(intent, ConstantDaCheZuChe.RequestCode.JIGOUZUCHE_CHOOSE_DRIVER);
//                break;
            case R.id.ll_dache_jg_choose_type_gongwuche:
                mCarType = 0;
                mPage = 0;
                mIsChoose = false;
                mLl_user_choose_car_info.setVisibility(View.GONE);
                mCarTypeContainsEntityList.clear();
                initTypeCarListData(mCarType + "", mPage + "");
                showPopupwindowForCarTypeList();
                mRb_dache_gongwuche.setChecked(true);
                mRb_dache_shangwuche.setChecked(false);
                mRb_dache_zhifache.setChecked(false);
                mRb_dache_yueyeche.setChecked(false);
                mRb_dache_pika.setChecked(false);
                mRb_dache_keche.setChecked(false);

                break;
            case R.id.ll_dache_jg_choose_type_shangwuche:
                mCarType = 1;
                mPage = 0;
                mIsChoose = false;
                mLl_user_choose_car_info.setVisibility(View.GONE);
                mCarTypeContainsEntityList.clear();
                initTypeCarListData(mCarType + "", mPage + "");
                showPopupwindowForCarTypeList();
                mRb_dache_gongwuche.setChecked(false);
                mRb_dache_shangwuche.setChecked(true);
                mRb_dache_zhifache.setChecked(false);
                mRb_dache_yueyeche.setChecked(false);
                mRb_dache_pika.setChecked(false);
                mRb_dache_keche.setChecked(false);
                break;
            case R.id.ll_dache_jg_choose_type_zhifache:
                mCarType = 2;
                mPage = 0;
                mIsChoose = false;
                mLl_user_choose_car_info.setVisibility(View.GONE);
                mCarTypeContainsEntityList.clear();
                initTypeCarListData(mCarType + "", mPage + "");
                showPopupwindowForCarTypeList();
                mRb_dache_gongwuche.setChecked(false);
                mRb_dache_shangwuche.setChecked(false);
                mRb_dache_zhifache.setChecked(true);
                mRb_dache_yueyeche.setChecked(false);
                mRb_dache_pika.setChecked(false);
                mRb_dache_keche.setChecked(false);
                break;
            case R.id.ll_dache_jg_choose_type_yueyeche:
                mCarType = 3;
                mPage = 0;
                mIsChoose = false;
                mLl_user_choose_car_info.setVisibility(View.GONE);
                mCarTypeContainsEntityList.clear();
                initTypeCarListData(mCarType + "", mPage + "");
                showPopupwindowForCarTypeList();
                mRb_dache_gongwuche.setChecked(false);
                mRb_dache_shangwuche.setChecked(false);
                mRb_dache_zhifache.setChecked(false);
                mRb_dache_yueyeche.setChecked(true);
                mRb_dache_pika.setChecked(false);
                mRb_dache_keche.setChecked(false);
                break;
            case R.id.ll_dache_jg_choose_type_pika:
                mCarType = 4;
                mPage = 0;
                mIsChoose = false;
                mLl_user_choose_car_info.setVisibility(View.GONE);
                mCarTypeContainsEntityList.clear();
                initTypeCarListData(mCarType + "", mPage + "");
                showPopupwindowForCarTypeList();
                mRb_dache_gongwuche.setChecked(false);
                mRb_dache_shangwuche.setChecked(false);
                mRb_dache_zhifache.setChecked(false);
                mRb_dache_yueyeche.setChecked(false);
                mRb_dache_pika.setChecked(true);
                mRb_dache_keche.setChecked(false);
                break;
            case R.id.ll_dache_jg_choose_type_keche:
                mCarType = 5;
                mPage = 0;
                mIsChoose = false;
                mLl_user_choose_car_info.setVisibility(View.GONE);
                mCarTypeContainsEntityList.clear();
                initTypeCarListData(mCarType + "", mPage + "");
                showPopupwindowForCarTypeList();
                mRb_dache_gongwuche.setChecked(false);
                mRb_dache_shangwuche.setChecked(false);
                mRb_dache_zhifache.setChecked(false);
                mRb_dache_yueyeche.setChecked(false);
                mRb_dache_pika.setChecked(false);
                mRb_dache_keche.setChecked(true);
                break;
            case R.id.iv_dailog_close:
                mAlertDialog.dismiss();
                finish();
                break;
            case R.id.ll_car_type_list_pop_close:
                mPopupWindow.dismiss();
                break;
            case R.id.tv_btn_get_more:
                if (mBasePageNum > mPage)
                {
                    mTv_btn_get_more.setEnabled(true);
                    mTv_btn_get_more.setText("加载更多数据！");
                    initTypeCarListData(mCarType + "", mPage + "");
                } else
                {
                    mTv_btn_get_more.setText("无更多数据！");
                    mTv_btn_get_more.setEnabled(false);
                }
                break;
            default:
                break;
        }
    }

    /**
     * 弹出车型对应的车辆列表信息
     */
    private void initTypeCarListData(String lei, String page)
    {
        Map<String, String> params = new HashMap<>();
        params.put("lei", lei);
        params.put("page", page);
        HTTPUtils.post(ZuChenJiGouYongCheActivity.this, ConstantDaCheZuChe.URL.GET_CAR_LIST, params, new VolleyListener()
        {
            @Override
            public void onErrorResponse(VolleyError volleyError)
            {

            }

            @Override
            public void onResponse(String s)
            {
                if (s != null && !"".equals(s))
                {
                    mPage++;
                    TypeCarListInfo typeCarListInfo = GsonUtils.parseJSON(s, TypeCarListInfo.class);
                    mBasePageNum = typeCarListInfo.getNum();
                    mCarTypeContainsEntityList.addAll(typeCarListInfo.getContains());
                    if (mBasePageNum == mPage)
                    {
                        mTv_btn_get_more.setVisibility(View.GONE);
                    } else
                    {
                        mTv_btn_get_more.setVisibility(View.VISIBLE);
                    }
                    if (mCarTypeContainsEntityList != null && mCarTypeContainsEntityList.size() > 0)
                    {
                        mTv_no_result.setVisibility(View.GONE);
                        mTv_btn_get_more.setVisibility(View.VISIBLE);

                    } else
                    {
                        mTv_no_result.setVisibility(View.VISIBLE);
                        mTv_btn_get_more.setVisibility(View.GONE);
                    }
                    mCarTypeListAdapter.notifyDataSetChanged();
                }
            }
        });

    }

    /**
     * 弹出车辆类型对应的汽车列表
     */
    public void showPopupwindowForCarTypeList()
    {
        View inflate = getLayoutInflater().inflate(R.layout.car_type_list_pop_layout, null);
        mLl_car_type_list_pop_close = (LinearLayout) inflate.findViewById(R.id.ll_car_type_list_pop_close);
        mTv_no_result = (TextView) inflate.findViewById(R.id.tv_no_result);
        mTv_btn_get_more = (TextView) inflate.findViewById(R.id.tv_btn_get_more);
        mLl_car_type_list_pop_close.setOnClickListener(this);
        mTv_btn_get_more.setOnClickListener(this);
        mLv_car_type_list_info = (ListView) inflate.findViewById(R.id.lv_car_type_list_info);
        mLv_car_type_list_info.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                mIsChoose = true;
                mLl_user_choose_car_info.setVisibility(View.VISIBLE);
                if (1 == mCarTypeContainsEntityList.get(position).getCar().getStatus())
                {
                    Toast.makeText(ZuChenJiGouYongCheActivity.this, "该车辆已出租!", Toast.LENGTH_SHORT).show();
                } else
                {
                    mCarID = mCarTypeContainsEntityList.get(position).getCar().getId();
                    mCarInfo = mCarTypeContainsEntityList.get(position).getCar();
                    mPlan = mCarTypeContainsEntityList.get(position).getPlan();
                    mLl_user_choose_car_info.setVisibility(View.VISIBLE);
                    UILUtils.displayImageNoAnim(mCarTypeContainsEntityList.get(position).getCar().getImage(), mIv_car_img);
                    mTv_car_name.setText(mCarTypeContainsEntityList.get(position).getCar().getModel() + mCarTypeContainsEntityList.get(position).getCar().getType());
                    mTv_carriage_count.setText(mCarTypeContainsEntityList.get(position).getCar().getBox());
                    if (0 == mCarTypeContainsEntityList.get(position).getCar().getZidong())
                    {
                        mTv_displacement.setText(mCarTypeContainsEntityList.get(position).getCar().getPailiang() + "自动");
                    } else
                    {
                        mTv_displacement.setText(mCarTypeContainsEntityList.get(position).getCar().getPailiang() + "手动");

                    }
                    mTv_car_seat_count.setText("乘坐" + mCarTypeContainsEntityList.get(position).getCar().getSeat() + "人");
                    mTv_car_price.setText(mCarTypeContainsEntityList.get(position).getPlan().getPrice() + "");
                    mPopupWindow.dismiss();
                }
            }
        });
        mCarTypeListAdapter = new carTypeListAdapter();
        mLv_car_type_list_info.setAdapter(mCarTypeListAdapter);
        //最后一个参数为true，点击PopupWindow消失,宽必须为match，不然肯呢个会导致布局显示不完全
        mPopupWindow = new PopupWindow(inflate, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        //设置外部点击无效
        mPopupWindow.setOutsideTouchable(false);
        //设置背景变暗
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.7f;
        getWindow().setAttributes(lp);
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener()
        {
            @Override
            public void onDismiss()
            {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        BitmapDrawable bitmapDrawable = new BitmapDrawable();
        mPopupWindow.setBackgroundDrawable(bitmapDrawable);
        mPopupWindow.showAtLocation(inflate, Gravity.BOTTOM, 0, 0);
    }

    /**
     * 判断是否选择携带司机
     *
     * @return
     */
    private int hasDriver()
    {
        if (mSwb_has_driver.isChecked())
        {
            return 0;
        } else
        {
            return 1;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null)
        {
            if (requestCode == ConstantDaCheZuChe.RequestCode.JIGOUZUCHE_TAKE_CAR_CITY && resultCode == ConstantDaCheZuChe.ResultCode.CHOOSE_CITY)
            {
                mTv_dache_jg_city_name.setText(data.getStringExtra(ConstantDaCheZuChe.IntentKey.CHOOSE_CITY));
            }
            //判断是否有选择司机
//            if (requestCode == ConstantDaCheZuChe.RequestCode.JIGOUZUCHE_CHOOSE_DRIVER && resultCode == ConstantDaCheZuChe.ResultCode.JIGOUZUCHE_CHOOSE_DRIVER)
//            {
//                mHasDriver = 0;
//                mDriverID = data.getIntExtra(ConstantDaCheZuChe.IntentKey.DRIVER_ID, -1);
//                mTv_dache_jg_driver_name.setText(data.getStringExtra(ConstantDaCheZuChe.IntentKey.DRIVER_NAME));
//            }
        }
    }

    class carTypeListAdapter extends BaseAdapter
    {

        @Override
        public int getCount()
        {
            return mCarTypeContainsEntityList.size();
        }

        @Override
        public Object getItem(int position)
        {
            return null;
        }

        @Override
        public long getItemId(int position)
        {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            View layout = getLayoutInflater().inflate(R.layout.dachezuche_choose_car_type_list_item, null);
            mRl_already_rent = (RelativeLayout) layout.findViewById(R.id.rl_already_rent);
            ImageView iv_car_img = (ImageView) layout.findViewById(R.id.iv_car_img);//车型图片
            TextView tv_car_name = (TextView) layout.findViewById(R.id.tv_car_name);//车型名称
            TextView tv_car_price = (TextView) layout.findViewById(R.id.tv_car_price);//租金
            TextView tv_carriage_count = (TextView) layout.findViewById(R.id.tv_carriage_count);//厢数(三厢)
            TextView tv_displacement = (TextView) layout.findViewById(R.id.tv_displacement);//排量(1.4自动)
            TextView tv_car_seat_count = (TextView) layout.findViewById(R.id.tv_car_seat_count);//可乘坐位数(乘坐5人)
            if (mCarTypeContainsEntityList != null && mCarTypeContainsEntityList.size() > 0)
            {
                if (1 == mCarTypeContainsEntityList.get(position).getCar().getStatus())
                {
                    mRl_already_rent.setVisibility(View.VISIBLE);
                } else
                {
                    mRl_already_rent.setVisibility(View.GONE);
                }
                TypeCarListInfo.ContainsEntity carInfo = mCarTypeContainsEntityList.get(position);
                UILUtils.displayImageNoAnim(carInfo.getCar().getImage(), iv_car_img);
                tv_car_name.setText(carInfo.getCar().getModel() + carInfo.getCar().getType());
                tv_car_price.setText(carInfo.getPlan().getPrice() + "");
                tv_carriage_count.setText(carInfo.getCar().getBox());
                if (carInfo.getCar().getZidong() == 0)
                {
                    tv_displacement.setText(carInfo.getCar().getPailiang() + "自动");
                } else
                {
                    tv_displacement.setText(carInfo.getCar().getPailiang() + "手动");
                }
                tv_car_seat_count.setText("乘坐" + carInfo.getCar().getSeat() + "人");
            }
            return layout;
        }
    }

}
