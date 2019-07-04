package com.example.demo.util.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author limeng
 * @Description
 * @Date @Date: 2019/6/13 16:22
 * @Modified by :
 **/
public class TimerServer {

    private static int port = 8080;

    public static void main(String[] args) throws Exception{

        ServerSocket server = null;
        server = new ServerSocket(port);
        while (true) {
            Socket socket = server.accept();
            new Thread(new TimerHandler(socket)).start();
        }



    }
}
