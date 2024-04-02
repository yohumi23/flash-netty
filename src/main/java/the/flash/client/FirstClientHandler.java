package the.flash.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * @author chao.yu
 * chao.yu@dianping.com
 * @date 2018/08/04 06:23.
 */
/**
 * 有关于inbound事件, 就是以自己为基准, 流向自己的事件
 */
public class FirstClientHandler extends ChannelInboundHandlerAdapter {
    /**
     * 方法会在客户端连接建立成功之后被调用
     * @param ctx
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println(new Date() + ": 客户端写出数据");

        // 1.获取数据
        ByteBuf buffer = getByteBuf(ctx);

        // 2.写数据
        ctx.channel().writeAndFlush(buffer);
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
        //1. 准备数据，指定字符串的字符集为UTF-8
        byte[] bytes = "你好，闪电侠!".getBytes(Charset.forName("utf-8"));
        //2. 获取二进制抽象ByteBuf
        // ctx.alloc()获取到一个ByteBuf的内存管理器
        ByteBuf buffer = ctx.alloc().buffer();
        //3. 填充数据到ByteBuf
        buffer.writeBytes(bytes);

        return buffer;
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf byteBuf = (ByteBuf) msg;

        System.out.println(new Date() + ": 客户端读到数据 -> " + byteBuf.toString(Charset.forName("utf-8")));
    }
    /**
     * 与传统的Socket编程不同，Netty的数据传输单位是ByteBuf,读取和写入都是如此
     */
}
