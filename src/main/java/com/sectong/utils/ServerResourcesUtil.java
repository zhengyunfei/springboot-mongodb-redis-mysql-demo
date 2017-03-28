package com.sectong.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 郑云飞 on 2017/3/28.
 * 获取当前访问的url前缀
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
