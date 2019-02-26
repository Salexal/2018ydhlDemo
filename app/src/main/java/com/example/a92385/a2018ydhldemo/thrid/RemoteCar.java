package com.example.a92385.a2018ydhldemo.thrid;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.a92385.a2018ydhldemo.AccountManager.Accounts;
import com.example.a92385.a2018ydhldemo.HttpUtils.HttpUtil;
import com.example.a92385.a2018ydhldemo.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.Response;

public class RemoteCar extends Fragment implements View.OnClickListener {

    private View view;
    private ListView mLstViewRemote;
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
    private List<Accounts> accountsList = new ArrayList<>();
    private RemoteAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_remote_car, container, false);
        initView(view);
        HttpUtil.sendOkHttp("haveCar", new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String data = response.body().string();
                Log.d("data", "onResponse: " + data);
                Gson gson = new Gson();
                Type listType = new TypeToken<List<Accounts>>() {
                }.getType();
                if (!data.equals("")){
                    accountsList = gson.fromJson(data, listType);
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter = new RemoteAdapter(getContext(),R.layout.list_view_remote_car_item,accountsList);
                        mLstViewRemote.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });
        return view;
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment, fragment);
        transaction.commit();
    }

    private void initView(View view) {
        mLstViewRemote = (ListView) view.findViewById(R.id.lst_view_remote);
        mMineCarBalance = (TextView) view.findViewById(R.id.mine_car_balance);
        mMineCarBalance.setOnClickListener(this);
        mMineCarRemoteControl = (TextView) view.findViewById(R.id.mine_car_remote_control);
        mMineCarRemoteControl.setOnClickListener(this);
        mMineCarPrepaidRecord = (TextView) view.findViewById(R.id.mine_car_prepaid_record);
        mMineCarPrepaidRecord.setOnClickListener(this);
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
                break;
            case R.id.mine_car_prepaid_record:
                replaceFragment(new MineAtuoCarPrepaid());
                break;
        }
    }

}
