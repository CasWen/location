package com.want.svm.push.impl.jpush;

import android.content.Context;

import com.want.svm.push.core.IPushAgent;
import com.want.svm.push.core.IPushCallback;

import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

/**
 * <b>Project:</b> apps<br>
 * <b>Create Date:</b> 15/10/30<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b>
 * Jpush implements.
 * <br>
 */
public class JpushAgentImpl implements IPushAgent {


    /**
     * Config debug mode.
     *
     * @param debug boolean value.
     */
    @Override
    public void setDebugMode(boolean debug) {
        JPushInterface.setDebugMode(debug);
    }

    /**
     * init this push agent.
     *
     * @param context {@link Context}.
     */
    @Override
    public void init(Context context) {
        JPushInterface.init(context);
    }

    /**
     * Set the {@link IPushCallback}
     *
     * @param callback callback
     */
    @Override
    public void setPushCallback(IPushCallback callback) {

    }

    /**
     * Get the {@link IPushCallback} of this agent.
     *
     * @return
     */
    @Override
    public IPushCallback getPushCallback() {
        return null;
    }

    /**
     * Set tags to agent.
     *
     * @param context  context.
     * @param tags     {@link Set} of tags.
     * @param listener {@link TagAliasListener}
     */
    @Override
    public void setTags(Context context, Set<String> tags, final TagAliasListener listener) {
        JPushInterface.setTags(context, tags, new TagAliasCallback() {
            @Override
            public void gotResult(int i, String s, Set<String> set) {
                if (null != listener) {
                    listener.onTagAliasCallback(i, s, set);
                }
            }
        });
    }

    /**
     * Set alias to agent.
     *
     * @param context  context.
     * @param alias    alias.
     * @param listener {@link TagAliasListener}.
     */
    @Override
    public void setAlias(Context context, String alias, final TagAliasListener listener) {
        JPushInterface.setAlias(context, alias, new TagAliasCallback() {
            @Override
            public void gotResult(int i, String s, Set<String> set) {
                if (null != listener) {
                    listener.onTagAliasCallback(i, s, set);
                }
            }
        });
    }

    /**
     * Set tags and alias to agent.
     *
     * @param context  context.
     * @param alias    alias.
     * @param tags     {@link Set} of tags.
     * @param listener {@link TagAliasListener}.
     */
    @Override
    public void setTagsAndAlias(Context context,
                                String alias,
                                Set<String> tags,
                                final TagAliasListener listener) {
        JPushInterface.setAliasAndTags(context, alias, tags, new TagAliasCallback() {
            @Override
            public void gotResult(int i, String s, Set<String> set) {
                if (null != listener) {
                    listener.onTagAliasCallback(i, s, set);
                }
            }
        });
    }

    /**
     * Resume agent.
     *
     * @param context context.
     */
    @Override
    public void resume(Context context) {
        JPushInterface.resumePush(context);
    }

    /**
     * Stop agent.
     *
     * @param context context.
     */
    @Override
    public void stop(Context context) {
//        JPushInterface.stopPush(context);
    }

    /**
     * Wheather this agent is stoped.
     *
     * @param context context.
     */
    @Override
    public boolean isStoped(Context context) {
        return JPushInterface.isPushStopped(context);
    }

    /**
     * Get this agent registration id.
     *
     * @param context context.
     */
    @Override
    public String getRegistrationID(Context context) {
        return JPushInterface.getRegistrationID(context);
    }
}
