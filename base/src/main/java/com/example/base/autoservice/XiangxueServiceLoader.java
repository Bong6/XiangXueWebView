package com.example.base.autoservice;

/*
    @Author 姜小龙
    @Description
                简单的工具类
                            ->  用于找到IWebViewService接口的实现类WebViewServiceImpl
    @Date 2022-02-07 15:28
*/

import java.util.ServiceLoader;

public class XiangxueServiceLoader {
//    不能够new的
    private XiangxueServiceLoader() {
    }

    public static <S> S load(Class<S> service) {
        try {

            //可能有多个实现
            return ServiceLoader.load(service).iterator().next();
        } catch (Exception e) {
            return null;
        }
    }

}
