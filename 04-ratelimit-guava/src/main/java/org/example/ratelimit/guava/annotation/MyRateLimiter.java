package org.example.ratelimit.guava.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyRateLimiter {
    int NOT_LIMITED = 0;

    @AliasFor("qps") double value() default NOT_LIMITED;

    @AliasFor("value") double qps() default NOT_LIMITED;

    // 超时时长
    int timeout() default 0;

    // 超时时间单位
    TimeUnit timeUnit() default TimeUnit.MILLISECONDS;
}
