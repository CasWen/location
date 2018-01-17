/*
 * Copyright (c) 2014. http://www.mmclick.com Inc. All rights reserved.
 *
 * 注意：本内容仅限于广州魅媒网络科技有限公司内部传阅，禁止外泄以及用于其他的商业目的。
 */

package com.want.location;

import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;


/**
 * <b>Project</b> <i>almanac</i><br>
 * <b>Create Date</b> <i>2014/12/1</i><br>
 * <b>Author</b> <i>Gordon</i><br>
 * <b>Email</b> <i>gaoyulong@mmclick.com</i><br>
 * <b>Update Date</b> <i>2014/12/1 15:15</i><br>
 * <b>Last Update</b> <i>Gordon</i><br>
 * <b>Description</b> <i>
 * <p/>
 * </i>
 */
public abstract class AbsLocationClient implements ILocationClient {

    protected static final String TAG = "[LBS] ";
    protected static final boolean DEBUG = true;
    private static final int[] sLock = new int[0];

    private List<LocationListener> mListeners = new ArrayList<LocationListener>();

    private Context mContext;

    public AbsLocationClient(Context context) {
        this.mContext = context;
    }

    protected Context getContext(){
        return this.mContext;
    }

    /**
     * 处理位置请求结果
     *
     * @param location
     * @param result
     */
    protected final void handleLocation(ILocation location, RESULT result) {
        sendLocationResult(mContext, location, result);
        List<LocationListener> tmpLisener = new ArrayList<>(
                getLocationListeners());
        for (LocationListener listener : tmpLisener) {
            listener.onReceiveLocation(location, result);
        }
    }

    /**
     * 广播位置更新
     *
     * @param location
     */
    public static void sendLocationResult(Context context, ILocation location, RESULT result) {
        Intent intent = new Intent(ACTION_LOCATION_UPDATE);
        intent.setPackage(context.getPackageName());
        intent.putExtra(EXTRA_LOCATION_RSULT, result);
        if (null != location) {
            intent.putExtra(EXTRA_LOCATION, location);
        }
        context.sendBroadcast(intent);
    }

    /**
     * 增加一个位置监听器
     *
     * @param listener
     */
    @Override
    public void addLocationListener(LocationListener listener) {
        synchronized (sLock) {
            if (null != listener && !mListeners.contains(listener)) {
                mListeners.add(listener);
            }
        }
    }

    /**
     * 移除指定的位置监听器
     *
     * @param listener
     */
    @Override
    public void removeLocationListener(LocationListener listener) {
        synchronized (sLock) {
            if (null != listener && mListeners.contains(listener)) {
                mListeners.remove(listener);
            }
        }
    }

    protected List<LocationListener> getLocationListeners() {
        return mListeners;
    }
}
