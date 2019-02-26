package com.example.a92385.a2018ydhldemo.CHARGE;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.a92385.a2018ydhldemo.R;

public class CarPark extends Fragment implements View.OnClickListener {

    private View view;
    /**
     * 剩余车位：4/10
     */
    private TextView mBalancePark;
    /**
     * 刷新
     */
    private Button mParkReflash;
    /**
     * ABC停车场
     */
    private TextView mParkname;
    /**
     * 收费标准：100元/小时
     */
    private TextView mParkCharge;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_car_park, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mBalancePark = (TextView) view.findViewById(R.id.balance_Park);
        mParkReflash = (Button) view.findViewById(R.id.park_reflash);
        mParkReflash.setOnClickListener(this);
        mParkname = (TextView) view.findViewById(R.id.parkname);
        mParkCharge = (TextView) view.findViewById(R.id.park_charge);
        mBalancePark.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.park_reflash:
                mParkCharge.setText("收费标准：55元/小时");
                mParkname.setText("汽车之家停车场");
                mBalancePark.setText("剩余车位：1/10");
                break;
        }
    }
}
