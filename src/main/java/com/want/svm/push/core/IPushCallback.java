package com.want.svm.push.core;

import android.content.Context;
import android.os.Bundle;

/**
 * <b>Project:</b> apps<br>
 * <b>Create Date:</b> 15/10/30<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b>
 * Push message callback.
 * <br>
 */
public interface IPushCallback {
    /**
     * Called after device registration.
     *
     * @param context context
     * @param id      registration id.
     */
    void onRegistration(Context context, String id);

    /**
     * Called after device unregistration.
     *
     * @param context context
     * @param id      registration id
     */
    void onUnRegistration(Context context, String id);

    /**
     * Called after custom message received.
     *
     * @param context context
     * @param data    json formated data
     */
    void onMessage(Context context, String data);

    /**
     * Called after rich message received.
     *
     * @param context context
     * @param data    json formated data
     */
    void onRichMessage(Context context, String data);

    /**
     * Called after notification received.
     *
     * @param context context
     * @param id      notification id
     */
    void onNotificationReceived(Context context, String id);

    /**
     * Called after notification opened
     *
     * @param context context
     * @param data    {@link Bundle}
     */
    void onNotificationOpened(Context context, Bundle data);

    /**
     * Called after data not be handled.
     *
     * @param context context
     * @param data    {@link Bundle}
     */
    void onUnHandledMessage(Context context, Bundle data);
}
