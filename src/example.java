public class example {
    public static void main(String[] args) {
        Battery nanfu = new Battery(100);
        //创建电池对象
        System.out.println("南孚电池的电量是："+nanfu.electricityAmount);
        Radio radio = new Radio();
        System.out.println("收音机开始使用南孚电池");
        radio.openRadio(nanfu);
        System.out.println("目前南孚电池的电量是："+nanfu.electricityAmount);
    }
}
