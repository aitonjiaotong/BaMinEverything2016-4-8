package com.aiton.bamin.changtukepiao.Zeverything.everything_fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.aiton.administrator.shane_library.shane.utils.GsonUtils;
import com.aiton.administrator.shane_library.shane.utils.HTTPUtils;
import com.aiton.administrator.shane_library.shane.utils.VolleyListener;
import com.aiton.bamin.changtukepiao.Bchangtukepiao.activity.OrderDeatilActivity;
import com.aiton.bamin.changtukepiao.Cdachezuche.QiYeZuChe.ZuCheJiGuoOrderDetailActivity;
import com.aiton.bamin.changtukepiao.R;
import com.aiton.bamin.changtukepiao.ZcustomView.CustomerFooter;
import com.aiton.bamin.changtukepiao.Zeverything.constant.EverythingConstant;
import com.aiton.bamin.changtukepiao.Zeverything.model.EveryThingOrderList;
import com.android.volley.VolleyError;
import com.andview.refreshview.XRefreshView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderEverythingFragment extends Fragment
{

    private ListView mLv_everything_order_list;
    private EverythingOrderAdapter mEverythingOrderAdapter;
    private View mLayout;
    private LayoutInflater mLayoutInflater;
    private boolean mIsLogin;
    private String mAccountID;
    private int mPage = 0;
    private TextView mTv_is_login_remind;
    private LinearLayout mLl_loading;
    private List<EveryThingOrderList.CodeEntity.ContainsEntity> mContains = new ArrayList<>();
    private XRefreshView mCustom_view_refresh;
    private int mTotalNum;

    public OrderEverythingFragment()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        mLayoutInflater = inflater;
        mLayout = inflater.inflate(R.layout.fragment_order_everything, null);
        getAccountID();
        findViewID();
        initUI();
        if (mIsLogin)
        {
            mTv_is_login_remind.setVisibility(View.GONE);
            mLl_loading.setVisibility(View.VISIBLE);
            initData();
        } else
        {
            mTv_is_login_remind.setVisibility(View.VISIBLE);
            mLl_loading.setVisibility(View.GONE);
        }
        return mLayout;
    }

    private void getAccountID()
    {
        SharedPreferences sp = getActivity().getSharedPreferences("isLogin", Context.MODE_PRIVATE);
        String phoneNum = sp.getString("phoneNum", "");
        if ("".equals(phoneNum))
        {
            mIsLogin = false;
        } else
        {
            mAccountID = sp.getString("id", "");
            mIsLogin = true;
        }

    }


    private void initData()
    {
        Map<String, String> params = new HashMap<>();
        params.put("account_id", mAccountID);
        params.put("page", mPage + "");
        HTTPUtils.post(getActivity(), EverythingConstant.GET_ALL_ORDER_LIST, params, new VolleyListener()
        {
            @Override
            public void onErrorResponse(VolleyError volleyError)
            {

            }

            @Override
            public void onResponse(String s)
            {
                Log.e("onResponse ", "onResponse " + s);
                mPage++;
                mLl_loading.setVisibility(View.GONE);
                EveryThingOrderList everyThingOrderList = GsonUtils.parseJSON(s, EveryThingOrderList.class);
                mTotalNum = everyThingOrderList.getCode().getNum();
                mContains.addAll(everyThingOrderList.getCode().getContains());
                mCustom_view_refresh.stopLoadMore();
                mEverythingOrderAdapter.notifyDataSetChanged();
            }
        });
    }

    private void findViewID()
    {
        mLv_everything_order_list = (ListView) mLayout.findViewById(R.id.lv_everything_order_list);
        mTv_is_login_remind = (TextView) mLayout.findViewById(R.id.tv_is_login_remind);
        mLl_loading = (LinearLayout) mLayout.findViewById(R.id.ll_loading);
        mCustom_view_refresh = (XRefreshView) mLayout.findViewById(R.id.custom_view_refresh);
    }

    private void initUI()
    {
        mEverythingOrderAdapter = new EverythingOrderAdapter();
        mLv_everything_order_list.setAdapter(mEverythingOrderAdapter);
        mLv_everything_order_list.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                if (mContains != null && mContains.size() > 0)
                {
                    if (!"".equals(mContains.get(position).getOrder_id()))
                    {
                        Intent intent = new Intent();
                        if (1 == mContains.get(position).getType())
                        {
                            intent.setClass(getActivity(), ZuCheJiGuoOrderDetailActivity.class);
                            intent.putExtra("order_id", Integer.parseInt(mContains.get(position).getOrder_id()));
                            intent.putExtra("everyting_order_list", "everyting_order_list");
                            Log.e("onItemClick ", "onItemClick " + mContains.get(position).getOrder_id());
                        } else if (0 == mContains.get(position).getType())
                        {
                            intent.setClass(getActivity(), OrderDeatilActivity.class);
                            intent.putExtra("BookLogAID", mContains.get(position).getOrder_id());
                            intent.putExtra("everyting_order_list", "everyting_order_list");
                        } else
                        {
                            Toast.makeText(getActivity(), "未找到相关页面\n请向我们反馈，谢谢!", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        startActivity(intent);
                    }
                }
            }
        });
        initXRefreshView();
    }

    private void initXRefreshView()
    {
        mCustom_view_refresh.setPullRefreshEnable(false);
        mCustom_view_refresh.setPullLoadEnable(false);
        mCustom_view_refresh.setPinnedTime(1000);
        mCustom_view_refresh.setAutoLoadMore(true);
        mCustom_view_refresh.setMoveForHorizontal(true);
        mCustom_view_refresh.setCustomFooterView(new CustomerFooter(getActivity()));
        mCustom_view_refresh.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener()
        {
            @Override
            public void onRefresh()
            {

            }

            @Override
            public void onLoadMore(boolean isSlience)
            {
                if (mTotalNum > mPage)
                {
                    initData();
                } else
                {
                    mCustom_view_refresh.stopLoadMore();
                    Toast.makeText(getActivity(), "没有更多订单了", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    class EverythingOrderAdapter extends BaseAdapter
    {

        @Override
        public int getCount()
        {
            return mContains.size();
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
            View listViewLayout = mLayoutInflater.inflate(R.layout.layout_everything_order_list_item, null);
            TextView tv_order_date = (TextView) listViewLayout.findViewById(R.id.tv_order_date);
            TextView tv_order_week = (TextView) listViewLayout.findViewById(R.id.tv_order_week);
            TextView tv_order_time = (TextView) listViewLayout.findViewById(R.id.tv_order_time);
            ImageView iv_order_list_type = (ImageView) listViewLayout.findViewById(R.id.iv_order_list_type);
            TextView tv_order_type_name = (TextView) listViewLayout.findViewById(R.id.tv_order_type_name);
            TextView tv_order_price = (TextView) listViewLayout.findViewById(R.id.tv_order_price);

            TextView tv_order_list_stage = (TextView) listViewLayout.findViewById(R.id.tv_order_list_stage);
            TextView tv_order_list_msg = (TextView) listViewLayout.findViewById(R.id.tv_order_list_msg);
            //getFlag 租车: 0 已结算  1 等待结算    票务 1 未支付 0 已支付 2 较早
            if (mContains != null && mContains.size() > 0)
            {
                if (1 == mContains.get(position).getType())
                {
                    tv_order_type_name.setText("租车·用车");
                    iv_order_list_type.setImageResource(R.mipmap.car_order_2x);
                    if (0 == mContains.get(position).getFlag())
                    {
                        tv_order_list_stage.setText("已结算");
                    } else if (1 == mContains.get(position).getFlag())
                    {
                        tv_order_list_stage.setText("等待结算中");
                    }
                } else if (0 == mContains.get(position).getType())
                {
                    tv_order_type_name.setText("长途客票");
                    iv_order_list_type.setImageResource(R.mipmap.kepiaoorder_2x);
                    if (0 == mContains.get(position).getFlag())
                    {
                        tv_order_list_stage.setText("已支付");
                    } else if (1 == mContains.get(position).getFlag())
                    {
                        tv_order_list_stage.setText("未支付");
                    } else if (2 == mContains.get(position).getFlag())
                    {
                        tv_order_list_stage.setText("点击查看详情");
                    }
                }
                tv_order_list_msg.setText(mContains.get(position).getYuliu());
                tv_order_date.setText(getDateToString(mContains.get(position).getDate()));
                tv_order_week.setText(getWeekToString(mContains.get(position).getDate()));
                tv_order_time.setText(getTimeToString(mContains.get(position).getDate()));

                tv_order_price.setText(mContains.get(position).getPrice() + "");
            }

            return listViewLayout;
        }
    }


    private String getDateToString(long l)
    {
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String time_format = mSimpleDateFormat.format(l);
        return time_format;
    }

    private String getWeekToString(long l)
    {
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("EE");
        String time_format = mSimpleDateFormat.format(l);
        return time_format;
    }

    private String getTimeToString(long l)
    {
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("HH:mm");
        String time_format = mSimpleDateFormat.format(l);
        return time_format;
    }
}
