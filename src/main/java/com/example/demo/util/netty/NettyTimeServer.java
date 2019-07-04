package com.example.demo.util.netty;

import com.netflix.loadbalancer.AbstractLoadBalancer;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @Author limeng
 * @Description
 * @Date @Date: 2019/6/17 16:48
 * @Modified by :
 **/
public class NettyTimeServer {

    public void bind(int port) {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap
                .group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childHandler(new ChildChannelHandler());
        try {
            ChannelFuture f = serverBootstrap.bind("192.168.32.47", port).sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }

    private class ChildChannelHandler extends ChannelInitializer<SocketChannel> {

        @Override
        protected void initChannel(SocketChannel ch) throws Exception {
            ch.pipeline().addLast("http-decode", new HttpRequestDecoder());
            ch.pipeline().addLast("http-aggregator", new HttpObjectAggregator(65536));
            ch.pipeline().addLast("http-encode", new HttpResponseEncoder());
            ch.pipeline().addLast("http-chunked", new ChunkedWriteHandler());
            ch.pipeline().addLast("httpServerHandler", new HttpFileServerHandler());
        }
    }

    public static void main(String[] args) {
        /*int port = 8030;
        new NettyTimeServer().bind(port);*/
        StringBuilder stringBuilder = new StringBuilder("智能物流、智能地磅、人脸闸机、数字工地(基坑、塔机、升降机、环境监测、材料管理、人员管理、)、");
        stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
        System.out.println(stringBuilder);
    }
}
