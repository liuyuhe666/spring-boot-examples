package org.example.websocket.model.server;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import lombok.Getter;
import lombok.Setter;

import java.lang.management.ManagementFactory;
import java.util.Date;

@Setter
public class Jvm {
    /**
     * 当前 JVM 占用的内存总数(M)
     */
    private double total;

    /**
     * JVM 最大可用内存总数(M)
     */
    private double max;

    /**
     * JVM 空闲内存(M)
     */
    private double free;

    /**
     * JDK 版本
     */
    @Getter
    private String version;

    /**
     * JDK 路径
     */
    @Getter
    private String home;

    /**
     * JDK 启动时间
     */
    private String startTime;

    /**
     * JDK 运行时间
     */
    private String runTime;

    public double getTotal() {
        return NumberUtil.div(total, (1024 * 1024), 2);
    }


    public double getMax() {
        return NumberUtil.div(max, (1024 * 1024), 2);
    }


    public double getFree() {
        return NumberUtil.div(free, (1024 * 1024), 2);
    }


    public double getUsed() {
        return NumberUtil.div(total - free, (1024 * 1024), 2);
    }

    public double getUsage() {
        return NumberUtil.mul(NumberUtil.div(total - free, total, 4), 100);
    }

    /**
     * 获取JDK名称
     */
    public String getName() {
        return ManagementFactory.getRuntimeMXBean().getVmName();
    }

    public String getStartTime() {
        return DateUtil.formatDateTime(new Date(ManagementFactory.getRuntimeMXBean().getStartTime()));
    }

    public String getRunTime() {
        long startTime = ManagementFactory.getRuntimeMXBean().getStartTime();
        return DateUtil.formatBetween(DateUtil.current(false) - startTime);
    }
}
