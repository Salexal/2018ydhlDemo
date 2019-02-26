package com.example.a92385.a2018ydhldemo.LoginUser;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a92385.a2018ydhldemo.HomePageActivity;
import com.example.a92385.a2018ydhldemo.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String isFirstLogin;
    private Intent intent;
    /**
     * 网络设置
     */
    private LinearLayout mLayoutTitle;
    /**
     * 请输入用户名
     */
    private EditText mAccount;
    /**
     * 请输入密码
     */
    private EditText mPassword;
    private CheckBox mRememberAccount;
    private CheckBox mAutoLogin;
    /**
     * 登录
     */
    private Button mBtnLogin;
    /**
     * 注册
     */
    private Button mBtnRegistered;
    /**
     * 注册新账号
     */
    private TextView mRegisteredAccountTextBtn;
    /**
     * 找回密码
     */
    private TextView mFindAccountTextBtn;
    /**
     * 网络设置
     */
    private ImageView mInternet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        sharedPreferences = getSharedPreferences("isFirst", 0);
        editor = sharedPreferences.edit();


        isFirstLogin = sharedPreferences.getString("first", "true");
        loginFirst();
        if (sharedPreferences.getString("remember", "false").equals("true")) {
//            mRememberAccount.setChecked(true);
            mAccount.setText(sharedPreferences.getString("account", ""));
            mPassword.setText(sharedPreferences.getString("password", ""));
        }

        if (sharedPreferences.getString("account", "").equals("user1") && sharedPreferences.getString("password", "").equals("123456")) {
            Intent intent = new Intent(LoginActivity.this, HomePageActivity.class);
            startActivity(intent);
        }

    }


    public void loginFirst() {
        if (isFirstLogin.equals("true")) {
            editor.putString("first", "false");
            editor.commit();
            Intent intent = new Intent(LoginActivity.this, FirstActivity.class);
            startActivity(intent);
        }
    }

    private void initView() {
//        mInternet = (TextView) findViewById(R.id.internet);
//        mInternet.setOnClickListener(this);
        mLayoutTitle = (LinearLayout) findViewById(R.id.layout_title);
        mAccount = (EditText) findViewById(R.id.account);
        mPassword = (EditText) findViewById(R.id.password);
//        mRememberAccount = (CheckBox) findViewById(R.id.remember_account);
//        mAutoLogin = (CheckBox) findViewById(R.id.auto_login);
        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mBtnLogin.setOnClickListener(this);
//        mBtnRegistered = (Button) findViewById(R.id.btn_registered);
//        mBtnRegistered.setOnClickListener(this);
        mRegisteredAccountTextBtn = (TextView) findViewById(R.id.registered_account_text_btn);
        mRegisteredAccountTextBtn.setOnClickListener(this);
        mFindAccountTextBtn = (TextView) findViewById(R.id.find_account_text_btn);
        mFindAccountTextBtn.setOnClickListener(this);
        mInternet = (ImageView) findViewById(R.id.internet);
        mInternet.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.internet:
                intent = new Intent(LoginActivity.this, IntentActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.btn_login:
                if (!isTrueAccount(mAccount.getText().toString(), mPassword.getText().toString())) {
                    Toast.makeText(LoginActivity.this, "账号或密码错误", Toast.LENGTH_SHORT).show();
                    break;
                }
//                if (mRememberAccount.isChecked())
//                    editor.putString("remember", "true");
//                else
//                    editor.putString("remember", "false");
                editor.putString("account", mAccount.getText().toString());
                editor.putString("password", mPassword.getText().toString());
                editor.commit();
                intent = new Intent(LoginActivity.this, HomePageActivity.class);
                startActivity(intent);
                finish();
                break;
//            case R.id.btn_registered:
//                intent = new Intent(LoginActivity.this, RegisteredActivity.class);
//                startActivity(intent);
//                finish();
//                break;
            case R.id.registered_account_text_btn:
                intent = new Intent(LoginActivity.this, RegisteredActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.find_account_text_btn:
                intent = new Intent(LoginActivity.this, FindAccount.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    public static boolean isTrueAccount(String account, String password) {
        if (account.equals("user1") && password.equals("123456")) {
            return true;
        }
        return false;
    }
}
