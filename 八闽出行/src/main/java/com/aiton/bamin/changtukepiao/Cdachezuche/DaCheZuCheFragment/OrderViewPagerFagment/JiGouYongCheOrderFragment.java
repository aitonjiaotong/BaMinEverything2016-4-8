package com.aiton.bamin.changtukepiao.Cdachezuche.DaCheZuCheFragment.OrderViewPagerFagment;


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
import com.aiton.administrator.shane_library.shane.utils.UILUtils;
import com.aiton.administrator.shane_library.shane.utils.VolleyListener;
import com.aiton.bamin.changtukepiao.Cdachezuche.QiYeZuChe.ZuCheJiGuoOrderDetailActivity;
import com.aiton.bamin.changtukepiao.Cdachezuche.constant_dachezuche.ConstantDaCheZuChe;
import com.aiton.bamin.changtukepiao.Cdachezuche.models.OrderListInfo;
import com.aiton.bamin.changtukepiao.R;
import com.aiton.bamin.changtukepiao.ZcustomView.CustomerFooter;
import com.android.volley.VolleyError;
import com.andview.refreshview.XRefreshView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class JiGouYongCheOrderFragment extends Fragment {

    private int mPage = 0;
    private View mInflate;
    private ListView mListView_jigouyongche;
    private TextView mTv_jg_order_list_remind;
    private TextView mTv_jg_order_list_unlogin;
    private String mAccountId;
    private LinearLayout mLl_loading_remind_progress_bar;
    private XRefreshView mCustom_view_refresh;
    private int mTotalNum;
    private List<OrderListInfo.ContainsEntity> mContains = new ArrayList<>();
    private MyAdapter mAdapter;
    private String mGetOrderListUrl;

    public JiGouYongCheOrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (mInflate == null) {
            mInflate = inflater.inflate(R.layout.fragment_ji_gou_yong_che_order, null);
            findID();
            initUI();
            if (isLogin()) {
                mTv_jg_order_list_unlogin.setVisibility(View.GONE);//未登陆状态文字提示—不可见
                mLl_loading_remind_progress_bar.setVisibility(View.VISIBLE);//未登陆状态加载框—可见
                //登陆状态
                mContains.clear();
                mPage = 0;
                initData();
            } else {
                mTv_jg_order_list_unlogin.setVisibility(View.VISIBLE);//未登陆状态文字提示—可见
                mLl_loading_remind_progress_bar.setVisibility(View.GONE);//未登陆状态加载框—不可见
            }
        }
        //缓存的rootView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) mInflate.getParent();
        if (parent != null) {
            parent.removeView(mInflate);
        }
        return mInflate;
    }

    private void initData() {
        Map<String, String> params = new HashMap<>();
        params.put("account_id", mAccountId);
        params.put("page", mPage + "");
        mGetOrderListUrl = ConstantDaCheZuChe.URL.GET_ORDER_LIST_INSTITUTIONS;
        HTTPUtils.post(getActivity(), mGetOrderListUrl, params, new VolleyListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }

            @Override
            public void onResponse(String s) {
                Log.e("onResponse ", "企业租车订单 " + s);
                mPage++;
                mLl_loading_remind_progress_bar.setVisibility(View.GONE);//数据加载完成后，加载框—不可见
                OrderListInfo orderListInfo = GsonUtils.parseJSON(s, OrderListInfo.class);
                mTotalNum = orderListInfo.getNum();
                mContains.addAll(orderListInfo.getContains());
                if (mContains != null && mContains.size() > 0) {
                    //有订单数据

                } else {
                    //无订单数据
                    mTv_jg_order_list_remind.setVisibility(View.VISIBLE);//未查到相关订单
                }
                mCustom_view_refresh.stopLoadMore();
                mAdapter.notifyDataSetChanged();
            }
        });
    }


    /**
     * 判断用户是否有登陆
     */
    private boolean isLogin() {
        SharedPreferences sp = getActivity().getSharedPreferences("isLogin", Context.MODE_PRIVATE);
        String phoneNum = sp.getString("phoneNum", "");
        mAccountId = sp.getString("id", "");
        if ("".equals(phoneNum)) {
            return false;
        } else {
            return true;
        }
    }

    private void findID() {
        mListView_jigouyongche = (ListView) mInflate.findViewById(R.id.listView_jigouyongche);
        mTv_jg_order_list_remind = (TextView) mInflate.findViewById(R.id.tv_jg_order_list_remind);//暂未查到您的相关订单…
        mTv_jg_order_list_unlogin = (TextView) mInflate.findViewById(R.id.tv_jg_order_list_unlogin);//未登陆，登陆后可查看相关订单!
        mLl_loading_remind_progress_bar = (LinearLayout) mInflate.findViewById(R.id.ll_loading_remind_progress_bar);
        mCustom_view_refresh = (XRefreshView) mInflate.findViewById(R.id.custom_view_refresh);
    }

    private void initUI() {
        mAdapter = new MyAdapter();
        mListView_jigouyongche.setAdapter(mAdapter);
        mListView_jigouyongche.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ZuCheJiGuoOrderDetailActivity.class);
                intent.putExtra("order_id", mContains.get(position).getOrder().getId());
                startActivityForResult(intent, 1);
            }
        });
        initXRefreshView();


    }

    private void initXRefreshView() {
        mCustom_view_refresh.setPullRefreshEnable(false);
        mCustom_view_refresh.setPullLoadEnable(false);
        mCustom_view_refresh.setPinnedTime(1000);
        mCustom_view_refresh.setAutoLoadMore(true);
        mCustom_view_refresh.setMoveForHorizontal(true);
        mCustom_view_refresh.setCustomFooterView(new CustomerFooter(getActivity()));
        mCustom_view_refresh.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadMore(boolean isSlience) {
                if (mTotalNum > mPage) {
                    initData();
                } else {
                    mCustom_view_refresh.stopLoadMore();
                    Toast.makeText(getActivity(), "没有更多订单了", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return mContains.size();
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
            View layout = getLayoutInflater(getArguments()).inflate(R.layout.jigouyongche_order_listitem, null);
            ImageView iv_car_img = (ImageView) layout.findViewById(R.id.iv_car_img);//显示汽车的图片
            TextView tv_car_name = (TextView) layout.findViewById(R.id.tv_car_name);//显示汽车的名称
            TextView tv_carriage_count = (TextView) layout.findViewById(R.id.tv_carriage_count);//显示汽车的厢数
            TextView tv_displacement = (TextView) layout.findViewById(R.id.tv_displacement);//显示汽车是否为自动档及排量
            TextView tv_car_seat_count = (TextView) layout.findViewById(R.id.tv_car_seat_count);//显示汽车可乘坐人数
            TextView tv_get_car_date = (TextView) layout.findViewById(R.id.tv_get_car_date);//显示取车时间的日期
            TextView tv_get_car_time = (TextView) layout.findViewById(R.id.tv_get_car_time);//显示取车时间的时间
            TextView tv_how_long = (TextView) layout.findViewById(R.id.tv_how_long);//显示租期
            TextView tv_return_car_date = (TextView) layout.findViewById(R.id.tv_return_car_date);//显示还车时间的日期
            TextView tv_return_car_time = (TextView) layout.findViewById(R.id.tv_return_car_time);//显示还车时间的时间
            TextView tv_dache_jg_store_name_get = (TextView) layout.findViewById(R.id.tv_dache_jg_store_name_get);//显示取车门店名称
            TextView tv_dache_jg_store_name_return = (TextView) layout.findViewById(R.id.tv_dache_jg_store_name_return);//显示还车门店名称
            TextView tv_order_num = (TextView) layout.findViewById(R.id.tv_order_list_num);//显示还车门店名称
            TextView tv_order_stage = (TextView) layout.findViewById(R.id.tv_order_list_stage);//显示还车门店名称
            if (mContains != null && mContains.size() > 0) {
                tv_car_name.setText(mContains.get(position).getCar().getModel());
                tv_carriage_count.setText(mContains.get(position).getCar().getBox());
                switch (mContains.get(position).getCar().getZidong()) {
                    case 0:
                        tv_displacement.setText(mContains.get(position).getCar().getPailiang() + "自动");
                        break;
                    case 1:
                        tv_displacement.setText(mContains.get(position).getCar().getPailiang() + "手动");
                        break;
                }
                tv_car_seat_count.setText("可乘坐" + mContains.get(position).getCar().getSeat() + "人");
                tv_get_car_date.setText(getDateToString(mContains.get(position).getOrder().getZuchuDate()));
                tv_get_car_time.setText(getTimeToString(mContains.get(position).getOrder().getZuchuDate()));
                tv_how_long.setText(getHowLong(mContains.get(position).getOrder().getZuchuDate(), mContains.get(position).getOrder().getPlanReturnDate()));
                tv_return_car_date.setText(getDateToString(mContains.get(position).getOrder().getPlanReturnDate()));
                tv_return_car_time.setText(getTimeToString(mContains.get(position).getOrder().getPlanReturnDate()));
                tv_dache_jg_store_name_get.setText(mContains.get(position).getGetCarStore().getName());
                tv_dache_jg_store_name_return.setText(mContains.get(position).getReturnStore().getName());
                tv_order_num.setText(mContains.get(position).getOrder().getId() + "");
                switch (mContains.get(position).getOrder().getFlag()) {
//                    flag:订单状态 0:进行中 1：完成 2:取消 3：等待结算(已还车)
                    case 0:
                        tv_order_stage.setText("订单进行中");
                        break;
                    case 1:
                        tv_order_stage.setText("订单已完成");
                        break;
                    case 2:
                        tv_order_stage.setText("订单已取消");
                        break;
                    case 3:
                        tv_order_stage.setText("订单等待结算(已还车)");
                        break;
                }
                UILUtils.displayImageNoAnim(mContains.get(position).getCar().getImage(), iv_car_img);
            }
            return layout;
        }
    }

    /**
     * 将时间毫秒数转换成日期形式
     */
    private String getDateToString(long l) {
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("MM-dd");
        String date_format = mSimpleDateFormat.format(l);
        return date_format;
    }

    /**
     * 将时间毫秒数转换成星期+时间的形式
     */
    private String getTimeToString(long l) {
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("EE HH:mm");
        String time_format = mSimpleDateFormat.format(l);
        return time_format;
    }

    /**
     * 计算初始时间与结束时间之间相关的时间天数
     */
    private String getHowLong(long starttime, long endting) {
        long howLong = (endting + (2 * 3600 * 1000)) - starttime;
        long l = howLong / (24 * 3600 * 1000);//得到多少天
        if (l > 30) {
            long month = l / 30;
            long day = l % 30;
            if (day == 0.0) {
                return month + "个月";
            } else {
                return month + "个月 + " + day + "天";
            }
        } else {
            return l + "天";
        }
    }


}
