import java.util.ArrayList;
import java.util.List;

public class test17 {
    public static void main(String[] args) {
        List list = new ArrayList();
        list.add(1);
        list.add("河池学院");
        list.add(2);

        for (Object li : list){   //
            System.out.println("当前集合的输出值为：" + li);
        }
    }
}
