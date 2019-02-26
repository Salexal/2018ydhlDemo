package com.example.a92385.a2018ydhldemo.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a92385.a2018ydhldemo.HttpUtils.HttpUtil;
import com.example.a92385.a2018ydhldemo.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class BusQuery extends Fragment implements View.OnClickListener {

    private View view;
    /**
     * 500
     */
    private TextView mBusQueryBus1Port1;
    /**
     * 600
     */
    private TextView mBusQueryBus2Port1;
    /**
     * 600
     */
    private TextView mBusQueryBus1Port2;
    /**
     * 1000
     */
    private TextView mBusQueryBus2Port2;

    private Timer timer;
    private JSONObject JSON;
    private int kms1 = 20;
    private int hms2 = 40;
    /**
     * 一号公交车
     */
    private TextView mBusNum1Port1;
    /**
     * 二号公交车
     */
    private TextView mBusNum2Port1;
    /**
     * 二号公交车
     */
    private TextView mBusNum1Port2;
    /**
     * 一号公交车
     */
    private TextView mBusNum2Port2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_bus_query, container, false);
        initView(view);

        Map map = new HashMap();
        map.put("aa", "sum");
        this.timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                HttpUtil.sendOkHttp("http://10.0.3.2:8080/ss", map, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.d("false", "onFailure: ");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        try {
                            JSON = new JSONObject(response.body().string());
                            Log.d("json", "onResponse: " + JSON);
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    setBusValue(mBusNum1Port1,mBusNum2Port1,mBusQueryBus1Port1,mBusQueryBus2Port1);
                                    setBusValue(mBusNum1Port2,mBusNum2Port2,mBusQueryBus1Port2,mBusQueryBus2Port2);
                                }
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }, 3000, 3000);
        return view;
    }

    public void setBusValue(TextView bus1, TextView bus2,TextView bus1value,TextView bus2value) {
        int busValue1 = Integer.valueOf(bus1value.getText().toString());
        int busValue2 = Integer.valueOf(bus2value.getText().toString());

        busValue1-=kms1;
        busValue2-=hms2;

        if(busValue1>busValue2){
            String tmp = bus1.getText().toString();
            bus1.setText(bus2.getText().toString());
            bus2.setText(tmp);

            int value = kms1;
            kms1 = hms2;
            hms2 = value;

            int tmpInt = busValue1;
            bus1value.setText(String.valueOf(busValue2));
            bus2value.setText(String.valueOf(tmpInt));
        }else{
            bus1value.setText(String.valueOf(busValue1));
            bus2value.setText(String.valueOf(busValue2));
        }

    }

    private void initView(View view) {
        mBusQueryBus1Port1 = (TextView) view.findViewById(R.id.bus_query_bus1_port1);
        mBusQueryBus2Port1 = (TextView) view.findViewById(R.id.bus_query_bus2_port1);
        mBusQueryBus1Port2 = (TextView) view.findViewById(R.id.bus_query_bus1_port2);
        mBusQueryBus2Port2 = (TextView) view.findViewById(R.id.bus_query_bus2_port2);
        mBusNum1Port1 = (TextView) view.findViewById(R.id.bus_num1_port1);
        mBusQueryBus1Port1.setOnClickListener(this);
        mBusNum2Port1 = (TextView) view.findViewById(R.id.bus_num2_port1);
        mBusQueryBus2Port1.setOnClickListener(this);
        mBusNum1Port2 = (TextView) view.findViewById(R.id.bus_num1_port2);
        mBusQueryBus1Port2.setOnClickListener(this);
        mBusNum2Port2 = (TextView) view.findViewById(R.id.bus_num2_port2);
        mBusQueryBus2Port2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.bus_query_bus1_port1:
                break;
            case R.id.bus_query_bus2_port1:
                break;
            case R.id.bus_query_bus1_port2:
                break;
            case R.id.bus_query_bus2_port2:
                break;
        }
    }
}
