package org.example.ratelimit.redis.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RateLimiter {
    long DEFAULT_REQUEST = 10;

    /**
     * max 最大请求数
     */
    @AliasFor("max") long value() default DEFAULT_REQUEST;

    /**
     * max 最大请求数
     */
    @AliasFor("value") long max() default DEFAULT_REQUEST;

    /**
     * 限流 key
     */
    String key() default "";

    /**
     * 超时时长，默认 1 分钟
     */
    long timeout() default 1;

    /**
     * 超时时间单位，默认为分钟
     */
    TimeUnit timeUnit() default TimeUnit.MINUTES;
}
