package com.example.a92385.a2018ydhldemo.NEWs;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.a92385.a2018ydhldemo.R;

import java.util.ArrayList;
import java.util.List;

public class News extends Fragment implements View.OnClickListener {

    private View view;
    /**
     * 科技
     */
    private TextView mNewsTechnology;
    /**
     * 教育
     */
    private TextView mNewsTeach;
    /**
     * 体育
     */
    private TextView mNewsPe;
    private ListView mListViewNews;
    private NewADapter adapter;
    private List<Neww> newwList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_news, container, false);
        initView(view);
        return view;
    }


    private void initView(View view) {
        mNewsTechnology = (TextView) view.findViewById(R.id.news_technology);
        mNewsTeach = (TextView) view.findViewById(R.id.news_teach);
        mNewsPe = (TextView) view.findViewById(R.id.news_pe);
        mListViewNews = (ListView) view.findViewById(R.id.list_view_news);

        for (int i = 0; i < 6; i++) {
            Neww n = new Neww();
            n.setData("aaaaa");
            newwList.add(n);
        }
        setNewData();
        mNewsTechnology.setTextColor(Color.parseColor("#FFF68F"));
        mNewsTechnology.setOnClickListener(this);
        mNewsTeach.setOnClickListener(this);
        mNewsPe.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.news_technology:
                newwList = new ArrayList<>();
                for (int i = 0; i < 6; i++) {
                    Neww n = new Neww();
                    n.setData("aaaaa");
                    newwList.add(n);
                }
                setNewData();
                mNewsTechnology.setTextColor(Color.parseColor("#FFF68F"));
                mNewsPe.setTextColor(Color.BLACK);
                mNewsTeach.setTextColor(Color.BLACK);
                break;
            case R.id.news_teach:
                newwList = new ArrayList<>();
                for (int i = 0; i < 6; i++) {
                    Neww n = new Neww();
                    n.setData("bbbbb");
                    newwList.add(n);
                }
                setNewData();
                mNewsTechnology.setTextColor(Color.BLACK);
                mNewsPe.setTextColor(Color.BLACK);
                mNewsTeach.setTextColor(Color.parseColor("#FFF68F"));
                break;
            case R.id.news_pe:
                newwList = new ArrayList<>();
                for (int i = 0; i < 6; i++) {
                    Neww n = new Neww();
                    n.setData("cccc");
                    newwList.add(n);
                }
                setNewData();
                mNewsTechnology.setTextColor(Color.BLACK);
                mNewsPe.setTextColor(Color.parseColor("#FFF68F"));
                mNewsTeach.setTextColor(Color.BLACK);
                break;
        }
    }

    public void setNewData(){
        adapter = new NewADapter(getContext(), R.layout.list_new_item, newwList);
        mListViewNews.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
