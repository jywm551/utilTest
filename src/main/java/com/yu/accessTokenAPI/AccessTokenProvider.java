package com.yu.accessTokenAPI;

/**
 * Created By Yu On 2018/9/4
 * Description：interface 获取accessToken的接口类
 **/
public interface AccessTokenProvider {
    String getAccessToken();

    void init();
}
