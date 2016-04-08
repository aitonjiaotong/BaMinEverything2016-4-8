package com.aiton.bamin.changtukepiao.Zeverything.everything_fragment;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aiton.administrator.shane_library.shane.utils.FileUtils;
import com.aiton.bamin.changtukepiao.R;
import com.aiton.bamin.changtukepiao.Zeverything.EveryThingSoftInfoActivity;
import com.aiton.bamin.changtukepiao.Zeverything.MineInfoActivity;
import com.aiton.bamin.changtukepiao.Zeverything.SmsLoginActivity;
import com.alibaba.sdk.android.feedback.impl.FeedbackAPI;
import com.alibaba.sdk.android.feedback.util.IWxCallback;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineEverythingFragment extends Fragment implements View.OnClickListener {


    private String mPhoneNum;
    private String mId;
    private boolean isLogined;
    private View mInflate;
    private TextView mTextView_unLogin;
    private RelativeLayout mRela_login;
    private TextView mTextView_login;
    private RelativeLayout mLoginED;
//    private Button button_cancle_login;
    private TextView mTextView_feedBackCount;
    private boolean isOpenFeedBack = false;
    private IWxCallback callback = new IWxCallback() {
        @Override
        public void onSuccess(final Object... objects) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    int count = (int) objects[0];
                    if (isOpenFeedBack) {
                        count = 0;
                    }
                    if (count == 0) {
                        mTextView_feedBackCount.setText("");
                    } else {
                        mTextView_feedBackCount.setText("+" + count);
                    }
                    isOpenFeedBack = false;
                }
            });
        }

        @Override
        public void onError(int i, String s) {

        }

        @Override
        public void onProgress(int i) {
        }
    };
    private RelativeLayout mRl_mine_evething_clear_diskcache;
    private TextView mTv_diskcache_num;

    public MineEverythingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (mInflate == null) {
            mInflate = inflater.inflate(R.layout.fragment_mine_everything, null);
            findID();
            initUI();
            setListener();
        }
        return mInflate;
    }

    private void setListener() {
        mLoginED.setOnClickListener(this);
//        button_cancle_login.setOnClickListener(this);
        mInflate.findViewById(R.id.rl_mine_evething_soft_info).setOnClickListener(this);
        mInflate.findViewById(R.id.rela_feedback).setOnClickListener(this);
        mRl_mine_evething_clear_diskcache.setOnClickListener(this);
    }

    private void findID() {
        mTextView_unLogin = (TextView) mInflate.findViewById(R.id.textView_unLogin);
        mRela_login = (RelativeLayout) mInflate.findViewById(R.id.rela_login);
        mTextView_login = (TextView) mInflate.findViewById(R.id.textView_login);
        mLoginED = (RelativeLayout) mInflate.findViewById(R.id.loginED);
//        button_cancle_login = (Button) mInflate.findViewById(R.id.button_cancle_login);
        mTextView_feedBackCount = (TextView) mInflate.findViewById(R.id.textView_feedBackCount);
        mRl_mine_evething_clear_diskcache = (RelativeLayout) mInflate.findViewById(R.id.rl_mine_evething_clear_diskcache);
        mTv_diskcache_num = (TextView) mInflate.findViewById(R.id.tv_diskcache_num);
    }

    private void initUI() {
        mTv_diskcache_num.setText(getSize());
    }

    /**
     * -------------获取缓存大小-----------------
     */
    private String getSize() {
        File cacheDir = getActivity().getExternalCacheDir();
        long fileLen = FileUtils.getFileLen(cacheDir);
        String size = FileUtils.size(fileLen);

        return size;
    }

    @Override
    public void onStart() {
        super.onStart();
        initLogin();
    }

    private void initLogin() {
        SharedPreferences sp = getActivity().getSharedPreferences("isLogin", Context.MODE_PRIVATE);
        mPhoneNum = sp.getString("phoneNum", "");
        Log.e("initLogin ", "initLogin mPhoneNum"+mPhoneNum);
        mId = sp.getString("id", "");
        if ("".equals(mPhoneNum)) {
            isLogined = false;
            mTextView_unLogin.setVisibility(View.VISIBLE);
            mRela_login.setVisibility(View.INVISIBLE);
            mTextView_login.setVisibility(View.INVISIBLE);
//            button_cancle_login.setVisibility(View.GONE);
        } else {
            isLogined = true;
            mTextView_unLogin.setVisibility(View.INVISIBLE);
            mRela_login.setVisibility(View.VISIBLE);
            mTextView_login.setVisibility(View.VISIBLE);
            mTextView_login.setText(mPhoneNum);
//            button_cancle_login.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
//            case R.id.button_cancle_login:
//                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//                builder.setTitle("提醒");
//                builder.setMessage("确定要退出吗？");
//                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        SharedPreferences sp = getActivity().getSharedPreferences("isLogin", Context.MODE_PRIVATE);
//                        SharedPreferences.Editor edit = sp.edit();
//                        edit.clear();
//                        edit.commit();
//                        initLogin();
//                    }
//                });
//                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//                AlertDialog alertDialog = builder.create();
//                alertDialog.show();
//                break;
            case R.id.loginED:
                if (isLogined) {
                    intent.setClass(getActivity(), MineInfoActivity.class);
                    startActivity(intent);
                } else {
                    intent.setClass(getActivity(), SmsLoginActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.rl_mine_evething_soft_info:
                intent.setClass(getActivity(), EveryThingSoftInfoActivity.class);
                startActivity(intent);
                break;
            case R.id.rela_feedback:
                //第二个参数是appkey，就是百川应用创建时候的appkey
                FeedbackAPI.initAnnoy(getActivity().getApplication(), "23334901");
                Map<String, String> map = new HashMap<>();
                map.put("bgColor", "#ff7d27");
                map.put("themeColor", "#ff7d27");
                map.put("enableAudio", "1");
                FeedbackAPI.setUICustomInfo(map);
                //可选功能，第二个参数是当前登录的openim账号，如果是匿名账号方式使用，则可以传空的
                FeedbackAPI.getFeedbackUnreadCount(getActivity().getApplication(), null, callback);
                //6.0以上系统需要判断权限
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    //申请WRITE_EXTERNAL_STORAGE权限
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA},
                            1);
                }
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    //申请WRITE_EXTERNAL_STORAGE权限
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            2);
                }if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.RECORD_AUDIO)
                        != PackageManager.PERMISSION_GRANTED) {
                    //申请WRITE_EXTERNAL_STORAGE权限
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.RECORD_AUDIO},
                            2);
                }
                else {
                    //如果发生错误，请查看logcat日志
                    FeedbackAPI.openFeedbackActivity(getActivity());
                    isOpenFeedBack = true;
                }
                break;
            case R.id.rl_mine_evething_clear_diskcache:
                ImageLoader.getInstance().clearDiskCache();
                mTv_diskcache_num.setText(getSize());
                Toast.makeText(getActivity(), "缓存清除完毕!", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
