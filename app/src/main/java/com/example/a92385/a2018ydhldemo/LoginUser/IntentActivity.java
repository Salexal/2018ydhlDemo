package com.example.a92385.a2018ydhldemo.LoginUser;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a92385.a2018ydhldemo.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IntentActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 请输入服务器地址
     */
    private EditText mServerIp;
    /**
     * 请输入密码
     */
    private EditText mServerPort;
    /**
     * 保存
     */
    private Button mBtnLogin;
    /**
     * 取消
     */
    private Button mBtnRegistered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);

        initView();

    }

    private void initView() {
        mServerIp = (EditText) findViewById(R.id.server_ip);
        mServerPort = (EditText) findViewById(R.id.server_port);
        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mBtnLogin.setOnClickListener(this);
        mBtnRegistered = (Button) findViewById(R.id.btn_registered);
        mBtnRegistered.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_login:
                if (!isTrueIP(mServerIp.getText().toString())) {
                    Toast.makeText(IntentActivity.this, "服务器地址不合理!", Toast.LENGTH_SHORT).show();
                    break;
                }
                if (!isTruePort()) {
                    Toast.makeText(IntentActivity.this, "端口号不合理!", Toast.LENGTH_SHORT).show();
                    break;
                }
                Toast.makeText(IntentActivity.this, "网络设置已保存!", Toast.LENGTH_SHORT).show();
            case R.id.btn_registered:
                Intent intent = new Intent(IntentActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    public boolean isTruePort() {
        Integer mPortValue = Integer.valueOf(mServerPort.getText().toString());
        if (mPortValue >= 0 && mPortValue <= 65535)
            return true;
        return false;
    }

    public boolean isTrueIP(String addr) {
        if (addr.length() < 7 || addr.length() > 15 || "".equals(addr)) {
            return false;
        }
        /**
         * 判断IP格式和范围
         */
        String rexp = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";

        Pattern pat = Pattern.compile(rexp);

        Matcher mat = pat.matcher(addr);

        boolean ipAddress = mat.find();

        return ipAddress;
    }
    @Override
    public void setRequestedOrientation(int requestedOrientation){
        return;
    }
}




