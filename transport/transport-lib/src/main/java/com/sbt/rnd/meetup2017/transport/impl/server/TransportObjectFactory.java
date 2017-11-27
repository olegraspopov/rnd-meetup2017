package com.sbt.rnd.meetup2017.transport.impl.server;

import java.util.List;
import java.util.Set;

public interface TransportObjectFactory {

    ApiServer createApiServer(String baseApiPackage, List<SpringApiServer.ApiServiceBean>  apiServices);

}
