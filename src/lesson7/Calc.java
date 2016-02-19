package lesson7;

import lesson7.Room.R1;

public class Calc extends AbsTest implements Room, MyFirstInt, R1 {
	
	int height;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Calc c = new Calc(); 
		c.height = 5;
		System.out.println(c.sromm()+"");
		System.out.println(c.vroom(c.height)+"");

	}

	@Override
	public int sromm() {
		// TODO Auto-generated method stub
		return width * length;
	}

	@Override
	public int vroom(int height) {
		// TODO Auto-generated method stub
		return width*length*height;
	}

	@Override
	public void f() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void f1() {
		// TODO Auto-generated method stub
		
	}

	@Override
	void conn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void f12() {
		// TODO Auto-generated method stub
		
	}

}
