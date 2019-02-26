package com.example.a92385.a2018ydhldemo.CusB;

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
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a92385.a2018ydhldemo.R;

public class CustomPageTwo extends Fragment implements View.OnClickListener {

    private View view;
    private ImageView mBackEtc;
    private DatePicker mCustomDatePicker;
    /**  */
    private TextView mCustomBusPageDate;
    /**
     * 下一步
     */
    private Button mBtnCustomPageNext;
    /**
     * 确认
     */
    private Button mSetData;
    private SharedPreferences sp ;
    private SharedPreferences.Editor editor;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_custom_page_two, container, false);
        initView(view);
        sp = getActivity().getSharedPreferences("customDate",0);
        editor = sp.edit();

        return view;
    }

    private void initView(View view) {
        mBackEtc = (ImageView) view.findViewById(R.id.back_etc);
        mBackEtc.setOnClickListener(this);
        mCustomDatePicker = (DatePicker) view.findViewById(R.id.custom_date_picker);
        mCustomBusPageDate = (TextView) view.findViewById(R.id.custom_bus_page_date);
        mCustomBusPageDate.setOnClickListener(this);
        mBtnCustomPageNext = (Button) view.findViewById(R.id.btn_custom_page_next);
        mBtnCustomPageNext.setOnClickListener(this);
        mSetData = (Button) view.findViewById(R.id.set_data);
        mSetData.setOnClickListener(this);
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
                replaceFragment(new CustomBus());
                break;
            case R.id.btn_custom_page_next:
                editor.putString("date",mCustomBusPageDate.getText().toString());
                editor.commit();
                replaceFragment(new CustomPageThree());
                break;
            case R.id.set_data:
                int year = mCustomDatePicker.getYear();
                int month = mCustomDatePicker.getMonth()+1;
                int day = mCustomDatePicker.getDayOfMonth();
                String data = mCustomBusPageDate.getText().toString();
                if(data.equals(""))
                    data+=""+year+"-"+month+"-"+day;
                else
                    data+=", "+year+"-"+month+"-"+day;
                mCustomBusPageDate.setText(data);
                break;
        }
    }
}
