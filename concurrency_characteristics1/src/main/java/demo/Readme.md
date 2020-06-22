##### 在高并发框架下两个线程可能的运行情况
```
@JCStressTest
@Outcome(id={"1","4"},expect= Expect.ACCEPTABLE,desc="ok")
@Outcome(id="0",expect= Expect.ACCEPTABLE_INTERESTING,desc="danger")
@State
public class Demo1 {
	int num = 0;
	boolean ready = false;

	@Actor
	public void actor1(I_Result r) {
		
		if(ready) {
			r.r1=num+num;
		}else {
			r.r1=1;
		}
	}
	@Actor
	public void actor2(I_Result r) {
		num=2;
		ready=true;
	}
}
```
###### 第一种情况
-  结果1:先运行线程1,ready的值不变,还是false,走的是所以结果就是1.
-  结果2:先运行线程2,并且线程2走完了,ready的值变为true,num=2.
-  结果3:还是先运行线程2,但只走了一半,ready的值
-  结果4:结果4比骄特殊,可能会对代码进行重排序,会先读到ready的值.  
