package org.example.websocket.model.server;

import cn.hutool.core.util.NumberUtil;
import lombok.Getter;
import lombok.Setter;

@Setter
public class Cpu {
    /**
     * 核心数
     */
    @Getter
    private int cpuNum;

    /**
     * CPU 总的使用率
     */
    private double total;

    /**
     * CPU 系统使用率
     */
    private double sys;

    /**
     * CPU 用户使用率
     */
    private double used;

    /**
     * CPU 当前等待率
     */
    private double wait;

    /**
     * CPU 当前空闲率
     */
    private double free;


    public double getTotal() {
        return NumberUtil.round(NumberUtil.mul(total, 100), 2).doubleValue();
    }

    public double getSys() {
        return NumberUtil.round(NumberUtil.mul(sys / total, 100), 2).doubleValue();
    }

    public double getUsed() {
        return NumberUtil.round(NumberUtil.mul(used / total, 100), 2).doubleValue();
    }


    public double getWait() {
        return NumberUtil.round(NumberUtil.mul(wait / total, 100), 2).doubleValue();
    }


    public double getFree() {
        return NumberUtil.round(NumberUtil.mul(free / total, 100), 2).doubleValue();
    }
}
