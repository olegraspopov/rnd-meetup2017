package com.sbt.rnd.meetup2017.transport.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>Маркер для interface'а который входит в публичное API модуля.</p>
 * <p>Реализация этого интерфейса должна быть помечена аннотацией {@link ApiImpl}</p>
 *
 * @see ApiImpl
 *
 *
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Api {
}
