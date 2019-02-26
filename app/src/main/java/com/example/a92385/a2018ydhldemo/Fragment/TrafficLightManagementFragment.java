package com.example.a92385.a2018ydhldemo.Fragment;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.a92385.a2018ydhldemo.R;
import com.example.a92385.a2018ydhldemo.light.LightAdapter;
import com.example.a92385.a2018ydhldemo.light.TrafficLight;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TrafficLightManagementFragment extends Fragment implements View.OnClickListener {


    private View view;
    private String sortValue;
    private Spinner mSpinnerSortCar;
    private ArrayAdapter arrayAdapter;
    private LightAdapter adapter;
    private List<TrafficLight> lightList = new ArrayList<>();


    /**
     * 查询
     */
    private Button mBtnSortCar;
    private ListView mListViewCar;
    private ImageView mAnimation;
    private AnimationDrawable animationDrawable;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_traffic_light_management, container, false);
        initView(view);
        relevanceSpinner();
        mSpinnerSortCar.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
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
        mBtnSortCar.setOnClickListener(v -> {
            if (sortValue.equals("路口降序")) {
                Collections.sort(lightList, new Comparator<TrafficLight>() {
                    @Override
                    public int compare(TrafficLight o1, TrafficLight o2) {
                        return -o1.getLightId().compareTo(o2.getLightId());
                    }
                });
            }else if (sortValue.equals("路口升序")) {
                Collections.sort(lightList, new Comparator<TrafficLight>() {
                    @Override
                    public int compare(TrafficLight o1, TrafficLight o2) {
                        return o1.getLightId().compareTo(o2.getLightId());
                    }
                });
            } else if (sortValue.equals("红灯降序")) {
                Collections.sort(lightList, new Comparator<TrafficLight>() {
                    @Override
                    public int compare(TrafficLight o1, TrafficLight o2) {
                        return o1.getRedLight().compareTo(o2.getRedLight());
                    }
                });
            }else if (sortValue.equals("红灯升序")) {
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
                        return o1.getGreenLight().compareTo(o2.getGreenLight());
                    }
                });
            }else if (sortValue.equals("绿灯升序")) {
                Collections.sort(lightList, new Comparator<TrafficLight>() {
                    @Override
                    public int compare(TrafficLight o1, TrafficLight o2) {
                        return -o1.getGreenLight().compareTo(o2.getGreenLight());
                    }
                });
            }else if (sortValue.equals("黄灯降序")) {
                Collections.sort(lightList, new Comparator<TrafficLight>() {
                    @Override
                    public int compare(TrafficLight o1, TrafficLight o2) {
                        return -o1.getYellowLight().compareTo(o2.getYellowLight());
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
            adapter.notifyDataSetChanged();
        });
        return view;
    }


    public void relevanceSpinner() {

        //R.array.spingArr 为 values 文件夹下 spinner.xml 中 string-array 的 name 值
        arrayAdapter = ArrayAdapter.createFromResource(getContext(), R.array.spinner_sort_car, android.R.layout.simple_spinner_item);

        //将 adapter2 添加到 spinner 中
        mSpinnerSortCar.setAdapter(arrayAdapter);
    }

    private void initView(View view) {
        mSpinnerSortCar = (Spinner) view.findViewById(R.id.spinner_sort_car);
        mBtnSortCar = (Button) view.findViewById(R.id.btn_sort_car);
        mBtnSortCar.setOnClickListener(this);
        mListViewCar = (ListView) view.findViewById(R.id.listView_car);
        lightList.add(new TrafficLight("1", "20", "15", "10"));
        lightList.add(new TrafficLight("2", "20", "10", "15"));
        lightList.add(new TrafficLight("3", "10", "15", "20"));

        adapter = new LightAdapter(getContext(), R.layout.listview_item, lightList);
        mListViewCar.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        mAnimation = (ImageView) view.findViewById(R.id.animation);

        mAnimation.setImageResource(R.drawable.animation_1);
        animationDrawable = (AnimationDrawable) mAnimation.getDrawable();
        animationDrawable.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_sort_car:
                break;
            case R.id.spinner_sort_car:
                break;
            case R.id.listView_car:
                break;
        }
    }

}
