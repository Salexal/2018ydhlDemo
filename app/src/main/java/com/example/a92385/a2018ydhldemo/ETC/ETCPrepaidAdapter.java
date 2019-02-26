package com.example.a92385.a2018ydhldemo.ETC;

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

public class ETCPrepaidAdapter extends ArrayAdapter<ETCPrepaid> {

    private int resourceId;
    int num = 0;
    private Context mContext;

    public ETCPrepaidAdapter(@NonNull Context context, int resource, @NonNull List<ETCPrepaid> objects) {
        super(context, resource, objects);
        resourceId = resource;
        mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(mContext).inflate(resourceId, parent, false);
        TextView mEtcItemCarId= (TextView) view.findViewById(R.id.etc_item_car_id);
        TextView mEtcItemCarName= (TextView) view.findViewById(R.id.etc_item_car_name);
        TextView mEtcItemCarMoney= (TextView) view.findViewById(R.id.etc_item_car_money);
        TextView mEtcItemCarDate= (TextView) view.findViewById(R.id.etc_item_car_date);

        ETCPrepaid prepaid = getItem(position);

        mEtcItemCarDate.setText(prepaid.getDate());
        mEtcItemCarId.setText(prepaid.getCarId());
        mEtcItemCarMoney.setText(prepaid.getMoney());
        mEtcItemCarName.setText(prepaid.getCarName());

        return view;
    }





}
