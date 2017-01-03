package com.ccut.aipin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.ui.EaseContactListFragment;
import com.hyphenate.easeui.widget.EaseContactList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/9.
 */
public class FriendListActivity extends AppCompatActivity {

    private EaseContactListFragment contactListFragment;
    private EaseContactList contactListLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friendlist);
        ButterKnife.bind(this);

//        initList();
    }

  /*  private void initList() {
        contactListFragment = new EaseContactListFragment();
//需要设置联系人列表才能启动fragment
        contactListFragment.setContactsMap(getContacts());
//设置item点击事件
        contactListFragment.setContactListItemClickListener(new EaseContactListFragment.EaseContactListItemClickListener() {

            @Override
            public void onListItemClicked(EaseUser user) {
                startActivity(new Intent(FriendListActivity.this, ChatActivity.class).putExtra(EaseConstant.EXTRA_USER_ID, user.getUsername()));
            }
        });


        contactListLayout = (EaseContactList) getView().findViewById(R.id.contact_list);
//初始化时需要传入联系人list
        contactListLayout.init(contactList);
//刷新列表
        contactListLayout.refresh();
    }*/
}
