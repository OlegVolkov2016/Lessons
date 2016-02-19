package lesson6;

public class Table {
	public Table(int a) {
		// TODO Auto-generated constructor stub
		System.out.println("This is a table with " + a);
		Ball b1 = new Ball();
		Cup c1 = new Cup(a);
	}

	static Ball bb = new Ball();
}
