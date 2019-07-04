package com.example.demo.util.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @Author limeng
 * @Description
 * @Date @Date: 2019/6/17 17:35
 * @Modified by :
 **/
public class TimeClientHandler extends ChannelHandlerAdapter {

    private byte[] bytes;

    public TimeClientHandler(String msg) {
        bytes = msg.getBytes();

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ByteBuf byteBuf = null;
        for (int a = 0 ; a < 100; a++) {
            byteBuf = Unpooled.buffer(bytes.length);
            byteBuf.writeBytes(bytes);
            ctx.writeAndFlush(byteBuf);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String body = (String) msg;
        System.out.println("Now body : " + body);
    }
}
