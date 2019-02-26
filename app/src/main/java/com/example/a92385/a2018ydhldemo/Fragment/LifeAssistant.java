package com.example.a92385.a2018ydhldemo.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a92385.a2018ydhldemo.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class LifeAssistant extends Fragment {

    private View view;
    private LineChart mLineChartTemperature;
    private String []data={"昨天","今天","明天","周二","周三","周四","周五"};
    int cont = 0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_life_assistant, container, false);
        initView(view);
        drawDoublet();

        return view;
    }

    private void drawDoublet(){
        //找到控件
        //创建Entry保存你的数据
        List<Entry> entry1 = new ArrayList<Entry>() ; //折线一的数据源
        List<Entry> entry2 = new ArrayList<Entry>() ; //折线二的数据源
        //向折线一添加数据
        XAxis xLabels = mLineChartTemperature.getXAxis();

        Entry x1 = new Entry(0 , 20f) ;
        entry1.add(x1) ;
        Entry x2 = new Entry(1 , 12f) ;
        entry1.add(x2) ;
        Entry x3 = new Entry(2 , 16f) ;
        entry1.add(x3) ;
        Entry x4 = new Entry(3 , 18f) ;
        entry1.add(x4) ;
        Entry x5 = new Entry(4 , 22f) ;
        entry1.add(x5) ;
        //向折线二添加数据
        Entry y1 = new Entry(0, 10f) ;
        entry2.add(y1) ;
        Entry y2 = new Entry(1 , 7f) ;
        entry2.add(y2) ;
        Entry y3 = new Entry(2 , 8f) ;
        entry2.add(y3) ;
        Entry y4 = new Entry(3 , 9f) ;
        entry2.add(y4) ;
        Entry y5 = new Entry(4 , 9f) ;
        entry2.add(y5) ;
        xLabels.setAxisLineWidth(2f);
        xLabels.setPosition(XAxis.XAxisPosition.BOTH_SIDED);
        xLabels.setValueFormatter(new IAxisValueFormatter() { // 自定义值格式
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                if(cont>=7){
                    cont-=7;
                    return data[cont++];
                }
                return data[cont++];
            }
        });
        xLabels.setDrawGridLines(false);//设置x轴上每个点对应的线
        xLabels.setDrawLabels(true);//绘制标签  指x轴上的对应数值
        xLabels.setPosition(XAxis.XAxisPosition.BOTTOM);
        //将数据传递给LineDataSet对象
        LineDataSet set1 = new LineDataSet(entry1 , "最高温度") ;
        //调用setAxisDependency（）指定DataSets绘制相应的折线
        set1.setAxisDependency(YAxis.AxisDependency.LEFT);

        //折线一 折线的颜色
        set1.setColor(getResources().getColor(R.color.colorAccent));
        LineDataSet set2 = new LineDataSet(entry2 , "最低温度") ;
        set2.setAxisDependency(YAxis.AxisDependency.LEFT);
        //折线二 折线的颜色
        set2.setColor(getResources().getColor(R.color.colorPrimary));
        //使用接口ILineDataSet
        List<ILineDataSet> dataSets = new ArrayList<ILineDataSet>() ;
        //添加数据
        dataSets.add(set1) ;
        dataSets.add(set2) ;
        LineData data = new LineData(dataSets) ;
        //设置图表
        mLineChartTemperature.setData(data);
        //刷新
        mLineChartTemperature.invalidate();
    }

    private void initView(View view) {
        mLineChartTemperature = (LineChart) view.findViewById(R.id.line_chart_temperature);
    }
}



