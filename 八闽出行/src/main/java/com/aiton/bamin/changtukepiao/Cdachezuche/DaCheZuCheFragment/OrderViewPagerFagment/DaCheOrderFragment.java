package com.aiton.bamin.changtukepiao.Cdachezuche.DaCheZuCheFragment.OrderViewPagerFagment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.aiton.bamin.changtukepiao.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DaCheOrderFragment extends Fragment {


    private View mInflate;
    private ListView mListView_dache;

    public DaCheOrderFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (mInflate == null) {
            mInflate = inflater.inflate(R.layout.fragment_da_che_order, null);
            findID();
            initUI();
        }
        //缓存的rootView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) mInflate.getParent();
        if (parent != null) {
            parent.removeView(mInflate);
        }
        return mInflate;
    }

    private void initUI() {
        mListView_dache.setAdapter(new MyAdapter());
    }

    private void findID() {
        mListView_dache = (ListView) mInflate.findViewById(R.id.listView_dache);
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 8;
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
            View inflate = getLayoutInflater(getArguments()).inflate(R.layout.dache_order_listitem, null);
            return inflate;
        }
    }
}
