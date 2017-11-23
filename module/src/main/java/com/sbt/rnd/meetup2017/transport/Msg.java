package com.sbt.rnd.meetup2017.transport;

import java.io.Serializable;

public class Msg<T> implements Serializable {

    private String id;

    private T value;

    public Msg(String id, T value) {
        this.id = id;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Msg{" +
                "id='" + id + '\'' +
                ", value=" + value +
                '}';
    }
}
