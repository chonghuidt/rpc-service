package com.lp;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        RpcProxyClient rpcProxyClient = new RpcProxyClient();
        IOrderService orderService = rpcProxyClient.client(IOrderService.class,"localhost",8080);
        TestService testService=rpcProxyClient.client(TestService.class,"localhost",8080);
        System.out.println( orderService.queryOrderList() );
        System.out.println(orderService.queryById("lp"));
        System.out.println(testService.say());
    }
}
