package electricity;

import java.util.Calendar;

public class Value extends Client {

	// Value and payment of electricity
	// discount is value;
	private Calendar date;

	public Value() {
		super();
		// TODO Auto-generated constructor stub
		this.date = Calendar.getInstance();
	}

	public Value(String name, int discount, Calendar date) {
		super(name, discount);
		// TODO Auto-generated constructor stub
		this.date = (Calendar) date.clone();
	}

	@Override
	public void set(Object obj) {
		// TODO Auto-generated method stub
		super.set(obj);
		this.date = (Calendar) ((Value) obj).getDate().clone();
	}
	
	@Override
	public void firstInit() {
		// TODO Auto-generated method stub
		super.firstInit();
	}
	
	@Override
	public void print(String type) {
		// TODO Auto-generated method stub
		System.out.print("Client name: "+getName()+", "+type+" on "+Monthdays.values()[getDate().get(Calendar.MONTH)]+", "+getDate().get(Calendar.DATE)+" "+getDate().get(Calendar.YEAR)+" is "+getDiscount());
		if (type.equals("value"))
			System.out.println("kWt.");
		else if (type.equals("payment"))
			System.out.println("$.");
		else System.out.println();
	}
	
	@Override
	public void edit(String type, boolean isNew) {
		// TODO Auto-generated method stub
		String name;
		int discount;
		Calendar date = Calendar.getInstance();
		name = inputName(type,isNew);
		date = inputDate(type,isNew);
		discount = inputDiscount(type,isNew);
//		setName(name);
//		setDiscount(discount);
//		setDate(date);
		set(new Value(name,discount,date));
	}
	
	@Override
	public int inputDiscount(String type, boolean isNew) {
		// TODO Auto-generated method stub
		int discount;
		boolean isMake;
		do {
			isMake = true;
			if (!isNew) {
				System.out.print("Current "+type+": "+getDiscount());
				if (type.equals("value"))
					System.out.println("kWt.");
				else if (type.equals("payment"))
					System.out.println("$.");
				else System.out.println();
			}
			System.out.print("Enter new "+type+" in ");
			if (type.equals("value"))
				System.out.print("kWt: ");
			else if (type.equals("payment"))
				System.out.print("$: ");
			else System.out.print(": ");
			discount = makeIntChoise(0,Integer.MAX_VALUE);
			if (discount == -1) 
				if (!isNew) discount = getDiscount();
				else {
					System.out.println("Wrong value.");
					isMake = false;
				}
		}
		while(!isMake);
		return discount;
	}
	
	public Calendar inputDate(String type, boolean isNew) {
		String str;
		Calendar date = Calendar.getInstance();
		boolean isMake;
		do {
			isMake = true;
			if (!isNew) System.out.println("Date of "+type+": "+Monthdays.values()[getDate().get(Calendar.MONTH)]+", "+getDate().get(Calendar.DATE)+" "+getDate().get(Calendar.YEAR));
			System.out.print("Enter new date of "+type+" (DD/MM/YYYY): ");
			str = makeDateChoise();
			if (str.equals("")) 
				if (!isNew) date = getDate();
				else {
					System.out.println("Wrong date.");
					isMake = false;
				}
			else
				date.set(Integer.parseInt(str.substring(6,10)),Integer.parseInt(str.substring(3,5))-1,Integer.parseInt(str.substring(0,2)));
		}
		while(!isMake);
		return date;
	}

	@Override
	public boolean menu(String type) {
		// TODO Auto-generated method stub
		return super.menu(type);
	}
	
	// Getters and setters
	public Calendar getDate() {
		return date;
	}
	
	public void setDate(Calendar date) {
		this.date = date;
	}
	
}
