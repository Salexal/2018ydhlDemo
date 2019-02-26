package com.example.a92385.a2018ydhldemo.Tourism;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.a92385.a2018ydhldemo.R;

public class Weather extends Fragment {

    private View view;
    private ImageView mInternet;
    private LinearLayout mLayoutTitle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_weather, container, false);
        initView(view);
        mInternet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    replaceFragment(new Weather());
            }
        });
        return view;
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment, fragment);
        transaction.commit();
    }
    private void initView(View view) {
        mInternet = (ImageView) view.findViewById(R.id.internet);
        mLayoutTitle = (LinearLayout) view.findViewById(R.id.layout_title);

    }

}
