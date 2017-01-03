package com.ccut.aipin.common;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by Administrator on 2016/10/15.
 * 统一app中的activity栈管理
 * 添加、删除当前、删除指定、删除所有、求栈的大小
 */
public class AppManager {
    private Stack<Activity> mActivityStack = new Stack<>();
    //单例
    public static AppManager sAppManager = null;
    private AppManager(){

    }
    public static AppManager getInstance() {
        if (sAppManager == null) {
            sAppManager = new AppManager();
        }
        return sAppManager;
    }


    public void addActivity(Activity activity){
        mActivityStack.add(activity);
    }

    public void removeActivity(Activity activity){
        for (int i = mActivityStack.size() - 1; i >= 0; i--) {

            Activity activity1 = mActivityStack.get(1);
            if(activity1.getClass().equals(activity.getClass())){
                activity1.finish();
                mActivityStack.remove(activity1);
                break;

            }
        }
    }

    public void removeCurrent(){
        Activity lastElement = mActivityStack.lastElement();
        lastElement.finish();
        mActivityStack.remove(lastElement);
    }
    public void removeAll(){
        for (int i = mActivityStack.size() - 1; i >= 0; i--) {
            Activity activity1 = mActivityStack.get(i);
            activity1.finish();
            mActivityStack.remove(activity1);
        }
    }
    public int getSize(){
        return mActivityStack.size();
    }
}
