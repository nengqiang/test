package com.hnq.study.demo.udp;

import java.io.IOException;
import java.net.*;

/**
 * @author henengqiang
 * @date 2019/06/04
 */
public class Client {

    public static void main(String[] args) {
        clientService();
    }

    private static void clientService() {
        try {
            DatagramSocket client = new DatagramSocket(6667);
            DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
            packet.setPort(6666);
            packet.setAddress(InetAddress.getLocalHost());
            packet.setData("Hello Server".getBytes());
            client.send(packet);
            System.out.println(packet.getAddress().getHostName() + ":" + packet.getPort() + " " + new String(packet.getData()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
