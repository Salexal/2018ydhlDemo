package com.example.a92385.a2018ydhldemo.light;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.a92385.a2018ydhldemo.R;

import java.util.List;


public class LightAdapter extends ArrayAdapter {

    private int resourceId;
    private Context mContext;

    public LightAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        resourceId = resource;
        mContext = context;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(mContext).inflate(resourceId, parent, false);

        TextView mListViewLightId = (TextView) view.findViewById(R.id.listView_lightId);
        TextView mListViewLightGreen = (TextView) view.findViewById(R.id.listView_light_green);
        TextView mListViewLightYellow = (TextView) view.findViewById(R.id.listView_light_yellow);
        TextView mListViewLightRed = (TextView) view.findViewById(R.id.listView_light_red);

        TrafficLight light = (TrafficLight) getItem(position);

        mListViewLightId.setText(light.getLightId());
        mListViewLightGreen.setText(light.getGreenLight());
        mListViewLightYellow.setText(light.getYellowLight());
        mListViewLightRed.setText(light.getRedLight());

        return view;
    }


}
