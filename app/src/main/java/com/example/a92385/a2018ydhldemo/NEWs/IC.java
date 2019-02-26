package com.example.a92385.a2018ydhldemo.NEWs;

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

public class IC extends Fragment implements View.OnClickListener {

    private View view;
    /**
     * 100
     */
    private TextView mIcHaveMoney;
    private EditText mIcUpMoney;
    /**
     * 充值
     */
    private Button mBtnIcUp;
    /**
     * 退出
     */
    private Button mBtnIcBack;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_ic, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mIcHaveMoney = (TextView) view.findViewById(R.id.ic_have_money);
        mIcUpMoney = (EditText) view.findViewById(R.id.ic_up_money);
        mBtnIcUp = (Button) view.findViewById(R.id.btn_ic_up);
        mBtnIcUp.setOnClickListener(this);
        mBtnIcBack = (Button) view.findViewById(R.id.btn_ic_back);
        mBtnIcBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_ic_up:
                int money = Integer.valueOf(mIcHaveMoney.getText().toString())+Integer.valueOf(mIcUpMoney.getText().toString());
                mIcHaveMoney.setText(String.valueOf(money));
                mIcUpMoney.setText("");
                break;
            case R.id.btn_ic_back:
                break;
        }
    }
}

