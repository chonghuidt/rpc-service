package com.lp;

import java.lang.reflect.Proxy;

/**
 * @auther lp
 * @date 2020/6/20 0020 14:08
 */
public class RpcProxyClient {
    public <T>T client(final Class<T> interfaceCls,final String host,int port){
        return (T) Proxy.newProxyInstance(interfaceCls.getClassLoader(), new Class<?>[]{interfaceCls}, new RemoteHandler());

    }

}
