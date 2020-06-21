package visibility;

import org.junit.Test;

/**
 * @author blue
 * @ClassName Visibility
 * @Description TODOd
 * @date 2020/6/10 21:07
 */
public class VisibilityDemo {
    //1.创建一个共享变量,观察共享变量的值用来测试.
    private static boolean flag = true;
    @Test
    public void test0() throws InterruptedException {
        /*
         * 多线程运行具有随机性,有可能先跑线程1,也有可能先跑线程2?
         * 经过测试一直都是1先跑.
         */
        new Thread(() -> {
            System.out.println("线程1执行了");
            System.out.println("开始进入循环");
            //一旦IDEA检测到某种结果无法改变，得出必然的结果后程序就不再执行了。
            while (flag) {

            }
            System.out.println("线程1循环结束");
        }).start();

        //睡眠两秒
        Thread.sleep(2000);

        new Thread(() -> {
            System.out.println("线程2执行了");
            flag = false;
            System.out.println("线程修改了变量的值为false");
        }).start();
    }
}

