package com.example.a92385.a2018ydhldemo.AccountManager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
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



public class MineAccountAdapter extends ArrayAdapter  {

    private int resourceId;
    int num = 0;
    private Context mContext;
    private Callback mCallback;

    public interface Callback {
        public void click(View v);
     }
    public MineAccountAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        resourceId = resource;
        mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(mContext).inflate(resourceId, parent, false);
        TextView mAccountCarNum = (TextView) view.findViewById(R.id.account_car_num);
        TextView mAccountCarBalance= (TextView) view.findViewById(R.id.account_car_balance);
//        CheckBox mAccountCheckbox= (CheckBox) view.findViewById(R.id.account_checkbox);
        TextView mBtnCarTopUp = (TextView) view.findViewById(R.id.btn_car_top_up);
        Accounts account = (Accounts) getItem(position);
        mAccountCarNum.setText(String.valueOf(Integer.valueOf(account.getId())-3));
        mAccountCarBalance.setText(account.getBalance());
        mBtnCarTopUp.setText("充值");
//        mBtnCarTopUp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mBtnCarTopUp.setTag(position);
//                Log.d("tag", "onClick: "+position);
//            }
//        });
        return view;
    }

//    @Override
//    public void onClick(View v) {
//        mCallback.click(v);
//    }

}
