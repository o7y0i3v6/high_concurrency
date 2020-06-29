### 什么是原子性
- 在一次或多次操作中,要么所有的操作元素都执行,并且不会受其他因素的干扰而中断,要么所有操作都不执行.
  - 概括的说,原子性就是指操作的不可细分性.
  

### 从原子的概念来看什么是原子性
- 原子在化学反应中是不可分割的,同时原子也是构成一般物质的最小粒子.
  - 从原子来理解并发编程的原子性,就是指某个操作不可再细分,从而多个线程在执行这个操作时彼此不会互相干扰.
 

##### 面试题：i++操作是线程安全的吗？
- 不是 
###### 代码演示:
- 开发工具：IDEA
- 首先，先创建一个project 名叫high_concurrency
- 然后在high_concurrency下创建一个module,名叫concurrency_characteristics
- 使用junit进行测试 
- 创建一个包名叫AtomicityDemo，在AtomicityDemo下创建一个类AtomicityDemo，代码如下:

```java
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

```

- 然后再建一个类RunTest


```java
public class RunTest {
    @Test
    public void Test0() throws InterruptedException {
        /**
         * 每创建一个对象就可以实现用五个线程分工自增5000次（一个线程自增1000次）
         */
        for(int i = 0;i<10;i++) {
            AtomicityDemo atomcityDemo = new AtomicityDemo();
        }
    }

}
```

##### 用反汇编去字节码指令
```
  javap -p -v target/name.class
```
- 相关操作的指令 
 ```
  getstatic  
  iconst_1
  iadd
  putstatic  
```
  
  
###### 指令解析  
- `getstatic`:取到number的值
- `iconst_1`:准备常量1用于计算
- `iadd`:执行相加,得到结果但是并没有赋值
- `putstatic`:赋值

###### 分析
- 在`iadd`这一步,如果执行另一个线程,就会得到两个一样的值需要去赋值,因为此时还没赋值，把原来的值又拿了一遍。而本来我们期望的一个线程基于另一个线程赋值的基础再继续赋值，是这两个线程作为同一个实现的分工，其值应当是一个是n一个是n+1。

