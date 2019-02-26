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

import com.example.a92385.a2018ydhldemo.R;

public class Forbidden extends Fragment implements View.OnClickListener {

    private View view;
    private ImageView mForbiddenIntent;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_forbidden, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mForbiddenIntent = (ImageView) view.findViewById(R.id.forbidden_intent);
        mForbiddenIntent.setOnClickListener(this);
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment, fragment);
        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.forbidden_intent:
                replaceFragment(new ScenicSpots());
                break;
        }
    }
}
