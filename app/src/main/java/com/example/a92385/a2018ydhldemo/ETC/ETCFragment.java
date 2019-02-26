package com.example.a92385.a2018ydhldemo.ETC;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a92385.a2018ydhldemo.R;

public class ETCFragment extends Fragment implements View.OnClickListener {

    private View view;
    private TextView mEtcTopUp;
    private TextView mEtcBalance;
    private TextView mEtcPrepaid;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_etc, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mEtcTopUp = (TextView) view.findViewById(R.id.etc_top_up);
        mEtcTopUp.setOnClickListener(this);
        mEtcBalance = (TextView) view.findViewById(R.id.etc_balance);
        mEtcBalance.setOnClickListener(this);
        mEtcPrepaid = (TextView) view.findViewById(R.id.etc_prepaid);
        mEtcPrepaid.setOnClickListener(this);
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
            case R.id.etc_top_up:
                replaceFragment(new ETCUPFragment());
                break;
            case R.id.etc_balance:
                replaceFragment(new ETCBalanceFragment());
                break;
            case R.id.etc_prepaid:
                replaceFragment(new ETCPrepaidFragment());
                break;
        }
    }
}
