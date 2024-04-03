package the.flash.protocol.command;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public abstract class Packet {
    /**
     * 协议版本
     */
    @JSONField(deserialize = false, serialize = false) //在序列化和反序列化的时候过滤这个字段
    private Byte version = 1;


    @JSONField(serialize = false)
    public abstract Byte getCommand();
}
