package com.hnq.study.demo.qqchat;

import com.hnq.study.config.Properties;
import com.hnq.toolkit.util.ThreadPoolUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author henengqiang
 * @date 2019/06/06
 */
@Slf4j
public class ClientForm extends JFrame implements ActionListener {

    private static String ip;

    private static int port;

    /**
     * 用户标识
     */
    private JTextField nameFiled = new JTextField(10);

    /**
     * 聊天信息显示
     */
    private JTextArea allMsg = new JTextArea();

    /**
     * 发送消息消息框
     */
    private JTextField msgFiled = new JTextField(10);

    private JButton btnSend;

    private JButton btnCon;

    /**
     * 在线用户列表
     */
    private DefaultListModel<String> dataModel = new DefaultListModel<>();

    private JList<String> list = new JList<>(dataModel);


    private Socket client;

    private PrintWriter pw;


    private static final int WIDTH = 400;

    private static final int HEIGHT = 300;

    private ClientForm() {

        ip = Properties.getProperty("net.host");
        port = Integer.valueOf(Properties.getProperty("net.port"));

        setBounds(300, 300, WIDTH, HEIGHT);

        addMenuBar();

        // --- 上方面板 --- //
        JPanel northPanel = new JPanel();
        northPanel.add(new JLabel("用户名称"));
        nameFiled.setText("");
        northPanel.add(nameFiled);

        btnCon = new JButton("连接");
        btnCon.setActionCommand("c");
        JButton btnExit = new JButton("退出");
        btnExit.setActionCommand("exit");
        northPanel.add(btnCon);
        northPanel.add(btnExit);

        getContentPane().add(northPanel, BorderLayout.NORTH);

        // --- 中间面板 --- //
        // 中
        JPanel centerPanel = new JPanel(new BorderLayout());
        allMsg.setEditable(false);
        allMsg.setForeground(Color.BLUE);
        allMsg.setFont(new Font("幼圆", Font.BOLD, 14));
        centerPanel.add(new JScrollPane(allMsg));

        // 东
        dataModel.addElement("全部");
        // 设置默认选择位置
        list.setSelectedIndex(0);
        // 设置只能单选
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        // 设置显示的行数
        list.setVisibleRowCount(5);
        list.setFont(new Font("幼圆", Font.BOLD, 12));

        // 为list添加滚动条
        JScrollPane scroll = new JScrollPane(list);
        scroll.setBorder(new TitledBorder("在线"));
        // 设置滚动条的首选大小
        scroll.setPreferredSize(new Dimension(70, allMsg.getHeight()));
        centerPanel.add(scroll, BorderLayout.EAST);

        // 南
        JPanel southPanel = new JPanel();
        southPanel.add(new JLabel("消息"));
        southPanel.add(msgFiled);

        btnSend = new JButton("发送");
        btnSend.setActionCommand("send");
        btnSend.setEnabled(false);
        southPanel.add(btnSend);

        centerPanel.add(southPanel, BorderLayout.SOUTH);

        // 把中间面板驾到框架中
        getContentPane().add(centerPanel);

        setVisible(true);

        // 事件监听
        btnCon.addActionListener(this);
        btnExit.addActionListener(this);
        btnSend.addActionListener(this);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (StringUtils.isBlank(nameFiled.getText())) {
                    int result = JOptionPane.showConfirmDialog(ClientForm.this, "你还没登录，是否退出？");
                    if (result == JOptionPane.YES_OPTION) {
                        System.exit(0);
                    } else {
                        return;
                    }
                }
                log.debug(nameFiled.getText() + "下线");
                sendExitMsg();
                System.exit(0);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("c".equals(e.getActionCommand())) {
            log.debug("用户名：" + nameFiled.getText());
            if (StringUtils.isBlank(nameFiled.getText())) {
                JOptionPane.showMessageDialog(this, "用户名不能为空！");
                return;
            }
            log.debug(nameFiled.getText() + "：连接中...");
            connecting();
        } else if ("exit".equals(e.getActionCommand())) {
            if (StringUtils.isBlank(nameFiled.getText())) {
                int result = JOptionPane.showConfirmDialog(this, "你还没有登录，是否退出？");
                if (result == JOptionPane.YES_OPTION) {
                    System.exit(0);
                } else {
                    return;
                }
            }
            log.debug(nameFiled.getText() + "下线");
            sendExitMsg();
        } else if ("send".equals(e.getActionCommand())) {
            if (StringUtils.isEmpty(msgFiled.getText())) {
                JOptionPane.showMessageDialog(this, "发送消息不能为空！");
                return;
            }
            String msg = "on@#" + list.getSelectedValue() + "@#" + msgFiled.getText() + "@#" + nameFiled.getText();
            pw.println(msg);
        }
    }

    private void addMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menu = new JMenu("选项");
        menuBar.add(menu);

        JMenuItem itemSet = new JMenuItem("设置");
        JMenuItem itemHelp = new JMenuItem("帮助");

        menu.add(itemSet);
        menu.add(itemHelp);

