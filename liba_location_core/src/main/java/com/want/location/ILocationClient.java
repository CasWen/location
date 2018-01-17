/*
 * Copyright (c) 2014. http://www.mmclick.com Inc. All rights reserved.
 *
 * 注意：本内容仅限于广州魅媒网络科技有限公司内部传阅，禁止外泄以及用于其他的商业目的。
 */

package com.want.location;

/**
 * <b>Project</b> <i>almanac</i><br>
 * <b>Create Date</b> <i>2014/12/1</i><br>
 * <b>Author</b> <i>Gordon</i><br>
 * <b>Email</b> <i>gaoyulong@mmclick.com</i><br>
 * <b>Update Date</b> <i>2014/12/1 14:51</i><br>
 * <b>Last Update</b> <i>Gordon</i><br>
 * <b>Description</b> <i>
 * <p/>位置客户端抽象
 * </i>
 */
public interface ILocationClient {
    /**
     * 位置变化后发出的Action
     */
    String ACTION_LOCATION_UPDATE = "oms.mmc.almanac.ACTION_LOCATION_UPDATE";
    /**
     * 位置请求的结果状态
     */
    String EXTRA_LOCATION_RSULT = "alc_location_rsult";
    /**
     * 位置变化后发出的广播所携带内容附加的key值
     */
    String EXTRA_LOCATION = "alc_location_extra";

    /**
     * 精度模式
     */
    enum MODE {
        /** 省电 */
        SAVING,
        /** 传感器 */
        SENSORS,
        /** 高精度 */
        ACCURACY
    }

    /**
     * 位置请求状态
     */
    enum RESULT {
        /**
         * 成功
         */
        SUCCESS(200),
        /**
         * 超时
         */
        TIMEOUT(503),
        /**
         * 失败
         */
        FAIL(0),
        /**
         * 无效
         */
        INVALID(-1);

        private int status;

        RESULT(int status) {
            this.status = status;
        }

        public int toValue() {
            return status;
        }

        public static RESULT valueOf(int status) {
            if (200 == status) {
                return SUCCESS;
            } else if (503 == status) {
                return TIMEOUT;
            } else if (0 == status) {
                return FAIL;
            } else {
                return INVALID;
            }
        }

        @Override
        public String toString() {
            if (200 == status) {
                return "SUCCESS";
            } else if (503 == status) {
                return "TIMEOUT";
            } else if (0 == status) {
                return "FAIL";
            } else {
                return "INVALID(" + status + ")";
            }
        }
    }


    /**
     * 配置LocationClient
     */
    void onConfigClient();

    /**
     * 异步请求定位
     */
    void requestLocationUpdate();

    /**
     * 同步请求定位, 返回最后一次定位结果
     *
     * @return
     */
    ILocation getLastKnownLocation();

    /**
     * 增加一个位置监听器，使用完毕后需要remove掉
     *
     * @param listener {@link LocationListener}
     */
    void addLocationListener(LocationListener listener);


    /**
     * 移除指定的位置监听器
     *
     * @param listener {@link LocationListener}
     */
    void removeLocationListener(LocationListener listener);

}
