package lesson7;

import electricity.Monthdays;

public class MonthdaysDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Monthdays md;
		for (Monthdays md : Monthdays.values()) {
			System.out.println(md+" "+md.ordinal()+" "+md.getCount());
		}
		for (int i = 0; i < Monthdays.values().length; i++) {
			System.out.println(Monthdays.values()[i]+" "+Monthdays.values()[i].getCount());
		}
	}

}
