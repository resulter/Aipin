package com.ccut.aipin.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ccut.aipin.ChatActivity;
import com.ccut.aipin.R;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.ui.EaseContactListFragment;
import com.hyphenate.easeui.widget.EaseContactList;

/**
 * Created by Administrator on 2016/11/6.
 */
public class MessageFriendFragment extends Fragment {

    private EaseContactList mContactListLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(container.getContext(), R.layout.fragment_message_friend, null);
        initFriendList();
        return view;
    }

    private void initFriendList() {

//        EaseContactListFragment contactListFragment = new EaseContactListFragment();
////需要设置联系人列表才能启动fragment
//        contactListFragment.setContactsMap(getContacts());
////设置item点击事件
//        contactListFragment.setContactListItemClickListener(new EaseContactListFragment.EaseContactListItemClickListener() {
//
//            @Override
//            public void onListItemClicked(EaseUser user) {
//                startActivity(new Intent(MessageFriendFragment.this, ChatActivity.class).putExtra(EaseConstant.EXTRA_USER_ID, user.getUsername()));
//            }
//        });
//
//        mContactListLayout = (EaseContactList) getView().findViewById(R.id.contact_list);
////初始化时需要传入联系人list
//        mContactListLayout.init(contactList);
////刷新列表
//        mContactListLayout.refresh();
    }
}
