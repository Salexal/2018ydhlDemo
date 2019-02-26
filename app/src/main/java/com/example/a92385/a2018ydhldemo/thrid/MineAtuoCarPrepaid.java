package com.example.a92385.a2018ydhldemo.thrid;

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
import android.widget.ListView;
import android.widget.TextView;

import com.example.a92385.a2018ydhldemo.AccountManager.Accounts;
import com.example.a92385.a2018ydhldemo.PrePaidRecords.PrepaidRecord;
import com.example.a92385.a2018ydhldemo.PrePaidRecords.RecordAdapter;
import com.example.a92385.a2018ydhldemo.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MineAtuoCarPrepaid extends Fragment implements View.OnClickListener {

    private View view;
    private ListView mListViewRemotePrepaid;
    /**
     * 我的余额
     */
    private TextView mMineCarBalance;
    /**
     * 我的余额
     */
    private TextView mMineCarRemoteControl;
    /**
     * 我的余额
     */
    private TextView mMineCarPrepaidRecord;
    private List<PrepaidRecord> recordList = new ArrayList<>();
    private AutoCarPrepaidAdapter adapter;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_mine_atuo_car_prepaid, container, false);
        initView(view);
        sp = getActivity().getSharedPreferences("autoPrepaid",0);
        editor = sp.edit();
        Gson gson = new Gson();
        String data = sp.getString("listRecord","");


        Type listType = new TypeToken<List<PrepaidRecord>>() {
        }.getType();

        if(!data.equals("")){
            recordList = gson.fromJson(data,listType);
        }
//    危险
        adapter = new AutoCarPrepaidAdapter(getContext(),R.layout.list_view_auto_car_prepaid_,recordList);
        mListViewRemotePrepaid.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        return view;
    }

    private void initView(View view) {
        mListViewRemotePrepaid = (ListView) view.findViewById(R.id.list_view_remote_prepaid);
        mMineCarBalance = (TextView) view.findViewById(R.id.mine_car_balance);
        mMineCarBalance.setOnClickListener(this);
        mMineCarRemoteControl = (TextView) view.findViewById(R.id.mine_car_remote_control);
        mMineCarRemoteControl.setOnClickListener(this);
        mMineCarPrepaidRecord = (TextView) view.findViewById(R.id.mine_car_prepaid_record);
        mMineCarPrepaidRecord.setOnClickListener(this);
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
            case R.id.mine_car_balance:
                replaceFragment(new MineAtuoCar());
                break;
            case R.id.mine_car_remote_control:
                replaceFragment(new RemoteCar());
                break;
            case R.id.mine_car_prepaid_record:
                break;
        }
    }
}
