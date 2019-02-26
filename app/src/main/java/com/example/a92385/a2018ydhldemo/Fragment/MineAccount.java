package com.example.a92385.a2018ydhldemo.Fragment;

import android.app.AlertDialog;
import android.content.SharedPreferences;
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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.a92385.a2018ydhldemo.AccountManager.Accounts;
import com.example.a92385.a2018ydhldemo.AccountManager.AccountAdapter;
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
import okhttp3.Response;

public class MineAccount extends Fragment implements View.OnClickListener {

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
    private MineAccountAdapter adapter;
    private List<Accounts> accountList = new ArrayList<>();
    private  String data;
    private SharedPreferences sharedPreferences;
    private  SharedPreferences.Editor editor;
    private List<PrepaidRecord> recordList = new ArrayList<>();

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

        sharedPreferences  = getActivity().getSharedPreferences("record",0);
        editor = sharedPreferences.edit();

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
                        adapter = new MineAccountAdapter(getContext(), R.layout.list_mine_account, accountList);
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



            ok_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int i = 0;
                    try{
                    for ( ; i < list.size();++i) {
                       String  name = list.get(i);
                        HttpUtil.sendOkHttp("haveCar/" + String.valueOf(Integer.valueOf(name) + 3), new okhttp3.Callback() {
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
                                            , value, new okhttp3.Callback() {
                                                @Override
                                                public void onFailure(Call call, IOException e) {
                                                    Log.d("充值", "onResponse: down");
                                                }

                                                @Override
                                                public void onResponse(Call call, Response response) throws IOException {
                                                    Log.d("充值", "onResponse: YES");

                                                    SimpleDateFormat dateFormat = new SimpleDateFormat(" yyyy-MM-dd HH:mm:ss");
                                                    String currentDate =   dateFormat.format( new Date() );
                                                    Gson gson = new Gson();
                                                    String data = sharedPreferences.getString("listRecord","");
                                                    Type listType = new TypeToken<List<PrepaidRecord>>() {
                                                    }.getType();
                                                    int  listLength = 0;
                                                    if(!data.equals("")){
                                                        recordList = gson.fromJson(data,listType);
                                                        listLength = recordList.size();
                                                    }

                                                    PrepaidRecord prepaidRecord = new PrepaidRecord();
                                                    prepaidRecord.setRecordId(String.valueOf(listLength++));
                                                    prepaidRecord.setMoney(editText.getText().toString());
                                                    prepaidRecord.setRecordTime(currentDate);
                                                    try {
                                                        prepaidRecord.setCarId(String.valueOf(Integer.valueOf(car.getString("id"))-3));
                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                    }
                                                    prepaidRecord.setTheOperator("admin");
                                                    recordList.add(prepaidRecord);
                                                    data = gson.toJson(recordList);
                                                    editor.putString("listRecord",data);
                                                    editor.commit();

                                                    HttpUtil.sendOkHttp("haveCar", new okhttp3.Callback() {
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
                                                                    mListViewAccountCar.setAdapter(adapter);
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
                    }
                        }catch (Exception e){

                        }finally {
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
                        TextView textView = (TextView) mListViewAccountCar.getChildAt(i).findViewById(R.id.account_car_num);
                        isChecked += textView.getText().toString() + " ";
                    }
                }
                addList(view);
                break;
            case R.id.history_car_up:
                replaceFragment(new PrepaidRecordsFragment());
                break;
        }
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment, fragment);
        transaction.commit();
    }

}
