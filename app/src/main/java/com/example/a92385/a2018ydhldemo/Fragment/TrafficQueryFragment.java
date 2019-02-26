package com.example.a92385.a2018ydhldemo.Fragment;

import android.graphics.Color;
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
import com.google.gson.JsonObject;

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

public class TrafficQueryFragment extends Fragment {

    private View view;
    private TextView mTrafficQueryPm25;
    private TextView mTrafficQueryTemperature;
    private TextView mTrafficQueryHumidity;
    /**
     * 通畅
     */
    private TextView mTrafficQueryLine1;
    /**
     * 通畅
     */
    private TextView mTrafficQueryLine2;
    /**
     * 通畅
     */
    private TextView mTrafficQueryLine3;
    private TextView mTrafficQueryLine1Color;
    private TextView mTrafficQueryLine2Color;
    private TextView mTrafficQueryLine3Color;

    private Timer timer;
    private JSONObject jsonObject;
    private String[] lineValues = {"爆表","通畅","较通畅","拥挤","堵塞"};
    private String[] lineColors = {"#4c060e","#0ebd12","#98ed1f","#ffff01","#ff0103"};
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_traffic_query_fragment, container, false);
        initView(view);

        Map map = new HashMap();
        map.put("aa","sum");
        this.timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                HttpUtil.sendOkHttp("http://10.0.3.2:8080/ss",map, new okhttp3.Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.d("false", "onFailure: ");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        try {
                            jsonObject = new JSONObject(response.body().string());
                            Log.d("json", "onResponse: "+jsonObject);
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    setValue(jsonObject,"pm25",mTrafficQueryPm25);
                                    setValue(jsonObject,"temperature",mTrafficQueryTemperature);
                                    setValue(jsonObject,"humidity",mTrafficQueryHumidity);
                                    setLineValue(jsonObject,"line1",mTrafficQueryLine1,mTrafficQueryLine1Color);
                                    setLineValue(jsonObject,"line2",mTrafficQueryLine2,mTrafficQueryLine2Color);
                                    setLineValue(jsonObject,"line3",mTrafficQueryLine3,mTrafficQueryLine3Color);
                                }
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        },3000,3000);


        return view;
    }


    public void setValue(JSONObject json,String value,TextView textView){
        String change = "";
        try {
            change = json.getString(value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(!change.equals(""))
        textView.setText(change);
    }

    public String setLineValue(JSONObject json,String value,TextView textView,TextView textViewColor){
        String lineValue = "";
        try {
            lineValue = json.getString(value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (!lineValue.equals("")){
            int num = Integer.valueOf(lineValue);
            textView.setText(lineValues[num%5]);
            textViewColor.setBackgroundColor(Color.parseColor(lineColors[num%5]));
        }else
            return textView.getText().toString();
        return lineValue;
    }

    private void initView(View view) {
        mTrafficQueryPm25 = (TextView) view.findViewById(R.id.traffic_query_pm25);
        mTrafficQueryTemperature = (TextView) view.findViewById(R.id.traffic_query_temperature);
        mTrafficQueryHumidity = (TextView) view.findViewById(R.id.traffic_query_humidity);
        mTrafficQueryLine1 = (TextView) view.findViewById(R.id.traffic_query_line1);
        mTrafficQueryLine2 = (TextView) view.findViewById(R.id.traffic_query_line2);
        mTrafficQueryLine3 = (TextView) view.findViewById(R.id.traffic_query_line3);
        mTrafficQueryLine1Color = (TextView) view.findViewById(R.id.traffic_query_line1_color);
        mTrafficQueryLine2Color = (TextView) view.findViewById(R.id.traffic_query_line2_color);
        mTrafficQueryLine3Color = (TextView) view.findViewById(R.id.traffic_query_line3_color);
    }
}
