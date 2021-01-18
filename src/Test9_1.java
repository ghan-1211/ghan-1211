import javax.swing.*;
import java.awt.*;

/*Container (容器类)
 */
public class Test9_1 {
    public static void main(String[] args) {
        JFrame window1 = new JFrame("第一个窗口");
        Container con = window1.getContentPane();//获取内容
        con.setBackground(Color.blue);
        window1.setBounds(60,80,200,100);//设置窗口在屏幕上的位置及大小
        window1.setVisible(true);//设置窗口会否可见
        window1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//释放当前窗口

       JFrame window2 = new JFrame("张");
        con.setBackground(Color.blue);
        window2.setBounds(60,80,200,100);
        window2.setVisible(true);//设置窗口会否可见
        window2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//释放当前窗口
    }
}
