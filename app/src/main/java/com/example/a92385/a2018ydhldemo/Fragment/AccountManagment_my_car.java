package com.example.a92385.a2018ydhldemo.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.a92385.a2018ydhldemo.R;

public class AccountManagment_my_car extends Fragment implements View.OnClickListener {

    private View view;
    /**
     * 我的1-4号车账户余额的预告阈值为 0 元
     */
    private TextView mMyAccountCar;
    private EditText mAccountSetThreshold;
    /**
     * 设置
     */
    private Button mBtnSetMineCarThreshold;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_account_managment_my_car, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mMyAccountCar = (TextView) view.findViewById(R.id.my_account_car);
        mAccountSetThreshold = (EditText) view.findViewById(R.id.account_set_threshold);
        mBtnSetMineCarThreshold = (Button) view.findViewById(R.id.btn_set_mine_car_threshold);
        mBtnSetMineCarThreshold.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_set_mine_car_threshold:
                String [] list = mMyAccountCar.getText().toString().split(" ");
                mMyAccountCar.setText(list[0]+" "+mAccountSetThreshold.getText().toString()+" "+list[2]);
                break;
        }
    }
}
