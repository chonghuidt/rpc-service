package com.lp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @auther lp
 * @date 2020/6/20 0020 14:14
 */
@Component
public class RemoteHandler implements InvocationHandler {
    @Value("${lp.host}")
    private String host;
    @Value("${lp.port}")
    private int port;

    public RemoteHandler() {

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            //建立远程连接
            RpcNetTransport rpcNetTransport = new RpcNetTransport(host,port);
            RpcRequest request = new RpcRequest();
            request.setArgs(args);
            request.setClassName(method.getDeclaringClass().getName());
            request.setTypes(method.getParameterTypes()); //参数的类型
            request.setMethodName(method.getName());
            return rpcNetTransport.send(request);
    }
}
