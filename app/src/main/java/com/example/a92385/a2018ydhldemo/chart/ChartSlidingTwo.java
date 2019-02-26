package com.example.a92385.a2018ydhldemo.chart;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.a92385.a2018ydhldemo.AppFragmentPageAdapter;
import com.example.a92385.a2018ydhldemo.Fragment.LineChartFragment;
import com.example.a92385.a2018ydhldemo.Fragment.linChart3;
import com.example.a92385.a2018ydhldemo.Fragment.lineChart2;
import com.example.a92385.a2018ydhldemo.Fragment.lineChart4;
import com.example.a92385.a2018ydhldemo.R;

import java.util.ArrayList;
import java.util.List;

public class ChartSlidingTwo extends AppCompatActivity {

    private ViewPager viewPager;
    public List<Fragment> fragments = new ArrayList<Fragment>();
    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_sliding_two);

        fragments.add(new RepeatIllegalPieChart());

        fragments.add(new TransverseBarChart());

        fragmentManager = this.getSupportFragmentManager();

        viewPager = (ViewPager)findViewById(R.id.viewPager_shujufenxi);
        viewPager.setAdapter(new AppFragmentPageAdapter(getSupportFragmentManager(),fragments));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //Do Nothing
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //Do Nothing
            }
        });
    }
}
