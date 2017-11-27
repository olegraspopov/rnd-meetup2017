package com.sbt.rnd.meetup2017.transport.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>Маркер для классов, реализующих один или несколько интерфейсов помеченных аннтотацией {@link Api}
 * т.е. для сервисов, реализующих публичное API модуля.</p>
 * <p>Аннотация исползуется для автоматического подключения сервиса к межмодульному трансопрту.
 * <br/>Для каждого интерфейса, реализуемого таким сервисом, будет создан отдельный набор Spring-been'ов:
 * Been самого сервиса и набор бинов Spring-AMQP, реализующих RPC.
 * <br/>Так-же, для сервиса будут автоматически созданы необходимые <i>очереди</i> и остальные компоненты на сервере сообщений.
 * </p>
 *
 * @see Api
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiImpl {

}
