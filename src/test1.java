import java.util.ArrayList;
import java.util.List;

public class Test1 {
    public static void main(String[] args) {
        List list = new ArrayList();
        list.add(1);
        list.add("�ӳ�ѧԺ");
        list.add(2);

        for (Object li : list){   //
            System.out.println("��ǰ���ϵ����ֵΪ��" + li);
        }
    }
}
