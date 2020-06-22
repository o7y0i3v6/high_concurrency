package demo;

import org.openjdk.jcstress.annotations.Actor;
import org.openjdk.jcstress.annotations.Expect;
import org.openjdk.jcstress.annotations.JCStressTest;
import org.openjdk.jcstress.annotations.Outcome;
import org.openjdk.jcstress.annotations.State;
import org.openjdk.jcstress.infra.results.I_Result;

/**
 * 本类用高并发测试框架演示有序性
 * 有序性(Ordering):是指程序中代码的执行顺序
 * java在编译和运行时和运行时或对代码进行优化,会导致程序最终
 * 的执行顺序不一定就是编写代码时的顺序.
 * 
 * 注解解释:注解JCStressTest写上这个注解后,类中的特定方法就会被识别.
 *          注解Outcome表示对输出结果的处理
 * 
 * @author mcunb
 *
 */
@JCStressTest
//如果在I_Result里保存的结果是1和4,打印结果"ok",
@Outcome(id={"1","4"},expect= Expect.ACCEPTABLE,desc="the order is normal")
//如果在I_Result里保存的结果是0,打印"danger"
@Outcome(id="0",expect= Expect.UNKNOWN,desc="the order is change")
@State
public class Demo1 {
	int num = 0;
	boolean ready = false;
	/*
	 * 线程1执行的代码
	 * I_Result是并发压测工具自带的,
	 * 点开里面有一个成员变量保存int类型的结果
	 * 接下来的操作就是赋值给这个成员变量
	 */
	@Actor
	public void actor1(I_Result r) {
		/*
		 * 先判断的ready的值
		 * 该判断会决定是否对某全局变量进行操作
		 */
		if(ready) {
			/*
			 * 根据本类的全局变量操作I_Result类的用于记录的全局变量r1
			 * 如果方法actor2正常走完r1会等于4
			 */
			r.r1=num+num;
		}
		//如果不走方法2,会得到1
		if(!ready){
			r.r1=1;
		}
	}
	
	@Actor
	public void actor2(I_Result r) {
		num=2;
		ready=true;
	}
}
