package com.ccut.aipin.IMFunction;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ccut.aipin.MainActivity;
import com.ccut.aipin.R;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/5.
 */
public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.et_username)
    EditText mTvUsername;
    @Bind(R.id.et_password)
    EditText mTvPassword;
    @Bind(R.id.bt_login)
    Button mBtLogin;
    @Bind(R.id.bt_register)
    Button mBtRegister;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_message);
        ButterKnife.bind(this);
        Log.e("wang", "oncreate");
        mBtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                singin();
                Log.e("wang", "singin   onclick");
            }
        });

        mBtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                singup();
                Log.e("wang", "singup   onclick");
                Toast.makeText(LoginActivity.this, "singup", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //注册
    private void singup() {
        Log.e("wang", "singup~~~~~~~~~~~~~~~~~~~~~~~");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    EMClient.getInstance().createAccount(mTvUsername.getText().toString().trim(), mTvPassword.getText().toString().trim());
                    Log.e("wang", "注册成功");
                } catch (HyphenateException e) {
                    e.printStackTrace();
                    Log.e("wang", "注册失败" + e.getMessage());
                }
            }
        }).start();
    }

    //登录
    private void singin() {
        EMClient.getInstance().login(mTvUsername.getText().toString().trim(), mTvPassword.getText().toString().trim(), new EMCallBack() {
            @Override
            public void onSuccess() {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                Log.e("wang", "登陆成功");
            }

            @Override
            public void onError(int i, String s) {
                Log.e("wang", "登录失败" + i + s);
            }

            @Override
            public void onProgress(int i, String s) {

            }
        });
    }
}
