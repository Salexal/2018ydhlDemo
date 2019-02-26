package com.example.a92385.a2018ydhldemo.CusB;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a92385.a2018ydhldemo.R;

public class CustomBus extends Fragment implements View.OnClickListener {

    private View view;
    /**
     * 我的订单
     */
    private TextView mInternet;
    private ImageView mCustomBusIntent;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_custom_bus, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mInternet = (TextView) view.findViewById(R.id.internet);
        mInternet.setOnClickListener(this);
        mCustomBusIntent = (ImageView) view.findViewById(R.id.custom_bus_intent);
        mCustomBusIntent.setOnClickListener(this);
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment, fragment);
        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.internet:
                replaceFragment(new MyBusOrder());
                break;
            case R.id.custom_bus_intent:
                replaceFragment(new CustomPageOne());
                break;
        }
    }
}
