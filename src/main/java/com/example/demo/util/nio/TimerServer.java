package com.example.demo.util.nio;

import com.example.demo.util.StringUtils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author limeng
 * @Description
 * @Date @Date: 2019/6/17 11:35
 * @Modified by :
 **/
public class TimerServer {

    private static Selector selector;

    public static void main(String[] args) throws Exception{
        // 1--打开serverSocketChannel
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        // 2--绑定监听地址 inetSocketAddress
        serverChannel.socket().bind(new InetSocketAddress(InetAddress.getByName("ip"), 8080));
        serverChannel.configureBlocking(false);
        // 3--打开serverSocketChannel注册到Selector
        selector = Selector.open();
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
        // 4--创建Selector 启动线程

        // 5--Selector轮询key
        Set<SelectionKey> selectionKeys = selector.selectedKeys();
        Iterator<SelectionKey> iterator = selectionKeys.iterator();
        SelectionKey key = null;
        while (iterator.hasNext()) {
            key = iterator.next();
            iterator.remove();
            handerKey(key);
        }
    }

    /**
     * 处理key
     * @param key
     */
    private static void handerKey (SelectionKey key) {
        if (key.isAcceptable()) {
            // 6--handlerAccept()处理新的客户端连接
            ServerSocketChannel channel = (ServerSocketChannel) key.channel();
            try {
                // 7--设置socket参数
                SocketChannel accept = channel.accept();
                accept.configureBlocking(false);
                // 8--向Selector注册读操作 SelectionKey.OP_Read
                channel.register(selector, SelectionKey.OP_READ);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (key.isReadable()) {
            // 9--handlerRead()读取数据到ByteBuffer
            SocketChannel socketChannel = (SocketChannel)key.channel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            try {
                int read = socketChannel.read(byteBuffer);
                if (read > 0) {
                    // 10--decode消息
                    byteBuffer.flip();
                    byte[] bytes = new byte[byteBuffer.remaining()];
                    byteBuffer.get(bytes);
                    String body = new String(bytes);
                    System.out.println("receive message :" + body);
                    // 11--异步写到打开serverSocketChannel
                    doWrite(socketChannel, body);
                } else if (read < 0) {
                    // 异常需关闭
                    key.cancel();
                    socketChannel.close();
                } else {
                    // = o 无需处理
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 异步写
     * @param socketChannel
     * @param body
     */
    private static void doWrite(SocketChannel socketChannel, String body) {
        if (StringUtils.isNotBlank(body)) {
            byte[] bytes = body.getBytes();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            byteBuffer.put(bytes);
            byteBuffer.flip();
            try {
                socketChannel.write(byteBuffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
