package com.hnq.study.demo.bio;

import com.hnq.study.config.Properties;
import com.hnq.toolkit.util.ThreadPoolUtils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * Blocking IO: 同步阻塞的编程方式
 *
 * @author henengqiang
 * @date 2019/06/04
 */
public class Server {

    public static void main(String[] args) {
        int port = Integer.valueOf(Properties.getProperty("net.port"));

        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
            System.out.println("Server started!");
            while (true) {
                Socket socket = server.accept();
                ThreadPoolUtils.execute(new Handler(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (server != null) {
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            ThreadPoolUtils.shutdown();
        }
    }

    private static class Handler implements Runnable {

        Socket socket;

        Handler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            BufferedReader reader = null;
            PrintWriter writer = null;
            try {
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
                writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
                String readMessage;
                while (true) {
                    System.out.println("Server reading...");
                    if ((readMessage = reader.readLine()) == null) {
                        break;
                    }
                    System.out.println(readMessage);
                    writer.println("Server receive: " + readMessage);
                    writer.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (writer != null) {
                    writer.close();
                }
            }
        }
    }

}
