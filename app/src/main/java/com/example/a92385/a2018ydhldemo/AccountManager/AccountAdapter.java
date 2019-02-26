package com.example.a92385.a2018ydhldemo.AccountManager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a92385.a2018ydhldemo.R;

import java.util.List;

public class AccountAdapter extends ArrayAdapter {

    private int resourceId;
    int num = 0;
    private Context mContext;
    public AccountAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        resourceId = resource;
        mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(mContext).inflate(resourceId, parent, false);
        TextView mAccountCarNum = (TextView) view.findViewById(R.id.account_car_num);
        ImageView mAccountCarImg= (ImageView) view.findViewById(R.id.account_car_img);
        TextView mAccountCarLicense = (TextView) view.findViewById(R.id.account_car_license);
        TextView mAccountCarOwner= (TextView) view.findViewById(R.id.account_car_owner);
        TextView mAccountCarBalance= (TextView) view.findViewById(R.id.account_car_balance);
        CheckBox mAccountCheckbox= (CheckBox) view.findViewById(R.id.account_checkbox);
        Button mBtnCarTopUp = (Button) view.findViewById(R.id.btn_car_top_up);

        Accounts account = (Accounts) getItem(position);

        mAccountCarImg.setImageResource(account.getImg());
        mAccountCarOwner.setText("车主："+account.getOwner());
        mAccountCarLicense.setText(account.getLicense());
        mAccountCarBalance.setText(account.getBalance());
        mBtnCarTopUp.setText("充值");

        return view;
    }


}
