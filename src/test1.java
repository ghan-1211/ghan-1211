public class test1 {
    public static void main(String[] args) {
        computer com = new computer();
        int m = 100;
        int n = 200;
        int result = com.add(m,n);  //将m,n的值传值给参数x，y
        System.out.println(result);
        result = com.add(120+m,n*10+8); //将表达式传给参数x，y
        System.out.println(result);
    }
}