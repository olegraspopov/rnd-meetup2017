package com.sbt.rnd.meetup2017.transport.impl;

import com.sbt.rnd.meetup2017.transport.api.ApiRequest;
import com.sbt.rnd.meetup2017.transport.api.TransportProxyFactory;
import com.sbt.rnd.meetup2017.transport.impl.client.RequestInterceptor;
import com.sbt.rnd.meetup2017.transport.impl.client.RpcRequestImpl;
import com.sbt.rnd.meetup2017.transport.impl.server.RpcServerImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.aop.framework.ProxyFactory;

public class TransportProxyFactoryImpl implements TransportProxyFactory {

    private static final Logger LOGGER = LogManager.getLogger(TransportProxyFactoryImpl.class.getName());

    @Override
    public <T> T createTransportProxy(Class<T> clazz) {

        RequestInterceptor requestInterceptor = new RequestInterceptor();
        if (!clazz.isAnnotationPresent(ApiRequest.class) || clazz.getAnnotation(ApiRequest.class).api() == null) {
            LOGGER.error("Not found annotation {} in interface {}", ApiRequest.class, clazz);

            return null;
        }

        Rpc rpc = new RpcRequestImpl(clazz.getAnnotation(ApiRequest.class).api(), System.getProperty("nodeId"), System.getProperty("moduleId"));
        requestInterceptor.setRpc(rpc);

        ProxyFactory proxyFactory = new ProxyFactory(clazz, requestInterceptor);
        //proxyFactory.setAopProxyFactory(new TransportAopProxyFactory());
        return (T) proxyFactory.getProxy();
    }
}
