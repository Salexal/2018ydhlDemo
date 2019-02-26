package com.example.a92385.a2018ydhldemo.ETC;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a92385.a2018ydhldemo.HttpUtils.HttpUtil;
import com.example.a92385.a2018ydhldemo.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ETCBalanceFragment extends Fragment implements View.OnClickListener {


    private View view;
    private ImageView mBackEtc;
    /**
     * 查询
     */
    private Button mEtcBtnQuery;
    /**
     * 0
     */
    private TextView mEtcShowMoney;
    /**
     * 1
     */
    private EditText mEtcBalanceCarId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_etcbalance_fragment, container, false);
        initView(view);
        HttpUtil.sendOkHttp("haveCar/4", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    JSONObject json = new JSONObject(response.body().string());
                    Log.d("json", "onResponse: " + json);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                mEtcShowMoney.setText(json.getString("Balance"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        return view;
    }

    private void initView(View view) {
        mBackEtc = (ImageView) view.findViewById(R.id.back_etc);
        mBackEtc.setOnClickListener(this);
        mEtcBtnQuery = (Button) view.findViewById(R.id.etc_btn_query);
        mEtcBtnQuery.setOnClickListener(this);
        mEtcShowMoney = (TextView) view.findViewById(R.id.etc_show_money);
        mEtcBalanceCarId = (EditText) view.findViewById(R.id.etc_balance_car_id);
        mEtcShowMoney.setOnClickListener(this);
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
                replaceFragment(new ETCFragment());
                break;
            case R.id.etc_btn_query:
                String carId = String.valueOf(Integer.valueOf(mEtcBalanceCarId.getText().toString())+3);
                HttpUtil.sendOkHttp("haveCar/" + carId, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        try {
                            JSONObject findCar = new JSONObject(response.body().string());
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        mEtcShowMoney.setText(findCar.getString("Balance"));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
//                HttpUtil.putOkHttp("haveCar/"+carId,mEtcBalanceCarId.getText().toString(),);
                break;
            case R.id.etc_show_money:
                break;
        }
    }
}
