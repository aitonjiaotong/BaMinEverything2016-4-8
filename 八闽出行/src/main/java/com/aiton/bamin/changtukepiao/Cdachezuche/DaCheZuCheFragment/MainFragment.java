package com.aiton.bamin.changtukepiao.Cdachezuche.DaCheZuCheFragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.aiton.administrator.shane_library.shane.utils.GsonUtils;
import com.aiton.administrator.shane_library.shane.utils.HTTPUtils;
import com.aiton.administrator.shane_library.shane.utils.VolleyListener;
import com.aiton.bamin.changtukepiao.Bchangtukepiao.constant.ConstantTicket;
import com.aiton.bamin.changtukepiao.Zeverything.everything_fragment.BannerFragment;
import com.aiton.bamin.changtukepiao.Bchangtukepiao.models.about_banner.BannerInfo;
import com.aiton.bamin.changtukepiao.Cdachezuche.DaChe.DaCheActivity;
import com.aiton.bamin.changtukepiao.Cdachezuche.QiYeZuChe.ZuChenJiGouYongCheActivity;
import com.aiton.bamin.changtukepiao.Cdachezuche.ZiJiaZuChe.ZiJiaZuCheActivity;
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
public class MainFragment extends Fragment implements View.OnClickListener
{

    private List<BannerInfo> bannerData = new ArrayList<BannerInfo>();
    private ViewPager mViewPager_banner;
    private int mPagerCount = Integer.MAX_VALUE / 3;
    private boolean mDragging;
    private boolean isFrist = true;
    private ViewPagerIndicator mViewPagerIndicator;
    private ImageView mIv_qicheguanjia_back;
    private View mInflate;

    public MainFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        if (mInflate == null)
        {
            mInflate = inflater.inflate(R.layout.fragment_main2, null);
            initBannerData();
            findViewID();
            initUI();
            setListener();
        }
        //缓存的rootView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) mInflate.getParent();
        if (parent != null) {
            parent.removeView(mInflate);
        }
        return mInflate;
    }

    private void setListener()
    {
        mIv_qicheguanjia_back.setOnClickListener(this);
        mInflate.findViewById(R.id.ll_rela_zijiazuche).setOnClickListener(this);
        mInflate.findViewById(R.id.ll_rela_jigouyongche).setOnClickListener(this);
        mInflate.findViewById(R.id.ll_rela_dache).setOnClickListener(this);
    }

    private void initUI()
    {
        initBanner();
    }

    private void findViewID()
    {
        mIv_qicheguanjia_back = (ImageView) mInflate.findViewById(R.id.iv_qicheguanjia_back);
        mViewPager_banner = (ViewPager) mInflate.findViewById(R.id.vp_headerview_pager);
        mViewPagerIndicator = (ViewPagerIndicator) mInflate.findViewById(R.id.ViewPagerIndicator);
    }

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
                mViewPager_banner.setAdapter(new MyPagerAdapter(getChildFragmentManager()));
            }
        });
    }

    /**
     * 设置广告条
     */
    private void initBanner()
    {
        mViewPager_banner.addOnPageChangeListener(new BannerOnPageChangeListener());
        if (isFrist)
        {
            autoScroll();
        }
    }

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

    @Override
    public void onClick(View v)
    {
        Intent intent = new Intent();
        switch (v.getId())
        {
            case R.id.iv_qicheguanjia_back:
                getActivity().finish();
                animFromBigToSmallOUT();
                break;
            case R.id.ll_rela_zijiazuche:
                intent.setClass(getActivity(), ZiJiaZuCheActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_rela_jigouyongche:
                intent.setClass(getActivity(), ZuChenJiGouYongCheActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_rela_dache:
                intent.setClass(getActivity(), DaCheActivity.class);
                startActivity(intent);
                break;
        }
    }

    /**
     * 从大到小结束动画
     */
    private void animFromBigToSmallOUT()
    {
        getActivity().overridePendingTransition(R.anim.fade_in, R.anim.big_to_small_fade_out);
    }

    class MyPagerAdapter extends FragmentPagerAdapter
    {

        public MyPagerAdapter(FragmentManager fm)
        {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position)
        {
            int pager_index = position % bannerData.size();
            return new BannerFragment(pager_index, bannerData.get(pager_index).getUrl(), bannerData.get(pager_index).getUrl2());
        }

        @Override
        public int getCount()
        {
            return mPagerCount;
        }
    }

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
}
