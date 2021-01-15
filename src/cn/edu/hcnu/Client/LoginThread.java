package cn.edu.hcnu.Client;


import cn.edu.hcnu.MD5.MD5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
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
        loginf = new JFrame();
        loginf.setResizable(false);
        loginf.setLocation(300, 200);
        loginf.setSize(400, 150);
        loginf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginf.setTitle("������" + " - ��¼");

        t = new JTextField("Version " + "1.1.0" + "By ZQL");
        t.setHorizontalAlignment(JTextField.CENTER);
        t.setEditable(false);
        loginf.getContentPane().add(t, BorderLayout.SOUTH);

        JPanel loginp = new JPanel(new GridLayout(3, 2));
        loginf.getContentPane().add(loginp);

        JTextField t1 = new JTextField("��¼��:");
        t1.setHorizontalAlignment(JTextField.CENTER);
        t1.setEditable(false);
        loginp.add(t1);

        final JTextField loginname = new JTextField("ZQL");
        loginname.setHorizontalAlignment(JTextField.CENTER);
        loginp.add(loginname);

        JTextField t2 = new JTextField("����:");
        t2.setHorizontalAlignment(JTextField.CENTER);
        t2.setEditable(false);
        loginp.add(t2);

        final JTextField loginPassword = new JTextField("ZQL817");
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
                try {
                    String url = "jdbc:oracle:thin:@localhost:1521:orcl";
                    String username_db = "opts";
                    String password_db = "opts1234";
                    Connection conn = DriverManager.getConnection(url, username_db, password_db);
                    String sql = "SELECT password FROM users WHERE username=?";
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1,username);
                    ResultSet rs = pstmt.executeQuery();
                    if (rs.next()) {
                        String encodePassword = rs.getString("PASSWORD");
                        if (MD5.checkpassword(password, encodePassword)) {
                            System.out.println("��¼�ɹ�");
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