package com.example.a92385.a2018ydhldemo.CusB;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.a92385.a2018ydhldemo.R;

public class CustomPageThree extends Fragment implements View.OnClickListener {

    private View view;
    private ImageView mBackEtc;
    private EditText mCustomBusPagePassenger;
    private EditText mCustomBusPageTel;
    private EditText mCustomBusPageSite;
    /**
     * 下一步
     */
    private Button mBtnCustomPageNext;
    private SharedPreferences sp ;
    private SharedPreferences.Editor editor;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_custom_page_three, container, false);
        initView(view);
        sp = getActivity().getSharedPreferences("customDate",0);
        editor = sp.edit();
        return view;
    }

    private void initView(View view) {
        mBackEtc = (ImageView) view.findViewById(R.id.back_etc);
        mBackEtc.setOnClickListener(this);
        mCustomBusPagePassenger = (EditText) view.findViewById(R.id.custom_bus_page_passenger);
        mCustomBusPageTel = (EditText) view.findViewById(R.id.custom_bus_page_tel);
        mCustomBusPageSite = (EditText) view.findViewById(R.id.custom_bus_page_site);
        mBtnCustomPageNext = (Button) view.findViewById(R.id.btn_custom_page_next);
        mBtnCustomPageNext.setOnClickListener(this);
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
            case R.id.back_etc:
                replaceFragment(new CustomBus());
                break;
            case R.id.btn_custom_page_next:
                editor.putString("name",mCustomBusPagePassenger.getText().toString());
                editor.putString("tel",mCustomBusPageTel.getText().toString());
                editor.putString("site",mCustomBusPageSite.getText().toString());
                editor.commit();
                replaceFragment(new CustomBus());
                break;
        }
    }
}

