package com.example.a92385.a2018ydhldemo.CHARGE;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import com.example.a92385.a2018ydhldemo.ETC.ETCPrepaidAdapter;
import com.example.a92385.a2018ydhldemo.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Charge extends Fragment implements View.OnClickListener {

    private View view;
    /**
     * 开始时间
     */
    private TextView mChargeBgTime;
    /**
     * 结束时间
     */
    private TextView mChargeEndTime;
    private ListView mParkSearchList;
    /**
     * 查询
     */
    private Button mSearchPark;

    private Calendar ca = Calendar.getInstance();
    final int[] mYear = {ca.get(Calendar.YEAR),ca.get(Calendar.YEAR)};
    final int[] mMonth = {ca.get(Calendar.MONTH),ca.get(Calendar.YEAR)};
    final int[] mDay = {ca.get(Calendar.DAY_OF_MONTH),ca.get(Calendar.YEAR)};
    private DatePickerDialog datePickerDialog;
    private List<ChargeBus> busList = new ArrayList<>();
    private ChargeBusAdapter adapter;
    private List<ChargeBus> busListCopy = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_charge, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mChargeBgTime = (TextView) view.findViewById(R.id.charge_bg_time);
        mChargeBgTime.setOnClickListener(this);
        mChargeEndTime = (TextView) view.findViewById(R.id.charge_end_time);
        mChargeEndTime.setOnClickListener(this);
        mParkSearchList = (ListView) view.findViewById(R.id.park_search_list);
        mSearchPark = (Button) view.findViewById(R.id.search_park);
        mSearchPark.setOnClickListener(this);
        init();
        busListCopy = busList;
        query();

    }

    public void init(){
        for(int i = 0;i<6;i++){
            ChargeBus chargeBus = new ChargeBus();
            chargeBus.setIcId("00"+i);
            chargeBus.setCarNum("Z000"+i+1);
            chargeBus.setStartTime("2019-2-"+(i+18));
            chargeBus.setEndTime("2019-2-"+(i+19));
            chargeBus.setMoney("100");
            chargeBus.setpName("admin");
            busList.add(chargeBus);
        }
    }

    public void query(){
        adapter = new ChargeBusAdapter(getContext(), R.layout.list_view_charge_item, busList);
        mParkSearchList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.charge_bg_time:
                 datePickerDialog = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                mYear[0] = year;
                                mMonth[0] = month;
                                mDay[0] = dayOfMonth;
                                final String data = (month + 1) + "月-" + dayOfMonth + "日 ";

                                mChargeBgTime.setText(""+mYear[0]+"-"+String.valueOf(mMonth[0]+1)+"-"+mDay[0]);
                            }
                        },
                        mYear[0], mMonth[0], mDay[0]);
                datePickerDialog.show();
                break;
            case R.id.charge_end_time:
                DatePickerDialog datePickerDialog1 = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                mYear[1] = year;
                                mMonth[1] = month;
                                mDay[1] = dayOfMonth;
                                final String data = (month + 1) + "月-" + dayOfMonth + "日 ";

                                mChargeEndTime.setText(""+mYear[1]+"-"+String.valueOf(mMonth[1]+1)+"-"+mDay[1]);
                            }
                        },
                        mYear[0], mMonth[0], mDay[0]);
                datePickerDialog1.show();
                break;
            case R.id.search_park:
                List<ChargeBus> tmp = new ArrayList<>();
                busList = busListCopy;
                for(ChargeBus chargeBus:busList){
                   String[] a  = chargeBus.getStartTime().split("-");
                    String[] b  = chargeBus.getEndTime().split("-");
                    int start = Integer.valueOf(a[2]);
                    int end = Integer.valueOf(b[2]);
                    if(start>=mDay[0]&&end<=mDay[1]){
                        tmp.add(chargeBus);
                    }
                }
                busList = tmp;
                query();
                break;
        }
    }
}
