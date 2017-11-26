package com.sbt.rnd.meetup2017.transport.message;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class MessageProperties implements Serializable {

    private volatile String id;
    private volatile String apiMethod;
    private volatile String apiFullName;
    private volatile String requestId;
    private volatile String corellationId;
    private volatile Date date;
    private volatile String nodeId;
    private volatile String moduleId;
    private volatile String destination;
    private volatile Long timeout;

    public MessageProperties() {
        id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public String getApiMethod() {
        return apiMethod;
    }

    public void setApiMethod(String apiMethod) {
        this.apiMethod = apiMethod;
    }

    public String getApiFullName() {
        return apiFullName;
    }

    public void setApiFullName(String apiFullName) {
        this.apiFullName = apiFullName;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getCorellationId() {
        return corellationId;
    }

    public void setCorellationId(String corellationId) {
        this.corellationId = corellationId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getModuleId() {
        return moduleId;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public Long getTimeout() {
        return timeout;
    }

    public void setTimeout(Long timeout) {
        this.timeout = timeout;
    }

    @Override
    public String toString() {
        return "MessageProperties{" +
                "id='" + id + '\'' +
                ", apiMethod='" + apiMethod + '\'' +
                ", apiFullName='" + apiFullName + '\'' +
                ", requestId='" + requestId + '\'' +
                ", corellationId='" + corellationId + '\'' +
                ", date=" + date +
                ", nodeId='" + nodeId + '\'' +
                ", moduleId='" + moduleId + '\'' +
                ", destination='" + destination + '\'' +
                '}';
    }
}
