package com.want.svm.push;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.want.base.sdk.framework.app.MBroadcastReceiver;
import com.want.core.log.lg;
import com.want.svm.push.core.IPushAgent;
import com.want.svm.push.core.LogHelper;

import cn.jpush.android.api.JPushInterface;

import static com.want.svm.push.core.IPushAgent.PUSH_DATA;
import static com.want.svm.push.core.PushSender.sendPush;

/**
 * <b>Project:</b> apps<br>
 * <b>Create Date:</b> 15/10/30<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public class JpushReceiver extends MBroadcastReceiver {

    private static String formatMessage(String message) {
        return String.format("[jpush] %s", message);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        final String action = intent.getAction();
        if (LogHelper.DEBUG) {
            lg.v(LogHelper.TAG, formatMessage("receive message, action: ") + action);
//            lg.v(LogHelper.TAG, formatMessage("extras: " + lg.getString(extras)));
        }

        if (TextUtils.isEmpty(action)) {
            if (LogHelper.DEBUG) {
                lg.d(LogHelper.TAG, formatMessage("action is empty, so break."));
            }
            return;
        }

        if (null == extras) {
            extras = new Bundle();
        }
        // tag the message frome jpush
        extras.putString(Extras.TAG, IPushAgent.AGENT_JPUSH);

        // 设备已注册到服务器
        if (JPushInterface.ACTION_REGISTRATION_ID.equals(action)) {
            String regId = extras.getString(JPushInterface.EXTRA_REGISTRATION_ID);
            if (LogHelper.DEBUG) {
                lg.v(LogHelper.TAG, formatMessage("receive registration id: " + regId));
            }

            innerHandleRegistration(context, regId);

            // 接收到自定义消息
        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(action)) {
            final String data = extras.getString(JPushInterface.EXTRA_MESSAGE);
            if (LogHelper.DEBUG) {
                lg.v(LogHelper.TAG,
                     formatMessage("receive custom message, data: " + data));
            }

            innerHandleCustomMessage(context, data);

            // 收到通知
        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(action)) {
            int notifactionId = extras.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
            if (LogHelper.DEBUG) {
                lg.v(LogHelper.TAG, formatMessage("receive notifation, id: " + notifactionId));
            }

            innerHandleNotificationReceived(context, String.valueOf(notifactionId));

            // 通知被打开
        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(action)) {
            if (LogHelper.DEBUG) {
                lg.v(LogHelper.TAG, formatMessage("notifation opened."));
            }

            innerHandleNotificationOpened(context, extras);

            // 收到富媒体消息
        } else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(action)) {
            final String data = extras.getString(JPushInterface.EXTRA_EXTRA);
            if (LogHelper.DEBUG) {
                lg.v(LogHelper.TAG, formatMessage("receive RICH MESSAGE, data: " + data));
            }
            // TODO: 15/10/30 handle the rich message
            // 网络发生了变化
        } else if (JPushInterface.ACTION_CONNECTION_CHANGE.equals(action)) {
            boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
            if (LogHelper.DEBUG) {
                lg.v(LogHelper.TAG, formatMessage("receive connection changed, changed: " + connected));
            }
        } else {
            // 未处理的消息
            lg.d(LogHelper.TAG, formatMessage("receive unhandled message"));
        }
    }

    /**
     * Handle the device registraed
     *
     * @param context context
     * @param id      registration id
     */
    private void innerHandleRegistration(Context context, String id) {
        Bundle bundle = new Bundle();
        bundle.putString(PUSH_DATA, id);
        sendPush(context, IPushAgent.ACTION_REGISTRATION, bundle);
    }

    /**
     * handle the notificaiton received
     *
     * @param context context
     * @param id      id
     */
    private void innerHandleNotificationReceived(Context context, String id) {
        Bundle bundle = new Bundle();
        bundle.putString(PUSH_DATA, id);
        sendPush(context, IPushAgent.ACTION_NOTIFICATION_RECEIVED, bundle);
    }

    /**
     * handle the notification opened
     *
     * @param context context
     * @param bundle  bundle
     */
    private void innerHandleNotificationOpened(Context context, Bundle bundle) {
        // TODO: 15/10/30 handle user open the notifation
        sendPush(context, IPushAgent.ACTION_NOTIFICATION_OPENED, bundle);
    }

    /**
     * Handle the custom message
     *
     * @param context {@link Context}
     * @param data    json data
     */
    private void innerHandleCustomMessage(Context context, String data) {
        Bundle bundle = new Bundle();
        bundle.putString(PUSH_DATA, data);
        sendPush(context, IPushAgent.ACTION_MESSAGE, bundle);
    }


}
