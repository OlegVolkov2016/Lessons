package lesson7;

import java.util.Scanner;

public class LifeBox implements Box, Ticket, Phone {
	
	Scanner sc = new Scanner(System.in);

	@Override
	public void fillphone(int count, String number) {
		// TODO Auto-generated method stub
		System.out.println("Fill number "+number+" is cost "+(count+price));
	}

	@Override
	public void buytram(int count, boolean isdisc) {
		// TODO Auto-generated method stub
		if (isdisc) System.out.println("Tickets price:"+count*pricetram);
		else System.out.println("Tickets price:"+count*pricetram*disc);
	}

	@Override
	public void buybus(int count, boolean isdisc) {
		// TODO Auto-generated method stub
		if (isdisc) System.out.println("Tickets price:"+count*pricebus);
		else System.out.println("Tickets price:"+count*pricebus*disc);		
	}

	@Override
	public void menu() {
		int choise, count, disc;
		String number;
		// TODO Auto-generated method stub
		System.out.println("Choise 1 - ticket, 2 - phone");
		choise = sc.nextInt();
		if (choise == 1) {
			System.out.println("Input count:");
			count = sc.nextInt();
			System.out.println("Input disccount (0/1):");
			disc = sc.nextInt();
			if (disc == 0) buytram(count,false);
			else buybus(count,true);
		}
		if (choise == 2) {
			System.out.println("Input sum:");
			count = sc.nextInt();
			number = sc.nextLine();
			System.out.println("Input number:");
			number = sc.nextLine();
			fillphone(count,number);
		}
		
	}
	
	public static void main(String[] args) {
		LifeBox lb = new LifeBox();
		while (true) lb.menu();
	}

}
