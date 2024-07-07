package org.example.websocket.payload.server;

import com.google.common.collect.Lists;
import lombok.Data;
import org.example.websocket.model.server.SysFile;
import org.example.websocket.payload.KV;

import java.util.List;

@Data
public class SysFileVO {
    List<List<KV>> data = Lists.newArrayList();

    public static SysFileVO create(List<SysFile> sysFiles) {
        SysFileVO vo = new SysFileVO();
        for (SysFile sysFile : sysFiles) {
            List<KV> item = Lists.newArrayList();

            item.add(new KV("盘符路径", sysFile.getDirName()));
            item.add(new KV("盘符类型", sysFile.getSysTypeName()));
            item.add(new KV("文件类型", sysFile.getTypeName()));
            item.add(new KV("总大小", sysFile.getTotal()));
            item.add(new KV("剩余大小", sysFile.getFree()));
            item.add(new KV("已经使用量", sysFile.getUsed()));
            item.add(new KV("资源的使用率", sysFile.getUsage() + "%"));

            vo.data.add(item);
        }
        return vo;
    }
}
