package com.example.a92385.a2018ydhldemo.light;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.a92385.a2018ydhldemo.PrePaidRecords.PrepaidRecord;
import com.example.a92385.a2018ydhldemo.R;

import java.util.List;

public class AdminLightAdapter extends ArrayAdapter {

    private int resourceId;
    private Context mContext;

    public AdminLightAdapter(@NonNull Context context, int resource, @NonNull List<TrafficLight> objects) {
        super(context, resource, objects);
        resourceId = resource;
        mContext = context;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        TextView mListViewLightSortId = (TextView) view.findViewById(R.id.listView_light_sort_id);
        TextView mListViewLightGreen= (TextView) view.findViewById(R.id.listView_light_green);
        TextView mListViewLightYellow = (TextView) view.findViewById(R.id.listView_light_yellow);
        TextView mListViewLightRed= (TextView) view.findViewById(R.id.listView_light_red);
        CheckBox mListViewLightChecked= (CheckBox) view.findViewById(R.id.listView_light_checked);
        Button mBtnLightSortOne= (Button) view.findViewById(R.id.btn_light_sort_one);

        TrafficLight trafficLight = (TrafficLight) getItem(position);

        mListViewLightSortId.setText(trafficLight.getLightId());
        mListViewLightGreen.setText(trafficLight.getGreenLight());
        mListViewLightRed.setText(trafficLight.getRedLight());
        mListViewLightYellow.setText(trafficLight.getRedLight());
        mBtnLightSortOne.setText("充值");

        return view;
    }

}
