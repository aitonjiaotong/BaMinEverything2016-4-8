package com.aiton.bamin.changtukepiao.Cdachezuche.DaCheZhuCheActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aiton.bamin.changtukepiao.Cdachezuche.DaCheZuCheFragment.MainFragment;
import com.aiton.bamin.changtukepiao.Cdachezuche.DaCheZuCheFragment.MoreFragment;
import com.aiton.bamin.changtukepiao.Cdachezuche.DaCheZuCheFragment.OrderFragment;
import com.aiton.bamin.changtukepiao.Cdachezuche.constant_dachezuche.ConstantDaCheZuChe;
import com.aiton.bamin.changtukepiao.R;

public class DaCheZuCheMainActivity extends AppCompatActivity
{
    private String[] tabsItem = new String[]{"首页", "订单", "更多"};
    private Class[] fragment = new Class[]{MainFragment.class, OrderFragment.class, MoreFragment.class};
    private int[] imgRes = new int[]{R.drawable.shouye_selector, R.drawable.dingdan_selector, R.drawable.gengduo_selector};
    private FragmentTabHost mTabHost;
    private int mBackKey;
    public int viewpagerTabs;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_da_che_zu_che_main);
        mTabHost = (FragmentTabHost) findViewById(R.id.tabHost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtab);

        for (int i = 0; i < imgRes.length; i++)
        {
            View inflate = getLayoutInflater().inflate(R.layout.tabs_item, null);
            TextView tabs_text = (TextView) inflate.findViewById(R.id.tabs_text);
            ImageView tabs_img = (ImageView) inflate.findViewById(R.id.tabs_img);
            tabs_text.setText(tabsItem[i]);
            tabs_img.setImageResource(imgRes[i]);
            mTabHost.addTab(mTabHost.newTabSpec("" + i).setIndicator(inflate), fragment[i], null);
        }
        getIntentForSetCurrentTab();
    }

    public void setViewpagerTabs(int viewpagerTabs){
        this.viewpagerTabs=viewpagerTabs;
    }

    public boolean onKeyDown(int keyCode, android.view.KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            finish();
            animFromBigToSmallOUT();
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 从大到小结束动画
     */
    private void animFromBigToSmallOUT()
    {
        overridePendingTransition(R.anim.fade_in, R.anim.big_to_small_fade_out);
    }

    private void getIntentForSetCurrentTab()
    {
        Intent intent = getIntent();
        mBackKey = intent.getIntExtra(ConstantDaCheZuChe.IntentKey.BACK_TO_ORDER_LIST_KEY, -1);
        if (-1 != mBackKey)
        {
            switch (mBackKey)
            {
                case ConstantDaCheZuChe.IntentKey.JI_GUO_ZU_CHE_BACK_INT:
                    //机构用车返回的当前【订单列表默认为机构用车，无需设置二级Tab】
                    break;
                case ConstantDaCheZuChe.IntentKey.ZI_JIA_ZU_CHE_BACK_INT:
                    viewpagerTabs = 1;
                    //设置当前显示的Tab选项卡
                    break;
            }
            mTabHost.setCurrentTab(1);
        }
    }
}
