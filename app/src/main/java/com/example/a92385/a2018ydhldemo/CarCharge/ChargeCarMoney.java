package com.example.a92385.a2018ydhldemo.CarCharge;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.a92385.a2018ydhldemo.AccountManager.Accounts;
import com.example.a92385.a2018ydhldemo.AccountManager.MineAccountAdapter;
import com.example.a92385.a2018ydhldemo.HttpUtils.HttpUtil;
import com.example.a92385.a2018ydhldemo.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ChargeCarMoney extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener {

    private View view;
    private ListView mListViewChargeCar;
    private MineAccountAdapter adapter;
    private List<Accounts> accountList = new ArrayList<>();
    private String data;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_charge_car_money, container, false);
        initView(view);
        HttpUtil.sendOkHttp("haveCar", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("获取出师失败", "onFailure: ");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                data = response.body().string();
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
                        adapter = new MineAccountAdapter(getContext(), R.layout.list_mine_account, accountList);
                        mListViewChargeCar.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });

        return view;
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
                                                                adapter = new MineAccountAdapter(getContext(), R.layout.list_mine_account, accountList);
                                                                mListViewChargeCar.setAdapter(adapter);
                                                                adapter.notifyDataSetChanged();
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

    private void initView(View view) {
        mListViewChargeCar = (ListView) view.findViewById(R.id.list_view_charge_car);
        mListViewChargeCar.setOnItemClickListener(this);
    }

//    @Override
//    public void click(View v) {
//        String name = accountList.get((Integer) v.getTag()).getLicense();
//        Log.d("tag tag", "click: " + name + "  " + v.getTag());
//        addList(name);
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.list_view_charge_car:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String name = String.valueOf(Integer.valueOf(accountList.get((Integer) position).getId())-3);
        Log.d("tag tag", "click: " + name + "  " + view.getTag());
        addList(name);
    }
}
