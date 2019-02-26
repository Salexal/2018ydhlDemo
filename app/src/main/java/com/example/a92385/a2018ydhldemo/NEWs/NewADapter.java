package com.example.a92385.a2018ydhldemo.NEWs;

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

public class NewADapter extends ArrayAdapter<Neww> {

    private int resourceId;
    int num = 0;
    private Context mContext;

    public NewADapter(@NonNull Context context, int resource, @NonNull List<Neww> objects) {
        super(context, resource, objects);
        resourceId = resource;
        mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(mContext).inflate(resourceId, parent, false);
        TextView value = view.findViewById(R.id.list_view_new_value);

        Neww n = getItem(position);
        value.setText(n.getData());

        return view;
    }
}
