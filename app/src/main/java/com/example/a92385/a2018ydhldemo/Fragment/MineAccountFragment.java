package com.example.a92385.a2018ydhldemo.Fragment;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a92385.a2018ydhldemo.HttpUtils.HttpUtil;
import com.example.a92385.a2018ydhldemo.PrePaidRecords.PrepaidRecord;
import com.example.a92385.a2018ydhldemo.R;
import com.example.a92385.a2018ydhldemo.light.TrafficLight;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MineAccountFragment extends Fragment {


    private List<PrepaidRecord> recordList = new ArrayList<>();
    private View view;
    private int listLength = 0;
    private String carNumber;
    /**
     * 账户余额：00元
     */
    private TextView mAccountBalance;
    /**
     * 车         号：
     */
    private TextView mCarNumber;
    private Spinner mSpinner;
    private ArrayAdapter arrayAdapter;
    /**
     * 1-999
     */
    private EditText mUpMoney;
    /**
     * 查询
     */
    private Button mBtnQueryCar;
    /**
     * 充值
     */
    private Button mBtnUpCar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_mine_account,container,false);
        initView(view);
        relevanceSpinner();
        OkHttpClient okHttpClient = new OkHttpClient();

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("record",0);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        mSpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            //选择下拉框数据监听
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                carNumber = arrayAdapter.getItem(arg2).toString().split("号")[0];
//                Log.d("car", "onItemSelected: "+carNumber);
            }
            //未选择下拉框监听
            public void onNothingSelected(AdapterView<?> arg0) {
                carNumber = "";
            }
        });
        Request request1 = new Request.Builder().url("http://10.0.3.2:3003/recharge/1").build();
        okHttpClient.newCall(request1).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    Log.d("json", "onResponse: "+jsonObject);
                    final String Balance = jsonObject.getString("Balance");
                    getActivity().runOnUiThread(()-> mAccountBalance.setText("账户余额:"+Balance));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        mBtnQueryCar.setOnClickListener(v -> new Thread(()->{
            final Request request = new Request.Builder().url("http://10.0.3.2:3003/recharge/"+carNumber).build();
            Log.d("car", "onItemSelected:"+carNumber);
            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.d("json", "onResponse: false");
                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        Log.d("json", "onResponse: "+jsonObject);
                        final String Balance = jsonObject.getString("Balance");
                        getActivity().runOnUiThread(()-> mAccountBalance.setText("账户余额:"+Balance));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }).start());


            mBtnUpCar.setOnClickListener(v -> new Thread(()->{
                String value = mUpMoney.getText().toString();
                if(value.equals("")||Integer.valueOf(value)>999){
                    Toast.makeText(getContext(),"金额过于庞大无法为你充值！",Toast.LENGTH_SHORT).show();
                    return;
                }
                String num = mAccountBalance.getText().toString().split(":")[1];
                String num1 = mUpMoney.getText().toString();
                Log.d("num", "onCreateView: "+num+" "+num1);
                Request request = new Request.Builder()
                        .url("http://10.0.3.2:3003/recharge/"+carNumber)
                        .put(new FormBody.Builder()
                                .add("id",carNumber)
                                .add("Balance",String.valueOf(Integer.valueOf(num)+Integer.valueOf(num1)))
                                .build())
                        .build();
                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        try {
                            JSONObject jsonObject = new JSONObject(response.body().string());
                            final String Balance = jsonObject.getString("Balance");
                            getActivity().runOnUiThread(()-> mAccountBalance.setText("账户余额:"+Balance));



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

                SimpleDateFormat dateFormat = new SimpleDateFormat(" yyyy-MM-dd HH:mm:ss");
                String currentDate =   dateFormat.format( new Date() );
                Gson gson = new Gson();
                String data = sharedPreferences.getString("listRecord","");
                Type listType = new TypeToken<List<PrepaidRecord>>() {
                }.getType();

                if(!data.equals("")){
                    recordList = gson.fromJson(data,listType);
                    listLength = recordList.size();
                }

                PrepaidRecord prepaidRecord = new PrepaidRecord();
                prepaidRecord.setRecordId(String.valueOf(listLength++));
                prepaidRecord.setMoney(num1);
                prepaidRecord.setRecordTime(currentDate);
                prepaidRecord.setCarId(carNumber);
                prepaidRecord.setTheOperator("admin");
                recordList.add(prepaidRecord);
                data = gson.toJson(recordList);
                editor.putString("listRecord",data);
                editor.commit();
            }).start());

        return view;
    }

    private void initView(View view) {

        mAccountBalance = (TextView) view.findViewById(R.id.account_balance);
        mCarNumber = (TextView) view.findViewById(R.id.car_number);
        mSpinner = (Spinner) view.findViewById(R.id.spinner);
        mUpMoney = (EditText) view.findViewById(R.id.up_money);
        mBtnQueryCar = (Button) view.findViewById(R.id.btn_query_car);
        mBtnUpCar = (Button) view.findViewById(R.id.btn_up_car);

    }

    //使数据布局文件和Adapter适配器关联
    public void relevanceSpinner(){

        //R.array.spingArr 为 values 文件夹下 spinner.xml 中 string-array 的 name 值
        arrayAdapter = ArrayAdapter.createFromResource(getContext(), R.array.spinner, android.R.layout.simple_spinner_item);

        //将 adapter2 添加到 spinner 中
        mSpinner.setAdapter(arrayAdapter);
    }


}
