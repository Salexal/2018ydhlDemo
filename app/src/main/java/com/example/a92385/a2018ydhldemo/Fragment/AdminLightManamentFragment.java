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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;


import com.example.a92385.a2018ydhldemo.HttpUtils.HttpUtil;
import com.example.a92385.a2018ydhldemo.R;
import com.example.a92385.a2018ydhldemo.light.AdminLightAdapter;

import com.example.a92385.a2018ydhldemo.light.TrafficLight;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class AdminLightManamentFragment extends Fragment implements View.OnClickListener {


    private String isChecked;
    private View view;
    private Spinner mSpinnerSortLight;
    /**
     * 查询
     */
    private Button mBtnSortLight;
    /**
     * 查询
     */
    private Button mBtnSortLightPi;
    private ListView mListViewLightSort;
    private List<TrafficLight> lightList = new ArrayList<>();
    private AdminLightAdapter adminLightAdapter;

    private String sortValue;
    private ArrayAdapter arrayAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_admin_light_manament_fragment, container, false);
        initView(view);
        relevanceSpinner();
        mSpinnerSortLight.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            //选择下拉框数据监听
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                sortValue = arrayAdapter.getItem(arg2).toString();
//                Log.d("car", "onItemSelected: "+carNumber);
            }

            //未选择下拉框监听
            public void onNothingSelected(AdapterView<?> arg0) {
                sortValue = "";
            }
        });
        return view;
    }

    private void initView(View view) {
        mSpinnerSortLight = (Spinner) view.findViewById(R.id.spinner_sort_light);
        mBtnSortLight = (Button) view.findViewById(R.id.btn_sort_light);
        mBtnSortLight.setOnClickListener(this);
        mBtnSortLightPi = (Button) view.findViewById(R.id.btn_sort_light_pi);
        mBtnSortLightPi.setOnClickListener(this);
        mListViewLightSort = (ListView) view.findViewById(R.id.listView_light_sort);
        inHttp();

    }

    public void inHttp() {
        HttpUtil.sendOkHttp("trafficLight", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("获取初始", "onFailure: false");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String data = response.body().string();
                Gson gson = new Gson();
                Type listType = new TypeToken<List<TrafficLight>>() {
                }.getType();
                lightList = gson.fromJson(data, listType);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adminLightAdapter = new AdminLightAdapter(getContext(), R.layout.listview_light_sort_item, lightList);
                        mListViewLightSort.setAdapter(adminLightAdapter);
                        adminLightAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_sort_light:

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        HttpUtil.sendOkHttp("trafficLight", new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                                Log.d("获取初始", "onFailure: false");
                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                String data = response.body().string();
                                Gson gson = new Gson();
                                Type listType = new TypeToken<List<TrafficLight>>() {
                                }.getType();
                                lightList = gson.fromJson(data, listType);
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (sortValue.equals("路口降序")) {
                                            Collections.sort(lightList, new Comparator<TrafficLight>() {
                                                @Override
                                                public int compare(TrafficLight o1, TrafficLight o2) {
                                                    return -o1.getLightId().compareTo(o2.getLightId());
                                                }
                                            });
                                        } else if (sortValue.equals("红灯降序")) {
                                           setflag("降","红");
                                        } else if (sortValue.equals("绿灯降序")) {
                                            setflag("绿","红");
                                        }
                                        adminLightAdapter = new AdminLightAdapter(getContext(), R.layout.listview_light_sort_item, lightList);
                                        mListViewLightSort.setAdapter(adminLightAdapter);
                                        adminLightAdapter.notifyDataSetChanged();
                                    }
                                });
                            }
                        });
                        sort();
                    }
                });
                break;
            case R.id.btn_sort_light_pi:

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        addList(view);
                        HttpUtil.sendOkHttp("trafficLight", new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                                Log.d("获取初始", "onFailure: false");
                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                String data = response.body().string();
                                Gson gson = new Gson();
                                Type listType = new TypeToken<List<TrafficLight>>() {
                                }.getType();
                                lightList = gson.fromJson(data, listType);
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (sortValue.equals("路口降序")) {
                                            Collections.sort(lightList, new Comparator<TrafficLight>() {
                                                @Override
                                                public int compare(TrafficLight o1, TrafficLight o2) {
                                                    return -o1.getLightId().compareTo(o2.getLightId());
                                                }
                                            });
                                        } else if (sortValue.equals("红灯降序")) {
                                            Collections.sort(lightList, new Comparator<TrafficLight>() {
                                                @Override
                                                public int compare(TrafficLight o1, TrafficLight o2) {
                                                    return -o1.getRedLight().compareTo(o2.getRedLight());
                                                }
                                            });
                                        } else if (sortValue.equals("绿灯降序")) {
                                            Collections.sort(lightList, new Comparator<TrafficLight>() {
                                                @Override
                                                public int compare(TrafficLight o1, TrafficLight o2) {
                                                    return -o1.getGreenLight().compareTo(o2.getGreenLight());
                                                }
                                            });
                                        }else if (sortValue.equals("绿灯升序")) {
                                            Collections.sort(lightList, new Comparator<TrafficLight>() {
                                                @Override
                                                public int compare(TrafficLight o1, TrafficLight o2) {
                                                    return o1.getGreenLight().compareTo(o2.getGreenLight());
                                                }
                                            });
                                        }else if (sortValue.equals("红灯升序")) {
                                            Collections.sort(lightList, new Comparator<TrafficLight>() {
                                                @Override
                                                public int compare(TrafficLight o1, TrafficLight o2) {
                                                    return o1.getRedLight().compareTo(o2.getRedLight());
                                                }
                                            });
                                        }else if (sortValue.equals("黄灯升序")) {
                                            Collections.sort(lightList, new Comparator<TrafficLight>() {
                                                @Override
                                                public int compare(TrafficLight o1, TrafficLight o2) {
                                                    return o1.getYellowLight().compareTo(o2.getYellowLight());
                                                }
                                            });
                                        }
                                        adminLightAdapter = new AdminLightAdapter(getContext(), R.layout.listview_light_sort_item, lightList);
                                        mListViewLightSort.setAdapter(adminLightAdapter);
                                        adminLightAdapter.notifyDataSetChanged();
                                    }
                                });
                            }
                        });
                        sort();
                    }
                });

                break;
        }
    }

    public void sort() {

    }

    /**
     * sortValue.equals("路口降序")) {
     Collections.sort(lightList, new Comparator<TrafficLight>() {
    @Override
    public int compare(TrafficLight o1, TrafficLight o2) {
    return -o1.getLightId().compareTo(o2.getLightId());
    }
    });
     * */
    public void setflag(String flag,String value){
        if(flag.equals("升")){
            Collections.sort(lightList, new Comparator<TrafficLight>() {
                @Override
                public int compare(TrafficLight trafficLight, TrafficLight trafficLight1) {
                    int len = getlight(value,trafficLight,trafficLight1);
                    return len;
                }
            });
        }else{
            Collections.sort(lightList, new Comparator<TrafficLight>() {
                @Override
                public int compare(TrafficLight trafficLight, TrafficLight trafficLight1) {
                    int len = getlight(value,trafficLight,trafficLight1);
                    return -len;
                }
            });
        }
    }

    public int getlight(String sortValue,TrafficLight o1,TrafficLight o2){
        if(sortValue.equals("红"))
            return o1.getRedLight().compareTo(o2.getRedLight());
         else if(sortValue.equals("黄"))
            return o1.getYellowLight().compareTo(o2.getYellowLight());
        else if(sortValue.equals("绿"))
            return o1.getGreenLight().compareTo(o2.getGreenLight());
        return 1;
    }

    public void relevanceSpinner() {

        //R.array.spingArr 为 values 文件夹下 spinner.xml 中 string-array 的 name 值
        arrayAdapter = ArrayAdapter.createFromResource(getContext(), R.array.spinner_sort_car, android.R.layout.simple_spinner_item);

        //将 adapter2 添加到 spinner 中
        mSpinnerSortLight.setAdapter(arrayAdapter);

    }


    public void addList(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view1 = View.inflate(getContext(), R.layout.dialog_light, null);
        builder.setTitle("红绿灯管理")
                .setView(view1);
        final AlertDialog alertDialog = builder.create();
        EditText red = (EditText) view1.findViewById(R.id.dialog_light_red);
        EditText yellow = (EditText) view1.findViewById(R.id.dialog_light_yellow);
        EditText green = (EditText) view1.findViewById(R.id.dialog_light_green);

        Button ok_btn = (Button) view1.findViewById(R.id.ok);
        Button cancel_btn = (Button) view1.findViewById(R.id.cancel);

        isChecked = "";
        for (int i = 0; i < mListViewLightSort.getChildCount(); i++) {
            CheckBox checkBox = (CheckBox) mListViewLightSort.getChildAt(i).findViewById(R.id.listView_light_checked);
            if (checkBox.isChecked()) {
                TextView textView = (TextView) mListViewLightSort.getChildAt(i).findViewById(R.id.listView_light_sort_id);
                isChecked += textView.getText().toString() + " ";
            }
        }
        String[] lightSplit = isChecked.split(" ");
        List<TrafficLight> list = new ArrayList<>();


        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (TrafficLight light : lightList) {
                    for (int i = 0; i < lightSplit.length; i++) {
                        if (light.getLightId().equals(lightSplit[i])) {
                            TrafficLight newlight = new TrafficLight();
                            newlight.setLightId(light.getLightId());
                            newlight.setGreenLight(green.getText().toString());
                            newlight.setRedLight(red.getText().toString());
                            newlight.setYellowLight(yellow.getText().toString());
                            HttpUtil.putOkHttp("trafficLight/" + light.getId(), light.getId(), newlight.getLightId()
                                    , newlight.getRedLight(), newlight.getYellowLight(), newlight.getGreenLight()
                                    , new Callback() {
                                        @Override
                                        public void onFailure(Call call, IOException e) {

                                        }

                                        @Override
                                        public void onResponse(Call call, Response response) throws IOException {
                                            Log.d("light", "onResponse: " + newlight);

                                        }
                                    });
                        }

                    }
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
