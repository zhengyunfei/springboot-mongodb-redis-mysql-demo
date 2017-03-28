package com.sectong.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2017/3/28.
 */
public class ServerResourcesUtil {
    public static String getCurrentDomainUrl(HttpServletRequest request){
        String requestUrl = request.getScheme() //当前链接使用的协议
                +"://" + request.getServerName()//服务器地址
                + ":" + request.getServerPort() //端口号
                + request.getContextPath(); //应用名称，如果应用名称为
        return requestUrl;
    }
}
