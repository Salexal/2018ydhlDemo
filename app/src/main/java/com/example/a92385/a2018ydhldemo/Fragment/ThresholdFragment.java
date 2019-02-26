package com.example.a92385.a2018ydhldemo.Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a92385.a2018ydhldemo.R;

public class ThresholdFragment extends Fragment implements View.OnClickListener {

    private View view;
    private  SharedPreferences sp;
    private SharedPreferences.Editor editor ;

    /**
     * 开
     */
    private TextView mUpOrDown;
    private Switch mOpenSwitch;
    private EditText mTemperatureSetThreshold;
    private EditText mHumiditySetThreshold;
    private EditText mLightSetThreshold;
    private EditText mCo2SetThreshold;
    private EditText mPm25SetThreshold;
    /**
     * 保存
     */
    private Button mBtnSetThreshold;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_threshold_fragment, container, false);
        initView(view);


        mOpenSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    setTextFocusableInTouchMode(false);
                    mLightSetThreshold.clearFocus();
                    mTemperatureSetThreshold.clearFocus();
                    mCo2SetThreshold.clearFocus();
                    mHumiditySetThreshold.clearFocus();
                    mPm25SetThreshold.clearFocus();
                }else {
                    setTextFocusableInTouchMode(true);
                }
            }
        });


        return view;
    }

    /**
     * 设置是否可编辑
     * time 2019/1/26
     * */
    public void setTextFocusableInTouchMode(boolean bool){
        if(bool)
            mUpOrDown.setText("关");
        else
            mUpOrDown.setText("开");
        mCo2SetThreshold.setFocusableInTouchMode(bool);
        mPm25SetThreshold.setFocusableInTouchMode(bool);
        mHumiditySetThreshold.setFocusableInTouchMode(bool);
        mTemperatureSetThreshold.setFocusableInTouchMode(bool);
        mLightSetThreshold.setFocusableInTouchMode(bool);
    }

    private void initView(View view) {
        mUpOrDown = (TextView) view.findViewById(R.id.up_or_down);
        mOpenSwitch = (Switch) view.findViewById(R.id.open_switch);
        mTemperatureSetThreshold = (EditText) view.findViewById(R.id.temperature_set_threshold);
        mHumiditySetThreshold = (EditText) view.findViewById(R.id.humidity_set_threshold);
        mLightSetThreshold = (EditText) view.findViewById(R.id.light_set_threshold);
        mCo2SetThreshold = (EditText) view.findViewById(R.id.co2_set_threshold);
        mPm25SetThreshold = (EditText) view.findViewById(R.id.pm25_set_threshold);
        mBtnSetThreshold = (Button) view.findViewById(R.id.btn_set_threshold);
        mBtnSetThreshold.setOnClickListener(this);

         sp = getActivity().getSharedPreferences("threshold",0);
        editor = sp.edit();
        if(sp.getString("switch","true").equals("false")){
            mOpenSwitch.setChecked(false);
        }else{
            setTextFocusableInTouchMode(false);
            mLightSetThreshold.clearFocus();
            mTemperatureSetThreshold.clearFocus();
            mCo2SetThreshold.clearFocus();
            mHumiditySetThreshold.clearFocus();
            mPm25SetThreshold.clearFocus();
        }

        setDefault(mCo2SetThreshold,"co2");
        setDefault(mHumiditySetThreshold,"humidity");
        setDefault(mLightSetThreshold,"light");
        setDefault(mPm25SetThreshold,"pm25");
        setDefault(mTemperatureSetThreshold,"temperature");

    }

    public void setDefault(EditText editText,String name){

            editText.setText(sp.getString(name,""));

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_set_threshold:
                editor.putString("switch", String.valueOf(mOpenSwitch.isChecked()));
                if(!mCo2SetThreshold.getText().toString().equals(""))
                editor.putString("co2",mCo2SetThreshold.getText().toString());
                if(!mPm25SetThreshold.getText().toString().equals(""))
                editor.putString("pm25",mPm25SetThreshold.getText().toString());
                if(!mTemperatureSetThreshold.getText().toString().equals(""))
                editor.putString("temperature",mTemperatureSetThreshold.getText().toString());
                if(!mLightSetThreshold.getText().toString().equals(""))
                editor.putString("light",mLightSetThreshold.getText().toString());
                if(!mHumiditySetThreshold.getText().toString().equals(""))
                editor.putString("humidity",mHumiditySetThreshold.getText().toString());
                editor.commit();
                Toast.makeText(getContext(),"保存成功",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
