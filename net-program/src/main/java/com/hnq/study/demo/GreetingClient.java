package com.hnq.study.demo;

import java.io.*;
import java.net.Socket;

/**
 * @author henengqiang
 * @date 2019/06/04
 */
public class GreetingClient {

    public static void main(String[] args) {
        String serverName = "localhost";
        int port = 6066;
        clientService(serverName, port);
    }

    private static void clientService(String serverName, int port) {
        try {
            System.out.println("连接到主机：" + serverName + "，端口号：" + port);
            Socket client = new Socket(serverName, port);
            System.out.println("远程主机地址：" + client.getRemoteSocketAddress());
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);

            out.writeUTF("Hello from " + client.getLocalSocketAddress());
            InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);
            System.out.println("服务器响应：" + in.readUTF());
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
