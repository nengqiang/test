package com.hnq.study.demo.qqchat;

import com.google.common.collect.Maps;
import com.hnq.study.config.Properties;
import com.hnq.toolkit.util.ThreadPoolUtils;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.Scanner;

/**
 * @author henengqiang
 * @date 2019/06/05
 */
@Slf4j
public class ServerForm extends JFrame {

    private static final int WIDTH = 500;

    private static final int HEIGHT = 400;

    /**
     * 在线的用户信息显示
     */
    private JTextArea area;

    /**
     * 在线的用户列表显示
     */
    private DefaultListModel<String> dataModel;

    /**
     * 注册的用户名不能相同
     * 用于存储所有的用户
     */
    private Map<String, Socket> userMap = Maps.newHashMap();

    private ServerForm() {
        setTitle("聊天服务器");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // 设置界面居中显示
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dim = toolkit.getScreenSize();
        int width = (int) dim.getWidth();
        int height = (int) dim.getHeight();
        setBounds((width - WIDTH) / 2, (height - HEIGHT) / 2, WIDTH, HEIGHT);

        area = new JTextArea();
        area.setEditable(false);
        getContentPane().add(new JScrollPane(area), BorderLayout.CENTER);

        // 列表显示
        dataModel = new DefaultListModel<>();
        JList<String> list = new JList<>(dataModel);
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setBorder(new TitledBorder("在线"));
        scrollPane.setPreferredSize(new Dimension(100, this.getHeight()));
        getContentPane().add(scrollPane, BorderLayout.EAST);

        // 菜单
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menu = new JMenu("控制(C)");
        // 设置快捷键为Alt + c
        menu.setMnemonic('C');
        menuBar.add(menu);

        // 开启
        final JMenuItem itemRun = new JMenuItem("开启");
        // 快捷键 Ctrl + R
        itemRun.setAccelerator(KeyStroke.getKeyStroke('R', KeyEvent.CTRL_MASK));
        itemRun.setActionCommand("run");
        menu.add(itemRun);

        //退出
        JMenuItem itemExit = new JMenuItem("退出");
        itemExit.setAccelerator(KeyStroke.getKeyStroke('E', KeyEvent.CTRL_MASK));
        itemExit.setActionCommand("exit");
        menu.add(itemExit);

        setVisible(true);

        itemRun.addActionListener(e -> {
            if ("run".equals(e.getActionCommand())) {
                startServer();
                itemRun.setEnabled(false);
            }
        });

        itemExit.addActionListener(e -> {
            if ("exit".equals(e.getActionCommand())) {
                System.exit(0);
                ThreadPoolUtils.shutdown();
            }
        });
    }

