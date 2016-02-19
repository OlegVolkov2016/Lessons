package lesson6;

public class Cup extends Ball {
	public Cup(int a) {
		// TODO Auto-generated constructor stub
		System.out.println("This is a cup and "+a);
		b.f();
	}
	static Ball b = new Ball(); 

	void f1() {
		System.out.println("f1() in the cup.");
	}
}
