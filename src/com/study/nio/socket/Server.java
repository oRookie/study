package com.study.nio.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class Server {
    public static void listen(int port) throws IOException {
//        try {
//            System.out.println("服务端开始监听 "+port);
//            ServerSocket serverSocket = new ServerSocket(port);
//            Socket socket = serverSocket.accept();
//            InputStream inputStream = socket.getInputStream();
//            byte[] bs = new byte[1024];
//            while (inputStream.read(bs)!=-1){
//                System.out.println("服务端-->> 收到："+new String(bs));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        ServerSocket serverSocket = new ServerSocket(port);
        while (true) {
            try {
                System.out.println("等待远程连接，端口号为：" + serverSocket.getLocalPort() + "...");
                Socket server = serverSocket.accept();
                System.out.println("远程主机地址：" + server.getRemoteSocketAddress());
                DataInputStream in = new DataInputStream(server.getInputStream());
                System.out.println(in.readUTF());
                DataOutputStream out = new DataOutputStream(server.getOutputStream());
                out.writeUTF("谢谢连接我：" + server.getLocalSocketAddress() + "\nGoodbye!");
                server.close();
            } catch (SocketTimeoutException s) {
                System.out.println("Socket timed out!");
                break;
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        listen(8888);
    }
}
