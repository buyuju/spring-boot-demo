package com.example.demo.util.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author limeng
 * @Description
 * @Date @Date: 2019/6/17 17:10
 * @Modified by :
 **/
public class TimeServerHandler extends ChannelHandlerAdapter{

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        String body = (String) msg;
        System.out.println("receive body : " + body);
        ByteBuf byteBuf = Unpooled.copiedBuffer(body.getBytes());
        ctx.writeAndFlush(byteBuf);
    }

    public static void main(String[] args) {
        int x = 0;
        System.out.println(checkReturn(x));
    }

    public static int checkReturn(int x) {
        try {
            return ++x;
        } finally {

            ++x;
        }
    }
}
