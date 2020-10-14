package com.hnq.study.httpserver;

import java.io.*;
import java.net.Socket;

/**
 * HTTP是基于TCP协议的。TCP负责数据传输，而HTTP只是规范了TCP传输的数据的格式，而这个具体的格式，请见后面给出的资料。
 *
 * HTTP服务的底层实现就是socket编程。
 *
 * 下面基于socket编写一个简单的HTTP server。
 *
 * @author henengqiang
 * @date 2020/3/10
 */
public class SocketHandler implements Runnable {

    private static final String CRLF = "\r\n";

    private Socket clientSocket;

    public SocketHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void handleSocket(Socket clientSocket) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(
                new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())), true);

        StringBuilder requestHeader = new StringBuilder();
        String s;
        while ((s = in.readLine()) != null) {
            // 很重要，默认情况下in.readLine的结果中`\r\n`被去掉了
            s += CRLF;
            requestHeader.append(s);
            if (s.equals(CRLF)) {
                break;
            }
        }

        System.out.println("客户端请求头：");
        System.out.println(requestHeader);

        String responseBody = "客户端的请求头是：\n" + requestHeader;

        String responseHeader = "HTTP/1.0 200 OK\r\n" +
                "Content-Type: text/plain; charset=UTF-8\r\n" +
                "Content-Length: "+responseBody.getBytes().length+"\r\n" +
                "\r\n";

        System.out.println("响应头：");
        System.out.println(responseHeader);

        out.write(responseHeader);
        out.write(responseBody);
        out.flush();

        out.close();
        in.close();
        clientSocket.close();
    }

    @Override
    public void run() {
        try {
            handleSocket(clientSocket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
