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
 * <b>Update Date</b> <i>2014/12/1 15:02</i><br>
 * <b>Last Update</b> <i>Gordon</i><br>
 * <b>Description</b> <i>
 * <p/>
 * </i>
 */
public interface LocationListener {

    void onReceiveLocation(ILocation location, ILocationClient.RESULT result);
}
