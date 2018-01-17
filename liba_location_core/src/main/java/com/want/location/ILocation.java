/*
 * Copyright (c) 2014. http://www.mmclick.com Inc. All rights reserved.
 *
 * 注意：本内容仅限于广州魅媒网络科技有限公司内部传阅，禁止外泄以及用于其他的商业目的。
 */

package com.want.location;

import java.io.Serializable;

/**
 * <b>Project</b> <i>almanac</i><br>
 * <b>Create Date</b> <i>2014/12/1</i><br>
 * <b>Author</b> <i>Gordon</i><br>
 * <b>Email</b> <i>gaoyulong@mmclick.com</i><br>
 * <b>Update Date</b> <i>2014/12/1 14:55</i><br>
 * <b>Last Update</b> <i>Gordon</i><br>
 * <b>Description</b> <i>
 * <p/>位置接口
 * </i>
 */
public interface ILocation extends Serializable {

    /**
     * 获取城市代码
     *
     * @return
     */
    String getCityCode();

    /**
     * 获取国家代码
     *
     * @return
     */
    String getCountryCode();

    /**
     * 获取纬度坐标
     *
     * @return
     */
    double getLatitude();

    /**
     * 获取经度
     *
     * @return
     */
    double getLongitude();

    /**
     * 获取省份
     *
     * @return
     */
    String getProvince();

    /**
     * 获取城市
     *
     * @return
     */
    String getCity();

    /**
     * 获取街道
     *
     * @return
     */
    String getStreet();



    /**
     * 获取地址
     *
     * @return
     */
    String getAddress();
}
