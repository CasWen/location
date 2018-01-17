package com.want.location.gd;

import android.content.Context;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.want.location.AbsLocationClient;
import com.want.location.ILocation;

/**
 * <b>Project:</b> android<br>
 * <b>Create Date:</b> 16/3/2<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public class GDLocationClient extends AbsLocationClient implements AMapLocationListener {

    private static GDLocationClient INSTANCE;

    private AMapLocationClient mAMapLocationClient;

    public static GDLocationClient getInstance(Context context) {
        if (null == INSTANCE) {
            synchronized (GDLocationClient.class) {
                if (null == INSTANCE) {
                    INSTANCE = new GDLocationClient(context.getApplicationContext());
                }
            }
        }
        return INSTANCE;
    }

    public GDLocationClient(Context context) {
        super(context);
    }

    /**
     * 配置LocationClient
     */
    @Override
    public void onConfigClient() {
        mAMapLocationClient = new AMapLocationClient(getContext());
        mAMapLocationClient.setLocationListener(this);

        AMapLocationClientOption option = new AMapLocationClientOption();
        option.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        option.setOnceLocation(true);
        mAMapLocationClient.setLocationOption(option);
    }

    /**
     * 异步请求定位
     */
    @Override
    public void requestLocationUpdate() {
        mAMapLocationClient.startLocation();
    }

    /**
     * 同步请求定位, 返回最后一次定位结果
     *
     * @return
     */
    @Override
    public ILocation getLastKnownLocation() {
        AMapLocation location = mAMapLocationClient.getLastKnownLocation();
        if (null != location) {
            return new GDLocation(location);
        }
        return null;
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (null != aMapLocation) {
            GDLocation location = new GDLocation(aMapLocation);
            RESULT status;
            if (0 == aMapLocation.getErrorCode()) {
                status = RESULT.SUCCESS;
            } else {
                status = RESULT.FAIL;
            }
            handleLocation(location, status);
        }

        mAMapLocationClient.stopLocation();
    }
}
