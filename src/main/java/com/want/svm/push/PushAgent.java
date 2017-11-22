package com.want.svm.push;

import android.content.Context;

import com.want.svm.push.callback.DefaultPushCallback;
import com.want.svm.push.core.IPushAgent;
import com.want.svm.push.core.IPushCallback;

import java.util.ArrayList;
import java.util.Set;

/**
 * <b>Project:</b> apps<br>
 * <b>Create Date:</b> 15/10/30<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b>
 * Push client.
 * <br>
 */
public class PushAgent implements IPushAgent {
    private ArrayList<IPushAgent> mAgentList = new ArrayList<>();
    private final Object mAddLock = new Object();

    private IPushCallback mDefaultPushCallback = new DefaultPushCallback();

    private IPushAgent mDefaultPushAgent;

    private static class Singleton {
        static PushAgent INSTANCE = new PushAgent();
    }

    private PushAgent() {
        // hide
    }

    public static PushAgent getInstance() {
        return Singleton.INSTANCE;
    }

    @Override
    public IPushCallback getPushCallback() {
        return this.mDefaultPushCallback;
    }

    /**
     * Set tags to agent.
     *
     * @param context  context.
     * @param tags     {@link Set} of tags.
     * @param listener {@link TagAliasListener}
     */
    @Override
    public void setTags(Context context, Set<String> tags, TagAliasListener listener) {
        mDefaultPushAgent.setTags(context, tags, listener);
    }

    /**
     * Set alias to agent.
     *
     * @param context  context.
     * @param alias    alias.
     * @param listener {@link TagAliasListener}.
     */
    @Override
    public void setAlias(Context context, String alias, TagAliasListener listener) {
        mDefaultPushAgent.setAlias(context, alias, listener);
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
                                TagAliasListener listener) {
        mDefaultPushAgent.setTagsAndAlias(context, alias, tags, listener);
    }

    /**
     * Resume agent.
     *
     * @param context context.
     */
    @Override
    public void resume(Context context) {
        mDefaultPushAgent.resume(context);
    }

    /**
     * Stop agent.
     *
     * @param context context.
     */
    @Override
    public void stop(Context context) {
        mDefaultPushAgent.stop(context);
    }

    /**
     * Wheather this agent is stoped.
     *
     * @param context context.
     *
     * @return true, is stoped.
     */
    @Override
    public boolean isStoped(Context context) {
        return mDefaultPushAgent.isStoped(context);
    }

    /**
     * Get this agent registration id.
     *
     * @param context context.
     */
    @Override
    public String getRegistrationID(Context context) {
        return mDefaultPushAgent.getRegistrationID(context);
    }

    /**
     * Set the {@link IPushCallback}
     *
     * @param callback callback
     */
    @Override
    public void setPushCallback(IPushCallback callback) {
        this.mDefaultPushCallback = callback;
    }

    /**
     * Get push callback with target pushagent.
     *
     * @param iPushAgent target pushagent.
     *
     * @return the target pushagent or default pushcallback.
     */
    public IPushCallback getPushCallback(IPushAgent iPushAgent) {
        final IPushCallback callback = iPushAgent.getPushCallback();
        if (null != callback) {
            return callback;
        }
        return mDefaultPushCallback;
    }


    /**
     * Add a push agent. You must call this method before call {@link #init(Context)}
     *
     * @param agent {@link IPushAgent}
     */
    public void addAgent(IPushAgent agent) {
        for (IPushAgent pushInterface : mAgentList) {
            // already add agent, so break.
            if (pushInterface.getClass().isInstance(agent)) {
                return;
            }
        }

        synchronized (mAddLock) {
            mAgentList.add(agent);
            if (null == mDefaultPushAgent) {
                mDefaultPushAgent = agent;
            }
        }
    }

    /**
     * Set the default push agent.
     *
     * @param agent {@link IPushAgent}.
     */
    public void setDefaultAgent(IPushAgent agent) {
        if (!mAgentList.contains(agent)) {
            throw new IllegalArgumentException("agent must one of the instances in this PushAgent.");
        }

        mDefaultPushAgent = agent;
    }

    /**
     * Set debug mode
     *
     * @param debug true, in debug.
     */
    @Override
    public void setDebugMode(boolean debug) {
        innersetDebugMode(debug);
    }

    private void innersetDebugMode(boolean debug) {
        for (IPushAgent iPushAgent : mAgentList) {
            iPushAgent.setDebugMode(debug);
        }
    }

    /**
     * Init the push implements.
     *
     * @param context {@link Context}.
     */
    @Override
    public void init(Context context) {
        innerInit(context);
    }

    private void innerInit(Context context) {
        for (IPushAgent iPushAgent : mAgentList) {
            iPushAgent.init(context);
        }
    }
}
