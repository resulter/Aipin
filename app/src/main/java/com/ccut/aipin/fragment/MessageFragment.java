package com.ccut.aipin.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ccut.aipin.R;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/4.
 */
public class MessageFragment extends Fragment {
    private static final int LETGOMAIN = 1;
    private static final int LETGOLOGIN = 2;
    @Bind(R.id.et_username)
    EditText mEtUsername;
    @Bind(R.id.et_password)
    EditText mEtPassword;
    @Bind(R.id.bt_login)
    Button mBtLogin;
    @Bind(R.id.bt_register)
    Button mBtRegister;
    @Bind(R.id.ll_loginView)
    LinearLayout mLlLoginView;
    @Bind(R.id.message_view_content)
    LinearLayout mMessageViewContent;
    @Bind(R.id.tittle_left)
    ImageView mTittleLeft;
    @Bind(R.id.tittle_right)
    ImageView mTittleRight;
    @Bind(R.id.tab_indicator)
    TabPageIndicator mTabIndicator;
    @Bind(R.id.pager)
    ViewPager mPager;
    @Bind(R.id.message_chat_view)
    LinearLayout mMessageChatView;
    public static boolean FLAG = false;

    private List<Fragment> mFragmentList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(container.getContext(), R.layout.fragment_message, null);
        ButterKnife.bind(this, view);
        loginCheck();
        initTittle();
        return view;

    }

    private void initTittle() {
        Log.e("wang", "connecetion" + EMClient.getInstance().isConnected() + "");

        mTittleLeft.setVisibility(View.INVISIBLE);
        mTittleRight.setVisibility(View.VISIBLE);
        mTittleRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EMClient.getInstance().logout(true);
                Log.e("wang", "登出成功");
                mHandler.obtainMessage(LETGOLOGIN).sendToTarget();
            }
        });
    }

    /*
    此处需要检测登陆情况
     */
    private void loginCheck() {

        if (EMClient.getInstance().isLoggedInBefore()) {
            goMessageMainView();

            // enter main activity
        } else {
            goLoginView();
            // enter login activity
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                checkIsLogining();
            }


        }).start();
        bindLogin();
    }

    private void goLoginView() {
        mMessageChatView.setVisibility(View.GONE);
        mMessageViewContent.setVisibility(View.VISIBLE);

    }

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {//此方法在ui线程运行
            switch (msg.what) {
                case LETGOMAIN:
                    mMessageChatView.setVisibility(View.VISIBLE);
                    mMessageViewContent.setVisibility(View.GONE);
                    break;
                case LETGOLOGIN:
                    mMessageChatView.setVisibility(View.GONE);
                    mMessageViewContent.setVisibility(View.VISIBLE);
            }
        }
    };

    private void goMessageMainView() {
        mMessageChatView.setVisibility(View.VISIBLE);
        mMessageViewContent.setVisibility(View.GONE);
        initFragment();
        mPager.setAdapter(new MyAdapter(getFragmentManager()));
        mTabIndicator.setViewPager(mPager);
        mTabIndicator.setVisibility(View.VISIBLE);

    }

    private void initFragment() {
        MessageChatFragment messageChatFragment = new MessageChatFragment();
        MessageFriendFragment messageFriendFragment = new MessageFriendFragment();
        MessageGroupFragment messageGroupFragment = new MessageGroupFragment();
        mFragmentList.add(messageChatFragment);
        mFragmentList.add(messageFriendFragment);
        mFragmentList.add(messageGroupFragment);
    }

    private void bindLogin() {
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
                    EMClient.getInstance().createAccount(mEtUsername.getText().toString().trim(), mEtPassword.getText().toString().trim());

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
        EMClient.getInstance().login(mEtUsername.getText().toString().trim(), mEtPassword.getText().toString().trim(), new EMCallBack() {
            @Override
            public void onSuccess() {
                FLAG = true;
                Log.e("wang", "登陆成功");
                /*
                登陆成功后
                **************************************************************
                 */
                mHandler.obtainMessage(LETGOMAIN).sendToTarget();

            }

            @Override
            public void onError(int i, String s) {
                Log.e("wang", "登录失败" + i + s);
                FLAG = false;
            }

            @Override
            public void onProgress(int i, String s) {

            }
        });

    }

    private void checkIsLogining() {
        if (FLAG) {
            mMessageChatView.setVisibility(View.VISIBLE);
            mMessageViewContent.setVisibility(View.GONE);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList == null ? 0 : mFragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return getContext().getResources().getStringArray(R.array.message_tittle)[position];
        }
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
    }
}
