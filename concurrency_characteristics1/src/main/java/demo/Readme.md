##### �ڸ߲�������������߳̿��ܵ��������
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
###### ��һ�����
-  ���1:�������߳�1,ready��ֵ����,����false,�ߵ������Խ������1.
-  ���2:�������߳�2,�����߳�2������,ready��ֵ��Ϊtrue,num=2.
-  ���3:�����������߳�2,��ֻ����һ��,ready��ֵ
-  ���4:���4�Ƚ�����,���ܻ�Դ������������,���ȶ���ready��ֵ.  
