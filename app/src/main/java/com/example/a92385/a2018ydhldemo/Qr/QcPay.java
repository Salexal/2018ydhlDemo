package com.example.a92385.a2018ydhldemo.Qr;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.a92385.a2018ydhldemo.R;

public class QcPay extends Fragment {

    private View view;
    private ImageView mInternet;
    private LinearLayout mLayoutTitle;
    /**
     * 车辆编号=
     */
    private TextView mQcPayCarNum;
    private TextView mQcPayCarNumValue;
    /**
     * ,      支付金额=
     */
    private TextView mQcPayUpMoney;
    private TextView mQcPayUpMoneyValue;
    private SharedPreferences sp ;
    private SharedPreferences.Editor editor;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_qc_pay, container, false);
        initView(view);
        sp = getActivity().getSharedPreferences("qc",0);
        editor = sp.edit();
        mQcPayCarNumValue.setText(sp.getString("carNum",""));
        mQcPayUpMoneyValue.setText(sp.getString("upMoney",""));
        return view;
    }

    private void initView(View view) {
        mInternet = (ImageView) view.findViewById(R.id.internet);
        mLayoutTitle = (LinearLayout) view.findViewById(R.id.layout_title);
        mQcPayCarNum = (TextView) view.findViewById(R.id.qc_pay_car_num);
        mQcPayCarNumValue = (TextView) view.findViewById(R.id.qc_pay_car_num_value);
        mQcPayUpMoney = (TextView) view.findViewById(R.id.qc_pay_up_money);
        mQcPayUpMoneyValue = (TextView) view.findViewById(R.id.qc_pay_up_money_value);
    }
}
