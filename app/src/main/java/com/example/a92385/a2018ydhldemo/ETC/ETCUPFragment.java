package com.example.a92385.a2018ydhldemo.ETC;

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
import android.widget.TextView;
import android.widget.Toast;

import com.example.a92385.a2018ydhldemo.AccountManager.Accounts;
import com.example.a92385.a2018ydhldemo.HttpUtils.HttpUtil;
import com.example.a92385.a2018ydhldemo.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ETCUPFragment extends Fragment implements View.OnClickListener {

    private View view;
    private EditText mEtcUpCarName;
    /**
     * 10元
     */
    private TextView mMoneyTen;
    /**
     * 10元
     */
    private TextView mMoneyFifty;
    /**
     * 10元
     */
    private TextView mMoneyHundred;
    private EditText mUpCarMoney;
    private ImageView mBackEtc;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private List<ETCPrepaid> prepaidList = new ArrayList<>();
    private JSONObject getCar = null;
    /**
     * 充值
     */
    private Button mEtcBtnUp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_etcupfragment, container, false);
        initView(view);
        sp = getActivity().getSharedPreferences("etc", 0);
        editor = sp.edit();
        return view;
    }

    private void initView(View view) {
        mEtcUpCarName = (EditText) view.findViewById(R.id.etc_up_car_name);
        mMoneyTen = (TextView) view.findViewById(R.id.money_ten);
        mMoneyFifty = (TextView) view.findViewById(R.id.money_fifty);
        mMoneyHundred = (TextView) view.findViewById(R.id.money_hundred);
        mUpCarMoney = (EditText) view.findViewById(R.id.up_car_money);
        mBackEtc = (ImageView) view.findViewById(R.id.back_etc);
        mBackEtc.setOnClickListener(this);
        mEtcUpCarName.setOnClickListener(this);
        mMoneyTen.setOnClickListener(this);
        mMoneyFifty.setOnClickListener(this);
        mMoneyHundred.setOnClickListener(this);
        mUpCarMoney.setOnClickListener(this);
        mEtcBtnUp = (Button) view.findViewById(R.id.etc_btn_up);
        mEtcBtnUp.setOnClickListener(this);
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
            case R.id.etc_up_car_name:
                break;
            case R.id.money_ten:
                mUpCarMoney.setText("10");
                break;
            case R.id.money_fifty:
                mUpCarMoney.setText("50");
                break;
            case R.id.money_hundred:
                mUpCarMoney.setText("100");
                break;
            case R.id.up_car_money:
                break;
            case R.id.etc_btn_up:
                String carId = String.valueOf(Integer.valueOf(mEtcUpCarName.getText().toString()) + 3);
                HttpUtil.sendOkHttp("haveCar/" + carId, new okhttp3.Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        try {
                            Gson gson = new Gson();
                            Type listType = new TypeToken<List<ETCPrepaid>>() {
                            }.getType();
                            getCar = new JSONObject(response.body().string());
                            if (getCar != null) {
                                HttpUtil.putOkHttp("haveCar/" + getCar.getString("id"), getCar.getString("id"), getCar.getString("License")
                                        , getCar.getString("owner"), getCar.getString("Balance"), new okhttp3.Callback() {

                                            @Override
                                            public void onFailure(Call call, IOException e) {

                                            }

                                            @Override
                                            public void onResponse(Call call, Response response) throws IOException {

                                                String data = sp.getString("etcprepaid", "");
                                                if (!data.equals("")) {

                                                    prepaidList = gson.fromJson(data, listType);
                                                }
                                                ETCPrepaid etcPrepaid = new ETCPrepaid();
                                                try {
                                                    etcPrepaid.setCarId(mEtcUpCarName.getText().toString());
                                                    etcPrepaid.setCarName(getCar.getString("License"));
                                                    etcPrepaid.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                                                    etcPrepaid.setMoney(mUpCarMoney.getText().toString());
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }
                                                prepaidList.add(etcPrepaid);
                                                String datas = gson.toJson(prepaidList);
                                                editor.putString("etcprepaid", datas);
                                                String sum = sp.getString("sum", "0");
                                                editor.putString("sum", String.valueOf(Integer.valueOf(sum) + Integer.valueOf(mUpCarMoney.getText().toString())));
                                                editor.commit();
                                            }
                                        });
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
                Toast.makeText(getContext(), "充值成功", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
