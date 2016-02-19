package electricity;

import java.util.Calendar;

public class Remove extends Control {
	
	// Remove of electricity
	private boolean isRemove;

	public Remove() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Remove(String name, int discount, Calendar date, boolean isDebt, boolean isRemove) {
		super(name, discount, date, isDebt);
		// TODO Auto-generated constructor stub
		this.isRemove = isRemove;
	}
	
	@Override
	public void set(Object obj) {
		// TODO Auto-generated method stub
		super.set(obj);
		this.isRemove = ((Remove) obj).getRemove();
	}
	
	@Override
	public void firstInit() {
		// TODO Auto-generated method stub
		super.firstInit();
	}
	
	@Override
	public void print(String type) {
		// TODO Auto-generated method stub
		System.out.print("Client name: "+getName()+", is ");
		if (!getRemove()) System.out.print("not ");
		System.out.println(type+"d on "+Monthdays.values()[getDate().get(Calendar.MONTH)]+", "+getDate().get(Calendar.DATE)+" "+getDate().get(Calendar.YEAR)+":");
		if (getDebt())
			System.out.println("Debt is "+getDiscount()+"$.");
		else
			System.out.println("Theft with a value "+getDiscount()+"kWt.");
	}
	
	@Override
	public void edit(String type, boolean isNew) {
		// TODO Auto-generated method stub
		String name;
		int discount;
		Calendar date = Calendar.getInstance();
		boolean isDebt, isRemove;
		name = inputName(type,isNew);
		date = inputDate(type,isNew);
		isDebt = inputDebt(type,isNew);
		setDebt(isDebt);
		discount = inputDiscount(type,isNew);
		isRemove = inputRemove(type,isNew);
//		setName(name);
//		setDiscount(discount);
//		setDate(date);
//		setDebt(isDebt);
//		setRemove(isRemove);
		set(new Remove(name,discount,date,isDebt,isRemove));
	}

	public boolean inputRemove(String type, boolean isNew) {
		int choise;
		boolean isMake;
		do {
			isMake = true;
			if (!isNew)
				if (getRemove()) 
					System.out.println("Current state is removed.");
				else
					System.out.println("Current state is not removes.");
			System.out.print("Enter new state (0 - not removed, 1 - removed): ");
			choise = makeIntChoise(0,1);
			if (choise == -1) 
				if (!isNew) return getRemove();
				else {
					System.out.println("Wrong value.");
					isMake = false;
				}
		}
		while(!isMake);
		return (choise == 1);
	}
	
	@Override
	public boolean menu(String type) {
		// TODO Auto-generated method stub
		return super.menu(type);
	}
	
	// Getters and setters
	boolean getRemove() {
		return isRemove;
	}
	
	public void setRemove(boolean isRemove) {
		this.isRemove = isRemove;
	}

}
