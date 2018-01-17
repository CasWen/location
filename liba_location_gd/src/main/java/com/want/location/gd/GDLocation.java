package com.want.location.gd;

import com.amap.api.location.AMapLocation;
import com.want.location.ILocation;

/**
 * <b>Project:</b> android<br>
 * <b>Create Date:</b> 16/3/2<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public class GDLocation implements ILocation {

    private String cityCode;
    private String countryCode;
    private double latitude;
    private double longitude;
    private String province;
    private String city;
    private String street;
    private String address;

    public GDLocation(AMapLocation location) {
        this.cityCode = location.getCityCode();
        this.city = location.getCity();
        this.countryCode = location.getAdCode();
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();
        this.province = location.getProvince();
        this.street = location.getStreet();
        this.address = location.getAddress();
    }

    /**
     * 获取城市代码
     *
     * @return
     */
    @Override
    public String getCityCode() {
        return this.cityCode;
    }

    /**
     * 获取国家代码
     *
     * @return
     */
    @Override
    public String getCountryCode() {
        return this.countryCode;
    }

    /**
     * 获取纬度坐标
     *
     * @return
     */
    @Override
    public double getLatitude() {
        return this.latitude;
    }

    /**
     * 获取经度
     *
     * @return
     */
    @Override
    public double getLongitude() {
        return this.longitude;
    }

    /**
     * 获取省份
     *
     * @return
     */
    @Override
    public String getProvince() {
        return this.province;
    }

    /**
     * 获取城市
     *
     * @return
     */
    @Override
    public String getCity() {
        return this.city;
    }

    /**
     * 获取街道
     *
     * @return
     */
    @Override
    public String getStreet() {
        return this.street;
    }

    /**
     * 获取地址
     *
     * @return
     */
    @Override
    public String getAddress() {
        return this.address;
    }

    @Override
    public String toString() {
        return "GDLocation{" +
               "cityCode='" + cityCode + '\'' +
               ", countryCode='" + countryCode + '\'' +
               ", latitude=" + latitude +
               ", longitude=" + longitude +
               ", province='" + province + '\'' +
               ", city='" + city + '\'' +
               ", street='" + street + '\'' +
               ", address='" + address + '\'' +
               '}';
    }
}
