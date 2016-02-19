package lesson6;

public class Test6 {

	int [] arr;
	private Test6(int a) {
		//		this();
		System.out.println("Hello" +a);
	}
	public Test6() {
		this(3);
		this.arr = new int[90];

		arr = new int[10];
		// TODO Auto-generated constructor stub
	}
	int a =90;
	void f() {
		System.out.println("f()");
	}

	public static void main(String[] args) {
		Test6 t = new Test6();
		Test6 t1 = new Test6(4);
		t.f();
	}
}
