import java.util.Scanner;

//通过 Scanner 类来获取用户的输入
public class Example1_1 {
    public static void main(String[] args) {
        System.out.println("请输入若干个数，每输入一个数回车确认");
        System.out.println("最后输入数字0结束输入操作");
        Scanner sc = new Scanner(System.in); //得到一个标准的输入流
        double sum = 0;
        double x = sc.nextDouble(); //等待输入一个double类型的变量
        while (x != 0){
            sum = sum + x;
            x = sc.nextDouble();
        }
        System.out.println("sum=" + sum);
    }
}
