import javax.swing.*;
import java.awt.*;

/*Container (������)
 */
public class Test9_1 {
    public static void main(String[] args) {
        JFrame window1 = new JFrame("��һ������");
        Container con = window1.getContentPane();//��ȡ����
        con.setBackground(Color.blue);
        window1.setBounds(60,80,200,100);//���ô�������Ļ�ϵ�λ�ü���С
        window1.setVisible(true);//���ô��ڻ��ɼ�
        window1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//�ͷŵ�ǰ����

       JFrame window2 = new JFrame("��");
        con.setBackground(Color.blue);
        window2.setBounds(60,80,200,100);
        window2.setVisible(true);//���ô��ڻ��ɼ�
        window2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//�ͷŵ�ǰ����
    }
}
