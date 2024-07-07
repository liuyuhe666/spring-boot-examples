package org.example.websocket.payload.server;

import com.google.common.collect.Lists;
import lombok.Data;
import org.example.websocket.model.server.Jvm;
import org.example.websocket.payload.KV;

import java.util.List;

@Data
public class JvmVO {
    List<KV> data = Lists.newArrayList();

    public static JvmVO create(Jvm jvm) {
        JvmVO vo = new JvmVO();
        vo.data.add(new KV("当前 JVM 占用的内存总数(M)", jvm.getTotal() + "M"));
        vo.data.add(new KV("JVM 最大可用内存总数(M)", jvm.getMax() + "M"));
        vo.data.add(new KV("JVM 空闲内存(M)", jvm.getFree() + "M"));
        vo.data.add(new KV("JVM 使用率", jvm.getUsage() + "%"));
        vo.data.add(new KV("JDK 版本", jvm.getVersion()));
        vo.data.add(new KV("JDK 路径", jvm.getHome()));
        vo.data.add(new KV("JDK 启动时间", jvm.getStartTime()));
        vo.data.add(new KV("JDK 运行时间", jvm.getRunTime()));
        return vo;
    }
}
