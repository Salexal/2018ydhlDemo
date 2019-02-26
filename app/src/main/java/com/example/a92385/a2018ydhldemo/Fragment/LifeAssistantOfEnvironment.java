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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.Response;

public class LifeAssistantOfEnvironment extends Fragment {

    private View view;
    private TextView mLifeEnPm25;
    private TextView mLifeEnTemperature;
    private TextView mLifeEnHumidity;
    /**
     * 非常弱
     */
    private TextView mSunLevel;
    /**
     * 您无需担心紫外线
     */
    private TextView mSunValue;
    /**
     * 良好
     */
    private TextView mSportLevel;
    /**
     * 气象条件会对晨练影响不大
     */
    private TextView mSportValue;

    private Timer timer;
    private JSONObject JSON;
    private String[] sports = {"良好","轻度","重度","爆表"};
    private String[] sportValues = {"气象条件会对晨练影响不大","受天气影响，较不宜晨练","减少外出，出行注意戴口罩","停止一切外出活动"};

    private String[] suns = {"非常弱","弱","强"};
    private String[] sunValues = {"您无需担心紫外线","外出适当涂抹低倍数防晒霜","外出需要涂抹中倍数防晒霜"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_life_assistant_of_environment, container, false);
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
                            JSON = new JSONObject(response.body().string());
                            Log.d("json", "onResponse: "+JSON);
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    setValue(JSON,"pm25",mLifeEnPm25);
                                    setValue(JSON,"temperature",mLifeEnTemperature);
                                    setValue(JSON,"humidity",mLifeEnHumidity);
                                    setSuns(mSunLevel,mSunValue,JSON,"light");
                                    setSuns(mSportLevel,mSportValue,JSON,"pm25");
                                    setValue(JSON,"pm25",mLifeEnPm25);
                                    setValue(JSON,"temperature",mLifeEnTemperature);
                                    setValue(JSON,"humidity",mLifeEnHumidity);
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

    public void setSuns(TextView sun,TextView sunValue,JSONObject JSON,String str)  {
        String change = null;
        try {
            change = JSON.getString(str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(!change.equals("")){
            if(str.equals("pm25")){
                Integer i = Integer.valueOf(change);
                if(i>=300){

                    sun.setText(sports[3]);
                    sun.setTextColor(Color.RED);
                    sunValue.setText(sportValues[3]);
                }else{
                    sun.setTextColor(Color.BLACK);
                    i/=100;
                    sun.setText(sports[i]);
                    sunValue.setText(sportValues[i]);
                }
            }else{
                Integer i = Integer.valueOf(change);
                    i/=100;
                    sun.setText(suns[i]);
                    if(i==2)
                        sun.setTextColor(Color.RED);
                    else
                        sun.setTextColor(Color.BLACK);
                    sunValue.setText(sunValues[i]);
            }
        }
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

    private void initView(View view) {
        mLifeEnPm25 = (TextView) view.findViewById(R.id.life_en_pm25);
        mLifeEnTemperature = (TextView) view.findViewById(R.id.life_en_temperature);
        mLifeEnHumidity = (TextView) view.findViewById(R.id.life_en_humidity);
        mSunLevel = (TextView) view.findViewById(R.id.sun_level);
        mSunValue = (TextView) view.findViewById(R.id.sun_value);
        mSportLevel = (TextView) view.findViewById(R.id.sport_level);
        mSportValue = (TextView) view.findViewById(R.id.sport_value);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}
