package com.study.nio.socket;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void connect(String host,int port) throws IOException {
//        Socket socket = new Socket(host,port);
//        System.out.println(socket.getRemoteSocketAddress());
//        socket.getOutputStream().write("hello server".getBytes());
        System.out.println("连接到主机：" + host + " ，端口号：" + port);
        Socket client = new Socket(host, port);
        System.out.println("远程主机地址：" + client.getRemoteSocketAddress());
        OutputStream outToServer = client.getOutputStream();
        DataOutputStream out = new DataOutputStream(outToServer);

        out.writeUTF("Hello from " + client.getLocalSocketAddress());

        client.close();
    }

    public static void main(String[] args) throws IOException {
        connect("127.0.0.1",8888);
    }
}
