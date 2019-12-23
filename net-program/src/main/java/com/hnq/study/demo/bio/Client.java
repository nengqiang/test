package com.hnq.study.demo.bio;

import com.hnq.study.config.Properties;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @author henengqiang
 * @date 2019/06/04
 */
public class Client {

    public static void main(String[] args) {
        String host = Properties.getProperty("net.host");
        Integer port = Integer.valueOf(Properties.getProperty("net.port"));
        clientService(host, port);
    }

    private static void clientService(String host, Integer port) {
        Socket socket = null;
        BufferedReader reader = null;
        PrintWriter writer = null;
        Scanner s = new Scanner(System.in);
        try {
            socket = new Socket(host, port);
            String message;
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
            while (true) {
                System.out.println("Please type the message you want to send to server:");
                message = s.nextLine();
                if ("exit".equals(message)) {
                    break;
                }
                writer.println(message);
                writer.flush();
                System.out.println(reader.readLine());
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
