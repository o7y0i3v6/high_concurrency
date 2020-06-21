package atomicityDemo;

import org.junit.Test;

/**
 * @author blue
 * @ClassName RunTest
 * @Description TODO
 * @date 2020/5/14 6:52
 */
public class RunTest {
    @Test
    public void test0() throws InterruptedException {
        /**
         * 每创建一个对象就可以实现用五个线程分工自增5000次（一个线程自增1000次）
         */
        for(int i = 0;i<10;i++) {
            AtomicityDemo atomcityDemo = new AtomicityDemo();
        }
    }
}
