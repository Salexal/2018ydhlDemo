package com.example.a92385.a2018ydhldemo.thrid;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.a92385.a2018ydhldemo.PrePaidRecords.PrepaidRecord;
import com.example.a92385.a2018ydhldemo.R;

import java.util.List;

public class AutoCarPrepaidAdapter extends ArrayAdapter<PrepaidRecord> {

    private int resourceId;
    private Context mContext;

    public AutoCarPrepaidAdapter(@NonNull Context context, int resource, @NonNull List<PrepaidRecord> recordList) {
        super(context, resource, recordList);
        resourceId = resource;
        mContext = context;
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(mContext).inflate(resourceId, parent, false);
        TextView mListViewRecordRecordId;
        TextView mListViewRecordCarId;
        TextView mListViewRecordMoney;
        TextView mListViewRecordTime;

        mListViewRecordRecordId = (TextView) view.findViewById(R.id.listView_record_recordId);
        mListViewRecordCarId = (TextView) view.findViewById(R.id.listView_record_carId);
        mListViewRecordMoney = (TextView) view.findViewById(R.id.listView_record_money);
        mListViewRecordTime = (TextView) view.findViewById(R.id.listView_record_time);

        PrepaidRecord record = (PrepaidRecord) getItem(position);

        mListViewRecordCarId.setText(record.getCarId());
        mListViewRecordMoney.setText(record.getMoney());
        mListViewRecordRecordId.setText(record.getRecordId());
        mListViewRecordTime.setText(record.getRecordTime().toString());

        return view;
    }
}
