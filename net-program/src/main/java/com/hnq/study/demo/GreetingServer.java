package com.hnq.study.demo;

import com.hnq.toolkit.util.ThreadPoolUtils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * @author henengqiang
 * @date 2019/06/04
 */
public class GreetingServer {

    public static void main(String[] args) {
        int port = 6066;
        try {
            ThreadPoolUtils.execute(new ServerService(port));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ServerService implements Runnable {

        private ServerSocket serverSocket;

        ServerService(int port) throws IOException {
            this.serverSocket = new ServerSocket(port);
//            serverSocket.setSoTimeout(10000);
        }

        @Override
        public void run() {
            while (true) {
                try {
                    System.out.println("等待远程接口，端口为：" + serverSocket.getLocalPort() + "...");
                    Socket server = serverSocket.accept();
                    System.out.println("远程主机地址：" + server.getRemoteSocketAddress());
                    DataInputStream in = new DataInputStream(server.getInputStream());
                    System.out.println(in.readUTF());
                    DataOutputStream out = new DataOutputStream(server.getOutputStream());
                    out.writeUTF("谢谢连接我：" + server.getLocalSocketAddress() + "\nGoodBye!");
                    server.close();
                } catch (SocketTimeoutException e) {
                    System.out.println("Socket timed out!");
                    break;
                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }
    }

}
