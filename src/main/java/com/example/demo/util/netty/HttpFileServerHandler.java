package com.example.demo.util.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * @Author limeng
 * @Description
 * @Date @Date: 2019/6/17 17:10
 * @Modified by :
 **/
public class HttpFileServerHandler extends SimpleChannelInboundHandler<FullHttpRequest>{

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {

    }
}
