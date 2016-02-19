package electricity;

import java.util.Calendar;

public class Control extends Value {
	
	// Control of electricity
	// discount is sum of debt or value on date;
	private boolean isDebt;

	public Control() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Control(String name, int discount, Calendar date, boolean isDebt) {
		super(name, discount, date);
		// TODO Auto-generated constructor stub
		this.isDebt = isDebt;
	}

	@Override
	public void set(Object obj) {
		// TODO Auto-generated method stub
		super.set(obj);
		this.isDebt = ((Control) obj).getDebt();
	}
	
	@Override
	public void firstInit() {
		// TODO Auto-generated method stub
		super.firstInit();
	}
	
	@Override
	public void print(String type) {
		// TODO Auto-generated method stub
		System.out.println("Client name: "+getName()+", "+type+" on "+Monthdays.values()[getDate().get(Calendar.MONTH)]+", "+getDate().get(Calendar.DATE)+" "+getDate().get(Calendar.YEAR)+":");
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
		boolean isDebt;
		name = inputName(type,isNew);
		date = inputDate(type,isNew);
		isDebt = inputDebt(type,isNew);
		setDebt(isDebt);
		discount = inputDiscount(type,isNew);
//		setName(name);
//		setDiscount(discount);
//		setDate(date);
//		setDebt(isDebt);
		set(new Control(name,discount,date,isDebt));
	}
	
	public boolean inputDebt(String type, boolean isNew) {
		int choise;
		boolean isMake;
		do {
			isMake = true;
			if (!isNew)
				if (getDebt()) 
					System.out.println("Current state is debt.");
				else
					System.out.println("Current state is theft.");
			System.out.print("Enter new state (0 - theft, 1 - debt): ");
			choise = makeIntChoise(0,1);
			if (choise == -1) 
				if (!isNew) return getDebt();
				else {
					System.out.println("Wrong value.");
					isMake = false;
				}
		}
		while(!isMake);
		return (choise == 1);
	}
	
	@Override
	public int inputDiscount(String type, boolean isNew) {
		// TODO Auto-generated method stub
		int discount;
		boolean isMake;
		do {
			isMake = true;
			if (!isNew) {
				if (getDebt()) 
					System.out.println("Current debt is "+getDiscount()+"$.");
				else
					System.out.println("Current value is "+getDiscount()+"kWt.");
			}
			System.out.print("Enter new ");
			if (getDebt()) System.out.print("debt in $: ");
			else System.out.print("value in kWt: ");
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
	
	@Override
	public boolean menu(String type) {
		// TODO Auto-generated method stub
		return super.menu(type);
	}
	
	// Getters and setters
	boolean getDebt() {
		return isDebt;
	}
	
	public void setDebt(boolean isDebt) {
		this.isDebt = isDebt;
	}
	
}
