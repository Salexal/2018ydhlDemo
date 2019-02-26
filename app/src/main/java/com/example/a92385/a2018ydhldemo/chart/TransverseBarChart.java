package com.example.a92385.a2018ydhldemo.chart;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a92385.a2018ydhldemo.HorizontalBarChartView;
import com.example.a92385.a2018ydhldemo.R;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TransverseBarChart extends Fragment {

   private View view;
    private HorizontalBarChartView hBarChart;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_transverse_bar_chart,container,false);
        initHBarChart();

        return view;
    }

    private void initHBarChart() {
        hBarChart = view.findViewById(R.id.hb_chart);
        Random random = new Random();
        List<BarEntry> list = new ArrayList<>();
        String[] values = new String[30];
        for (int i = 0; i < 3; i++) {
            BarEntry barEntry = null;
            if (i == 0) {
                barEntry = new BarEntry(i, (float) 60.58);
            }else if (i ==1) {
                barEntry = new BarEntry(i, (float) 26.28);
            }
            else {
                barEntry = new BarEntry(i, (float) 13.2);
            }
            values[i] = (i + 1) + "鄂尔多斯市";
            list.add(barEntry);
        }
        hBarChart.setBarDataSet(Color.BLUE, list.size(), list, values);
    }


}
