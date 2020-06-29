### ʲô��ԭ����
- ��һ�λ��β�����,Ҫô���еĲ���Ԫ�ض�ִ��,���Ҳ������������صĸ��Ŷ��ж�,Ҫô���в�������ִ��.
  - ������˵,ԭ���Ծ���ָ�����Ĳ���ϸ����.
  

### ��ԭ�ӵĸ�������ʲô��ԭ����
- ԭ���ڻ�ѧ��Ӧ���ǲ��ɷָ��,ͬʱԭ��Ҳ�ǹ���һ�����ʵ���С����.
  - ��ԭ������Ⲣ����̵�ԭ����,����ָĳ������������ϸ��,�Ӷ�����߳���ִ���������ʱ�˴˲��ụ�����.
 

##### �����⣺i++�������̰߳�ȫ����
- ���� 
###### ������ʾ:
- �������ߣ�IDEA
- ���ȣ��ȴ���һ��project ����high_concurrency
- Ȼ����high_concurrency�´���һ��module,����concurrency_characteristics
- ʹ��junit���в��� 
- ����һ��������AtomicityDemo����AtomicityDemo�´���һ����AtomicityDemo����������:

```java
public class AtomicityDemo {
    //1. ����һ���������
    private static int data = 0;

    //2. �ڹ��캯������forѭ������5���̲߳����������������
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

        //3. ��ӡ�������
        System.out.println(data);
    }

    //��װforѭ��
    private void increment() {
        for (int i = 0; i < 1000; i++) {
            data++;
        }
    }
}

```

- Ȼ���ٽ�һ����RunTest


```java
public class RunTest {
    @Test
    public void Test0() throws InterruptedException {
        /**
         * ÿ����һ������Ϳ���ʵ��������̷ֹ߳�����5000�Σ�һ���߳�����1000�Σ�
         */
        for(int i = 0;i<10;i++) {
            AtomicityDemo atomcityDemo = new AtomicityDemo();
        }
    }

}
```

##### �÷����ȥ�ֽ���ָ��
```
  javap -p -v target/name.class
```
- ��ز�����ָ�� 
 ```
  getstatic  
  iconst_1
  iadd
  putstatic  
```
  
  
###### ָ�����  
- `getstatic`:ȡ��number��ֵ
- `iconst_1`:׼������1���ڼ���
- `iadd`:ִ�����,�õ�������ǲ�û�и�ֵ
- `putstatic`:��ֵ

###### ����
- ��`iadd`��һ��,���ִ����һ���߳�,�ͻ�õ�����һ����ֵ��Ҫȥ��ֵ,��Ϊ��ʱ��û��ֵ����ԭ����ֵ������һ�顣����������������һ���̻߳�����һ���̸߳�ֵ�Ļ����ټ�����ֵ�����������߳���Ϊͬһ��ʵ�ֵķֹ�����ֵӦ����һ����nһ����n+1��

