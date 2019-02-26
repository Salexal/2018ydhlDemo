package com.example.a92385.a2018ydhldemo.LoginUser;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.a92385.a2018ydhldemo.R;

public class FindAccount extends AppCompatActivity implements View.OnClickListener {

    private ImageView mRegisteredInternet;
    private LinearLayout mLayoutTitle;
    /**
     * 请输入用户名
     */
    private EditText mRegisteredAccount;
    /**
     * 请输入邮箱
     */
    private EditText mRegisteredPasswordAgain;
    /**
     * 找回
     */
    private Button mBtnFindAccountClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_account);
        initView();
    }

    private void initView() {
        mRegisteredInternet = (ImageView) findViewById(R.id.registered_internet);
        mRegisteredInternet.setOnClickListener(this);
        mLayoutTitle = (LinearLayout) findViewById(R.id.layout_title);
        mRegisteredAccount = (EditText) findViewById(R.id.registered_account);
        mRegisteredPasswordAgain = (EditText) findViewById(R.id.registered_password_again);
        mBtnFindAccountClick = (Button) findViewById(R.id.btn_find_account_click);
        mBtnFindAccountClick.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.registered_internet:
                Intent intent = new Intent(FindAccount.this,LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_find_account_click:
                break;
        }
    }
}
