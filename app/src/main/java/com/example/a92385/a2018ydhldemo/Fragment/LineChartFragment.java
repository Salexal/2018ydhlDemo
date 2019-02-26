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

import com.example.a92385.a2018ydhldemo.HttpUtils.HttpUtil;
import com.example.a92385.a2018ydhldemo.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.Response;

public class LineChartFragment extends Fragment {


    private View view;
    private LineChart lineChart;

    private Timer timer;
    private List<String> xList = new ArrayList<>();
    private List<Entry> yList = new ArrayList<>();
    private LineDataSet lineDataSet = new LineDataSet(yList,"This is CO2");
    private LineData lineData;
    private String tmpJS = "";
    private int count = 0;
    private XAxis xAxis;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_line_chart_fragment, container, false);


        initView(view);
        xAxis = lineChart.getXAxis();
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return ""+new SimpleDateFormat("hh-mm-ss").format(new Date());
            }
        });


        this.timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Map map = new HashMap();
                map.put("aa","sum");
//                map.put("SenseName","co2");
//                map.put("UserName","Z0004");
                //  /transportservice/action/GetSenseByName.do  http://10.0.3.2//ss
                HttpUtil.sendOkHttp("http://10.0.3.2/ss",map, new okhttp3.Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.d("is false", "onFailure: ");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        tmpJS = response.body().string();
                        Log.d("js", "onResponse: "+tmpJS);
                    }
                });
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(tmpJS);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if(jsonObject!=null){
                    final String co2 = JP(jsonObject);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(count==0){
                                lineDataSet.setCircleColor(Color.BLACK);
                                xList.add(""+new SimpleDateFormat("hh-mm-ss").format(new Date()));
                                lineData = new LineData(lineDataSet);

                                lineChart.setData(lineData);
                            }else{
//                                lineChart.getLineData().addXValue(""+new SimpleDateFormat("hh-mm-ss").format(new Date()));
                                lineChart.getLineData().addEntry(new Entry(Float.parseFloat(co2),count),0);
                            }
                            count++;
                            lineData.notifyDataChanged();
                            lineChart.notifyDataSetChanged();

                            lineChart.setVisibleXRangeMaximum(10);
                            lineChart.setVisibleXRangeMinimum(5);

                            if(count>10)
                                lineChart.moveViewToX(count-10);
                            lineChart.invalidate();
                        }
                    });
                }
            }
        },1000,1000);

        return view;
    }

    private void initView(View view) {
        lineChart = (LineChart) view.findViewById(R.id.lineChart);
    }

    public String JP(JSONObject jsonObject){
        String co2 = null;
        try {
            co2 = jsonObject.getString("co2");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return co2;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }

}
