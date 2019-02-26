package com.example.a92385.a2018ydhldemo.Fragment;

import android.content.SharedPreferences;
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
import android.widget.ListView;
import android.widget.Spinner;

import com.example.a92385.a2018ydhldemo.PrePaidRecords.PrepaidRecord;
import com.example.a92385.a2018ydhldemo.PrePaidRecords.RecordAdapter;
import com.example.a92385.a2018ydhldemo.R;
import com.example.a92385.a2018ydhldemo.light.LightAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PrepaidRecordsFragment extends Fragment implements View.OnClickListener {

    private List<PrepaidRecord> recordList;
    private View view;
    private String num;
    private Spinner mSpinnerPrepaidRecordSort;
    private ArrayAdapter arrayAdapter;
    private RecordAdapter adapter;
    /**
     * 查询
     */
    private Button mBtnPrepaidRecordSortQuery;
    private ListView mListViewPrepaidRecord;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_prepaid_records, container, false);
        initView(view);
        relevanceSpinner();
        getPrepaidRecordValue();
        mSpinnerPrepaidRecordSort.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            //选择下拉框数据监听
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                num = arrayAdapter.getItem(arg2).toString().split("号")[0];
//                Log.d("car", "onItemSelected: "+carNumber);
            }
            //未选择下拉框监听
            public void onNothingSelected(AdapterView<?> arg0) {
                num = "";
            }
        });

        Collections.sort(recordList, new Comparator<PrepaidRecord>() {
            @Override
            public int compare(PrepaidRecord o1, PrepaidRecord o2) {
                return -o1.getRecordTime().compareTo(o2.getRecordTime());
            }
        });
        return view;
    }

    public void getPrepaidRecordValue(){

    }


    private void initView(View view) {
        mSpinnerPrepaidRecordSort = (Spinner) view.findViewById(R.id.spinner_prepaid_record_sort);
        mBtnPrepaidRecordSortQuery = (Button) view.findViewById(R.id.btn_prepaid_record_sort_query);
        mBtnPrepaidRecordSortQuery.setOnClickListener(this);
        mListViewPrepaidRecord = (ListView) view.findViewById(R.id.listView_prepaid_record);

        SharedPreferences sp = getActivity().getSharedPreferences("record",0);
        recordList = new ArrayList<>();
        Gson gson = new Gson();
        String data = sp.getString("listRecord","");
        Type listType = new TypeToken<List<PrepaidRecord>>() {
        }.getType();

        if(!data.equals("")){
            recordList = gson.fromJson(data,listType);
        }
//    危险
        adapter = new RecordAdapter(getContext(),R.layout.listview_record_item,recordList);
        mListViewPrepaidRecord.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_prepaid_record_sort_query:
                if(num.equals("时间降序")){
                    Collections.sort(recordList, new Comparator<PrepaidRecord>() {
                        @Override
                        public int compare(PrepaidRecord o1, PrepaidRecord o2) {
                            return -o1.getRecordTime().compareTo(o2.getRecordTime());
                        }
                    });
                }else if(num.equals("时间升序")){
                    Collections.sort(recordList, new Comparator<PrepaidRecord>() {
                        @Override
                        public int compare(PrepaidRecord o1, PrepaidRecord o2) {
                            return o1.getRecordTime().compareTo(o2.getRecordTime());
                        }
                    });
                }
                adapter.notifyDataSetChanged();
                break;
        }
    }

    public void relevanceSpinner(){

        //R.array.spingArr 为 values 文件夹下 spinner.xml 中 string-array 的 name 值
        arrayAdapter = ArrayAdapter.createFromResource(getContext(), R.array.spinner_sort_prepaid_record, android.R.layout.simple_spinner_item);

        //将 adapter2 添加到 spinner 中
        mSpinnerPrepaidRecordSort.setAdapter(arrayAdapter);
    }
}
