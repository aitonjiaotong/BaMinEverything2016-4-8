package com.aiton.bamin.changtukepiao.Zeverything.everything_fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aiton.administrator.shane_library.shane.utils.GsonUtils;
import com.aiton.administrator.shane_library.shane.utils.HTTPUtils;
import com.aiton.administrator.shane_library.shane.utils.VolleyListener;
import com.aiton.bamin.changtukepiao.Abusline.busline_aiton.BusOnLineWebActivity;
import com.aiton.bamin.changtukepiao.Bchangtukepiao.activity.MainActivity;
import com.aiton.bamin.changtukepiao.Bchangtukepiao.constant.ConstantTicket;
import com.aiton.bamin.changtukepiao.Bchangtukepiao.models.about_banner.BannerInfo;
import com.aiton.bamin.changtukepiao.Cdachezuche.DaCheZhuCheActivity.DaCheZuCheMainActivity;
import com.aiton.bamin.changtukepiao.Ddaibanpaotui.model.DaBanPaoTuiGridViewItemInfo;
import com.aiton.bamin.changtukepiao.Flvyoulvxing.MainlvyouActivity;
import com.aiton.bamin.changtukepiao.Gkuaidibao.activity.KuaiDiActivity;
import com.aiton.bamin.changtukepiao.R;
import com.aiton.bamin.changtukepiao.ZcustomView.ViewPagerIndicator;
import com.android.volley.VolleyError;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainEverytingFragment extends Fragment implements View.OnClickListener
{

    private View mLayout;
    private LinearLayout mLl_onlinebus;
    private LinearLayout mLl_ticket;
    private LinearLayout mLl_taxi;
    private List<BannerInfo> bannerData = new ArrayList<BannerInfo>();
    private int mPagerCount = Integer.MAX_VALUE / 3;
    private ViewPager mViewPager_banner;
    private ViewPagerIndicator mViewPagerIndicator;
    private boolean isFrist = true;
    private boolean mDragging;
    private MyPagerAdapter mPagerAdapter;
    private GridView mGv_everything_classify;
    private List<DaBanPaoTuiGridViewItemInfo> mGridViewItemInfo = new ArrayList<DaBanPaoTuiGridViewItemInfo>();
    private MyGridViewAdapter mMyGridViewAdapter;

    public MainEverytingFragment()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        mLayout = inflater.inflate(R.layout.fragment_main, null);
        initData();
        findID();
        initUI();
        setListener();
        return mLayout;
    }




    private void initData()
    {
        //初始化Banner数据
        initBannerData();
        initGridViewClassifyData();
    }

    /**
     * 加载广告条数据
     */

    private void initBannerData()
    {
        HTTPUtils.get(getActivity(), ConstantTicket.URL.GET_BANNER_IMG, new VolleyListener()
        {
            @Override
            public void onErrorResponse(VolleyError volleyError)
            {
            }

            @Override
            public void onResponse(String s)
            {
                Type type = new TypeToken<ArrayList<BannerInfo>>()
                {
                }.getType();
                bannerData = GsonUtils.parseJSONArray(s, type);
                mViewPager_banner.setAdapter(mPagerAdapter);
            }
        });
    }

    /**
     * 初始化主界面子分类的数据
     */
    private void initGridViewClassifyData()
    {
        mGridViewItemInfo.clear();
        mGridViewItemInfo.add(new DaBanPaoTuiGridViewItemInfo("吃喝玩乐", R.mipmap.ic_01_gray));
        mGridViewItemInfo.add(new DaBanPaoTuiGridViewItemInfo("代办跑腿", R.mipmap.ic_02_gray));
        mGridViewItemInfo.add(new DaBanPaoTuiGridViewItemInfo("汽车管家", R.mipmap.ic_03_gray));
        mGridViewItemInfo.add(new DaBanPaoTuiGridViewItemInfo("旅游旅行", R.mipmap.ic_04));
        mGridViewItemInfo.add(new DaBanPaoTuiGridViewItemInfo("快递物流", R.mipmap.ic_05));
        mGridViewItemInfo.add(new DaBanPaoTuiGridViewItemInfo("保险超市", R.mipmap.ic_06_gray));
        mGridViewItemInfo.add(new DaBanPaoTuiGridViewItemInfo("特快商城", R.mipmap.ic_07_gray));
        mGridViewItemInfo.add(new DaBanPaoTuiGridViewItemInfo("优惠充值", R.mipmap.ic_08_gray));
    }


    private void findID()
    {
        mLl_onlinebus = (LinearLayout) mLayout.findViewById(R.id.ll_onlinebus);
        mLl_ticket = (LinearLayout) mLayout.findViewById(R.id.ll_ticket);
        mLl_taxi = (LinearLayout) mLayout.findViewById(R.id.ll_taxi);
        mGv_everything_classify = (GridView) mLayout.findViewById(R.id.gv_everything_classify);
    }

    private void initUI()
    {
        initBanner();
        initGridView();
    }

    private void initGridView()
    {
        mMyGridViewAdapter = new MyGridViewAdapter();
        mGv_everything_classify.setAdapter(mMyGridViewAdapter);
        mGv_everything_classify.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent intent = new Intent();
                switch (position)
                {
                    case 0://吃喝玩乐
//                        intent.setClass(getActivity(), MainCheHeWanLeActivity.class);
//                        startActivity(intent);
//                        animFromSmallToBigIN();
                        break;
                    case 1://代办跑腿
//                        intent.setClass(getActivity(), DaiBanPaoTuiMainActivity.class);
//                        startActivity(intent);
//                        animFromSmallToBigIN();
                        break;
                    case 2://汽车管家
//                        intent.setClass(getActivity(), QiCheGuanJiaMainActivity.class);
//                        startActivity(intent);
//                        animFromSmallToBigIN();
                        break;
                    case 3://旅游旅行
                        intent.setClass(getActivity(), MainlvyouActivity.class);
                        startActivity(intent);
                        animFromSmallToBigIN();
                        break;
                    case 4://快递物流
                        intent.setClass(getActivity(), KuaiDiActivity.class);
                        startActivity(intent);
                        animFromSmallToBigIN();
                        break;
                    case 5://保险超市
//                        intent.setClass(getActivity(), com.aiton.bamin.changtukepiao.Hbaoxianchaoshi.MainActivity.class);
//                        startActivity(intent);
//                        animFromSmallToBigIN();
                        break;
                    case 6://特快商城
//                        intent.setClass(getActivity(), TeKuaiShangChengActivity.class);
//                        startActivity(intent);
//                        animFromSmallToBigIN();
                        break;
                    case 7://优惠充值
//                        intent.setClass(getActivity(), YuoHuiChongZhiActivity.class);
//                        startActivity(intent);
//                        animFromSmallToBigIN();
                        break;
                }

            }
        });
    }

    /**
     * 设置广告条
     */
    private void initBanner()
    {
        mViewPager_banner = (ViewPager) mLayout.findViewById(R.id.vp_headerview_pager);
        mPagerAdapter = new MyPagerAdapter(getChildFragmentManager());
        mViewPager_banner.addOnPageChangeListener(new BannerOnPageChangeListener());
        mViewPagerIndicator = (ViewPagerIndicator) mLayout.findViewById(R.id.ViewPagerIndicator);
        if (isFrist)
        {
            autoScroll();
        }
    }

    private void setListener()
    {
        mLl_onlinebus.setOnClickListener(this);
        mLl_ticket.setOnClickListener(this);
        mLl_taxi.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        Intent intent = new Intent();
        switch (v.getId())
        {
            case R.id.ll_onlinebus:

                intent.setClass(getActivity(), BusOnLineWebActivity.class);
                startActivity(intent);
                animFromSmallToBigIN();
                /**原生本地公交查询相关Activity*/
                /*if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                {
                    //申请WRITE_EXTERNAL_STORAGE权限
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
                } else
                {
                    intent.setClass(getActivity(), MainBusLineActivity.class);
                    startActivity(intent);
                    animFromSmallToBigIN();
                }*/
                break;
            case R.id.ll_ticket:
                intent.setClass(getActivity(), MainActivity.class);
                startActivity(intent);
                animFromSmallToBigIN();
                break;
            case R.id.ll_taxi:
                intent.setClass(getActivity(), DaCheZuCheMainActivity.class);
                startActivity(intent);
                animFromSmallToBigIN();
                break;

        }
    }

    /**
     * 广告条Fragment的适配器
     */
    class MyPagerAdapter extends FragmentPagerAdapter
    {

        public MyPagerAdapter(FragmentManager fm)
        {
            super(fm);
        }

        @Override
        public Fragment getItem(int position)
        {
            int index = position % bannerData.size();
            return new BannerFragment(index, bannerData.get(index).getUrl(), bannerData.get(index).getUrl2());
        }

        @Override
        public int getCount()
        {
            return mPagerCount;
        }
    }

    /**
     * 广告条页数改变监听
     */
    class BannerOnPageChangeListener implements ViewPager.OnPageChangeListener
    {
        public void onPageScrollStateChanged(int state)
        {
            switch (state)
            {
                case ViewPager.SCROLL_STATE_IDLE:
                    mDragging = false;
                    break;
                case ViewPager.SCROLL_STATE_DRAGGING:
                    mDragging = true;
                    break;
                case ViewPager.SCROLL_STATE_SETTLING:
                    mDragging = false;
                    break;
                default:
                    break;
            }
        }

        public void onPageScrolled(int position, float arg1, int arg2)
        {
            position = position % 3;
            mViewPagerIndicator.move(arg1, position);
        }

        public void onPageSelected(int arg0)
        {
        }
    }

    /**
     * 广告条自动播放的方法
     */
    private void autoScroll()
    {
        mViewPager_banner.setCurrentItem(mPagerCount / 2);
        mViewPager_banner.postDelayed(new Runnable()
        {
            public void run()
            {
                int position = mViewPager_banner.getCurrentItem() + 1;
                if (!mDragging)
                {
                    isFrist = false;
                    mViewPager_banner.setCurrentItem(position);
                }
                mViewPager_banner.postDelayed(this, 3000);
            }
        }, 3000);
    }

    /**
     * 从小到大打开动画
     */
    private void animFromSmallToBigIN()
    {
        getActivity().overridePendingTransition(R.anim.magnify_fade_in, R.anim.fade_out);
    }

    /**
     * GridView的Adapter适配器
     */
    class MyGridViewAdapter extends BaseAdapter
    {

        @Override
        public int getCount()
        {
            return mGridViewItemInfo.size();
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
            View layout = getActivity().getLayoutInflater().inflate(R.layout.layout_dabanpaotui_gridview_item, null);
            ImageView iv_item_img = (ImageView) layout.findViewById(R.id.iv_dabanpaotui_grid_item_img);
            TextView tv_item_name = (TextView) layout.findViewById(R.id.tv_dabanpaotui_grid_item_name);
            if (mGridViewItemInfo != null && mGridViewItemInfo.size() > 0)
            {
                iv_item_img.setImageResource(mGridViewItemInfo.get(position).getIconId());
                tv_item_name.setText(mGridViewItemInfo.get(position).getName());
            }
            return layout;
        }
    }
}
