## 位置模块使用说明

### 快速使用

1. 添加依赖

        // 位置核心模块
        compile 'com.want.model:location-core:1.0-SNAPSHOT@aar'
        // 基于高德的位置实现
        compile 'com.want.model:location-gd:1.0-SHAPSHOT@aar'
        
2. 添加代码

        // location 模块配置
        // 如果你实现了其他位置模块，在此指定即可
        LocationManager.init(new GDLocationClient(this));
        
        // 合适的位置调用以下代码获取位置信息
        final LocationManager manager = LocationManager.getInstance();
        // 增加位置更新回调
        manager.addLocationListener(listener);
        // 请求位置更新
        manager.requestLocationUpdate();
        // 获取最近一次已知的位置信息
        anager.getLastKnownLocation();
        
3. 其他配置：

    1. 基于高德的位置实现
    
        在AndroidManifest.xml文件中增加以下代码：
        
            <meta-data
                        android:name="com.amap.api.v2.apikey"
                        android:value="你的KEY"
                        tools:replace="android:value"/>


4. 代码混淆配置：

    1. 基于高德的位置实现
    
            在生成apk进行代码混淆时进行如下配置(如果爆出warning，在报出warning的包加入类似的语句：-dontwarn 包名)
            
            -keep class com.amap.api.location.**{*;}
            
            -keep class com.amap.api.fence.**{*;}
            
            -keep class com.autonavi.aps.amapapi.model.**{*;}# location
