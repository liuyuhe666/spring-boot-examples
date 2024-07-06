package org.example.ratelimit.guava.aspect;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.example.ratelimit.guava.annotation.MyRateLimiter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
@Aspect
public class RateLimiterAspect {
    private static final ConcurrentHashMap<String, RateLimiter> rateLimiterMap = new ConcurrentHashMap<>();

    @Pointcut("@annotation(org.example.ratelimit.guava.annotation.MyRateLimiter)")
    public void rateLimit() {

    }

    @Around("rateLimit()")
    public Object pointCut(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        // 获取 RateLimiter 注解
        MyRateLimiter rateLimiter = AnnotationUtils.findAnnotation(method, MyRateLimiter.class);
        if (rateLimiter != null && rateLimiter.qps() > MyRateLimiter.NOT_LIMITED) {
            double qps = rateLimiter.qps();
            if (rateLimiterMap.get(method.getName()) == null) {
                // 初始化
                rateLimiterMap.put(method.getName(), RateLimiter.create(qps));
            }
            log.debug("{} 的 QPS 被设置为: {}", method.getName(), rateLimiterMap.get(method.getName()).getRate());
            // 尝试获取令牌
            if (rateLimiterMap.get(method.getName()) != null && !rateLimiterMap.get(method.getName()).tryAcquire(rateLimiter.timeout(), rateLimiter.timeUnit())) {
                throw new RuntimeException("手速太快了，慢点吧~");
            }
        }
        return joinPoint.proceed();
    }
}
