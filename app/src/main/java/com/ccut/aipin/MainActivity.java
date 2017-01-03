package com.ccut.aipin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ccut.aipin.common.AppManager;
import com.ccut.aipin.fragment.LocationFragment;
import com.ccut.aipin.fragment.MeFragment;
import com.ccut.aipin.fragment.MessageFragment;
import com.ccut.aipin.IMFunction.LoginActivity;
import com.hyphenate.chat.EMClient;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends FragmentActivity {


    @Bind(R.id.content)
    FrameLayout mContent;
    @Bind(R.id.iv_message)
    ImageView mIvMessage;
    @Bind(R.id.tv_message)
    TextView mTvMessage;
    @Bind(R.id.ll_message)
    LinearLayout mLlMessage;
    @Bind(R.id.iv_location)
    ImageView mIvLocation;
    @Bind(R.id.tv_location)
    TextView mTvLocation;
    @Bind(R.id.ll_location)
    LinearLayout mLlLocation;
    @Bind(R.id.iv_me)
    ImageView mIvMe;
    @Bind(R.id.tv_me)
    TextView mTvMe;
    @Bind(R.id.ll_me)
    LinearLayout mLlMe;
    private LocationFragment mLocationFragment;
    private MessageFragment mMessageFragment;
    private MeFragment mMeFragment;
    private FragmentTransaction mFragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        AppManager.getInstance().addActivity(this);
        initdata();
        Log.e("wang", "main*********************************************");
    }

    private void initdata() {
        setSelect(1);
    }

    /*
    切换fragment
     */
    @OnClick({R.id.ll_message, R.id.ll_location, R.id.ll_me})
    public void changeTab(View view) {
        switch (view.getId()) {
            case R.id.ll_message:
                setSelect(0);
                Log.e("wang", "********************************************message");
                break;
            case R.id.ll_location:
                setSelect(1);
                break;
            case R.id.ll_me:
                setSelect(2);
                break;
        }
    }

    /*
    选择某个fragment
     */
    private void setSelect(int i) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = supportFragmentManager.beginTransaction();
        hideFragment();
        resetTab();
        switch (i) {
            case 0:
//                message
                if (mMessageFragment == null) {
                    mMessageFragment = new MessageFragment();
                    mFragmentTransaction.add(R.id.content, mMessageFragment);
                }
                mFragmentTransaction.show(mMessageFragment);
                mIvMessage.setImageResource(R.mipmap.tubiao_message_checked);
                mTvMessage.setTextColor(getResources().getColor(R.color.a));
                break;
            case 1:
//                location
                if (mLocationFragment == null) {
                    mLocationFragment = new LocationFragment();
                    mFragmentTransaction.add(R.id.content, mLocationFragment);
                }
                mFragmentTransaction.show(mLocationFragment);
                mIvLocation.setImageResource(R.mipmap.tubiao_location_checked);
                mTvLocation.setTextColor(getResources().getColor(R.color.a));
                break;
            case 2:
//                me
                if (mMeFragment == null) {
                    mMeFragment = new MeFragment();
                    mFragmentTransaction.add(R.id.content, mMeFragment);
                }
                mFragmentTransaction.show(mMeFragment);
                mIvMe.setImageResource(R.mipmap.tubiao_me_checked);
                mTvMe.setTextColor(getResources().getColor(R.color.a));
                break;
        }
        mFragmentTransaction.commit();

    }

    private void resetTab() {
        mIvMessage.setImageResource(R.mipmap.tubiao_message_unchecked);
        mIvLocation.setImageResource(R.mipmap.tubiao_location_unchecked);
        mIvMe.setImageResource(R.mipmap.tubiao_me_unchecked);

       /* mTvMessage.setTextColor(getResources().getColor(R.color.a));
        mTvLocation.setTextColor(getResources().getColor(R.color.a));
        mTvMe.setTextColor(getResources().getColor(R.color.a));*/
    }

    private void hideFragment() {
        if (mMessageFragment != null) {
            mFragmentTransaction.hide(mMessageFragment);
        }
        if (mLocationFragment != null) {
            mFragmentTransaction.hide(mLocationFragment);
        }
        if (mMeFragment != null) {
            mFragmentTransaction.hide(mMeFragment);
        }
    }


}
