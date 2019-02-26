package com.example.a92385.a2018ydhldemo.Fragment;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.a92385.a2018ydhldemo.FragmentSliding;
import com.example.a92385.a2018ydhldemo.HomePageActivity;
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
import okhttp3.Response;

public class EnvironmentalIndicatorsFragment extends Fragment implements View.OnClickListener {

    private View view;
    private Button mTemperature;
    private LinearLayout mTemperatureColor;
    private Button mHumidity;
    private LinearLayout mHumidityColor;
    private Button mValueLight;
    private LinearLayout mLightColor;
    private Button mCo2;
    private LinearLayout mCo2Color;
    private Button mPm25;
    private LinearLayout mPm25Value;
    private JSONObject jsonObject = null;
    private Timer timer;
    /**
     *  获取阈值数据
     * */
    private SharedPreferences sp;
    private  SharedPreferences.Editor editor;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_environmental_indicators_fragme, container, false);
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
                                    setColor(mTemperatureColor,"temperature",mTemperature,sp.getString("temperature","300"));
                                    setColor(mHumidityColor,"humidity",mHumidity,sp.getString("humidity","300"));
                                    setColor(mLightColor,"light",mValueLight,sp.getString("light","300"));
                                    setColor(mCo2Color,"co2",mCo2,sp.getString("co2","300"));
                                    setColor(mPm25Value,"pm25",mPm25,sp.getString("pm25","300"));
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

    public void setColor(LinearLayout linearLayout,String name,Button button,String thresholdValue){
        try {
            if((Double.valueOf(jsonObject.getString(name)))>Integer.valueOf(thresholdValue)){
                NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(name+"报警")
                        .setContentText("阈值"+thresholdValue+",当前"+Double.valueOf(jsonObject.getString(name))+"。");
                Intent resultIntent = new Intent(getContext(),HomePageActivity.class);

                PendingIntent pendingIntent = PendingIntent.getActivity(getContext(),0,resultIntent,PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(pendingIntent);

                Notification notification = builder.build();
                notification.flags = Notification.FLAG_AUTO_CANCEL;

                NotificationManager notificationManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(1,notification);

                linearLayout.setBackgroundColor(Color.parseColor("#FF0000"));
            }

            else
                linearLayout.setBackgroundColor(Color.parseColor("#00FF00"));
            button.setText(jsonObject.getString(name));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void initView(View view) {
        mTemperature = (Button) view.findViewById(R.id.temperature);
        mTemperature.setOnClickListener(this);
        mTemperatureColor = (LinearLayout) view.findViewById(R.id.temperature_color);
        mHumidity = (Button) view.findViewById(R.id.humidity);
        mHumidity.setOnClickListener(this);
        mHumidityColor = (LinearLayout) view.findViewById(R.id.humidity_color);
        mValueLight = (Button) view.findViewById(R.id.value_light);
        mValueLight.setOnClickListener(this);
        mLightColor = (LinearLayout) view.findViewById(R.id.light_color);
        mCo2 = (Button) view.findViewById(R.id.co2);
        mCo2.setOnClickListener(this);
        mCo2Color = (LinearLayout) view.findViewById(R.id.co2_color);
        mPm25 = (Button) view.findViewById(R.id.pm25);
        mPm25.setOnClickListener(this);
        mPm25Value = (LinearLayout) view.findViewById(R.id.pm25_value);

        sp = getActivity().getSharedPreferences("threshold",0);
        editor = sp.edit();
    }

    @Override
    public void onClick(View v) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("lineChart",0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        switch (v.getId()) {
            default:
                break;
            case R.id.temperature:
                editor.putString("tu","temperature");
                editor.commit();
                Intent intent = new Intent(getContext(), FragmentSliding.class);
                startActivity(intent);
                break;
            case R.id.humidity:
                editor.putString("tu","humidity");
                editor.commit();
                break;
            case R.id.value_light:
                editor.putString("tu","light");
                editor.commit();
                break;
            case R.id.co2:
                editor.putString("tu","co2");
                editor.commit();
                break;
            case R.id.pm25:
                editor.putString("tu","pm25");
                editor.commit();
                break;
        }
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment, fragment);
        transaction.commit();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}
