package demo;

import org.openjdk.jcstress.annotations.Actor;
import org.openjdk.jcstress.annotations.Expect;
import org.openjdk.jcstress.annotations.JCStressTest;
import org.openjdk.jcstress.annotations.Outcome;
import org.openjdk.jcstress.annotations.State;
import org.openjdk.jcstress.infra.results.I_Result;

/**
 * �����ø߲������Կ����ʾ������
 * ������(Ordering):��ָ�����д����ִ��˳��
 * java�ڱ��������ʱ������ʱ��Դ�������Ż�,�ᵼ�³�������
 * ��ִ��˳��һ�����Ǳ�д����ʱ��˳��.
 * 
 * ע�����:ע��JCStressTestд�����ע���,���е��ض������ͻᱻʶ��.
 *          ע��Outcome��ʾ���������Ĵ���
 * 
 * @author mcunb
 *
 */
@JCStressTest
//�����I_Result�ﱣ��Ľ����1��4,��ӡ���"ok",
@Outcome(id={"1","4"},expect= Expect.ACCEPTABLE,desc="the order is normal")
//�����I_Result�ﱣ��Ľ����0,��ӡ"danger"
@Outcome(id="0",expect= Expect.UNKNOWN,desc="the order is change")
@State
public class Demo1 {
	int num = 0;
	boolean ready = false;
	/*
	 * �߳�1ִ�еĴ���
	 * I_Result�ǲ���ѹ�⹤���Դ���,
	 * �㿪������һ����Ա��������int���͵Ľ��
	 * �������Ĳ������Ǹ�ֵ�������Ա����
	 */
	@Actor
	public void actor1(I_Result r) {
		/*
		 * ���жϵ�ready��ֵ
		 * ���жϻ�����Ƿ��ĳȫ�ֱ������в���
		 */
		if(ready) {
			/*
			 * ���ݱ����ȫ�ֱ�������I_Result������ڼ�¼��ȫ�ֱ���r1
			 * �������actor2��������r1�����4
			 */
			r.r1=num+num;
		}
		//������߷���2,��õ�1
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
