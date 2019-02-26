package com.example.a92385.a2018ydhldemo.Fragment;

import android.app.DatePickerDialog;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.a92385.a2018ydhldemo.R;

import java.util.Calendar;


public class TravelManagementFragment extends Fragment implements View.OnClickListener {

    private View view;
    private AnimationDrawable animationDrawable;
    /**
     * 单日出行车辆：
     */
    private TextView mTodayCar;
    /**
     * 开
     */
    private TextView mCarTextNum1;
    private Switch mCarSwitchNum1;
    /**
     * 开
     */
    private TextView mCarTextNum2;
    private Switch mCarSwitchNum2;
    /**
     * 开
     */
    private TextView mCarTextNum3;
    private Switch mCarSwitchNum3;
    private ImageView mAnimation;
    /**
     * 单日出行车辆：
     */
    private TextView mDate;
    /**
     * 单日出行车辆：
     */

    private Calendar ca = Calendar.getInstance();
    final int[] mYear = {ca.get(Calendar.YEAR)};
    final int[] mMonth = {ca.get(Calendar.MONTH)};
    final int[] mDay = {ca.get(Calendar.DAY_OF_MONTH)};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.activity_travel_management_fragment, container, false);
        initView(view);


        if(mDay[0]%2==0) {
            mTodayCar.setText("双号出行车辆： 2");
            mCarSwitchNum1.setChecked(false);
            mCarSwitchNum1.setClickable(false);
            mCarSwitchNum2.setChecked(true);
            mCarSwitchNum2.setClickable(true);
            mCarSwitchNum3.setChecked(false);
            mCarSwitchNum3.setClickable(false);
            mCarTextNum1.setText("停");
            mCarTextNum2.setText("开");
            mCarTextNum3.setText("停");
        }
        else{
            mTodayCar.setText("单号出行车辆： 1、3");
            mCarSwitchNum3.setChecked(true);
            mCarSwitchNum3.setClickable(true);
            mCarSwitchNum1.setChecked(true);
            mCarSwitchNum1.setClickable(true);
            mCarSwitchNum2.setChecked(false);
            mCarSwitchNum2.setClickable(false);
            mCarTextNum1.setText("开");
            mCarTextNum2.setText("停");
            mCarTextNum3.setText("开");
        }

        return view;
    }

    private void initView(View view) {
        mTodayCar = (TextView) view.findViewById(R.id.today_car);
        mCarTextNum1 = (TextView) view.findViewById(R.id.car_text_num1);
        mCarSwitchNum1 = (Switch) view.findViewById(R.id.car_switch_num1);
        mCarTextNum2 = (TextView) view.findViewById(R.id.car_text_num2);
        mCarSwitchNum2 = (Switch) view.findViewById(R.id.car_switch_num2);
        mCarTextNum3 = (TextView) view.findViewById(R.id.car_text_num3);
        mCarSwitchNum3 = (Switch) view.findViewById(R.id.car_switch_num3);
        mAnimation = (ImageView) view.findViewById(R.id.animation);

        mAnimation.setImageResource(R.drawable.animation);
        animationDrawable = (AnimationDrawable) mAnimation.getDrawable();
        animationDrawable.start();

        mDate = (TextView) view.findViewById(R.id.data_change);
        mDate.setText(""+mYear[0]+"-"+String.valueOf(mMonth[0]+1)+"-"+mDay[0]);
        mDate.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.data_change:
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                mYear[0] = year;
                                mMonth[0] = month;
                                mDay[0] = dayOfMonth;
                                final String data = (month + 1) + "月-" + dayOfMonth + "日 ";

                                if(mDay[0]%2==0) {
                                    mTodayCar.setText("双号出行车辆： 2");
                                    mCarSwitchNum1.setChecked(false);
                                    mCarSwitchNum1.setClickable(false);
                                    mCarSwitchNum2.setChecked(true);
                                    mCarSwitchNum2.setClickable(true);
                                    mCarSwitchNum3.setChecked(false);
                                    mCarSwitchNum3.setClickable(false);
                                    mCarTextNum1.setText("停");
                                    mCarTextNum2.setText("开");
                                    mCarTextNum3.setText("停");
                                }
                                else{
                                    mTodayCar.setText("单号出行车辆： 1、3");
                                    mCarSwitchNum3.setChecked(true);
                                    mCarSwitchNum3.setClickable(true);
                                    mCarSwitchNum1.setChecked(true);
                                    mCarSwitchNum1.setClickable(true);
                                    mCarSwitchNum2.setChecked(false);
                                    mCarSwitchNum2.setClickable(false);
                                    mCarTextNum1.setText("开");
                                    mCarTextNum2.setText("停");
                                    mCarTextNum3.setText("开");
                                }
                                mDate.setText(""+mYear[0]+"-"+String.valueOf(mMonth[0]+1)+"-"+mDay[0]);
                            }

                        },
                        mYear[0], mMonth[0], mDay[0]);

                datePickerDialog.show();

                break;
        }
    }
}
