package com.example.mytest2023.module.Home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mytest2023.R;
import com.example.mytest2023.helper.OnItemRecyclerListener;
import com.example.mytest2023.helper.RecyclerViewHelper;
import com.example.mytest2023.model.home.Home1Item;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 钟文祥 on 2023-04-18.
 * Describer:
 */
public class Home1Fragment2 extends Fragment {

    @BindView(R.id.rv) RecyclerView rv;

    private Home1Adapter adapter;
    private List<Home1Item> list;

    public static Home1Fragment2 newInstance() {
        Home1Fragment2 fragment = new Home1Fragment2();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(setLayout(), container, false);
        ButterKnife.bind(this, rootView);

        return rootView;
    }

    public int setLayout() {
        return R.layout.fragment_home1;
    }

    private boolean isFirst = true;

    @Override
    public void onResume() {
        super.onResume();
        if (isFirst) {
            initUI();
            isFirst = false;
        }

    }

    private void initData() {
        list = new ArrayList<>();
        list.add(new Home1Item<String>("第一", "第一副标题", null, null, null));
    }

    public void initUI() {

        initData();
        RecyclerViewHelper.init(getActivity(), rv, new LinearLayoutManager(getActivity()), true);
        adapter = new Home1Adapter(getActivity(), list, new OnItemRecyclerListener() {
            @Override
            public void onClick(int position, Object object) {
                Intent intent = new Intent(getActivity(), ((Home1Item) object).getCls());
                if (((Home1Item) object).getData() != null) {
                    intent.putExtra("data", (Serializable) ((Home1Item) object).getData());
                }
                getActivity().startActivity(intent);
            }
        });
        rv.setAdapter(adapter);

    }
}
