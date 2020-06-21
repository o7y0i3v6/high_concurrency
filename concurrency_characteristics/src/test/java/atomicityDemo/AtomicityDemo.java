package atomicityDemo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author blue
 * @ClassName AtomicityDemo
 * @Description TODO
 * @date 2020/5/14 6:52
 */
public class AtomicityDemo {
    //1. 创建一个共享变量
    private static int data = 0;

    //2. 在构造函数里用for循环创建5个线程操作共享变量的自增
    public AtomicityDemo() throws InterruptedException {
        List<Thread> list = new ArrayList<Thread>();

        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(() -> {
                increment();
            });
            thread.start();
            list.add(thread);
        }

        for (Thread t : list) {
            t.join();
        }

        //3. 打印共享变量
        System.out.println(data);
    }

    //封装for循环
    private void increment() {
        for (int i = 0; i < 1000; i++) {
            data++;
        }
    }
}
