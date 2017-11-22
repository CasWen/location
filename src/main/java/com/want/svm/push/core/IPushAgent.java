package com.want.svm.push.core;

import android.content.Context;

import java.util.Set;

/**
 * <b>Project:</b> apps<br>
 * <b>Create Date:</b> 15/10/30<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b>
 * Push agent interface.
 * <br>
 */
public interface IPushAgent {

    /**
     * Callback with set tags or alias
     */
    interface TagAliasListener {
        /**
         * Called after tags or alias setted.
         *
         * @param status status code.
         * @param alias  alias
         * @param tags   tags
         */
        void onTagAliasCallback(int status, String alias, Set<String> tags);
    }

    String PUSH_SENDER = "com.want.push.receiver.PushReceiver";

    /** device registrted */
    String ACTION_REGISTRATION = "com.want.push.ACTION_REGISTRATION";
    /** custom message received */
    String ACTION_MESSAGE = "com.want.push.ACTION_MESSAGE";
    /** notification received */
    String ACTION_NOTIFICATION_RECEIVED = "com.want.push.ACTION_NOTIFICATION_RECEIVED";
    /** notification opened */
    String ACTION_NOTIFICATION_OPENED = "com.want.push.ACTION_NOTIFICATION_OPENED";
    /** rich message received */
    String ACTION_RICH_MESSAGE = "com.want.push.ACTION_RICH_MESSAGE";

    /** PUSH DATA */
    String PUSH_DATA = "push_data";


    String AGENT_JPUSH = "jpush";
    String AGENT_UMENG = "umeng";

    /**
     * Config debug mode.
     *
     * @param debug boolean value.
     */
    void setDebugMode(boolean debug);

    /**
     * init this push agent.
     *
     * @param context {@link Context}.
     */
    void init(Context context);

    /**
     * Set the {@link IPushCallback}
     *
     * @param callback callback
     */
    void setPushCallback(IPushCallback callback);

    /**
     * Get the {@link IPushCallback} of this agent.
     *
     * @return
     */
    IPushCallback getPushCallback();


    /**
     * Set tags to agent.
     *
     * @param context  context.
     * @param tags     {@link Set} of tags.
     * @param listener {@link TagAliasListener}
     */
    void setTags(Context context, Set<String> tags, final TagAliasListener listener);

    /**
     * Set alias to agent.
     *
     * @param context  context.
     * @param alias    alias.
     * @param listener {@link TagAliasListener}.
     */
    void setAlias(Context context, String alias, final TagAliasListener listener);

    /**
     * Set tags and alias to agent.
     *
     * @param context  context.
     * @param alias    alias.
     * @param tags     {@link Set} of tags.
     * @param listener {@link TagAliasListener}.
     */
    void setTagsAndAlias(Context context, String alias, Set<String> tags, final TagAliasListener listener);

    /**
     * Resume agent.
     *
     * @param context context.
     */
    void resume(Context context);

    /**
     * Stop agent.
     *
     * @param context context.
     */
    void stop(Context context);

    /**
     * Wheather this agent is stoped.
     *
     * @param context context.
     *
     * @return true, is stoped.
     */
    boolean isStoped(Context context);

    /**
     * Get this agent registration id.
     *
     * @param context context.
     */
    String getRegistrationID(Context context);
}
