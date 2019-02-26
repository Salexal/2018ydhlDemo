
package com.example.a92385.a2018ydhldemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.a92385.a2018ydhldemo.chart.BarChartFragment;
import com.example.a92385.a2018ydhldemo.chart.IllegalPieChart;
import com.example.a92385.a2018ydhldemo.chart.RepeatIllegalPieChart;

import java.util.ArrayList;
import java.util.List;

public class  FragmentSlidingChart extends AppCompatActivity    {

    private FrameLayout mFragment;
    /**
     * 我的账户
     */
    private TextView mMyAccount;
    /**
     * 红绿灯管理
     */
    private TextView mTrafficLightManagement;
    /**
     * 我的账户
     */
    private TextView mPrepaidRecordManger;
    /**
     * 环境指标
     */
    private TextView mEnvironmentalIndicators;

    //    public MainViewPager viewPager;
    public ViewPager viewPager;
    public List<Fragment> fragments = new ArrayList<Fragment>();
    private FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_sliding_chart);
        initView();
        fragments.add(new IllegalPieChart());
        fragments.add(new RepeatIllegalPieChart());
        fragments.add(new BarChartFragment());

        fragmentManager = this.getSupportFragmentManager();

//        viewPager = (MainViewPager) findViewById(R.id.viewPager);
//        //viewPager.setSlipping(false);//设置ViewPager是否可以滑动
//        viewPager.setOffscreenPageLimit(4);
//        viewPager.setAdapter(new MyPagerAdapter());

        viewPager = (ViewPager)findViewById(R.id.viewPager_analysis);
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



    public void replaceFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment, fragment);
        transaction.commit();
    }

    private void initView() {
//        mFragment = (FrameLayout) findViewById(R.id.fragment);
//        mMyAccount = (TextView) findViewById(R.id.my_account);
//        mTrafficLightManagement = (TextView) findViewById(R.id.traffic_light_management);
//        mMyAccount.setOnClickListener(this);
//        mTrafficLightManagement.setOnClickListener(this);
//        mPrepaidRecordManger = (TextView) findViewById(R.id.prepaid_record_manger);
//        mPrepaidRecordManger.setOnClickListener(this);
//        mEnvironmentalIndicators = (TextView) findViewById(R.id.environmental_indicators);
//        mEnvironmentalIndicators.setOnClickListener(this);
    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            default:
//                break;
//            case R.id.my_account:
//                replaceFragment(new MineAccountFragment());
//                break;
//            case R.id.traffic_light_management:
//                replaceFragment(new TrafficLightManagementFragment());
//                break;
//            case R.id.prepaid_record_manger:
//                replaceFragment(new PrepaidRecordsFragment());
//                break;
//            case R.id.environmental_indicators:
//                replaceFragment(new EnvironmentalIndicatorsFragment());
//                break;
//        }
//    }


//    private class MyPagerAdapter extends PagerAdapter {
//        @Override
//        public boolean isViewFromObject(View arg0, Object arg1) {
//            return arg0 == arg1;
//        }
//
//        @Override
//        public int getCount() {
//            return fragments.size();
//        }
//
//        @Override
//        public void destroyItem(View container, int position, Object object) {
//            ((ViewPager) container).removeView(fragments.get(position)
//                    .getView());
//        }
//
//        @Override
//        public Object instantiateItem(ViewGroup container, int position) {
//            Fragment fragment = fragments.get(position);
//            if (!fragment.isAdded()) { // 如果fragment还没有added
//                FragmentTransaction ft = fragmentManager.beginTransaction();
//                ft.add(fragment, fragment.getClass().getSimpleName());
//                ft.commit();
//                /**
//                 * 在用FragmentTransaction.commit()方法提交FragmentTransaction对象后
//                 * 会在进程的主线程中,用异步的方式来执行。
//                 * 如果想要立即执行这个等待中的操作,就要调用这个方法(只能在主线程中调用)。
//                 * 要注意的是,所有的回调和相关的行为都会在这个调用中被执行完成,因此要仔细确认这个方法的调用位置。
//                 */
//                fragmentManager.executePendingTransactions();
//            }
//
//            if (fragment.getView().getParent() == null) {
//                container.addView(fragment.getView()); // 为viewpager增加布局
//            }
//            return fragment.getView();
//        }
//    }
}

