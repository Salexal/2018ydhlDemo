package com.example.a92385.a2018ydhldemo.thrid;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.a92385.a2018ydhldemo.AccountManager.Accounts;
import com.example.a92385.a2018ydhldemo.R;

import java.util.List;

public class RemoteAdapter extends ArrayAdapter<Accounts> {

    private int resourceId;
    int num = 0;
    private Context mContext;

    public RemoteAdapter(@NonNull Context context, int resource, @NonNull List<Accounts> objects) {
        super(context, resource, objects);
        resourceId = resource;
        mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(mContext).inflate(resourceId, parent, false);
        TextView mCarNum = (TextView) view.findViewById(R.id.car_num);
        TextView mRemoteCarStart = (TextView) view.findViewById(R.id.remote_car_start);
        TextView mRemoteCarStop = (TextView) view.findViewById(R.id.remote_car_stop);
        mRemoteCarStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String val = position+"-"+1;
                view.setTag(val);

                mRemoteCarStart.setBackgroundResource(R.drawable.remote_green);
                mRemoteCarStop.setBackgroundResource(R.drawable.borders);
            }
        });
        mRemoteCarStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String val = position+"-"+2;
                view.setTag(val);
                Log.d("tag", "onClick: "+val);
                mRemoteCarStop.setBackgroundResource(R.drawable.remote_green);
                mRemoteCarStart.setBackgroundResource(R.drawable.borders);
            }
        });
        Accounts accounts = getItem(position);
        mCarNum.setText(String.valueOf(Integer.valueOf(accounts.getId()) - 3));
        return view;
    }

}
