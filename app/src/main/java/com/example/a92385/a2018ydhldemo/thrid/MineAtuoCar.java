package com.example.a92385.a2018ydhldemo.thrid;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.graphics.Color;
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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.a92385.a2018ydhldemo.AccountManager.Accounts;
import com.example.a92385.a2018ydhldemo.AccountManager.MineAccountAdapter;
import com.example.a92385.a2018ydhldemo.HttpUtils.HttpUtil;
import com.example.a92385.a2018ydhldemo.PrePaidRecords.PrepaidRecord;
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

public class MineAtuoCar extends Fragment implements View.OnClickListener {

    private View view;
    /**
     * 正常
     */
    private TextView mCarNum1Statue;
    /**
     * 0
     */
    private Button mCarNum1Value;
    private LinearLayout mCar1;
    /**
     * 正常
     */
    private TextView mCarNum2Statue;
    /**
     * 0
     */
    private Button mCarNum2Value;
    private LinearLayout mCar2;
    /**
     * 正常
     */
    private TextView mCarNum3Statue;
    /**
     * 0
     */
    private Button mCarNum3Value;
    private LinearLayout mCar3;
    /**
     * 正常
     */
    private TextView mCarNum4Statue;
    /**
     * 0
     */
    private Button mCarNum4Value;
    private LinearLayout mCar4;
    /**
     * 我的余额
     */
    private TextView mMineCarBalance;
    /**
     * 我的余额
     */
    private TextView mMineCarRemoteControl;
    /**
     * 我的余额
     */
    private TextView mMineCarPrepaidRecord;
    private JSONObject json = null;
    private String data="";
    private List<Accounts> accountList = new ArrayList<>();
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private List<PrepaidRecord> recordList = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_mine_atuo_car, container, false);
        initView(view);
        HttpUtil.sendOkHttp("haveCar", new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                data = response.body().string();
                Log.d("data", "onResponse: " + data);
                Gson gson = new Gson();
                Type listType = new TypeToken<List<Accounts>>() {
                }.getType();
                if (!data.equals("")){
                    accountList = gson.fromJson(data, listType);
                    startValue(mCar1,mCarNum1Value,mCarNum1Statue,0);
                    startValue(mCar2,mCarNum2Value,mCarNum2Statue,1);
                    startValue(mCar3,mCarNum3Value,mCarNum3Statue,2);
                    startValue(mCar4,mCarNum4Value,mCarNum4Statue,3);
                }
            }
        });
        sp = getActivity().getSharedPreferences("autoPrepaid",0);
        editor = sp.edit();
        return view;
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment, fragment);
        transaction.commit();
    }

    public void startValue(LinearLayout color,Button carValue,TextView carStatue,int num){
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                carValue.setText(accountList.get(num).getBalance());
                if(Integer.valueOf(accountList.get(num).getBalance())>=100){
                    carStatue.setText("正常");
                    color.setBackgroundColor(Color.GREEN);
                }
                else{
                    carStatue.setText("警告");
                    color.setBackgroundColor(Color.RED);
                }

            }
        });
    }


    private void initView(View view) {
        mCarNum1Statue = (TextView) view.findViewById(R.id.car_num1_statue);
        mCarNum1Value = (Button) view.findViewById(R.id.car_num1_value);
        mCarNum1Value.setOnClickListener(this);
        mCar1 = (LinearLayout) view.findViewById(R.id.car1);
        mCarNum2Statue = (TextView) view.findViewById(R.id.car_num2_statue);
        mCarNum2Value = (Button) view.findViewById(R.id.car_num2_value);
        mCarNum2Value.setOnClickListener(this);
        mCar2 = (LinearLayout) view.findViewById(R.id.car2);
        mCarNum3Statue = (TextView) view.findViewById(R.id.car_num3_statue);
        mCarNum3Value = (Button) view.findViewById(R.id.car_num3_value);
        mCarNum3Value.setOnClickListener(this);
        mCar3 = (LinearLayout) view.findViewById(R.id.car3);
        mCarNum4Statue = (TextView) view.findViewById(R.id.car_num4_statue);
        mCarNum4Value = (Button) view.findViewById(R.id.car_num4_value);
        mCarNum4Value.setOnClickListener(this);
        mCar4 = (LinearLayout) view.findViewById(R.id.car4);
        mMineCarBalance = (TextView) view.findViewById(R.id.mine_car_balance);
        mMineCarBalance.setOnClickListener(this);
        mMineCarRemoteControl = (TextView) view.findViewById(R.id.mine_car_remote_control);
        mMineCarRemoteControl.setOnClickListener(this);
        mMineCarPrepaidRecord = (TextView) view.findViewById(R.id.mine_car_prepaid_record);
        mMineCarPrepaidRecord.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.car_num1_value:
                addList("1");
                break;
            case R.id.car_num2_value:
                addList("2");
                break;
            case R.id.car_num3_value:
                addList("3");
                break;
            case R.id.car_num4_value:
                addList("4");
                break;
            case R.id.mine_car_balance:
                break;
            case R.id.mine_car_remote_control:
                replaceFragment(new RemoteCar());
                break;
            case R.id.mine_car_prepaid_record:
                replaceFragment(new MineAtuoCarPrepaid());
                break;
        }
    }
    public void addList(String name) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view1 = View.inflate(getContext(), R.layout.alertdialog, null);
        builder.setTitle("车辆账户充值")
                .setView(view1);
        final AlertDialog alertDialog = builder.create();
        TextView textView = (TextView) view1.findViewById(R.id.alertdialog_car_num);
        EditText editText = (EditText) view1.findViewById(R.id.alertdialog_car_up);
        Button ok_btn = (Button) view1.findViewById(R.id.ok);
        Button cancel_btn = (Button) view1.findViewById(R.id.cancel);
        textView.setText(name);
        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = 0;
                try {
                    HttpUtil.sendOkHttp("haveCar/" + String.valueOf(Integer.valueOf(name) + 3), new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            Log.d("carName", "onResponse: ");
                            String one = response.body().string();
                            try {
                                JSONObject car = new JSONObject(one);
                                String value = String.valueOf(Integer.valueOf(car.getString("Balance")) + Integer.valueOf(editText.getText().toString()));
                                HttpUtil.putOkHttp("haveCar/" + car.getString("id"), car.getString("id"), car.getString("License"), car.getString("owner")
                                        , value, new Callback() {
                                            @Override
                                            public void onFailure(Call call, IOException e) {
                                                Log.d("充值", "onResponse: down");
                                            }

                                            @Override
                                            public void onResponse(Call call, Response response) throws IOException {
                                                Log.d("充值", "onResponse: YES");


                                                HttpUtil.sendOkHttp("haveCar", new Callback() {
                                                    @Override
                                                    public void onFailure(Call call, IOException e) {
                                                        Log.d("获取出师失败", "onFailure: ");
                                                    }

                                                    @Override
                                                    public void onResponse(Call call, Response response) throws IOException {
                                                        String data = response.body().string();
                                                        Log.d("data", "onResponse: " + data);
                                                        Gson gson = new Gson();
                                                        Type listType = new TypeToken<List<Accounts>>() {
                                                        }.getType();

                                                        if (!data.equals("")) {
                                                            accountList = gson.fromJson(data, listType);
                                                        }
                                                        Log.d("list", "onResponse: " + accountList.get(0).getOwner());
                                                        getActivity().runOnUiThread(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                if (!data.equals("")){
                                                                    SimpleDateFormat dateFormat = new SimpleDateFormat(" yyyy-MM-dd HH:mm:ss");
                                                                    String currentDate =   dateFormat.format( new Date() );
                                                                    Gson gson = new Gson();
                                                                    String allPrepaid = sp.getString("listRecord","");
                                                                    Type listType = new TypeToken<List<PrepaidRecord>>() {
                                                                    }.getType();
                                                                    int  listLength = 0;
                                                                    if(!allPrepaid.equals("")){
                                                                        recordList = gson.fromJson(allPrepaid,listType);
                                                                        listLength = recordList.size();
                                                                    }

                                                                    PrepaidRecord prepaidRecord = new PrepaidRecord();
                                                                    prepaidRecord.setRecordId(String.valueOf(++listLength));
                                                                    prepaidRecord.setMoney(editText.getText().toString());
                                                                    prepaidRecord.setRecordTime(currentDate);
                                                                    prepaidRecord.setCarId(name);
                                                                    recordList.add(prepaidRecord);
                                                                    allPrepaid = gson.toJson(recordList);
                                                                    editor.putString("listRecord",allPrepaid);
                                                                    editor.commit();
                                                                    startValue(mCar1,mCarNum1Value,mCarNum1Statue,0);
                                                                    startValue(mCar2,mCarNum2Value,mCarNum2Statue,1);
                                                                    startValue(mCar3,mCarNum3Value,mCarNum3Statue,2);
                                                                    startValue(mCar4,mCarNum4Value,mCarNum4Statue,3);
                                                                }
                                                            }
                                                        });
                                                    }

                                                });
                                            }
                                        });
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                } catch (Exception e) {

                } finally {
                }
                alertDialog.dismiss();
            }
        });

        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

}