package cn.edu.hcnu.Client;


import cn.edu.hcnu.Client.MD5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;
import java.sql.*;


/**
 * ��¼�߳�
 */
public class LoginThread extends Thread {
    private JFrame loginf;

    private JTextField t;

    public void run() {
        /*
         * ���õ�¼����
         */
        loginf = new JFrame();//�����ޱ���Ĵ���
        loginf.setResizable(false);//������̨��С
        loginf.setLocation(300, 200);//����λ��
        loginf.setSize(400, 150);//���ô�̨��С
        loginf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//ʹ�� System exit �����˳�Ӧ�ó���
        loginf.setTitle("������" + " - ��¼");

        t = new JTextField("Version " + "1.1.0" + "        By liwei");
        t.setHorizontalAlignment(JTextField.CENTER);//CENTER���뷽ʽΪ���ж���
        t.setEditable(false);
        loginf.getContentPane().add(t, BorderLayout.SOUTH);

        JPanel loginp = new JPanel(new GridLayout(3, 2));//������������
        loginf.getContentPane().add(loginp);

        JTextField t1 = new JTextField("��¼��:");
        t1.setHorizontalAlignment(JTextField.CENTER);
        t1.setEditable(false);
        loginp.add(t1);

        final JTextField loginname = new JTextField("liwei");
        loginname.setHorizontalAlignment(JTextField.CENTER);
        loginp.add(loginname);

        JTextField t2 = new JTextField("����:");
        t2.setHorizontalAlignment(JTextField.CENTER);
        t2.setEditable(false);
        loginp.add(t2);

        final JTextField loginPassword = new JTextField("lw1234");
        loginPassword.setHorizontalAlignment(JTextField.CENTER);
        loginp.add(loginPassword);
        /*
         * �����˳���ť(�����ڲ���)
         */
        JButton b1 = new JButton("��  ��");
        loginp.add(b1);
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        final JButton b2 = new JButton("��  ¼");
        loginp.add(b2);

        loginf.setVisible(true);

        /**
         * ������,����"��¼"Button�ĵ����TextField�Ļس�
         */
        class ButtonListener implements ActionListener {
            private Socket s;

            public void actionPerformed(ActionEvent e) {
                String username = loginname.getText();
                String password = loginPassword.getText();
                PreparedStatement pstmt = null;
                String sql = "";
                try {
                    String url = "jdbc:oracle:thin:@localhost:1521:orcl";
                    String username_db = "opts";
                    String password_db = "opts1234";
                    Connection conn = DriverManager.getConnection(url, username_db, password_db);
                    sql = "SELECT password FROM users WHERE username=?";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, username);
                    ResultSet rs = pstmt.executeQuery();
                    if (rs.next()) {
                        String encodePassword = rs.getString("PASSWORD");
                        if (MD5.checkpassword(password, encodePassword)) {
                            /*
                            ��ȡ����IP
                            ����һ���˿�8888
                            ���ص�¼����
                            ��ʾ���촰��
                             */
                            InetAddress addr = InetAddress.getLocalHost();
                            System.out.println("����IP��ַ: " + addr.getHostAddress());
                            int port=1688;
                            DatagramSocket ds=null;
                            while(true) {
                                try {
                                    ds=new DatagramSocket(port);
                                    break;
                                } catch (IOException ex) {
                                    port += 1;
                                    //ex.printStackTrace();
                                }
                            }
                            sql = "UPDATE users SET ip=?,port=?,status=? WHERE username=?";
                            pstmt = conn.prepareStatement(sql);
                            pstmt.setString(1, addr.getHostAddress());
                            pstmt.setInt(2,port);
                            pstmt.setString(3,"online");
                            pstmt.setString(4, username);
                            pstmt.executeUpdate();
                            loginf.setVisible(false);
                            ChatThreadWindow chatThreadWindow = new ChatThreadWindow(username,ds);
                        } else {
                            System.out.println("��¼ʧ��");
                        }
                    }
                } catch (SQLException ee) {
                    ee.printStackTrace();
                } catch (NoSuchAlgorithmException ex) {
                    ex.printStackTrace();
                } catch (UnsupportedEncodingException ex) {
                    ex.printStackTrace();
                } catch (UnknownHostException ex) {
                    ex.printStackTrace();
                }
				/*
				1�������û�ȥ���ݿ�Ѽ��ܺ�������õ�
				SELECT password FROM users WHERE username='liwei';
				2���ѵ�¼�����������������ݿ�����ܺ�Ľ��бȶԣ�����MD5���checkpassword������
				 */
            }
        }
        ButtonListener bl = new ButtonListener();
        b2.addActionListener(bl);
        loginname.addActionListener(bl);
        loginPassword.addActionListener(bl);
    }
}