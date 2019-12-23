package com.hnq.study.demo.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * DatagramSocket
 *
 * @author henengqiang
 * @date 2019/06/04
 */
public class Server {

    public static void main(String[] args) {
        serverService();
    }

    private static void serverService() {
        try {
            DatagramSocket server = new DatagramSocket(6666);
            DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
            server.receive(packet);
            System.out.println(packet.getAddress().getHostName() + ":" + packet.getPort() + " " + new String(packet.getData()));
            packet.setData("Hello Client".getBytes());
            packet.setPort(6667);
            packet.setAddress(InetAddress.getLocalHost());
            server.send(packet);
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
