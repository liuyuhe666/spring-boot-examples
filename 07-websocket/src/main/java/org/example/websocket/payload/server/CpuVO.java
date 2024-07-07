package org.example.websocket.payload.server;

import com.google.common.collect.Lists;
import lombok.Data;
import org.example.websocket.model.server.Cpu;
import org.example.websocket.payload.KV;

import java.util.List;

@Data
public class CpuVO {
    List<KV> data = Lists.newArrayList();

    public static CpuVO create(Cpu cpu) {
        CpuVO vo = new CpuVO();
        vo.data.add(new KV("核心数", cpu.getCpuNum()));
        vo.data.add(new KV("CPU 总的使用率", cpu.getTotal()));
        vo.data.add(new KV("CPU 系统使用率", cpu.getSys() + "%"));
        vo.data.add(new KV("CPU 用户使用率", cpu.getUsed() + "%"));
        vo.data.add(new KV("CPU 当前等待率", cpu.getWait() + "%"));
        vo.data.add(new KV("CPU 当前空闲率", cpu.getFree() + "%"));
        return vo;
    }
}
