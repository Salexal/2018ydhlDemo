package com.example.a92385.a2018ydhldemo.Qr;

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
import android.widget.LinearLayout;

import com.example.a92385.a2018ydhldemo.R;

public class QrCode extends Fragment implements View.OnClickListener {

    private View view;
    private ImageView mInternet;
    private LinearLayout mLayoutTitle;
    private EditText mQcCarNum;
    private EditText mQcCarUpMoney;
    private EditText mQcCarUpRestart;
    /**
     * 生   成
     */
    private Button mBtnQcBuild;
    private SharedPreferences sp ;
    private SharedPreferences.Editor editor;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_qr_code, container, false);
        initView(view);
        sp = getActivity().getSharedPreferences("qc",0);
        editor = sp.edit();
        return view;
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment, fragment);
        transaction.commit();
    }

    private void initView(View view) {
        mInternet = (ImageView) view.findViewById(R.id.internet);
        mLayoutTitle = (LinearLayout) view.findViewById(R.id.layout_title);
        mQcCarNum = (EditText) view.findViewById(R.id.qc_car_num);
        mQcCarUpMoney = (EditText) view.findViewById(R.id.qc_car_up_money);
        mQcCarUpRestart = (EditText) view.findViewById(R.id.qc_car_up_restart);
        mBtnQcBuild = (Button) view.findViewById(R.id.btn_qc_build);
        mBtnQcBuild.setOnClickListener(this);
        mInternet.setOnClickListener(this);
        mLayoutTitle.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_qc_build:
                editor.putString("carNum",mQcCarNum.getText().toString());
                editor.putString("upMoney",mQcCarUpMoney.getText().toString());
                editor.commit();
                replaceFragment(new QcPay());
                break;
            case R.id.internet:
                mQcCarNum.setText("");
                mQcCarUpMoney.setText("");
                mQcCarUpRestart.setText("");
                break;

        }
    }
}
