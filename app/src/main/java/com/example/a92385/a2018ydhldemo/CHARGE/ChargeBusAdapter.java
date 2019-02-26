package com.example.a92385.a2018ydhldemo.CHARGE;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.a92385.a2018ydhldemo.R;

import java.util.List;

public class ChargeBusAdapter extends ArrayAdapter<ChargeBus> {

    private int resourceId;
    int num = 0;
    private Context mContext;

    public ChargeBusAdapter(@NonNull Context context, int resource, @NonNull List<ChargeBus> objects) {
        super(context, resource, objects);
        resourceId = resource;
        mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(mContext).inflate(resourceId, parent, false);
        TextView mItemIcNum= (TextView) view.findViewById(R.id.item_ic_num);
        TextView mItemCarNum= (TextView) view.findViewById(R.id.item_car_num);
        TextView mItemName= (TextView) view.findViewById(R.id.item_name);
        TextView mItemStartTime= (TextView) view.findViewById(R.id.item_start_time);
        TextView mItemEndTime= (TextView) view.findViewById(R.id.item_end_time);
        TextView mItemMoney = (TextView) view.findViewById(R.id.item_money);
        ChargeBus bus = getItem(position);
        mItemCarNum.setText(bus.getCarNum());
        mItemIcNum.setText(bus.getIcId());
        mItemName.setText(bus.getpName());
         mItemStartTime.setText(bus.getStartTime());
        mItemEndTime.setText(bus.getEndTime());
        mItemMoney.setText(bus.getMoney());

        return view;
    }

}
