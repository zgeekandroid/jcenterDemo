package com.example.jcenterlib;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.lang.ref.SoftReference;

/**
 * date        :  2016-07-15  11:36
 * author      :  Mickaecle gizthon
 * description :
 */
public abstract class AbsFragmentHandler<T extends Fragment> extends Handler {

    private final SoftReference<T> mFragmentRef;

    public AbsFragmentHandler(T mFragment) {
        mFragmentRef = new SoftReference<T>(mFragment);
    }

    /**
     * 不许覆写，若对需对消息处理可对{@link #handleMessage(Fragment, Message, Bundle)}进行覆写
     *
     * @param msg Message消息对象
     */
    @Override
    public final void handleMessage(Message msg) {
        T mFragment = mFragmentRef.get();
        if (mFragment == null) {
            return;
        }
        handleMessage(mFragment, msg, msg.getData());
    }

    /**
     * 主要的消息处理逻辑
     *
     * @param mFragment 类型参数T所指定的Fragment对象
     * @param msg       Message消息对象
     * @param mBundle   可以为null
     */
    protected abstract void handleMessage(T mFragment, Message msg, Bundle mBundle);

}