        itemSetAddAction(itemSet);

        itemHelpAddAction(itemHelp);

    }

    private void itemSetAddAction(JMenuItem itemSet) {
        itemSet.addActionListener(e -> {
            final JDialog setDlg = new JDialog(ClientForm.this);
            setDlg.setBounds(ClientForm.this.getX(), ClientForm.this.getY(), WIDTH, 100);
            setDlg.setLayout(new FlowLayout());
            setDlg.add(new JLabel("服务器："));
            final JTextField tfdIp = new JTextField(10);
            tfdIp.setText(ip);
            setDlg.add(tfdIp);
            setDlg.add(new JLabel("端口："));
            final JTextField tfdPort = new JTextField(10);
            tfdPort.setText(port + "");
            setDlg.add(tfdPort);

            JButton btnSet = new JButton("设置");
            btnSet.setActionCommand("set");
            JButton btnCancel = new JButton("取消");
            btnCancel.setActionCommand("cancel");
            setDlg.add(btnSet);
            setDlg.add(btnCancel);

            setDlg.setVisible(true);

            btnSet.addActionListener(e2 -> {
                if ("set".equals(e2.getActionCommand())) {
                    if (StringUtils.isNotBlank(tfdIp.getText())) {
                        ip = tfdIp.getText();
                    }
                    if (StringUtils.isNotBlank(tfdPort.getText())) {
                        try {
                            port = Integer.valueOf(tfdPort.getText());
                        } catch (NumberFormatException e1) {
                            JOptionPane.showMessageDialog(setDlg, "端口号格式错误，请输入数字！");
                        }
                    }
                    btnCon.setEnabled(true);
                    nameFiled.setEnabled(false);
                    if (client != null) {
                        // 如果前面已经登录着用户，就把用户退出
                        String msg = "exit@#全部@#null@#" + nameFiled.getText();
                        pw.println(msg);
                        dataModel.removeElement(nameFiled.getText());
                        list.validate();
                        nameFiled.setText("");
                    }
                    setDlg.dispose();
                }
            });

            btnCancel.addActionListener(e2 -> {
                if ("cancel".equals(e2.getActionCommand())) {
                    setDlg.dispose();
                }
            });
        });
    }

    private void itemHelpAddAction(JMenuItem itemHelp) {
        itemHelp.addActionListener(e -> {
            JDialog helpDlg = new JDialog(ClientForm.this);
            helpDlg.setBounds(ClientForm.this.getX() + 10, ClientForm.this.getY(), WIDTH, 100);
            helpDlg.setLayout(new FlowLayout());
            JLabel str = new JLabel("版权所有@Copyright");
            helpDlg.add(str);

            JButton btnSure = new JButton("确定");
            btnSure.setActionCommand("sure");
            helpDlg.add(btnSure);
            helpDlg.setVisible(true);

            btnSure.addActionListener(e2 -> {
                if ("sure".equals(e2.getActionCommand())) {
                    helpDlg.dispose();
                }
            });
        });
    }

    /**
     * 与服务器建立连接，把userName传给服务器
     */
    private void connecting() {
        try {
            client = new Socket(ip, port);
            // 连接成功后关掉连接按钮
            btnCon.setEnabled(false);
            String userName = nameFiled.getText().trim();
            pw = new PrintWriter(client.getOutputStream(), true);
            pw.println(userName);
            // 连接之后，设置标题为userName在线
            setTitle(userName + "在线");

            // 打开发送按钮
            btnSend.setEnabled(true);
            // 用户名不能再修改
            nameFiled.setEnabled(false);

            // 开一个线程单独与服务器通信
            ClientRunnable clientRunnable = new ClientRunnable(client);
            ThreadPoolUtils.execute(clientRunnable);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendExitMsg() {
        try {
            client = new Socket(ip, port);
            String msg = "exit@#全部@#null@#" + nameFiled.getText();
            pw.println(msg);
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class ClientRunnable implements Runnable {

        private Socket client;

        ClientRunnable(Socket client) {
            this.client = client;
        }

        @Override
        public void run() {
            try {
                Scanner sc = new Scanner(client.getInputStream());

                while (sc.hasNext()) {
                    String msg = sc.nextLine();
                    String[] msgs = msg.split("@#");
                    if (msgs.length != 3) {
                        log.error("通讯异常！");
                        return;
                    }

                    if ("msg".equals(msgs[0])) {
                        if ("server".equals(msgs[1])) {
                            // 系统信息
                            msg = "系统信息：" + msgs[2];
                            allMsg.append(msg + "\r\n");
                        } else {
                            // 聊天信息
                            msg = msgs[1] + msgs[2];
                            allMsg.append(msg + "\r\n");
                        }
                    } else if ("cmdAdd".equals(msgs[0])) {
                        // 添加用户
                        dataModel.addElement(msgs[2]);
                    } else if ("cmdRed".equals(msgs[0])) {
                        // 移除用户
                        dataModel.removeElement(msgs[2]);
                    }
                    // 刷新list
                    list.validate();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        new ClientForm();
    }

}
