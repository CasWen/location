package com.want.location;

/**
 * <b>Project:</b> android<br>
 * <b>Create Date:</b> 16/3/2<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public class LocationManager implements ILocationClient {

    private static LocationManager INSTANCE;

    private ILocationClient mLocationClient;


    private LocationManager(ILocationClient client) {
        // hide
        this.mLocationClient = client;
        client.onConfigClient();
    }

    public static void init(ILocationClient client) {
        if (null == INSTANCE) {
            synchronized (LocationManager.class) {
                if (null == INSTANCE) {
                    INSTANCE = new LocationManager(client);
                }
            }
        }
    }

    public static LocationManager getInstance() {
        if (null == INSTANCE) {
            throw new RuntimeException("You should init LocationManger before call 'getInstance()'");
        }
        return INSTANCE;
    }


    /**
     * 配置LocationClient
     */
    @Override
    public void onConfigClient() {
        // empty
    }

    /**
     * 异步请求定位
     */
    @Override
    public void requestLocationUpdate() {
        mLocationClient.requestLocationUpdate();
    }

    /**
     * 同步请求定位, 返回最后一次定位结果
     *
     * @return
     */
    @Override
    public ILocation getLastKnownLocation() {
        return mLocationClient.getLastKnownLocation();
    }

    /**
     * 增加一个位置监听器，使用完毕后需要remove掉
     *
     * @param listener {@link LocationListener}
     */
    @Override
    public void addLocationListener(LocationListener listener) {
        mLocationClient.addLocationListener(listener);
    }

    /**
     * 移除指定的位置监听器
     *
     * @param listener {@link LocationListener}
     */
    @Override
    public void removeLocationListener(LocationListener listener) {
        mLocationClient.removeLocationListener(listener);
    }
}
