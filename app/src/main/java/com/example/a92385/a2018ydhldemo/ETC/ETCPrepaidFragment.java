package com.example.a92385.a2018ydhldemo.ETC;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.a92385.a2018ydhldemo.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ETCPrepaidFragment extends Fragment implements View.OnClickListener {

    private View view;
    /**  */
    private TextView mEtcTotalMoney;
    private ListView mEtcListView;
    private List<ETCPrepaid> prepaidList = new ArrayList<>();
    private ETCPrepaidAdapter adapter;
    private ImageView mBackEtc;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_etcprepaid_fragment, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mEtcTotalMoney = (TextView) view.findViewById(R.id.etc_total_money);
        mEtcListView = (ListView) view.findViewById(R.id.etc_list_view);

        SharedPreferences sp = getActivity().getSharedPreferences("etc", 0);
        Gson gson = new Gson();
        String data = sp.getString("etcprepaid", "");
        Type listType = new TypeToken<List<ETCPrepaid>>() {
        }.getType();
        if (!data.equals("")) {
            prepaidList = gson.fromJson(data, listType);
        }
//    危险
        adapter = new ETCPrepaidAdapter(getContext(), R.layout.etc_item, prepaidList);
        mEtcListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        mEtcTotalMoney.setText(sp.getString("sum", "0"));
        mBackEtc = (ImageView) view.findViewById(R.id.back_etc);
        mBackEtc.setOnClickListener(this);
        mEtcTotalMoney.setOnClickListener(this);
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment, fragment);
        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.back_etc:
                replaceFragment(new ETCFragment());
                break;
            case R.id.etc_total_money:
                break;
            case R.id.etc_list_view:
                break;
        }
    }
}