    private void startServer() {
        try {
            log.debug("服务器启动");
            int port = Integer.valueOf(Properties.getProperty("net.port"));
            ServerSocket server = new ServerSocket(port);
            area.append("启动服务器：" + server);

            // 单独开启一个线程用于与客户端握手
            ServerRunnable serverRunnable = new ServerRunnable(server);
            ThreadPoolUtils.execute(serverRunnable);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class ServerRunnable implements Runnable {

        private ServerSocket server;

        ServerRunnable(ServerSocket server) {
            this.server = server;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    Socket s = server.accept();
                    Scanner sc = new Scanner(s.getInputStream());
                    if (sc.hasNext()) {
                        String userName = sc.next();
                        area.append("\r\n" + userName + "上线了 " + s);
                        dataModel.addElement(userName);

                        ClientRunnable clientRunnable = new ClientRunnable(s);
                        ThreadPoolUtils.execute(clientRunnable);

                        // 告诉其他人有人上线了
                        sendMsgToAll(userName);
                        // 把其他在线人的信息发送给登录的这个客户端
                        sendMsgToSelf(s);

                        userMap.put(userName, s);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 专门用于和某一个用户通讯的线程
     */
    private class ClientRunnable implements Runnable {

        private Socket socket;

        ClientRunnable(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                // 根据客户端发来的协议判断，客户端进行的是怎样的请求
                Scanner sc = new Scanner(socket.getInputStream());
                while (sc.hasNextLine()) {
                    String msg = sc.nextLine();
                    String[] msgs = msg.split("@#");
                    // 简单防黑
                    if (msgs.length != 4) {
                        log.error("通讯异常: " + msg);
                        return;
                    }

                    if ("on".equals(msgs[0])) {
                        // 客户端请求向别人发送消息
                        sendMsgToSb(msgs);
                    } else if ("exit".equals(msgs[0])) {
                        // 客户端发送请求是退出
                        area.append("\r\n" + msgs[3] + "下线了" + socket);
                        dataModel.removeElement(msgs[3]);
                        userMap.remove(msgs[3]);
                        // 通知其他在线用户某某下线了
                        sendSbExitMsgToAll(msgs);
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendMsgToAll(String userName) throws IOException {
        // 此时登录的用户还没有加入到userMap中，所以可以直接遍历map发信息
        for (Map.Entry<String, Socket> entry : userMap.entrySet()) {
            Socket s = entry.getValue();
            PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
            // 服务器向客户端发消息的格式：
            // 命令关键字@#发送方@#消息内容
            // 用于显示用的
            String msg = "msg@#server@#" + userName + "登录了";
            pw.println(msg);
            // 用于客户端维护在线用户列表的
            msg = "cmdAdd@#server@#" + userName;
            pw.println(msg);
        }
    }

    private void sendMsgToSelf(Socket s) throws IOException {
        PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
        for (Map.Entry<String, Socket> entry : userMap.entrySet()) {
            String userName = entry.getKey();
            log.debug("map: " + userMap);
            // 告诉用户当前在线的用户信息，不需要发送显示信息，只需要发送给客户端更新在线列表信息
            String msg = "cmdAdd@#server@#" + userName;
            pw.println(msg);
        }
    }

    /**
     * 命令关键字@#接收方@#消息内容@#发送方
     */
    private void sendMsgToSb(String[] msgs) throws IOException {
        if ("全部".equals(msgs[1])) {
            // 群聊
            for (Map.Entry<String, Socket> entry : userMap.entrySet()) {
                String userName = entry.getKey();
                String msg;
                if (userName.equals(msgs[3])) {
                    msg = "msg@#" + "我" + "@#说：" + msgs[2];
                } else {
                    msg = "msg@#" + msgs[3] + "@#说：" + msgs[2];
                }
                Socket s = userMap.get(userName);
                // msg@#消息发送者@#消息内容
                PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
                pw.println(msg);
            }
        } else {
            // 私聊
            String userName = msgs[1];
            Socket s = userMap.get(userName);
            // msg@#消息发送者@#消息内容
            String msg = "msg@#" + msgs[3] + "@#悄悄对你说：" + msgs[2];
            PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
            pw.println(msg);

            // 再发给自己
            s = userMap.get(msgs[3]);
            pw = new PrintWriter(s.getOutputStream(), true);
            msg = "msg@#" + "我" + "@#对" + userName + "说：" + msgs[2];
            pw.println(msg);
        }
    }

    /**
     * 通知所有在线用户XX退出了
     * 1) msg@#server@#用户[userName]退出了 （给客户端显示用的）
     * 2) cmdRed@#server@#userName （给客户端维护在线用户列表用的）
     */
    private void sendSbExitMsgToAll(String[] msgs) throws IOException {
        for (Map.Entry<String, Socket> entry : userMap.entrySet()) {
            Socket s = entry.getValue();
            PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
            String msg = "msg@#server@#用户[" + msgs[3] + "]退出了";
            pw.println(msg);
            msg = "cmdRed@#server@#" + msgs[3];
            pw.println(msg);
        }
    }

    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        new ServerForm();
    }

}
