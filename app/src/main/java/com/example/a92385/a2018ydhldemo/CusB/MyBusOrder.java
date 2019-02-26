package com.example.a92385.a2018ydhldemo.CusB;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a92385.a2018ydhldemo.R;

public class MyBusOrder extends Fragment implements View.OnClickListener {

    private View view;
    private ImageView mBackEtc;
    private TextView mCustomBusPagePassenger;
    private TextView mCustomBusPageTel;
    private TextView mCustomBusPageSite;
    private TextView mCustomBusPageDate;
    private SharedPreferences sp ;
    private SharedPreferences.Editor editor;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_my_bus_order, container, false);
        sp = getActivity().getSharedPreferences("customDate",0);
        editor = sp.edit();
        initView(view);


        return view;
    }

    private void initView(View view) {
        mBackEtc = (ImageView) view.findViewById(R.id.back_etc);
        mBackEtc.setOnClickListener(this);
        mCustomBusPagePassenger = (TextView) view.findViewById(R.id.custom_bus_page_passenger);
        mCustomBusPageTel = (TextView) view.findViewById(R.id.custom_bus_page_tel);
        mCustomBusPageSite = (TextView) view.findViewById(R.id.custom_bus_page_site);
        mCustomBusPageDate = (TextView) view.findViewById(R.id.custom_bus_page_date);

        mCustomBusPagePassenger.setText(sp.getString("name",""));
        mCustomBusPageDate.setText(sp.getString("date",""));
        mCustomBusPageSite.setText(sp.getString("site",""));
        mCustomBusPageTel.setText(sp.getString("tel",""));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.back_etc:
                break;
        }
    }
}
