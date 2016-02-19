package lesson6;

public class T2 {
//	static int a =8;
//	static  void f () {}
	
	static T1 r4= new T1();
static {
		int a=90;
		System.out.println("Hello");
	}
	static int count = 0;
	public T2() {
	
		count++;
		System.out.println("T2 " +count);
		// TODO Auto-generated constructor stub
	}
	int t=0;
	static int t1 =9;
	static void  f() {
		System.out.println("f");
		t1=8;
		//t =4;
	}
	public static void main(String[] args) {
		T2  t1 = new  T2();
		T2  t2 = new  T2();
		T2  t3 = new  T2();
		T2  t4 = new  T2();
		f();
		t1.f();
		
	}
}
