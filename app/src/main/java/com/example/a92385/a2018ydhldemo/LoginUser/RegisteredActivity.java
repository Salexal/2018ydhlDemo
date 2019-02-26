package com.example.a92385.a2018ydhldemo.LoginUser;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.a92385.a2018ydhldemo.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisteredActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 网络设置
     */
//    private TextView mRegisteredInternet;
    /**
     * 请输入用户名
     */
    private EditText mRegisteredAccount;
    /**
     * 请输入密码
     */
    private EditText mRegisteredPassword;
    /**
     * 请再次输入密码
     */
    private EditText mRegisteredPasswordAgain;
    /**
     * 请输入手机号
     */
    private EditText mRegisteredPhone;
    /**
     * 取消
     */
    private Button mBtnRegisteredCancel;
    /**
     * 注册
     */
    private Button mBtnRegisteredRegistered;
    private ImageView mRegisteredInternet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);

        initView();

    }

    @Override
    public void setRequestedOrientation(int requestedOrientation) {
        return;
    }

    public static boolean isTrueAccount(String account, String password) {
        if (account.equals(password)) {
            return true;
        }
        return false;
    }

    public static boolean isCellphone(String str) {
        Pattern pattern = Pattern.compile("^((13[0-9])|(14[5,7,9])|(15[^4])|(18[0-9])|(17[0,1,3,5,6,7,8]))\\d{8}$");
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }


    private void initView() {
//        mRegisteredInternet = (TextView) findViewById(R.id.registered_internet);
//        mRegisteredInternet.setOnClickListener(this);
        mRegisteredAccount = (EditText) findViewById(R.id.registered_account);
        mRegisteredPassword = (EditText) findViewById(R.id.registered_password);
        mRegisteredPasswordAgain = (EditText) findViewById(R.id.registered_password_again);
        mRegisteredPhone = (EditText) findViewById(R.id.registered_phone);
//        mBtnRegisteredCancel = (Button) findViewById(R.id.btn_registered_cancel);
//        mBtnRegisteredCancel.setOnClickListener(this);
        mBtnRegisteredRegistered = (Button) findViewById(R.id.btn_registered_registered);
        mBtnRegisteredRegistered.setOnClickListener(this);
        mRegisteredInternet = (ImageView) findViewById(R.id.registered_internet);
        mRegisteredInternet.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.registered_internet:
                Intent intent = new Intent(RegisteredActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.btn_registered_registered:
                if (!isTrueAccount(mRegisteredPassword.getText().toString(), mRegisteredPasswordAgain.getText().toString())) {
                    Toast.makeText(RegisteredActivity.this, "两次密码不一致", Toast.LENGTH_SHORT).show();
                    break;
                }
                if (!isCellphone(mRegisteredPhone.getText().toString())) {
                    Toast.makeText(RegisteredActivity.this, "手机号码格式错误", Toast.LENGTH_SHORT).show();
                    break;
                }
                Toast.makeText(RegisteredActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
//            case R.id.btn_registered_cancel:
//                Intent intent1 = new Intent(RegisteredActivity.this, LoginActivity.class);
//                startActivity(intent1);
//                finish();
//                break;
        }
    }
}
