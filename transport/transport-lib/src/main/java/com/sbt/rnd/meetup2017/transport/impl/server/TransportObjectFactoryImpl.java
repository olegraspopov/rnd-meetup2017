package com.sbt.rnd.meetup2017.transport.impl.server;

import org.springframework.remoting.support.RemoteInvocationBasedExporter;

import java.util.List;

public class TransportObjectFactoryImpl implements TransportObjectFactory {

    public TransportObjectFactoryImpl() {
    }

    @Override
    public ApiServer createApiServer(String baseApiPackage, List<SpringApiServer.ApiServiceBean> apiServices) {
        ApiServer server = null;
        for (SpringApiServer.ApiServiceBean apiServiceBean : apiServices) {

            RemoteInvocationBasedExporter exporter;

            exporter = new RpcServerImpl(apiServiceBean.getApiClass(), System.getProperty("nodeId"), System.getProperty("moduleId"));

            exporter.setServiceInterface(apiServiceBean.getApiClass());
            exporter.setService(apiServiceBean.getBean());
            server = (ApiServer) exporter;

        }
        return server;
    }
}
