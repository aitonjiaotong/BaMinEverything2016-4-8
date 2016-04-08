package com.aiton.bamin.changtukepiao.Cdachezuche.DaCheZuCheFragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aiton.bamin.changtukepiao.Cdachezuche.DaCheZhuCheActivity.UsedAdressActivity;
import com.aiton.bamin.changtukepiao.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoreFragment extends Fragment implements View.OnClickListener {


    private View mInflate;

    public MoreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (mInflate == null) {
            mInflate = inflater.inflate(R.layout.fragment_more2, null);
            findID();
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

    private void setListener() {
        mInflate.findViewById(R.id.rela_usedAddress).setOnClickListener(this);
    }

    private void initUI() {

    }

    private void findID() {

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.rela_usedAddress:
                intent.setClass(getActivity(), UsedAdressActivity.class);
                startActivity(intent);
                break;
        }
    }
}
