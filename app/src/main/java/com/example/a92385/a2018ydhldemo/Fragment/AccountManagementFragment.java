package com.example.a92385.a2018ydhldemo.Fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.a92385.a2018ydhldemo.AccountManager.Accounts;
import com.example.a92385.a2018ydhldemo.AccountManager.AccountAdapter;
import com.example.a92385.a2018ydhldemo.HttpUtils.HttpUtil;
import com.example.a92385.a2018ydhldemo.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class AccountManagementFragment extends Fragment implements View.OnClickListener {

    private View view;
    /**
     * 批量充值
     */
    private TextView mBatchTopUp;
    /**
     * 充值记录
     */
    private TextView mHistoryCarUp;
    private ListView mListViewAccountCar;
    private String isChecked;
    private AccountAdapter adapter;
    private List<Accounts> accountList = new ArrayList<>();
    private  String data;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_account_management_fragment, container, false);
        initView(view);

        return view;
    }

    private void initView(View view) {
        mBatchTopUp = (TextView) view.findViewById(R.id.batch_top_up);
        mBatchTopUp.setOnClickListener(this);
        mHistoryCarUp = (TextView) view.findViewById(R.id.history_car_up);
        mHistoryCarUp.setOnClickListener(this);
        mListViewAccountCar = (ListView) view.findViewById(R.id.listView_account_car);

        HttpUtil.sendOkHttp("haveCar", new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("获取出师失败", "onFailure: ");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                 data = response.body().string();
                Log.d("data", "onResponse: "+data);
                Gson gson = new Gson();
                Type listType = new TypeToken<List<Accounts>>() {
                }.getType();

                if (!data.equals("")) {
                    accountList = gson.fromJson(data, listType);
                }
                Log.d("list", "onResponse: "+accountList.get(0).getOwner());
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter = new AccountAdapter(getContext(), R.layout.listview_account_car_item, accountList);
                        mListViewAccountCar.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });



    }

    public void addList(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view1 = View.inflate(getContext(), R.layout.alertdialog, null);
        builder.setTitle("车辆账户充值")
                .setView(view1);
        final AlertDialog alertDialog = builder.create();
        TextView textView = (TextView) view1.findViewById(R.id.alertdialog_car_num);
        EditText editText = (EditText) view1.findViewById(R.id.alertdialog_car_up);
        textView.setText(isChecked);
        Button ok_btn = (Button) view1.findViewById(R.id.ok);
        Button cancel_btn = (Button) view1.findViewById(R.id.cancel);

        String[] carName = isChecked.split(" ");
        List<String> list = new ArrayList<>();
        for(int i = 0;i<carName.length;i++)
            list.add(carName[i]);
        int i = 0;
        for (String name = list.get(i);i<list.size();i++) {
            if (name.equals(""))
                continue;

            ok_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    HttpUtil.sendOkHttp("haveCar?License=" + name, new okhttp3.Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            Log.d("carName", "onResponse: ");
                                String one = response.body().string();
                                Gson gson = new Gson();
                                Type listType = new TypeToken<List<Accounts>>() {
                                }.getType();
                                List<Accounts> a = gson.fromJson(one,listType);
                                String value = String.valueOf(Integer.valueOf(a.get(0).getBalance()) + Integer.valueOf(editText.getText().toString()));
                                HttpUtil.putOkHttp("haveCar/" + String.valueOf(a.get(0).getId()),a.get(0).getId(), a.get(0).getLicense(), a.get(0).getOwner()
                                        , value, new okhttp3.Callback() {
                                            @Override
                                            public void onFailure(Call call, IOException e) {
                                                Log.d("充值", "onResponse: down");
                                            }

                                            @Override
                                            public void onResponse(Call call, Response response) throws IOException {
                                                Log.d("充值", "onResponse: YES");

                                            }
                                        });
                        }
                    });
                    HttpUtil.sendOkHttp("haveCar", new okhttp3.Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.d("获取出师失败", "onFailure: ");
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String data = response.body().string();
                            Log.d("data", "onResponse: "+data);
                            Gson gson = new Gson();
                            Type listType = new TypeToken<List<Accounts>>() {
                            }.getType();

                            if (!data.equals("")) {
                                accountList = gson.fromJson(data, listType);
                            }
                            Log.d("list", "onResponse: "+accountList.get(0).getOwner());
                            alertDialog.dismiss();


                        }

                    });

                }

            });

        }

        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        alertDialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.batch_top_up:
                isChecked = "";
                for (int i = 0; i < mListViewAccountCar.getChildCount(); i++) {
                    CheckBox checkBox = (CheckBox) mListViewAccountCar.getChildAt(i).findViewById(R.id.account_checkbox);
                    if (checkBox.isChecked()) {
                        TextView textView = (TextView) mListViewAccountCar.getChildAt(i).findViewById(R.id.account_car_license);
                        isChecked += textView.getText().toString() + " ";
                    }
                }
                addList(view);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
                break;
            case R.id.history_car_up:
                break;
        }
    }


}
