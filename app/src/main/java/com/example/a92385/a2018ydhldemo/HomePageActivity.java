package com.example.a92385.a2018ydhldemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.a92385.a2018ydhldemo.CHARGE.CarPark;
import com.example.a92385.a2018ydhldemo.CHARGE.Charge;
import com.example.a92385.a2018ydhldemo.CarCharge.ChargeCarMoney;
import com.example.a92385.a2018ydhldemo.CusB.CustomBus;
import com.example.a92385.a2018ydhldemo.ETC.ETCFragment;
import com.example.a92385.a2018ydhldemo.ETC.ETCLine;
import com.example.a92385.a2018ydhldemo.Fragment.BusQuery;
import com.example.a92385.a2018ydhldemo.Fragment.LifeAssistantOfEnvironment;
import com.example.a92385.a2018ydhldemo.Fragment.MineAccountFragment;
import com.example.a92385.a2018ydhldemo.NEWs.IC;
import com.example.a92385.a2018ydhldemo.NEWs.News;
import com.example.a92385.a2018ydhldemo.Qr.QrCode;
import com.example.a92385.a2018ydhldemo.Tourism.Forbidden;
import com.example.a92385.a2018ydhldemo.Tourism.Weather;
import com.example.a92385.a2018ydhldemo.thrid.MineAtuoCar;

