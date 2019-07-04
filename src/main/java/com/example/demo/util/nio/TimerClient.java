package com.example.demo.util.nio;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author limeng
 * @Description
 * @Date @Date: 2019/6/17 11:35
 * @Modified by :
 **/
public class TimerClient {

    private static SocketChannel clientChannel;

    private static Selector selector;

    private static int port = 8080;

    private static String ip;

    public static void main(String[] args) throws Exception{
        // 1--打开SocketChannel
        clientChannel = SocketChannel.open();
        selector = Selector.open();
        selector.select(1000);
        clientChannel.configureBlocking(false);
        // 2--
        Set<SelectionKey> selectionKeys = selector.selectedKeys();
        SelectionKey key = null;
        Iterator<SelectionKey> iterator = selectionKeys.iterator();
        while (iterator.hasNext()) {
            key = iterator.next();
            iterator.remove();
            //handerKey(key, null);
        }
        boolean flag = clientChannel.connect(new InetSocketAddress("ip", port));
        if (flag) {
            //clientChannel.re
        } else {

        }

        // 3--

        // 4--

        // 5--

        // 6--

        // 7--

        // 8--

        // 9--

        // 10--
    }
}