public class HomePageActivity extends AppCompatActivity implements View.OnClickListener {

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
    /**
     * 环境指标
     */
    private TextView mThreshold;
    /**
     * 出行管理
     */
    private TextView mTravel;
    /**
     * 出行管理
     */
    private TextView mAccountManagment;
    /**
     * 红路灯管理(admin)
     */
    private TextView mAccountLightSortAdmin;
    /**
     * 红路灯管理(admin)
     */
    private TextView mAnalysisIllegal;
    /**
     * 账户管理
     */
    private TextView mMineAccount1;
    /**
     * 账户设置
     */
    private TextView mMineAccountSet;
    /**
     * 生活助手
     */
    private TextView mLifeAssis;
    /**
     * 路况查询
     */
    private TextView mTrafficQueryFragment;
    /**
     * 数据分析
     */
    private TextView mTwoChart;
    /**
     * 生活助手
     */
    private TextView mLifeEn;
    /**
     * 公交查询
     */
    private TextView mBusQuery;
    /**
     * 高速ETC
     */
    private TextView mEtcHome;
    /**
     * 高速路况
     */
    private TextView mEtcLineMsg;
    /**
     * 旅游信息
     */
    private TextView mTourismPage;
    /**
     * 天气信息
     */
    private TextView mWeatherMsg;
    /**
     * 二维码支付
     */
    private TextView mQcCodePage;
    /**
     * 天气信息
     */
    private TextView mCustomBusP;
    /**
     * 新闻显示
     */
    private TextView mNewsPage;
    /**
     * IC卡充值
     */
    private TextView mIcPage;
    /**
     * IC卡查询
     */
    private TextView mIcQueryPage;
    /**
     * 停车场
     */
    private TextView mCarParkPage;
    /**
     * 小车充值
     */
    private TextView mChargeCarPage;
    /**
     * 停车场
     */
    private TextView mZhuce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);
        initView();
        replaceFragment(new MineAccountFragment());
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment, fragment);
        transaction.commit();
    }

    private void initView() {
        mFragment = (FrameLayout) findViewById(R.id.fragment);
//        mMyAccount = (TextView) findViewById(R.id.my_account);
//        mTrafficLightManagement = (TextView) findViewById(R.id.traffic_light_management);
//        mMyAccount.setOnClickListener(this);
//        mTrafficLightManagement.setOnClickListener(this);
        mFragment.setOnClickListener(this);
//        mPrepaidRecordManger = (TextView) findViewById(R.id.prepaid_record_manger);
//        mPrepaidRecordManger.setOnClickListener(this);
//        mEnvironmentalIndicators = (TextView) findViewById(R.id.environmental_indicators);
//        mEnvironmentalIndicators.setOnClickListener(this);
//        mThreshold = (TextView) findViewById(R.id.threshold);
//        mThreshold.setOnClickListener(this);
//        mTravel = (TextView) findViewById(R.id.travel);
//        mTravel.setOnClickListener(this);
//        mAccountManagment = (TextView) findViewById(R.id.account_managment);
//        mAccountManagment.setOnClickListener(this);
//        mAccountLightSortAdmin = (TextView) findViewById(R.id.account_light_sort_admin);
//        mAccountLightSortAdmin.setOnClickListener(this);
//        mAnalysisIllegal = (TextView) findViewById(R.id.analysis_illegal);
//        mAnalysisIllegal.setOnClickListener(this);
//        mMineAccount1 = (TextView) findViewById(R.id.mine_account_1);
//        mMineAccount1.setOnClickListener(this);
//        mMineAccountSet = (TextView) findViewById(R.id.mine_account_set);
//        mMineAccountSet.setOnClickListener(this);
//        mLifeAssis = (TextView) findViewById(R.id.life_assis);
//        mLifeAssis.setOnClickListener(this);
//        mTrafficQueryFragment = (TextView) findViewById(R.id.traffic_query_fragment);
//        mTrafficQueryFragment.setOnClickListener(this);
//        mTwoChart = (TextView) findViewById(R.id.twoChart);
//        mTwoChart.setOnClickListener(this);
//        mLifeEn = (TextView) findViewById(R.id.life_en);
//        mLifeEn.setOnClickListener(this);
//        mBusQuery = (TextView) findViewById(R.id.bus_query);
//        mBusQuery.setOnClickListener(this);
//        mEnvironmentalIndicators = (TextView) findViewById(R.id.environmental_indicators);
//        mEnvironmentalIndicators.setOnClickListener(this);
        mEtcHome = (TextView) findViewById(R.id.etc_home);
        mEtcHome.setOnClickListener(this);
        mEtcLineMsg = (TextView) findViewById(R.id.etc_line_msg);
        mEtcLineMsg.setOnClickListener(this);
        mTourismPage = (TextView) findViewById(R.id.tourism_page);
        mTourismPage.setOnClickListener(this);
        mWeatherMsg = (TextView) findViewById(R.id.weather_msg);
        mWeatherMsg.setOnClickListener(this);
        mQcCodePage = (TextView) findViewById(R.id.qc_code_page);
        mQcCodePage.setOnClickListener(this);
        mCustomBusP = (TextView) findViewById(R.id.custom_bus_p);
        mCustomBusP.setOnClickListener(this);
        mNewsPage = (TextView) findViewById(R.id.news_page);
        mNewsPage.setOnClickListener(this);
        mIcPage = (TextView) findViewById(R.id.ic_page);
        mIcPage.setOnClickListener(this);
        mIcQueryPage = (TextView) findViewById(R.id.ic_query_page);
        mIcQueryPage.setOnClickListener(this);
        mCarParkPage = (TextView) findViewById(R.id.car_park_page);
        mCarParkPage.setOnClickListener(this);
        mChargeCarPage = (TextView) findViewById(R.id.charge_car_page);
        mChargeCarPage.setOnClickListener(this);
        mZhuce = (TextView) findViewById(R.id.zhuce);
        mZhuce.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
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
//            case R.id.threshold:
//                replaceFragment(new ThresholdFragment());
//                break;
//            case R.id.travel:
//                replaceFragment(new TravelManagementFragment());
//                break;
//            case R.id.account_managment:
//                replaceFragment(new AccountManagementFragment());
//                break;
            case R.id.fragment:
                break;
//            case R.id.account_light_sort_admin:
//                replaceFragment(new AdminLightManamentFragment());
//                break;
//            case R.id.analysis_illegal:
//                Intent intent = new Intent(HomePageActivity.this, FragmentSlidingChart.class);
//                startActivity(intent);
//                break;
//            case R.id.mine_account_1:
//                replaceFragment(new MineAccount());
//                break;
//            case R.id.mine_account_set:
//                replaceFragment(new AccountManagment_my_car());
//                break;
//            case R.id.life_assis:
//                replaceFragment(new LifeAssistant());
//                break;
//            case R.id.traffic_query_fragment:
//                replaceFragment(new TrafficQueryFragment());
//                break;
//            case R.id.twoChart:
//                Intent intent1 = new Intent(HomePageActivity.this, ChartSlidingTwo.class);
//                startActivity(intent1);
//                break;
//            case R.id.life_en:
//                replaceFragment(new LifeAssistantOfEnvironment());
//                break;
//            case R.id.bus_query:
//                replaceFragment(new BusQuery());
//                break;
//            case R.id.environmental_indicators:
//                break;
            case R.id.etc_home:
                replaceFragment(new ETCFragment());
                break;
            case R.id.etc_line_msg:
                replaceFragment(new ETCLine());
                break;
            case R.id.tourism_page:
                replaceFragment(new Forbidden());
                break;
            case R.id.weather_msg:
                replaceFragment(new Weather());
                break;
            case R.id.qc_code_page:
                replaceFragment(new QrCode());
                break;
            case R.id.custom_bus_p:
                replaceFragment(new CustomBus());
                break;
            case R.id.news_page:
                replaceFragment(new News());
                break;
            case R.id.ic_page:
                replaceFragment(new IC());
                break;
            case R.id.ic_query_page:
                replaceFragment(new Charge());
                break;
            case R.id.car_park_page:
                replaceFragment(new CarPark());
                break;
            case R.id.charge_car_page:
                replaceFragment(new ChargeCarMoney());
                break;
            case R.id.zhuce:
                replaceFragment(new MineAtuoCar());
                break;
        }
    }
}
